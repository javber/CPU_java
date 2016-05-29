package tp.pr5.mv.modulos.excepciones;

public class BadMemoryAccessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BadMemoryAccessException(int direccion){
		super("dirección incorrecta (" + direccion + ")");
	}

}
