package robotControls;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class Posiciones {
    //ELEVADOR
    public DcMotor elevador1 = null;
    public DcMotor elevador2 = null;
    //GARRA
    public Servo servo_Garra;
    public Servo servo_Brazo1;
    public Servo servo_Brazo2;
    public Servo servo_hand;
    //Barredora
    public Servo LBarredora1;
    public Servo LBarredora2;
    public Servo Articulacion_Barredora1;
    public Servo Articulacion_Barredora2;


    public double targetPosition = 0;



    //FUNCIONES DE POSICIONES
    public void LanzarBarredora(){
        LBarredora1.setPosition(1.0);
        LBarredora2.setPosition(1.0);
    }
    public void RetraerBarredora(){
        LBarredora1.setPosition(0);
        LBarredora2.setPosition(0);
    }
    public void RecogerSpecimen(){

    }
    public void RecogerSample(){

    }

//FUNCIONES DE INICIACION
    public void initGarra(){
        servo_Garra = hardwareMap.get(Servo.class, "garra");
        servo_Brazo1 = hardwareMap.get(Servo.class, "brazo1");
        servo_Brazo2 = hardwareMap.get(Servo.class, "brazo2");
        servo_hand = hardwareMap.get(Servo.class, "hand");
        telemetry.addLine("Garra iniciada");
    }
    public void initBarredora(){
        LBarredora1 = hardwareMap.get(Servo.class, "L_Barredora1");
        LBarredora2 = hardwareMap.get(Servo.class, "L_Barredora2");
        LBarredora2.setDirection(Servo.Direction.REVERSE);
        Articulacion_Barredora1 = hardwareMap.get(Servo.class, "A_Barredora1");
        Articulacion_Barredora2 = hardwareMap.get(Servo.class, "A_Barredora2");
        telemetry.addLine("Barredora iniciada");
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
//FUNCIONES MOVER COSAS
    public void moverBrazo(double POS){
        servo_Brazo1.setPosition(POS);
        servo_Brazo2.setPosition(-POS);
    }
    public void moverMano(double POS){
        servo_hand.setPosition(POS);
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