package tp.pr5.mv.parsers;

import tp.pr5.mv.instrucciones.Instruction;

public class InstructionParser {

	private static Instruction colection[];

	/**
	 * Configures the InstructionParser, receiving the Instructions it will be
	 * able to parse.
	 * 
	 * @param coleccion
	 * 				The Instructions array.
	 */
	public static void ParserConfig(Instruction coleccion[]) {
		colection = coleccion;
	}

	/**
	 * Parses a string into an Instruction type object, if the string passed as
	 * parameter is not a valid instruction, it returns a null pointer.
	 * 
	 * @param cadena
	 *            The string to be parsed.
	 * @return An Instruction type object representing the parsed string.
	 */
	public static Instruction parse(String cadena) {
		Instruction instruccion = null;
		String cadSeparadas[] = cadena.trim().split(" ");
		int i = 0;

		while (i < colection.length && instruccion == null) {
			instruccion = colection[i].parse(cadSeparadas);
			i++;
		}

		return instruccion;
	}
}
