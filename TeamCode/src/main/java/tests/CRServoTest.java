package tests;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

//@Config
//@TeleOp
public class CRServoTest extends LinearOpMode {
    public static double CRServoPOS = 0.5;

    public CRServo servo;

    public void runOpMode(){
        initGarra();
        waitForStart();

        while (opModeIsActive()){
            if (gamepad1.b){
                servo.setPower(CRServoPOS);
            } else if (gamepad1.a) {
                servo.setPower(0.0);
            }
        }
    }
    public void initGarra(){
        servo = hardwareMap.get(CRServo.class, "servo");
    }
}