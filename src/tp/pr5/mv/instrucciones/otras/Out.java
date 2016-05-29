package tp.pr5.mv.instrucciones.otras;

import java.io.IOException;

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

public class Out extends NoParamInstruction {

	protected Instruction instanciate(){
		return new Out();
	}
	
	public String getMnemonic() {
		return "OUT";
	}

	public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output) throws InstructionExecutionException{
		if (pilaOperandos.getNumElementos() < 1)
			throw new StackOperandException(this.getInstructionRepresentation(), pilaOperandos.getNumElementos());
		try{
			output.write(pilaOperandos.pop());
		}
		catch(EmptyStackException | IOException e){
			// TODO agregar excepcion para posible error de escritura
		}
	}
}
