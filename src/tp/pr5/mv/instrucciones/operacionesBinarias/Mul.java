package tp.pr5.mv.instrucciones.operacionesBinarias;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;

public class Mul extends NoParamBinaryOperation {

	protected Instruction instanciate(){
		return new Mul();
	}
	
	public String getMnemonic() {
		return "MUL";
	}

	protected int instOperation(int cima, int subcima) throws InstructionExecutionException {
		return cima * subcima;
	}
}
