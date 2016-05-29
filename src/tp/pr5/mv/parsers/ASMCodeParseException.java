package tp.pr5.mv.parsers;

public class ASMCodeParseException extends Exception{

	private String texto;
	@SuppressWarnings("unused")
	private int linea; // Es útil llevarlo...
	
	public ASMCodeParseException(String text, int lane){
		texto = text;
		linea = lane;
	}
	
	public String getMessage(){
		return "Error en el programa. " + "Línea: " + texto;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1798723433658453789L;

	
}
