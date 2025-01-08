package positionTests.Elevador; //ELEVADOR CON BUMPERS

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp
public class ElevadorTest extends LinearOpMode {
    public DcMotor elevador1 = null;
    public DcMotor elevador2 = null;
    private static final double TICKS_PER_REVOLUTION = 537.7;   //8192
//    float RightTriggerPos = gamepad1.right_trigger;
//    float LeftTriggerPos = gamepad1.left_trigger;
    int elevatorTolerance = 50;
    @Override
    public void runOpMode() {
        initElevador();
        waitForStart();

        while (opModeIsActive()){
            //ELEVADOR
            while(gamepad1.right_bumper){  //mover Elevador Arriba
                elevadorEnfrente(0.8);
            }
            while(gamepad1.left_bumper){  //mover Elevador Abajo
                elevadorAtras(0.8);
            }
            if(gamepad1.b){
                elevador1.setPower(0);
                elevador2.setPower(0);
            }
            elevador1.setPower(0);
            elevador2.setPower(0);
        }
    }


    public void initElevador(){
        elevador1 = hardwareMap.get(DcMotor.class,"elevador1");
        elevador2 = hardwareMap.get(DcMotor.class,"elevador2");
//        elevador1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        elevador2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevador2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevador2.setDirection(DcMotorSimple.Direction.REVERSE);
        elevador1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevador2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addLine("Elevadores iniciados");
    }
    public void elevadorEnfrente(double POWER){
        elevador1.setPower(POWER);
        elevador2.setPower(POWER);
    }
    public void elevadorAtras(double POWER){
        elevador1.setPower(-POWER);
        elevador2.setPower(-POWER);
    }
}
