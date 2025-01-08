package positionTests.Garra;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class BrazoGarra extends LinearOpMode {
    double servoBPosition = 0.5;
    double Bincrement = 0.1;
    public Servo servo_Brazo1;
    public Servo servo_Brazo2;


    public void runOpMode(){
        initGarra();
        waitForStart();

        while (opModeIsActive()){

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
        }
    }
    public void initGarra(){

        servo_Brazo1 = hardwareMap.get(Servo.class, "brazo1");
        servo_Brazo2 = hardwareMap.get(Servo.class, "brazo2");
        telemetry.addLine("Garra iniciada");
    }
    public void moverBrazo(double POS){
        servo_Brazo1.setPosition(POS);
        servo_Brazo2.setPosition(-POS);
    }
}

