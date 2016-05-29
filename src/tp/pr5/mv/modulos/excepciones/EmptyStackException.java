package tp.pr5.mv.modulos.excepciones;

public class EmptyStackException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public EmptyStackException(){
		super("Error de acceso: la pila está vacía");
	}

}
