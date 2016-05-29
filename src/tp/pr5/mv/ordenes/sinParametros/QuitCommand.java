package tp.pr5.mv.ordenes.sinParametros;

import tp.pr5.mv.modulos.controladores.Controlador;
import tp.pr5.mv.ordenes.CommandInterpreter;

public class QuitCommand extends NoParamOrder {

	protected CommandInterpreter instanciate(){
		return new QuitCommand();
	}
	
	public String getMnemonic() {
		return "QUIT";
	}

	public void execute(Controlador control) {
		control.halt();
	}

	public void stopExecution() {
	}
}
