package tp.pr5.mv.ordenes.sinParametros;

import tp.pr5.mv.modulos.controladores.Controlador;
import tp.pr5.mv.ordenes.CommandInterpreter;

public class RstProgCommand extends NoParamOrder {

	protected CommandInterpreter instanciate(){
		return new RstProgCommand();
	}
	
	//Implementado para debug, reinicia el programa cargado en la cpu
	public String getMnemonic() {
		return "RESET";
	}

	public void execute(Controlador control){
		control.resetProgram();
	}

	public void stopExecution() {
	}
}
