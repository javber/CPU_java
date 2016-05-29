package tp.pr5.mv.ordenes.dosParametros;

import tp.pr5.mv.modulos.controladores.Controlador;
import tp.pr5.mv.ordenes.CommandInterpreter;


public class WriteCommand extends TwoParamOrder {

	public WriteCommand(int value, int direction){
		this.param1 = value;
		this.param2 = direction;
	}
	
	protected CommandInterpreter instanciate(int param1, int param2){
		return new WriteCommand(param1, param2);
	}
	
	public String getMnemonic() {
		return "WRITE";
	}

	public void execute(Controlador control) {
		control.executeWrite(param1, param2);
	}

	public void stopExecution() {
	}
}
