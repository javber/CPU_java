package tp.pr5.mv.instrucciones.excepciones;

import tp.pr5.mv.modulos.excepciones.EmptyStackException;
import tp.pr5.mv.modulos.excepciones.FullStackException;

public class StackOperandException extends InstructionExecutionException {

	private static final long serialVersionUID = 1L;

	public StackOperandException(String instruccion, int operandos){
		super("Error ejecutando " + instruccion + ": faltan operandos en la pila (Hay " + operandos + ")");
	}
	
	public StackOperandException(String instruccion, FullStackException excepcion){
		super("Error ejecutando " + instruccion + ": " + excepcion.getMessage());
	}
	
	public StackOperandException(String instruccion, EmptyStackException excepcion){
		super("Error ejecutando " + instruccion + ": faltan operandos en la pila (Hay 0)");
	}
}
