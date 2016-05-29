package tp.pr5.mv.instrucciones.otras;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.NoParamInstruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.excepciones.MemoryException;
import tp.pr5.mv.instrucciones.excepciones.StackOperandException;
import tp.pr5.mv.modulos.Memory;
import tp.pr5.mv.modulos.OperandStack;
import tp.pr5.mv.modulos.ProgramCounter;
import tp.pr5.mv.modulos.excepciones.FullMemoryException;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

public class StoreInd extends NoParamInstruction{

	public String getMnemonic() {
		return "STOREIND";
	}

	public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output)
			throws InstructionExecutionException {
		int valor, direccion;
		if(pilaOperandos.getNumElementos() < 2)
			throw new StackOperandException(this.getInstructionRepresentation(), pilaOperandos.getNumElementos());
		try{
			valor = pilaOperandos.pop();
			direccion = pilaOperandos.pop();
			memoria.setRegister(valor, direccion);
		}
		catch(FullMemoryException e){
			throw new MemoryException(this.getInstructionRepresentation(),e);
		}
		catch(Exception e){}
		
	}

	protected Instruction instanciate() {
		return new StoreInd();
	}

}
