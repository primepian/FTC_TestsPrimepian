package tests;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
//@Config
//@TeleOp
public class ServoTest extends LinearOpMode {
    public static double ServoPOS = 0.5;

    public Servo servo;

    public void runOpMode(){
        initGarra();
        waitForStart();

        while (opModeIsActive()){
            if (gamepad1.b){
                servo.setPosition(ServoPOS);
            } else if (gamepad1.a) {
                servo.setPosition(0.0);
            }
        }
    }
    public void initGarra(){
        servo = hardwareMap.get(Servo.class, "servo");
    }
}