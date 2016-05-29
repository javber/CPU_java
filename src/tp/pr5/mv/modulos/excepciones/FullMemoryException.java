package tp.pr5.mv.modulos.excepciones;

public class FullMemoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public FullMemoryException(){
		super("la memoria está llena");
	}
}
