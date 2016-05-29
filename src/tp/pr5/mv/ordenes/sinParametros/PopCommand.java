package tp.pr5.mv.ordenes.sinParametros;

import tp.pr5.mv.modulos.controladores.Controlador;
import tp.pr5.mv.ordenes.CommandInterpreter;

public class PopCommand extends NoParamOrder {

	protected CommandInterpreter instanciate(){
		return new PopCommand();
	}
	
	public String getMnemonic() {
		return "POP";
	}

	public void execute(Controlador control) {
		control.executePop();
	}

	public void stopExecution() {
	}
}
