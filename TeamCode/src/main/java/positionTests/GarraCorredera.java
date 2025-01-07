package positionTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class GarraCorredera extends LinearOpMode {
    double servoPositionC = 0.0; // Initial servo position (adjust as needed)
    double increment = 0.01;

    public Servo servo_Corredera1;
    public Servo servo_Corredera2;

    public void runOpMode(){
        initGarra();
        waitForStart();

        while (opModeIsActive()){

            while (gamepad1.y) {   //CORREDERA GARRA FRENTE
                servoPositionC += increment;
                if (servoPositionC > 1.0) {
                    servoPositionC = 1.0;
                }
                moverCorredera(servoPositionC);
            }
            while (gamepad1.a) {    //CORREDERA GARRA ATRAS
                servoPositionC -= increment;
                if (servoPositionC < 0.0) {
                    servoPositionC = 0.0;
                }
                moverCorredera(servoPositionC);
            }
        }
    }
    public void initGarra(){

        servo_Corredera1 = hardwareMap.get(Servo.class, "Corredera1");
        servo_Corredera2 = hardwareMap.get(Servo.class, "Corredera2");
        telemetry.addLine("Garra iniciada");
    }

    public void moverCorredera(double POS){
        servo_Corredera1.setPosition(POS);
        servo_Corredera2.setPosition(-POS);
    }

}
