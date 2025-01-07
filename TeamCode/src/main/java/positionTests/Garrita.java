package positionTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

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
        servo_Garra = hardwareMap.get(Servo.class, "garra");
        telemetry.addLine("Garra iniciada");
    }
}