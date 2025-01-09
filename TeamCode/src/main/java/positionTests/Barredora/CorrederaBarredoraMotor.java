package positionTests.Barredora;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp
public class CorrederaBarredoraMotor extends LinearOpMode {
    public static double motor_Power = 0.7;
    public DcMotor motorCorredera;


    public void runOpMode(){
        initGarra();
        waitForStart();

        while (opModeIsActive()){
            if (gamepad1.right_bumper){
                moverCorredera(motor_Power);
            } else if (gamepad1.left_bumper) {
                moverCorredera(-motor_Power);
            }
        }
    }
    public void initGarra(){
        motorCorredera = hardwareMap.get(DcMotor.class, "Corredera1");
        motorCorredera.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorCorredera.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void moverCorredera(double POW){
        motorCorredera.setPower(POW); // aumenta
    }


}
