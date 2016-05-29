package tp.pr5.mv.instrucciones;

public abstract class OneParamInstruction implements Instruction {

	protected int param;

	public int getParam() {
		return param;
	}
	
	protected abstract Instruction instanciate(int param);

	public Instruction parse(String cadena[]) {
		Instruction instruccion = null;
		if (cadena.length == 2 && cadena[0].trim().equalsIgnoreCase(getMnemonic()) && cadena[1].matches("-?\\d+"))
			instruccion = instanciate(Integer.parseInt(cadena[1]));
		return instruccion;
	}

	public String getInstructionRepresentation() {
		return this.getMnemonic() + " " + this.getParam();
	}
}
