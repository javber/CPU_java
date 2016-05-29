package tp.pr5.mv.instrucciones.operacionesBinarias;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;

public class Eq extends NoParamBinaryOperation {

	protected Instruction instanciate(){
		return new Eq();
	}
	
	public String getMnemonic() {
		return "EQ";
	}

	protected int instOperation(int cima, int subcima) throws InstructionExecutionException {
		return (cima == subcima) ? 1 : 0;
	}
}
