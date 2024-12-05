package tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class PruebaEncoder extends OpMode {
    private DcMotor motor;
    private static final double TICKS_PER_REVOLUTION = 8192; // Update based on your motor's gear ratio
    private static final double MOTOR_POWER = 0.1;
    private double targetPosition = 0;

    @Override
    public void init() {
        // Initialize motor
        motor = hardwareMap.get(DcMotor.class, "motor");

        // Set motor to reset encoder and use encoder mode
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set motor to brake when zero power is applied
//        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            moveMotorRevolutions(2);
        }
        if (gamepad1.b) {
            stopMotor();
        }
        if (gamepad1.x){
            moveMotorRevolutions(14);
        }
        while (motor.isBusy()) {
            telemetry.addData("Motor Position (revolutions)", motor.getCurrentPosition() / TICKS_PER_REVOLUTION);
            telemetry.update();
        }

        // Telemetry for monitoring motor position and status
        telemetry.addData("Target Position (ticks)", targetPosition);
        telemetry.addData("Motor Current Position (ticks)", motor.getCurrentPosition());
        telemetry.addData("Motor Position (revolutions)", motor.getCurrentPosition() * 10 / TICKS_PER_REVOLUTION);
        telemetry.update();
    }

    private void moveMotorRevolutions(int revolutions) {
        targetPosition = revolutions * TICKS_PER_REVOLUTION;
        motor.setTargetPosition((int) targetPosition);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(MOTOR_POWER);
//        motor.setPower(0);
    }
    private void stopMotor() {
        motor.setPower(0);
    }
}
