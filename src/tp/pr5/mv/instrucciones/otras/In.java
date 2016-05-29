package tp.pr5.mv.instrucciones.otras;

import java.io.IOException;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.NoParamInstruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.excepciones.StackOperandException;
import tp.pr5.mv.modulos.Memory;
import tp.pr5.mv.modulos.OperandStack;
import tp.pr5.mv.modulos.ProgramCounter;
import tp.pr5.mv.modulos.excepciones.FullStackException;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

public class In extends NoParamInstruction{

	@Override
	public String getMnemonic() {
		return "IN";
	}

	public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output) throws InstructionExecutionException {
			try{
				pilaOperandos.push(input.read());
			}
			catch(FullStackException e){
				throw new StackOperandException(this.getInstructionRepresentation(),e);
			}
			catch(IOException e){
				//TODO agregar excepcion para controlar un posible error de lectura de IN
			}
	}

	protected Instruction instanciate() {
		return new In();
	}

}
