package tp.pr5.mv.ordenes.unParametro;

import tp.pr5.mv.modulos.controladores.Controlador;
import tp.pr5.mv.ordenes.CommandInterpreter;

public class PushCommand extends OneParamOrder {

	public PushCommand(int parametro){
		this.param = parametro;
	}

	protected CommandInterpreter instanciate(int param){
		return new PushCommand(param);
	}

	public String getMnemonic() {
		return "PUSH";
	}

	public void execute(Controlador control) {
		control.executePush(param);
	}

	public void stopExecution() {
	}
}
