package tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class elevador extends OpMode {
    public Servo servo_gancho1;
    public Servo Servo_gancho2;

    @Override
    public void init() {
        servo_gancho1 = hardwareMap.get(Servo.class, "servo gancho1");
        Servo_gancho2 = hardwareMap.get(Servo.class, "servo gancho2");
    }
    @Override
    public void loop(){
        if (gamepad1.dpad_up){
            servo_gancho1.setPosition(0);
        }
        if (gamepad1.dpad_down){
            servo_gancho1.setPosition(0.5);
        }
    }
}

