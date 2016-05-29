package tp.pr5.mv.instrucciones.operacionesBinarias;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;

public class And extends NoParamBinaryOperation {

	protected Instruction instanciate(){
		return new And();
	}
	
	public String getMnemonic() {
		return "AND";
	}

	protected int instOperation(int cima, int subcima) throws InstructionExecutionException {
		return (cima == 0 || subcima == 0) ? 0 : 1;
	}
}
