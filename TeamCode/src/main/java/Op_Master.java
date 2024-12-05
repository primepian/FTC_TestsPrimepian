import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import robotControls.Posiciones;
import robotControls.Chassis;
public class Op_Master  extends OpMode {
    Posiciones  misPosiciones =  new Posiciones();
    Chassis  chasisovic =  new Chassis();
    float RightTriggerPos = gamepad2.right_trigger;
    float LeftTriggerPos = gamepad2.left_trigger;
    double servoPosition = 0.5; // Initial servo position (adjust as needed)
    double servoPositionH = 0.5; // Initial servo position (adjust as needed)
    double servoPositionE = 0.5; // Initial servo position (adjust as needed)
    double increment = 0.01;
    public boolean garra_abierta;
    public boolean barredora;

    @Override
    public void init(){
        chasisovic.runOpMode();
    misPosiciones.initBarredora();
    misPosiciones.initGarra();
    misPosiciones.initElevador();
    }

    @Override
    public void loop() {
        //GARRA
        if (gamepad2.x && !garra_abierta){  //abrir garra
            misPosiciones.servo_Garra.setPosition(0);
            garra_abierta = true;
        }
        if (gamepad2.x && garra_abierta){  //cerrar garra
            misPosiciones.servo_Garra.setPosition(0);
            garra_abierta = false;
        }

        if (gamepad2.left_stick_y > 0) {    //MOVER BRAZO FRENTE
            servoPosition += increment;
            if (servoPosition > 1.0) {
                servoPosition = 1.0;
            }
            misPosiciones.moverBrazo(servoPosition);
        } else if (gamepad2.left_stick_y < 0) {     //MOVER BRAZO ATRAS
            servoPosition -= increment;
            if (servoPosition < 0.0) {
                servoPosition = 0.0;
            }
            misPosiciones.moverBrazo(servoPosition);
        }

        if (gamepad2.right_stick_y > 0) {   //GARRA MANO FRENTE
            servoPositionH += increment;
            if (servoPositionH > 1.0) {
                servoPositionH = 1.0;
            }
            misPosiciones.moverMano(servoPositionH);
        } else if (gamepad2.right_stick_y < 0) {    //GARRA MANO ATRAS
            servoPositionH -= increment;
            if (servoPositionH < 0.0) {
                servoPositionH = 0.0;
            }
            misPosiciones.moverMano(servoPositionH);
        }
        if (gamepad2.y) {   //CORREDERA GARRA FRENTE
            servoPositionE += increment;
            if (servoPositionE > 1.0) {
                servoPositionE = 1.0;
            }
            misPosiciones.moverMano(servoPositionE);
        } else if (gamepad2.a) {    //CORREDERA GARRA ATRAS
            servoPositionE -= increment;
            if (servoPositionE < 0.0) {
                servoPositionE = 0.0;
            }
            misPosiciones.moverMano(servoPositionE);
        }


        //ELEVADOR
        if(gamepad2.right_trigger > 0){  //mover Elevador Arriba
            misPosiciones.elevadorEnfrente(RightTriggerPos);
        }
        if(gamepad2.left_trigger > 0){  //mover Elevador Abajo
            misPosiciones.elevadorAtras(LeftTriggerPos);
        }

        //BARREDORA
        if(gamepad1.y && !barredora){  //Lanzar barredora
            misPosiciones.LanzarBarredora();
            barredora = true;
        }
        if(gamepad1.a && barredora){  //retraer barredora
            misPosiciones.RetraerBarredora();
            barredora = false;
        }

        //POS
        if(gamepad2.dpad_up){  //Recoger Sample
            misPosiciones.RecogerSample();
        }
        if(gamepad2.dpad_down){  //Recoger Specimen
            misPosiciones.RecogerSpecimen();
        }
    }
}
