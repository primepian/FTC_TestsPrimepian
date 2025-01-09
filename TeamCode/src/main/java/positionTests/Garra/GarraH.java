package positionTests.Garra;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class GarraH extends LinearOpMode {

    double servoPositionH = 0.5; // Initial servo position (adjust as needed)
    double Bincrement = 0.005;
    public Servo servo_hand;

    public void runOpMode(){
        initGarra();
        waitForStart();

        while (opModeIsActive()){

            if (gamepad1.right_stick_y > 0) {   // GARRA MANO FRENTE
                servoPositionH = Math.min(servoPositionH + Bincrement, 1.0);
                moverMano(servoPositionH);
            }
            else if (gamepad1.right_stick_y < 0) {    // GARRA MANO ATRAS
                servoPositionH = Math.max(servoPositionH - Bincrement, 0.0);
                moverMano(servoPositionH);
            }

            if (gamepad1.right_stick_button) {
                moverMano(servo_hand.getPosition());
            }

            telemetry.addData("Garra Position", servoPositionH);
            telemetry.update();
        }
    }

    public void initGarra(){
        servo_hand = hardwareMap.get(Servo.class, "hand");
        servo_hand.setPosition(0.5);
        telemetry.addLine("Garra iniciada");
        telemetry.update();
    }

    public void moverMano(double POS){
        servo_hand.setPosition(POS);
    }
}
