package positionTests.Garra;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class GarraH extends LinearOpMode {

    double servoPositionH = 0.5; // Initial servo position (adjust as needed)
    double Bincrement = 0.1;
    public Servo servo_hand;

    public void runOpMode(){
        initGarra();
        waitForStart();

        while (opModeIsActive()){

            if (gamepad1.right_stick_y > 0) {   //GARRA MANO FRENTE
                servoPositionH += Bincrement;
                if (servoPositionH > 1.0) {
                    servoPositionH = 1.0;
                }
                moverMano(servoPositionH);
            } else if (gamepad1.right_stick_y < 0) {    //GARRA MANO ATRAS
                servoPositionH -= Bincrement;
                if (servoPositionH < 0.0) {
                    servoPositionH = 0.0;
                }
                moverMano(servoPositionH);
            }

        }
    }
    public void initGarra(){
        servo_hand = hardwareMap.get(Servo.class, "hand");
        telemetry.addLine("Garra iniciada");
    }
    public void moverMano(double POS){
        servo_hand.setPosition(POS);
    }
}
