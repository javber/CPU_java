package tp.pr5.mv.vistas.consola;

import java.util.Scanner;

import tp.pr5.mv.modulos.controladores.ControladorConsola;
import tp.pr5.mv.ordenes.CommandInterpreter;
import tp.pr5.mv.parsers.CommandParser;

public class VistaConsolaInteractive extends VistaConsola{
	
	private boolean cpuNotifiedHalt;
	private CommandInterpreter comandoActual;
	
	public VistaConsolaInteractive(ControladorConsola controlador){
		super(controlador);
		this.cpuNotifiedHalt = false;
	}

	public void runInteractive(){
		Scanner entrada;
		String linea;
		entrada = new Scanner(System.in);
		
		while ( !cpuNotifiedHalt ){
			System.out.print("> ");
			linea = entrada.nextLine();
			comandoActual = CommandParser.parse(linea);
			if (comandoActual != null)
				comandoActual.execute(controlador);
			else 
				System.out.println("no te entiendo.");
		}

		controlador.closeIO();
		entrada.close();
	}
	
	private void mostrarPrograma(String[] programa){
		System.out.println("El programa introducido es:");
		for(int i = 0 ; i < programa.length ; i++)
			System.out.println(i + ": " + programa[i]);
	}
	
	
	/*-----------------------------------------------------------------------------*
	 *       Events                                                                *
	 *-----------------------------------------------------------------------------*/
	
	public void onStart(String[] programa) {
		this.mostrarPrograma(programa);
		this.runInteractive();
	}
	
	public void onHalt() {
		this.cpuNotifiedHalt = true;
		if(comandoActual != null)
			comandoActual.stopExecution();
	}

	public void onExecutionStart(String InstructionRepresentation) {
		System.out.println("Comienza la ejecucion de " + InstructionRepresentation);
	}

	public void onExecutionEnd(String memoryState, String stackState) {	
		System.out.println("El estado de la maquina tras ejecutar la instruccion es:");
		System.out.println("Memoria: " + ( memoryState != null ? memoryState : "<vacía>") );
		System.out.println("Pila de operandos: " + ( stackState != null ? stackState : "<vacía>") );
	}

	public void onError(Exception e) {
		System.err.println(e.getMessage());
		if(comandoActual != null)
			comandoActual.stopExecution();
	}

	public void onMemoryModify(int value, int direction) {
	}

	public void onPush(int value) {		
	}

	public void onPop() {
	}

	public void onPcAdvance(int programCounter) {	
	}
}
