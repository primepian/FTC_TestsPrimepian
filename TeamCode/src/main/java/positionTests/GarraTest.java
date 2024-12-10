package positionTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class GarraTest extends LinearOpMode {
    double servoPosition = 0.5; // Initial servo position (adjust as needed)
    double servoPositionH = 0.5; // Initial servo position (adjust as needed)
    double servoPositionE = 0.5; // Initial servo position (adjust as needed)
    double increment = 0.01;
    double Bincrement = 0.1;

    public boolean garra_abierta = false;
    public Servo servo_Garra;
    public Servo servo_Brazo1;
    public Servo servo_Brazo2;
    public Servo servo_hand;

    public void runOpMode(){
        initGarra();
        waitForStart();

        while (opModeIsActive()){
            if (gamepad2.x && !garra_abierta){  //abrir garra
                servo_Garra.setPosition(1);
                garra_abierta = true;
            }
            if (gamepad2.x && garra_abierta){  //cerrar garra
               servo_Garra.setPosition(0);
                garra_abierta = false;
            }
            if (gamepad2.left_stick_y > 0) {    //MOVER BRAZO FRENTE
                servoPosition += Bincrement;
                if (servoPosition > 1.0) {
                    servoPosition = 1.0;
                }
                moverBrazo(servoPosition);
            } else if (gamepad2.left_stick_y < 0) {     //MOVER BRAZO ATRAS
                servoPosition -= Bincrement;
                if (servoPosition < 0.0) {
                    servoPosition = 0.0;
                }
                moverBrazo(servoPosition);
            }

            if (gamepad2.right_stick_y > 0) {   //GARRA MANO FRENTE
                servoPositionH += Bincrement;
                if (servoPositionH > 1.0) {
                    servoPositionH = 1.0;
                }
                moverMano(servoPositionH);
            } else if (gamepad2.right_stick_y < 0) {    //GARRA MANO ATRAS
                servoPositionH -= Bincrement;
                if (servoPositionH < 0.0) {
                    servoPositionH = 0.0;
                }
                moverMano(servoPositionH);
            }
            if (gamepad2.y) {   //CORREDERA GARRA FRENTE
                servoPositionE += increment;
                if (servoPositionE > 1.0) {
                    servoPositionE = 1.0;
                }
                moverMano(servoPositionE);
            } else if (gamepad2.a) {    //CORREDERA GARRA ATRAS
                servoPositionE -= increment;
                if (servoPositionE < 0.0) {
                    servoPositionE = 0.0;
                }
                moverMano(servoPositionE);
            }
        }
    }
    public void initGarra(){
        servo_Garra = hardwareMap.get(Servo.class, "garra");
        servo_Brazo1 = hardwareMap.get(Servo.class, "brazo1");
        servo_Brazo2 = hardwareMap.get(Servo.class, "brazo2");
        servo_hand = hardwareMap.get(Servo.class, "hand");
        telemetry.addLine("Garra iniciada");
    }
    public void moverBrazo(double POS){
        servo_Brazo1.setPosition(POS);
        servo_Brazo2.setPosition(-POS);
    }
    public void moverMano(double POS){
        servo_hand.setPosition(POS);
    }

}
