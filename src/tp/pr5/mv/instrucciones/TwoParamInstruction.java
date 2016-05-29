package tp.pr5.mv.instrucciones;


public abstract class TwoParamInstruction implements Instruction{

	protected int param1;
	protected int param2;
	
	public int getParam1(){
		return this.param1;
	}
	
	public int getParam2(){
		return this.param2;
	}
	
	protected abstract Instruction instanciate(int param1, int param2);

	public Instruction parse(String[] cadena) {
		Instruction instruccion = null;
		if (cadena.length == 3 && cadena[0].trim().equalsIgnoreCase(getMnemonic()) && 
				cadena[1].matches("-?\\d+") && cadena[2].matches("-?\\d+"))
			instruccion = instanciate(Integer.parseInt(cadena[1]), Integer.parseInt(cadena[2]));
		return instruccion;
	}
	
	public String getInstructionRepresentation() {
		return this.getMnemonic() + " " + this.getParam1() + this.getParam2();
	}
}
