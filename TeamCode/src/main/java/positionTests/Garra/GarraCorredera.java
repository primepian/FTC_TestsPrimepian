package positionTests.Garra;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@Config
@TeleOp
public class GarraCorredera extends LinearOpMode {
    public static double pos = 0.45;

    public Servo servo_Corredera1;
    public Servo servo_Corredera2;

    public void runOpMode(){
        initGarra();
        waitForStart();

        while (opModeIsActive()){
            if (gamepad1.y){
                moverCorredera(pos, 1.0 - pos);
            } else if (gamepad1.a) {
                moverCorredera(0.0,1.0);
            }


            telemetry.addData("S1 Pos= ", servo_Corredera1.getPosition());
            telemetry.addData("S2 Pos= ", servo_Corredera2.getPosition());
            telemetry.update();
        }
    }
    public void initGarra(){
        servo_Corredera1 = hardwareMap.get(Servo.class, "Corredera1");
        servo_Corredera2 = hardwareMap.get(Servo.class, "Corredera2");
        telemetry.addLine("Garra iniciada");
    }

    public void moverCorredera(double POS, double POS2){
        servo_Corredera1.setPosition(POS); // aumenta
        servo_Corredera2.setPosition(POS2); // disminuye
    }
}
