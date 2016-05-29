package tp.pr5.mv.instrucciones.otras;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.NoParamInstruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.excepciones.StackOperandException;
import tp.pr5.mv.modulos.Memory;
import tp.pr5.mv.modulos.OperandStack;
import tp.pr5.mv.modulos.ProgramCounter;
import tp.pr5.mv.modulos.excepciones.EmptyStackException;
import tp.pr5.mv.modulos.excepciones.FullStackException;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

public class Dup extends NoParamInstruction {

	protected Instruction instanciate(){
		return new Dup();
	}
	
	public String getMnemonic() {
		return "DUP";
	}

	public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output) throws InstructionExecutionException{
		int cima;
		
		try{
			cima = pilaOperandos.pop();
			pilaOperandos.push(cima);
			pilaOperandos.push(cima);
		}
		catch(EmptyStackException e){
			throw new StackOperandException(this.getInstructionRepresentation(),e);
		}
		catch(FullStackException e){
			throw new StackOperandException(this.getInstructionRepresentation(),e);
		}
	}

}
