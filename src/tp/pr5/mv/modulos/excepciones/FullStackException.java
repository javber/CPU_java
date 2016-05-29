package tp.pr5.mv.modulos.excepciones;

public class FullStackException extends Exception{

	private static final long serialVersionUID = 1L;

	public FullStackException(){
		super("Error de escritura: la pila está llena");
	}

}
