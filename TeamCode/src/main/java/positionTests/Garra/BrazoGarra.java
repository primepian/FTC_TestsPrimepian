package positionTests.Garra;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class BrazoGarra extends LinearOpMode {
    double servoBPosition1 = 0.4;
    double servoBPosition2 = 1.0;
    double Bincrement = 0.005; //Valor de vel

    public Servo servo_Brazo1;
    public Servo servo_Brazo2;

    @Override
    public void runOpMode() {
        initGarra();
        waitForStart();

        while (opModeIsActive()) {
            // MOVER BRAZO FRENTE
            if (gamepad1.left_stick_y > 0) {
                servoBPosition1 = Math.min(servoBPosition1 + Bincrement, 1.0);
                servoBPosition2 = Math.max(servoBPosition2 - Bincrement, 0.4);
                moverBrazo(servoBPosition1, servoBPosition2);
            }

            // MOVER BRAZO ATRÁS
            else if (gamepad1.left_stick_y < 0) {
                servoBPosition1 = Math.max(servoBPosition1 - Bincrement, 0.4);
                servoBPosition2 = Math.min(servoBPosition2 + Bincrement, 1.0);
                moverBrazo(servoBPosition1, servoBPosition2);
            }
            //congelar Brazo
            if (gamepad1.left_stick_button){
                servoBPosition1 = servo_Brazo1.getPosition();
                servoBPosition2 = servo_Brazo2.getPosition();
                moverBrazo(servoBPosition1,servoBPosition2);
            }


            // Posiciones específicas con botones
            if (gamepad1.right_bumper) { // MIN
                moverBrazo(0.4, 1.0);
            } else if (gamepad1.left_bumper) { // MAX
                moverBrazo(1.0, 0.4);
            }

            // Mostrar telemetría
            telemetry.addData("B1 pos=", servo_Brazo1.getPosition());
            telemetry.addData("B2 pos=", servo_Brazo2.getPosition());
            telemetry.update();
        }
    }

    public void initGarra() {
        servo_Brazo1 = hardwareMap.get(Servo.class, "brazo1");
        servo_Brazo2 = hardwareMap.get(Servo.class, "brazo2");
        telemetry.addLine("Garra iniciada");
        telemetry.update();
    }

    public void moverBrazo(double POS1, double POS2) {
        servo_Brazo1.setPosition(POS1);
        servo_Brazo2.setPosition(POS2);
    }
}
