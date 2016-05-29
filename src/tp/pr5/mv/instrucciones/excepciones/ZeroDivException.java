package tp.pr5.mv.instrucciones.excepciones;

public class ZeroDivException extends InstructionExecutionException{

	private static final long serialVersionUID = 1L;

	public ZeroDivException(){
		super("División por cero");
	}
}
