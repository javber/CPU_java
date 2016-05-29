package tp.pr5.mv.instrucciones.otras;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.NoParamInstruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.excepciones.StackOperandException;
import tp.pr5.mv.modulos.*;
import tp.pr5.mv.modulos.excepciones.EmptyStackException;
import tp.pr5.mv.modulos.excepciones.FullStackException;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

public class Flip extends NoParamInstruction{

	protected Instruction instanciate(){
		return new Flip();
	}
	
	public String getMnemonic() {
		return "FLIP";
	}

	public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output) throws InstructionExecutionException{
		
		if(pilaOperandos.getNumElementos() < 2)
			throw new StackOperandException(this.getInstructionRepresentation(), pilaOperandos.getNumElementos());
		
		int cima, subcima;
		try{
			cima = pilaOperandos.pop();
			subcima = pilaOperandos.pop();
			pilaOperandos.push(cima);
			pilaOperandos.push(subcima);
		}
		catch(EmptyStackException | FullStackException e){}
	}
}
