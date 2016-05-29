package tp.pr5.mv.instrucciones.excepciones;

import tp.pr5.mv.modulos.excepciones.BadMemoryAccessException;
import tp.pr5.mv.modulos.excepciones.FullMemoryException;

public class MemoryException extends InstructionExecutionException {

	private static final long serialVersionUID = 1L;

	public MemoryException(String instruccion, BadMemoryAccessException e){
		super("Error ejecutando " + instruccion + ": " + e.getMessage());
	}
	
	public MemoryException(String instruccion, FullMemoryException e){
		super("Error ejecutando " + instruccion + ": " + e.getMessage());
	}
}
