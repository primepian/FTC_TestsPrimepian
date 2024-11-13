package tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Posiciones extends OpMode {
    //GARRA
    public Servo servo_Garra;
    public Servo servo_Brazo1;
    public Servo servo_Brazo2;
    public Servo servo_hand;
    //GANCHO
    public Servo servo_gancho1;
    public Servo servo_gancho2;
    //ELEVADOR
    public DcMotor elevador1 = null;
    public DcMotor elevador2 = null;
    boolean garra_abierta;
    boolean barredora;
    boolean ganchito;

    private static final double TICKS_PER_REVOLUTION = 557.7; // Update based on your motor's gear ratio
    private static final double MOTOR_POWER = 0.4;
    private double targetPosition = 0;

    @Override
    public void init() {
        initGarra();
        initGancho();
        initElevador();
    }

    @Override
    public void loop() {
        if (gamepad1.x && !garra_abierta){  //abrir garra
            servo_Garra.setPosition(0);
        }
        if (gamepad1.x && garra_abierta){  //cerrar garra
            servo_Garra.setPosition(0);
        }
        if(gamepad1.y && !barredora){  //Lanzar barredora
            LanzarBarredora();
        }
        if(gamepad1.y && barredora){  //retraer barredora
            RetraerBarredora();
        }
        if(gamepad1.a && !ganchito){ //ganchito abajo
            moverGancho(0);
        }
        if(gamepad1.a && ganchito){ //ganchito arriba
            moverGancho(0);
        }
        if(gamepad1.left_bumper){ //Colgarse
            Colgarse();
        }
        if(gamepad1.dpad_left){  //Agarrar Specimen
            AgarrarSpecimen();
        }
        if(gamepad1.b){ //Poner Specimen
            PonerArriba();
        }
        if(gamepad1.dpad_right){ //Agarrar Sample
            AgarrarSample();
        }
        if(gamepad1.dpad_up){ //Canasta arriva
            CanastaArriva();
        }
        if(gamepad1.dpad_down){ //Canasta abajo
            CanastaAbajo();
        }
        telemetry.addData("Target Position (ticks)", targetPosition);
        telemetry.addData("Motor Current Position (ticks)", elevador1.getCurrentPosition());
        telemetry.addData("Motor Position (revolutions)", elevador1.getCurrentPosition() * 10 / TICKS_PER_REVOLUTION);
        telemetry.update();
    }
//FUNCIONES DE POSICIONES
    public void LanzarBarredora(){
        moverElevador(0);
        moverBrazo(0);
    }
    public void RetraerBarredora(){
        moverElevador(0);
        moverBrazo(0);
    }
    public void AgarrarSpecimen(){
        moverElevador(0);
        moverBrazo(0);
    }
    public void AgarrarSample(){
        moverElevador(0);
        moverBrazo(0);
    }
    public void CanastaArriva(){
        moverElevador(0);
        moverBrazo(0);
    }
    public void CanastaAbajo(){
        moverElevador(0);
        moverBrazo(0);
    }
    public void PonerArriba(){
        moverElevador(0);
        moverBrazo(0);
    }
    public void Colgarse(){
        moverElevador(0);
        moverGancho(0.5);
    }
//FUNCIONES DE INICIACION
    public void initGarra(){
        servo_Garra = hardwareMap.get(Servo.class, "servo_garra");
        servo_Brazo1 = hardwareMap.get(Servo.class, "servo_brazo1");
        servo_Brazo2 = hardwareMap.get(Servo.class, "servo_brazo2");
        servo_hand = hardwareMap.get(Servo.class, "servo_hand");
        telemetry.addLine("Garra iniciada");
    }
    public void initGancho(){
        servo_gancho1 = hardwareMap.get(Servo.class, "servo gancho1");
        servo_gancho2 = hardwareMap.get(Servo.class, "servo gancho2");
        servo_gancho1.setPosition(0);//set defaul position
        servo_gancho2.setPosition(0);//set defaul position
        telemetry.addLine("Gancho iniciado");
    }
    public void initElevador(){
        elevador1 = hardwareMap.get(DcMotor.class,"elevador1");
        elevador2 = hardwareMap.get(DcMotor.class,"elevador2");
        elevador1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevador2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevador1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevador2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addLine("Elevadores iniciados");
    }
//FUNCIONES MOVER COSAS
    private void moverElevador(int revolutions) {
        // Calculate target position in ticks
        targetPosition = revolutions * TICKS_PER_REVOLUTION;
        // Set motor to run to target position
        elevador1.setTargetPosition((int) targetPosition);
        elevador1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevador1.setPower(MOTOR_POWER);
        elevador2.setTargetPosition((int) targetPosition);
        elevador2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevador2.setPower(MOTOR_POWER);
    }
    private void moverBrazo(int grado){
        servo_Brazo1.setPosition(grado);
        servo_Brazo2.setPosition(grado);
    }
    public void moverGancho(double pos){
        servo_gancho1.setPosition(pos);
        servo_gancho2.setPosition(pos);
    }
}