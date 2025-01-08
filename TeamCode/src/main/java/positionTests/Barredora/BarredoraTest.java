package positionTests.Barredora;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class BarredoraTest extends LinearOpMode {
    public Servo LBarredora1;
    public Servo LBarredora2;
    public Servo Articulacion_Barredora1;
    public Servo Articulacion_Barredora2;
    public CRServo SBarredora;
    public boolean barredora;

    public void runOpMode(){
        initBarredora();
        waitForStart();

        while (opModeIsActive()){
            //BARREDORA
            if(gamepad1.right_bumper && !barredora){  //Lanzar barredora
                LanzarBarredora();
                barredora = true;
            }
            if(gamepad1.left_bumper && barredora){  //retraer barredora
                RetraerBarredora();
                barredora = false;
            }
            while (gamepad1.b){
                SBarredora.setPower(1.0);
            }
        }
    }
    public void initBarredora(){
        LBarredora1 = hardwareMap.get(Servo.class, "L_Barredora1");
        LBarredora2 = hardwareMap.get(Servo.class, "L_Barredora2");
        LBarredora2.setDirection(Servo.Direction.REVERSE);
        Articulacion_Barredora1 = hardwareMap.get(Servo.class, "A_Barredora1");
        Articulacion_Barredora2 = hardwareMap.get(Servo.class, "A_Barredora2");
        SBarredora = hardwareMap.get(CRServo.class, "SBarredora");
        telemetry.addLine("Barredora iniciada");
    }
    public void LanzarBarredora(){
        LBarredora1.setPosition(.5);
        LBarredora2.setPosition(.5);
        Articulacion_Barredora1.setPosition(0.5);
        Articulacion_Barredora2.setPosition(0.5);
    }
    public void RetraerBarredora(){
        Articulacion_Barredora1.setPosition(0);
        Articulacion_Barredora2.setPosition(0);
        LBarredora1.setPosition(0);
        LBarredora2.setPosition(0);
    }
}
