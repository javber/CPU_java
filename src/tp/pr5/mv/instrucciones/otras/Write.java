package tp.pr5.mv.instrucciones.otras;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.TwoParamInstruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.excepciones.MemoryException;
import tp.pr5.mv.modulos.Memory;
import tp.pr5.mv.modulos.OperandStack;
import tp.pr5.mv.modulos.ProgramCounter;
import tp.pr5.mv.modulos.excepciones.FullMemoryException;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

public class Write extends TwoParamInstruction{

	public Write(int direction, int value){
		this.param1 = direction;
		this.param2 = value;
	}
	
	public String getMnemonic() {
		return "WRITE";
	}

	public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output)
			throws InstructionExecutionException {
		try {
			memoria.setRegister(this.param2, this.param1);
		} catch (FullMemoryException e) {
			throw new MemoryException(this.getInstructionRepresentation(), e);
		}
		
	}

	protected Instruction instanciate(int param1, int param2) {
		return new Write(param1, param2);
	}

}
