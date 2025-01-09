package positionTests.Garra;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@Config
@TeleOp
public class Garrita extends LinearOpMode {

    public boolean garra_abierta = false;
    public Servo servo_Garra;

    public void runOpMode() {
        initGarra();
        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.x && !garra_abierta) {  //abrir garra
                servo_Garra.setPosition(0.5);
                garra_abierta = true;
            }
            if (gamepad1.x && garra_abierta) {  //cerrar garra
                servo_Garra.setPosition(0);
                garra_abierta = false;
            }
        }
    }
    public void initGarra() {

        telemetry.addLine("Garra iniciada");
    }
}