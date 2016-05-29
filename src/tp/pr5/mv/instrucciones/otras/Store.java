package tp.pr5.mv.instrucciones.otras;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.OneParamInstruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.excepciones.MemoryException;
import tp.pr5.mv.instrucciones.excepciones.StackOperandException;
import tp.pr5.mv.modulos.Memory;
import tp.pr5.mv.modulos.OperandStack;
import tp.pr5.mv.modulos.ProgramCounter;
import tp.pr5.mv.modulos.excepciones.EmptyStackException;
import tp.pr5.mv.modulos.excepciones.FullMemoryException;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

public class Store extends OneParamInstruction {

	public Store(int parametro){
		this.param = parametro;
	}
	
	protected Instruction instanciate(int param){
		return new Store(param);
	}
	
	public String getMnemonic() {
		return "STORE";
	}

	public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output) throws InstructionExecutionException{	
		try{
			memoria.setRegister(pilaOperandos.pop(), getParam());
		}
		catch(EmptyStackException e){
			throw new StackOperandException(this.getInstructionRepresentation(),e);
		}
		catch(FullMemoryException e){
			throw new MemoryException(this.getInstructionRepresentation(),e);
		}
	}
}
