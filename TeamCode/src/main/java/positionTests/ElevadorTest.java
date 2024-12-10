package positionTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp
public class ElevadorTest extends LinearOpMode {
    public DcMotor elevador1 = null;
    public DcMotor elevador2 = null;
    private static final double TICKS_PER_REVOLUTION = 537.7d;   //8192
    float RightTriggerPos = gamepad2.right_trigger;
    float LeftTriggerPos = gamepad2.left_trigger;
    int elevatorTolerance = 50;
    @Override
    public void runOpMode() {
        initElevador();
        waitForStart();

        while (opModeIsActive()){
            //ELEVADOR
            if(gamepad2.right_trigger > 0){  //mover Elevador Arriba
                elevadorEnfrente(RightTriggerPos);
            }
            if(gamepad2.left_trigger > 0){  //mover Elevador Abajo
                elevadorAtras(LeftTriggerPos);
            }
        }
    }


    public void elevador(int targetPos){
        elevador1.setTargetPosition((int)targetPos);
        elevador2.setTargetPosition((int)targetPos);
        elevador1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevador2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (elevador1.getCurrentPosition() > elevador2.getCurrentPosition() + elevatorTolerance ||
                elevador1.getCurrentPosition() < elevador2.getCurrentPosition() - elevatorTolerance||
                elevador2.getCurrentPosition() > elevador1.getCurrentPosition() + elevatorTolerance||
                elevador2.getCurrentPosition() < elevador1.getCurrentPosition() - elevatorTolerance){
            elevador1.setPower(0);
            elevador2.setPower(0);
        }
        if (elevador1.getCurrentPosition() > 1000 || elevador2.getCurrentPosition() > 1000){
        }
    }
    public void initElevador(){
        elevador1 = hardwareMap.get(DcMotor.class,"elevador1");
        elevador2 = hardwareMap.get(DcMotor.class,"elevador2");
        elevador1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevador2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevador2.setDirection(DcMotorSimple.Direction.REVERSE);
        elevador1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevador2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addLine("Elevadores iniciados");
    }
    public void elevadorEnfrente(float POWER){
        elevador1.setPower(POWER);
        elevador2.setPower(POWER);
    }
    public void elevadorAtras(float POWER){
        elevador1.setPower(-POWER);
        elevador2.setPower(-POWER);
    }
}
