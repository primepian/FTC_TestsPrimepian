package positionTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class BarredoraTest extends LinearOpMode {
    public Servo LBarredora1;
    public Servo LBarredora2;
    public Servo Articulacion_Barredora1;
    public Servo Articulacion_Barredora2;
    public boolean barredora;

    public void runOpMode(){
        initBarredora();
        waitForStart();

        while (opModeIsActive()){
            //BARREDORA
            if(gamepad1.y && !barredora){  //Lanzar barredora
                LanzarBarredora();
                barredora = true;
            }
            if(gamepad1.a && barredora){  //retraer barredora
                RetraerBarredora();
                barredora = false;
            }
        }
    }
    public void initBarredora(){
        LBarredora1 = hardwareMap.get(Servo.class, "L_Barredora1");
        LBarredora2 = hardwareMap.get(Servo.class, "L_Barredora2");
        LBarredora2.setDirection(Servo.Direction.REVERSE);
        Articulacion_Barredora1 = hardwareMap.get(Servo.class, "A_Barredora1");
        Articulacion_Barredora2 = hardwareMap.get(Servo.class, "A_Barredora2");
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
