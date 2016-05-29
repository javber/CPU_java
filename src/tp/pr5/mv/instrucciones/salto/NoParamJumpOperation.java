package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.instrucciones.NoParamInstruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.excepciones.StackOperandException;
import tp.pr5.mv.modulos.Memory;
import tp.pr5.mv.modulos.OperandStack;
import tp.pr5.mv.modulos.ProgramCounter;
import tp.pr5.mv.modulos.excepciones.EmptyStackException;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

public abstract class NoParamJumpOperation extends NoParamInstruction {

	public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output) throws InstructionExecutionException{

		try{
			pc.setPc( nextPc(pilaOperandos.pop(), pc.getPc()) );
		}
		catch(EmptyStackException e){
			throw new StackOperandException(this.getInstructionRepresentation(), pilaOperandos.getNumElementos());
		}
	}

	protected abstract int nextPc(int cima, int pc);
}
