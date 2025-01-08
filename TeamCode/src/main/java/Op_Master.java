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
    double servoPositionC = 0.5; // Initial servo position (adjust as needed)
    double increment = 0.01;
    double Bincrement = 0.1;
    public boolean garra_abierta;
    public boolean barredora;

    @Override
    public void init(){
    chasisovic.chasisoico();
    misPosiciones.initBarredora();
    misPosiciones.initGarra();
    misPosiciones.initElevador();
    }
/*
* ABRIR GARRA [x]
* MOVER BRAZO [left_stick_y]
* ARTICULACION GARRA[right_stick_y]
* CORREDERA GARRA [y,a]
* ELEVADOR [right,left trigger]
* BARREDORA [{RBumper,LBumper}]
* RECOGER SAMPLE [dpad_up]
* RECOGER SPECIMEN[dpad_down]
*/
    @Override
    public void loop() {
        chasisovic.chasisoico();
        //GARRA
        if (gamepad2.x && !garra_abierta){  //abrir garra
            misPosiciones.servo_Garra.setPosition(1);
            garra_abierta = true;
        }
        if (gamepad2.x && garra_abierta){  //cerrar garra
            misPosiciones.servo_Garra.setPosition(0);
            garra_abierta = false;
        }

        if (gamepad2.left_stick_y > 0) {    //MOVER BRAZO FRENTE
            servoPosition += Bincrement;
            if (servoPosition > 1.0) {
                servoPosition = 1.0;
            }
            misPosiciones.moverBrazo(servoPosition);
        } else if (gamepad2.left_stick_y < 0) {     //MOVER BRAZO ATRAS
            servoPosition -= Bincrement;
            if (servoPosition < 0.0) {
                servoPosition = 0.0;
            }
            misPosiciones.moverBrazo(servoPosition);
        }

        if (gamepad2.right_stick_y > 0) {   //GARRA MANO FRENTE
            servoPositionH += Bincrement;
            if (servoPositionH > 1.0) {
                servoPositionH = 1.0;
            }
            misPosiciones.moverMano(servoPositionH);
        } else if (gamepad2.right_stick_y < 0) {    //GARRA MANO ATRAS
            servoPositionH -= Bincrement;
            if (servoPositionH < 0.0) {
                servoPositionH = 0.0;
            }
            misPosiciones.moverMano(servoPositionH);
        }
        while (gamepad1.y) {   //CORREDERA GARRA FRENTE
            servoPositionC += increment;
            if (servoPositionC > 1.0) {
                servoPositionC = 1.0;
            }
            misPosiciones.moverCorredera(servoPositionC);
        }
        while (gamepad1.a) {    //CORREDERA GARRA ATRAS
            servoPositionC -= increment;
            if (servoPositionC < 0.0) {
                servoPositionC = 0.0;
            }
            misPosiciones.moverCorredera(servoPositionC);
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
