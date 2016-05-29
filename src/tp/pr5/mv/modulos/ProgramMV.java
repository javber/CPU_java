package tp.pr5.mv.modulos;

import tp.pr5.mv.instrucciones.*;

public class ProgramMV {
	private Instruction instrucciones[];
	private int contador;
	private final int DEFAULT_PROGRAM_DIM = 100;

	/*-----------------------------------------------------------------------------*
	 *       Constructors                                                          *
	 *-----------------------------------------------------------------------------*/
	
	public ProgramMV(){
		this.instrucciones = new Instruction[DEFAULT_PROGRAM_DIM];
		this.contador = 0;
	}
	
	public ProgramMV(int dimension) {
		this.instrucciones = new Instruction[dimension];
		this.contador = 0;
	}

	
	/*-----------------------------------------------------------------------------*
	 *       Methods                                                               *
	 *-----------------------------------------------------------------------------*/
	
	public int numInstrucciones() {
		return contador;
	}

	public Instruction getInstruction(int posicion) {
		Instruction instruccion = null;
		if (posicion >= 0 && posicion <= instrucciones.length)
			instruccion = instrucciones[posicion];
		return instruccion;
	}

	public void agregarInstruccion(Instruction instruccion) {
		this.instrucciones[contador] = instruccion;
		contador++;
	}
	
	public String[] aStringArray(){
		String cadena[] = new String[this.contador];
		for (int i = 0; i < contador; i++)
			cadena[i] = instrucciones[i].getInstructionRepresentation();
		return cadena;
	}
}
