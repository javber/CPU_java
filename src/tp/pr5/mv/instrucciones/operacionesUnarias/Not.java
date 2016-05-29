package tp.pr5.mv.instrucciones.operacionesUnarias;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;

public class Not extends NoParamUnaryOperation {

	protected Instruction instanciate(){
		return new Not();
	}
	
	public String getMnemonic() {
		return "NOT";
	}

	protected int instOperation(int cima) throws InstructionExecutionException {
		return (cima == 0) ? 1 : 0;
	}
}
