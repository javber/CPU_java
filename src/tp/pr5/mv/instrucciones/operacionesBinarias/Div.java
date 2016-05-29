package tp.pr5.mv.instrucciones.operacionesBinarias;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.excepciones.ZeroDivException;

public class Div extends NoParamBinaryOperation {

	protected Instruction instanciate(){
		return new Div();
	}
	
	public String getMnemonic() {
		return "DIV";
	}

	protected int instOperation(int cima, int subcima) throws InstructionExecutionException {
		if (cima == 0)
			throw new ZeroDivException();
		return subcima / cima;
	}
}
