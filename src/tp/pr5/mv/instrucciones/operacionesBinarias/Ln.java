package tp.pr5.mv.instrucciones.operacionesBinarias;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;

public class Ln extends NoParamBinaryOperation {

	protected Instruction instanciate(){
		return new Ln();
	}
	
	public String getMnemonic() {
		return "LN";
	}

	protected int instOperation(int cima, int subcima) throws InstructionExecutionException {
		return (subcima < cima) ? 1 : 0;
	}
}
