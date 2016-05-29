package tp.pr5.mv.ordenes.sinParametros;

import tp.pr5.mv.ordenes.CommandInterpreter;

public abstract class NoParamOrder implements CommandInterpreter {
	
	public CommandInterpreter parse(String[] cadena) {
		CommandInterpreter orden = null;
		if (cadena.length == 1 && cadena[0].trim().equalsIgnoreCase(getMnemonic()))
				orden = instanciate();
		return orden;
	}
	
	abstract protected CommandInterpreter instanciate();
}
