package tp.pr5.mv.ordenes.sinParametros;

import tp.pr5.mv.modulos.controladores.Controlador;
import tp.pr5.mv.ordenes.CommandInterpreter;

public class StepCommand extends NoParamOrder {

	protected CommandInterpreter instanciate(){
		return new StepCommand();
	}
	
	public String getMnemonic() {
		return "STEP";
	}

	public void execute(Controlador control){
		control.CpuStep();
	}

	public void stopExecution() {
	}
}
