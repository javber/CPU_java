package tp.pr5.mv.instrucciones.operacionesBinarias;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;

public class Gt extends NoParamBinaryOperation {

	protected Instruction instanciate(){
		return new Gt();
	}
	
	public String getMnemonic() {
		return "GT";
	}

	protected int instOperation(int cima, int subcima) throws InstructionExecutionException {
		return (subcima > cima) ? 1 : 0;
	}
}
