package tp.pr5.mv.instrucciones.otras;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.NoParamInstruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.excepciones.StackOperandException;
import tp.pr5.mv.modulos.Memory;
import tp.pr5.mv.modulos.OperandStack;
import tp.pr5.mv.modulos.ProgramCounter;
import tp.pr5.mv.modulos.excepciones.EmptyStackException;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

public class Pop extends NoParamInstruction {

	protected Instruction instanciate(){
		return new Pop();
	}
	
	public String getMnemonic() {
		return "POP";
	}

	protected int instOperation(int cima) throws InstructionExecutionException{
		return 0;
	}

	@Override
	public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output)
			throws InstructionExecutionException {
		try{
			pilaOperandos.pop();
		}
		catch(EmptyStackException e){
			throw new StackOperandException(this.getInstructionRepresentation(),e);
			
		}
		
	}
}