package tp.pr5.mv.instrucciones.excepciones;

public class HaltedCpuException extends InstructionExecutionException{

	private static final long serialVersionUID = 1L;

	public HaltedCpuException() {
		super("La CPU está apagada");
	}
	
}
