package tp.pr5.mv.modulos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.mv.eventos.listeners.CpuEventListener;
import tp.pr5.mv.eventos.listeners.CpuMemoryEventListener;
import tp.pr5.mv.eventos.listeners.CpuStackEventListener;
import tp.pr5.mv.instrucciones.*;
import tp.pr5.mv.instrucciones.excepciones.HaltedCpuException;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.excepciones.NullInstructionException;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;


public class CPU {

	private ArrayList<CpuEventListener> listeners;
	private ProgramMV programa;
	private Memory memoria;
	private OperandStack pilaOperandos;
	private ProgramCounter pc;
	private OutMethod output;
	private InMethod input;
	final private int DEFAULT_MEMORY_DIM = 100;
	final private int DEFAULT_STACK_DIM = 30;

	
	/*-----------------------------------------------------------------------------*
	 *       Constructors                                                          *
	 *-----------------------------------------------------------------------------*/

	public CPU(InMethod entrada, OutMethod salida) {
		listeners = new ArrayList<CpuEventListener>();
		memoria = new Memory(DEFAULT_MEMORY_DIM);
		pilaOperandos = new OperandStack(DEFAULT_STACK_DIM);
		pc = new ProgramCounter();
		input = entrada;
		output = salida;
	}
	
	public CPU(int dimMemoria, int dimPila, InMethod entrada, OutMethod salida) {
		listeners = new ArrayList<CpuEventListener>();
		memoria = new Memory(dimMemoria);
		pilaOperandos = new OperandStack(dimPila);
		pc = new ProgramCounter();
		input = entrada;
		output = salida;
	}
	
	
	
	/*-----------------------------------------------------------------------------*
	 *       Events                                                                *
	 *-----------------------------------------------------------------------------*/
	
	public void addListener(CpuEventListener listener){
		if( !this.listeners.contains(listener) ){
			this.listeners.add(listener);	
		}
		pc.addCpuEventListener(listener);
	}

	public void addListener(CpuMemoryEventListener listener){
		memoria.addMemoryEventListener(listener);
	}
	
	public void addListener(CpuStackEventListener listener){
		pilaOperandos.addStackEventListener(listener);
	}
	
	public void removeListener(CpuEventListener listener){
		pc.removeCpuEventListener(listener);
		this.listeners.remove(listener);
	}
	
	public void removeListener(CpuMemoryEventListener listener){
		memoria.removeMemoryEventListener(listener);
	}
	
	public void removeListener(CpuStackEventListener listener){
		pilaOperandos.removeStackEventListener(listener);
	}
	
	private void notificarStartEvent(){
		Iterator<CpuEventListener> it = listeners.iterator();
		CpuEventListener oyente;
		
		while(it.hasNext()){
			oyente = it.next();
			oyente.onStart(programa.aStringArray());
		}
	}
	
	private void notificarExecutionStartEvent(String instructionRepresentation){
		Iterator<CpuEventListener> it = listeners.iterator();
		CpuEventListener oyente;
		
		while(it.hasNext()){
			oyente = it.next();
			oyente.onExecutionStart(instructionRepresentation);
		}
	}
	
	private void notificarExecutionEndEvent(){
		Iterator<CpuEventListener> it = listeners.iterator();
		CpuEventListener oyente;
		
		while(it.hasNext()){
			oyente = it.next();
			oyente.onExecutionEnd(memoria.state(), pilaOperandos.state());
		}
	}
	
	private void notificarError(Exception e){
		Iterator<CpuEventListener> it = listeners.iterator();
		CpuEventListener oyente;
		
		while(it.hasNext()){
			oyente = it.next();
			oyente.onError(e);
		}
	}
	
	
	/*-----------------------------------------------------------------------------*
	 *        Methods                                                              *
	 *-----------------------------------------------------------------------------*/
	
	
	public void start(ProgramMV programa){
		// si ya está encendida, no hace nada
		if(!pc.isHalted())
			return;
		this.pc.setHalted(false);
		this.loadProgram(programa);
		notificarStartEvent();
		if(programa.numInstrucciones()<= 0)
			this.halt();
	}
	
	
	/**
	 *  Executes the next instruction of the loaded program (which is referenced by 
	 *  the program counter) and increments the program counter.
	 *  
	 */
	public void step() throws InstructionExecutionException{
		Instruction instruccion = null;
		
		instruccion = programa.getInstruction(pc.getPc());
		this.executeInstruction(instruccion);
		pc.advancePc();
		
		// si no apunta a una instrucción válida, tipicamente, cuando programa ha terminado, se apaga
		if( programa.getInstruction(pc.getPc()) == null)
			this.halt();
	}

	/**
	 * @return The representation of the instruction referenced by the program counter
	 * (the next instruction to execute).
	 */
	public String nextInstructionRepresentation() {
		String representacion = "";
		Instruction instruccion = programa.getInstruction(pc.getPc());
		if (instruccion != null)
			representacion = instruccion.getInstructionRepresentation();
		return representacion;
	}

	/**
	 * Executes the specified instruction.
	 * 
	 * @param instruccion
	 * 					The Instruction to be executed.
	 * @return True if the execution was successful.
	 */
	public void executeInstruction(Instruction instruccion) throws InstructionExecutionException{
		if(this.pc.isHalted()){
			notificarError(new HaltedCpuException());
			throw new HaltedCpuException();
		}
		if(instruccion == null){
			notificarError(new NullInstructionException());
			throw new NullInstructionException();
		}
		
		notificarExecutionStartEvent(instruccion.getInstructionRepresentation());
		try{
			instruccion.execute(pilaOperandos, memoria, pc, input, output);
			notificarExecutionEndEvent();
		}catch(InstructionExecutionException e){
			notificarError(e);
			throw e;
		}
	}

	// despues de cargar el programa comprueba que la primera instrucción existe, si no es así
	// se apaga
	public void loadProgram(ProgramMV nuevoPrograma) {
		this.programa = nuevoPrograma;
		this.pc.resetProgram();
		if( programa.getInstruction(pc.getPc()) == null)
			this.halt();
	}
	
	public void addOutMethod(OutMethod newOutput){
		this.output = newOutput;
	}
	
	public void addInMethod(InMethod newOutput){
		this.input = newOutput;
	}

	public void closeIO() throws IOException{
		this.input.close();
		this.output.close();
	}
	
	// Despues de apagar la cpu, el usuario deberá, siempre, llamar a "closeIO()", esto parece que tiene más
	// sentido que lo hiciera directamente cpu... pero vale de momento.
	public void halt() {
		this.pc.setHalted(true);
	}

	public boolean isHalted(){
		return this.pc.isHalted();
	}
	
	/**
	 * Reset the loaded program
	 */
	public void resetProgram() {
		pc.resetProgram();
	}

	/**
	 * @return A string with the state of the cpu
	 */
	public String state() {
		String cadena = "";
		cadena = cadena.concat("Memoria: " + memoria.state() + "\n");
		cadena = cadena.concat("Pila de operandos: " + pilaOperandos.state());
		return cadena;
	}
	
	public String stackState(){
		return pilaOperandos.state();
	}
	
	public String memoryState(){
		return memoria.state();
	}
}
