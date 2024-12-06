package tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "SampleSearch")

public class SampleSearch extends LinearOpMode {

    public DcMotor leftFront, leftRear, rightFront, rightRear;
    double cX = 0;
    double cY = 0;
    double width = 0;

    private OpenCvCamera controlHubCam;
    private static final int CAMERA_WIDTH = 640;
    private static final int CAMERA_HEIGHT = 360;
    public static final double objectWidthInRealWorldUnits = 3.46;
    public static final double focalLength = 560;

    @Override
    public void runOpMode() {

        initOpenCV();
        initMotores();
        moveToSample();
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        FtcDashboard.getInstance().startCameraStream(controlHubCam, 30);

        waitForStart();
        while (opModeIsActive()) {

            telemetry.addData("Coordinate", "(" + (int) cX + ", " + (int) cY + ")");
            telemetry.addData("Distance in in", (getDistance(width)));
            telemetry.update();
        }
        controlHubCam.stopStreaming();
    }

    public void initOpenCV() {

        // Create an instance of the camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        // Use OpenCvCameraFactory class from FTC SDK to create camera instance
        controlHubCam = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        controlHubCam.setPipeline(new YellowBlobDetectionPipeline());

        controlHubCam.openCameraDevice();
        controlHubCam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);
    }
    class YellowBlobDetectionPipeline extends OpenCvPipeline {

        @Override
        public Mat processFrame(Mat input) {
            // Preprocess the frame to detect yellow regions
            Mat yellowMask = preprocessFrame(input);

            // Find contours of the detected yellow regions
            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(yellowMask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

            // Find the largest yellow contour (blob)
            MatOfPoint largestContour = findLargestContour(contours);

            if (largestContour != null) {
                // Draw a red outline around the largest detected object
                Imgproc.drawContours(input, contours, contours.indexOf(largestContour), new Scalar(0, 240, 120), 2);
                // Calculate the width of the bounding box
                width = calculateWidth(largestContour);

                // Display the width next to the label
                String widthLabel = "Width: " + (int) width + " pixels";
                Imgproc.putText(input, widthLabel, new Point(cX + 10, cY + 20), Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(0, 255, 0), 2);
                //Display the Distance
                String distanceLabel = "Distance: " + String.format("%.2f", getDistance(width)) + " inches";
                Imgproc.putText(input, distanceLabel, new Point(cX + 10, cY + 60), Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(0, 255, 0), 2);
                // Calculate the centroid of the largest contour
                Moments moments = Imgproc.moments(largestContour);
                if (moments.get_m00() != 0) {
                    cX = moments.get_m10() / moments.get_m00();
                    cY = moments.get_m01() / moments.get_m00();
                }
                // Draw a dot at the centroid
                String label = "(" + (int) cX + ", " + (int) cY + ")";
                Imgproc.putText(input, label, new Point(cX + 10, cY), Imgproc.FONT_HERSHEY_COMPLEX, 0.5, new Scalar(0, 255, 0), 2);
                Imgproc.circle(input, new Point(cX, cY), 1, new Scalar(0, 255, 0), -1);

                // Draw the camera's center point for reference
                Point cameraCenter = new Point(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2);
                Imgproc.circle(input, cameraCenter, 25, new Scalar(255, 0, 0), 2);
            }
            return input;
        }


        public Mat preprocessFrame(Mat frame) {
            Mat hsvFrame = new Mat();
            Imgproc.cvtColor(frame, hsvFrame, Imgproc.COLOR_BGR2HSV);

            Scalar lowerYellow = new Scalar(90, 100, 100); //Hue,Saturation,Brightness
            Scalar upperYellow = new Scalar(108, 255, 255);//


            Mat yellowMask = new Mat();
            Core.inRange(hsvFrame, lowerYellow, upperYellow, yellowMask);

            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
            Imgproc.morphologyEx(yellowMask, yellowMask, Imgproc.MORPH_OPEN, kernel);
            Imgproc.morphologyEx(yellowMask, yellowMask, Imgproc.MORPH_CLOSE, kernel);

            return yellowMask;
        }

        public MatOfPoint findLargestContour(List<MatOfPoint> contours) {
            double maxArea = 0;
            MatOfPoint largestContour = null;

            for (MatOfPoint contour : contours) {
                double area = Imgproc.contourArea(contour);
                if (area > maxArea) {
                    maxArea = area;
                    largestContour = contour;
                }
            }

            return largestContour;
        }
        public double calculateWidth(MatOfPoint contour) {
            Rect boundingRect = Imgproc.boundingRect(contour);
            return boundingRect.width;
        }

    }
    public static double getDistance(double width){
        double distance = (objectWidthInRealWorldUnits * focalLength) / width;
        return distance;
    }

    public void moveToSample() {
        if (cX > (CAMERA_WIDTH/2) + 25){
            telemetry.addLine("MOVE RIGHT");
//            moveRight(1);
        } else if (cX < (CAMERA_WIDTH/2) - 25) {
            telemetry.addLine("MOVE LEFT");
//            moveLeft(1);
        } else if (cX < (CAMERA_WIDTH/2) + 25 && cX > (CAMERA_WIDTH/2) - 25) {
            telemetry.addLine("IN CENTER");

        } else {
            telemetry.addLine(">:/");
        }
        telemetry.update();
    }

    public void initMotores() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightRear.setDirection(DcMotor.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void moveLeft(double power) {
        leftFront.setPower(-power);
        leftRear.setPower(power);
        rightFront.setPower(power);
        rightRear.setPower(-power);
    }

    public void moveRight(double power) {
        leftFront.setPower(power);
        leftRear.setPower(-power);
        rightFront.setPower(-power);
        rightRear.setPower(power);
    }
}
