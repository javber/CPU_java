package tp.pr5.mv.instrucciones.excepciones;

public abstract class InstructionExecutionException extends Exception{

	public InstructionExecutionException(String mensaje){
		super(mensaje);
	}
	
	private static final long serialVersionUID = 1L;
}
