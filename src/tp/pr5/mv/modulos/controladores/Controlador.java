package tp.pr5.mv.modulos.controladores;

import java.io.IOException;

import tp.pr5.mv.eventos.listeners.CpuEventListener;
import tp.pr5.mv.eventos.listeners.CpuMemoryEventListener;
import tp.pr5.mv.eventos.listeners.CpuStackEventListener;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.instrucciones.otras.Pop;
import tp.pr5.mv.instrucciones.otras.Push;
import tp.pr5.mv.instrucciones.otras.Write;
import tp.pr5.mv.modulos.CPU;
import tp.pr5.mv.modulos.ProgramMV;


/* COSAS IMPORTANTES
 * =========================
 * 
 * El controlador no debe hablar jamás con quién le llame, esto es:
 * 	- Sus métodos no deben devolver valores
 *  - No debe propagar excepciones
 */


public abstract class Controlador {
	protected CPU cpu;
	
	
	public Controlador(CPU cpu){		   
		this.cpu=cpu;
	}
	   
	public void halt(){
		this.cpu.halt();   
	}
	
	public void start(ProgramMV programa){
		this.cpu.start(programa);
	}
	   
	public void CpuLoadProgram(ProgramMV program){
		cpu.loadProgram(program);	      
	}
	   
	public void CpuStep(){
		try {
			this.cpu.step();
		} catch (InstructionExecutionException e) {}
			// Parece que es una mala práctica recoger excepciones para no hacer nada...
			// pero supongo que en este caso queda justificado por la naturaleza de la
			// clase
	}
	  
	public void closeIO(){
		try {
			this.cpu.closeIO();
		}catch (IOException e) {}
	}

	public void executePop(){
		try {
			cpu.executeInstruction(new Pop());
		}catch (InstructionExecutionException e) {}
	}
	   
	public void executePush(int valor){
		try{
			cpu.executeInstruction(new Push(valor));
		}catch(InstructionExecutionException e) {}
	}
	   
	public void executeWrite(int valor,int pos){
		try{
			cpu.executeInstruction(new Write(pos, valor));
		}catch(InstructionExecutionException e) {}
	}
	   
	public void resetProgram(){
		cpu.resetProgram();
	}
	
	public void CpuAddListenerCpu(CpuEventListener listener){
		this.cpu.addListener(listener);
	}

	public void CpuAddListenerMemory(CpuMemoryEventListener listener){
		this.cpu.addListener(listener);
	}
	
	public void CpuAddListenerStack(CpuStackEventListener listener){
		this.cpu.addListener(listener);
	}
	
	public void CpuRemoveListenerCpu(CpuEventListener listener){
		this.cpu.removeListener(listener);
	}

	public void CpuRemoveListenerMemory(CpuMemoryEventListener listener){
		this.cpu.removeListener(listener);
	}
	
	public void CpuRemoveListenerStack(CpuStackEventListener listener){
		this.cpu.removeListener(listener);
	}
	

}
