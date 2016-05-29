package tp.pr5.mv.instrucciones.operacionesBinarias;

import tp.pr5.mv.instrucciones.*;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.excepciones.StackOperandException;
import tp.pr5.mv.modulos.Memory;
import tp.pr5.mv.modulos.OperandStack;
import tp.pr5.mv.modulos.ProgramCounter;
import tp.pr5.mv.modulos.excepciones.EmptyStackException;
import tp.pr5.mv.modulos.excepciones.FullStackException;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

/**
 * Abstract Class of an arithmetic instruction. extracts 2 operands from stack
 * and executes de operations overrided in
 * "instOperation(int valor1, int valor2, OperandStack pilaOperandos)"
 * 
 */
public abstract class NoParamBinaryOperation extends NoParamInstruction
		implements Cloneable, Instruction {
	
	public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output) throws InstructionExecutionException{
		int cima, subcima;
		if (pilaOperandos.getNumElementos() < 2)
			throw new StackOperandException(this.getInstructionRepresentation(), pilaOperandos.getNumElementos());
		
		try{
			cima = pilaOperandos.pop();
			subcima = pilaOperandos.pop();
			pilaOperandos.push(instOperation(cima, subcima));
		}
		catch(EmptyStackException e){
			throw new StackOperandException(this.getInstructionRepresentation(),e);
		}
		catch(FullStackException e){
			throw new StackOperandException(this.getInstructionRepresentation(),e);
		}
	}

	protected abstract int instOperation(int cima, int subcima) throws InstructionExecutionException;
}
