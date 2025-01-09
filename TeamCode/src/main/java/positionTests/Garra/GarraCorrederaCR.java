package positionTests.Garra;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;


@TeleOp
public class GarraCorrederaCR extends LinearOpMode {
    public CRServo servo_Corredera1;
    public CRServo servo_Corredera2;

    public void runOpMode(){
        initGarra();
        waitForStart();

        while (opModeIsActive()){

            if (gamepad1.y) {   //CORREDERA GARRA FRENTE
                servo_Corredera1.setPower(0.7);
                servo_Corredera2.setPower(-0.7);
            }
            else if (gamepad1.a) {    //CORREDERA GARRA ATRAS
                servo_Corredera1.setPower(-0.7);
                servo_Corredera2.setPower(0.7);
            }
            else {
                servo_Corredera1.setPower(0);
                servo_Corredera2.setPower(0);
            }

            telemetry.addData("Servo 1", servo_Corredera1.getPower());
            telemetry.addData("Servo 2", servo_Corredera2.getPower());
            telemetry.update();


        }
    }
    public void initGarra(){

        servo_Corredera1 = hardwareMap.get(CRServo.class, "Corredera1");
        servo_Corredera2 = hardwareMap.get(CRServo.class, "Corredera2");
        telemetry.addLine("Garra iniciada");
        telemetry.update();
    }
}
