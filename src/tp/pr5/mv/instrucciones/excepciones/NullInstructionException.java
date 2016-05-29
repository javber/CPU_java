package tp.pr5.mv.instrucciones.excepciones;

public class NullInstructionException extends InstructionExecutionException{

	private static final long serialVersionUID = 1L;

	public NullInstructionException() {
		super("Error: instucción vacía");
	}

}
