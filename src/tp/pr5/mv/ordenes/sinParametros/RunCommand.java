package tp.pr5.mv.ordenes.sinParametros;

import tp.pr5.mv.modulos.controladores.Controlador;
import tp.pr5.mv.ordenes.CommandInterpreter;


public class RunCommand extends NoParamOrder {

	boolean stoped;
	
	protected CommandInterpreter instanciate(){
		return new RunCommand();
	}
	
	public String getMnemonic() {
		return "RUN";
	}

	public void execute(Controlador control) {
		this.stoped = false;
		
		while(!stoped)
			control.CpuStep();
	}

	@Override
	public void stopExecution() {
		stoped = true;
	}
}
