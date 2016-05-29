package tp.pr5.mv.vistas.consola;


import tp.pr5.mv.modulos.controladores.ControladorConsola;

public class VistaConsolaBatch extends VistaConsola{

	private boolean cpuNotifiedHalt;
	private boolean cpuNotifiedError;
	
	public VistaConsolaBatch(ControladorConsola controlador) {
		super(controlador);
		this.cpuNotifiedHalt = false;
		this.cpuNotifiedError = false;
	}
	
	private void runBatch(){
		while( !cpuNotifiedHalt && !cpuNotifiedError){
			controlador.CpuStep();
		}
		controlador.closeIO();
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
		this.runBatch();
	}
	
	public void onHalt() {
		this.cpuNotifiedHalt = true;
	}

	public void onExecutionStart(String instructionRepresentation) {
		// En batch no se muestra nada
	}

	public void onExecutionEnd(String memoryState, String stackState) {
		//en batch no se muestra nada
	}

	public void onError(Exception e) {
		System.err.println(e.getMessage());
		this.cpuNotifiedError = true;
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
