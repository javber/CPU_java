package tp.pr5.mv.instrucciones.operacionesBinarias;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;

public class Add extends NoParamBinaryOperation {

	protected Instruction instanciate() {
		return new Add();
	}
	
	public String getMnemonic() {
		return "ADD";
	}

	protected int instOperation(int cima, int subcima) throws InstructionExecutionException {
		return cima + subcima;
	}
}