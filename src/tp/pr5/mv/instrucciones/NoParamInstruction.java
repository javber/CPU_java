package tp.pr5.mv.instrucciones;

public abstract class NoParamInstruction implements Instruction {

	protected abstract Instruction instanciate();
	
	public Instruction parse(String cadena[]) {
		Instruction instruccion = null;
		if (cadena.length == 1 && cadena[0].trim().equalsIgnoreCase(getMnemonic()))
				instruccion = instanciate();
		return instruccion;
	}

	public String getInstructionRepresentation() {
		return this.getMnemonic();
	}
}
