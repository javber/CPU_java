package tp.pr5.mv.instrucciones.otras;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.OneParamInstruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.excepciones.MemoryException;
import tp.pr5.mv.instrucciones.excepciones.StackOperandException;
import tp.pr5.mv.modulos.Memory;
import tp.pr5.mv.modulos.OperandStack;
import tp.pr5.mv.modulos.ProgramCounter;
import tp.pr5.mv.modulos.excepciones.BadMemoryAccessException;
import tp.pr5.mv.modulos.excepciones.FullStackException;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

public class Load extends OneParamInstruction {

	public Load(int parametro){
		this.param = parametro;
	}
	
	protected Instruction instanciate(int param){
		return new Load(param);
	}
	
	public String getMnemonic() {
		return "LOAD";
	}

	public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output) throws InstructionExecutionException{

		try{
			pilaOperandos.push(memoria.getRegisterValue(getParam()));
		}
		catch(BadMemoryAccessException e){
			throw new MemoryException(this.getInstructionRepresentation(), e);
		}
		catch(FullStackException e){
			throw new StackOperandException(this.getInstructionRepresentation(),e);
		}
	}
}
