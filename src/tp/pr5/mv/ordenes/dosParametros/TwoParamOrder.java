package tp.pr5.mv.ordenes.dosParametros;

import tp.pr5.mv.ordenes.CommandInterpreter;

public abstract class TwoParamOrder implements CommandInterpreter{

	protected int param1;
	protected int param2;

	/**
	 * @return the first parameter of the Command.
	 */
	public int getParam1() {
		return param1;
	}

	/**
	 * @return the second parameter of the Command.
	 */
	public int getParam2() {
		return param2;
	}
	
	public CommandInterpreter parse(String[] cadena) {
		CommandInterpreter orden = null;
		if (cadena.length == 3 && cadena[0].trim().equalsIgnoreCase(getMnemonic()) && cadena[1].matches("-?\\d+") && cadena[2].matches("-?\\d+"))
				orden = instanciate(Integer.parseInt(cadena[1]),Integer.parseInt(cadena[2]));
		return orden;
	}

	abstract protected CommandInterpreter instanciate(int param1, int param2);
}
