package positionTests.Garra;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class GarraTest extends LinearOpMode {
    double servoBPosition = 0.5; // Initial servo position (adjust as needed)
    double servoPositionH = 0.5; // Initial servo position (adjust as needed)
    double servoPositionC = 0.5; // Initial servo position (adjust as needed)
    double increment = 0.01;
    double Bincrement = 0.1;

    public boolean garra_abierta = false;
    public Servo servo_Garra;
    public Servo servo_Brazo1;
    public Servo servo_Brazo2;
    public Servo servo_hand;
    public Servo servo_Corredera1;
    public Servo servo_Corredera2;

    public void runOpMode(){
        initGarra();
        waitForStart();

        while (opModeIsActive()){
            if (gamepad1.x && !garra_abierta){  //abrir garra
                servo_Garra.setPosition(1);
                garra_abierta = true;
            }
            if (gamepad1.x && garra_abierta){  //cerrar garra
               servo_Garra.setPosition(0);
                garra_abierta = false;
            }
            if (gamepad1.left_stick_y > 0) {    //MOVER BRAZO FRENTE
                servoBPosition += Bincrement;
                if (servoBPosition > 1.0) {
                    servoBPosition = 1.0;
                }
                moverBrazo(servoBPosition);
            } else if (gamepad1.left_stick_y < 0) {     //MOVER BRAZO ATRAS
                servoBPosition -= Bincrement;
                if (servoBPosition < 0.0) {
                    servoBPosition = 0.0;
                }
                moverBrazo(servoBPosition);
            }

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
        servo_Garra = hardwareMap.get(Servo.class, "garra");
        servo_Brazo1 = hardwareMap.get(Servo.class, "brazo1");
        servo_Brazo2 = hardwareMap.get(Servo.class, "brazo2");
        servo_hand = hardwareMap.get(Servo.class, "hand");
        servo_Corredera1 = hardwareMap.get(Servo.class, "Corredera1");
        servo_Corredera2 = hardwareMap.get(Servo.class, "Corredera2");
        telemetry.addLine("Garra iniciada");
    }
    public void moverBrazo(double POS){
        servo_Brazo1.setPosition(POS);
        servo_Brazo2.setPosition(-POS);
    }
    public void moverMano(double POS){
        servo_hand.setPosition(POS);
    }
    public void moverCorredera(double POS){
        servo_Corredera1.setPosition(POS);
        servo_Corredera2.setPosition(-POS);
    }

}
