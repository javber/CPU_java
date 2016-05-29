package tp.pr5.mv.instrucciones.operacionesBinarias;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;

public class Sub extends NoParamBinaryOperation {

	protected Instruction instanciate(){
		return new Sub();
	}
	
	public String getMnemonic() {
		return "SUB";
	}

	protected int instOperation(int cima, int subcima) throws InstructionExecutionException {
		return subcima - cima;
	}
}