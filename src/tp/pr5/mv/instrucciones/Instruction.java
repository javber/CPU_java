package tp.pr5.mv.instrucciones;

import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.modulos.*;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

public interface Instruction {

	abstract public String getMnemonic();

	abstract public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output) throws InstructionExecutionException;

	abstract public String getInstructionRepresentation();

	abstract public Instruction parse(String cadena[]);
}

/*
	ZeroDivException
		-> División por cero
		
	StackOperandException
		-> Faltan operandos en la pila
	
	MemoryException
		-> Dirección de memoria no válida(negativa, memoria llena...)

*/