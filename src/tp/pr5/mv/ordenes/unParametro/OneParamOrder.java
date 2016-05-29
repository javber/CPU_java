package tp.pr5.mv.ordenes.unParametro;

import tp.pr5.mv.ordenes.CommandInterpreter;

public abstract class OneParamOrder implements CommandInterpreter {

	protected int param;

	/**
	 * @return the parameter of the Command.
	 */
	public int getParam() {
		return param;
	}

	public CommandInterpreter parse(String[] cadena) {
		CommandInterpreter orden = null;
		if (cadena.length == 2 && cadena[0].trim().equalsIgnoreCase(getMnemonic()) && cadena[1].matches("-?\\d+"))
			orden = instanciate(Integer.parseInt(cadena[1]));
		return orden;
	}
	
	abstract protected CommandInterpreter instanciate(int param);
}
