package tp.pr5.mv.parsers;

import tp.pr5.mv.ordenes.*;

;

public class CommandParser {

	private static CommandInterpreter colection[];

	/**
	 * Configures the CommandParser, receiving the Commands it will be
	 * able to parse
	 * 
	 * @param coleccion
	 * 				The Commands array
	 */
	public static void ParserConfig(CommandInterpreter coleccion[]) {
		colection = coleccion;
	}

	/**
	 * Parses a string into a CommandInterpreter type object, if the string 
	 * passed as parameter is not a valid instruction, it returns a null 
	 * pointer
	 * 
	 * @param cadena
	 *            The string to be parsed
	 * @return A CommandInterpreter type object representing the parsed string
	 */
	public static CommandInterpreter parse(String cadena) {
		CommandInterpreter orden = null;
		String cadSeparadas[] = cadena.trim().split(" ");
		int i = 0;

		while (i < colection.length && orden == null) {
			orden = colection[i].parse(cadSeparadas);
			i++;
		}
		return orden;
	}
}