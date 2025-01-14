package positionTests.Barredora;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Motor Test ")
public class CorrederaBarredoraMotor extends LinearOpMode {
    DcMotor motor;
//     ticks = 537.7;
    int newTarget = 90;
    public int nsdkl(int degrees){return (int) Math.round(degrees * 1.4936111);}

    public void runOpMode(){
        initBarredora();

        while (opModeIsActive()) {
            if (gamepad1.right_bumper) {
                motor.setTargetPosition(nsdkl(newTarget));
                motor.setPower(0.6);
            }
            if (gamepad1.left_bumper) {
                motor.setTargetPosition(0);
                motor.setPower(0.6);
            }
            telemetry.addData("motorPos", motor.getTargetPosition());
        }
    }
    public void initBarredora(){
        motor = hardwareMap.get(DcMotor.class, "Corredera");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setTargetPosition(nsdkl(newTarget));
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
