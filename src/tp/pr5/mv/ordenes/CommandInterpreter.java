package tp.pr5.mv.ordenes;

import tp.pr5.mv.modulos.controladores.Controlador;

public interface CommandInterpreter {

	/**
	 * The returned String is the used to parse the CommandInterpreter
	 * by the CommandParser.
	 * 
	 * @return The mnemonic String representing the Command.
	 */
	public String getMnemonic();

	/**
	 * Executes the current CommandInterpreter in the specified Controller
	 * 
	 * @param Controller
	 */
	public void execute(Controlador control);

	/**
	 * Parses the specified String into a CommandInterpreter type object using as
	 * reference the String returned by "getMnemonic()", if they does not match, it
	 * returns a null value.
	 * 
	 * @param cadena
	 * 			The String to be parsed.
	 * @return The CommandInterpreter object represented by the String "cadena".
	 */
	public CommandInterpreter parse(String cadena[]);
	
	public void stopExecution();
}
