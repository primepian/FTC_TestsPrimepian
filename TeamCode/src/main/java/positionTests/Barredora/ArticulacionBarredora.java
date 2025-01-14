package positionTests.Barredora;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ArticulacionBarredora extends LinearOpMode {
    public Servo Articulacion_Barredora1;
    public Servo Articulacion_Barredora2;

    boolean SampleInChamber;
    DcMotor Barredora;

    public void runOpMode(){
        initBarredora();
        waitForStart();

        while (opModeIsActive()){
            if (gamepad1.dpad_up) {
                Articulacion(0.5,0.5);
            }
            if (gamepad1.dpad_down){
                Articulacion(0.0,1.0);
            }
            if (gamepad1.a){
                Barredora.setPower(1.0);
            } else if (gamepad1.x){
                Barredora.setPower(-1.0);
            } else{
                Barredora.setPower(0.0);
            }
        }
    }
    public void initBarredora(){
        Articulacion_Barredora1 = hardwareMap.get(Servo.class, "A_Barredora1");
        Articulacion_Barredora2 = hardwareMap.get(Servo.class, "A_Barredora2");
        Barredora = hardwareMap.get(DcMotor.class, "Barredora");
        telemetry.addLine("Barredora iniciada");
        telemetry.update();
    }
    public void Articulacion(double POS, double POS2){
        Articulacion_Barredora1.setPosition(POS);
        Articulacion_Barredora2.setPosition(POS2);
    }
}
