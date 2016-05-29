package tp.pr5.mv.ordenes.unParametro;

import tp.pr5.mv.modulos.controladores.Controlador;
import tp.pr5.mv.ordenes.CommandInterpreter;

public class StepsCommand extends OneParamOrder {

	private boolean stoped;
	
	public StepsCommand(int parametro){
		this.param = parametro;
	}
	
	protected CommandInterpreter instanciate(int param){
		return new StepsCommand(param);
	}
	
	public String getMnemonic() {
		return "STEP";
	}

	public void execute(Controlador control){
		int n = getParam();
		stoped = false;
		
		while ( (n > 0) && !stoped ) {
			control.CpuStep();
			n--;
		}
	}

	public void stopExecution() {
		stoped = true;
	}
}
