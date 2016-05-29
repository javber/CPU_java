package tp.pr5.mv.modulos;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.mv.eventos.listeners.CpuEventListener;

public class ProgramCounter {

	ArrayList<CpuEventListener> listeners;
	private int pc;
	private int nextPc;
	private boolean halted;

	
	/*-----------------------------------------------------------------------------*
	 *       Constructors                                                          *
	 *-----------------------------------------------------------------------------*/
	
	public ProgramCounter(){
		resetProgram();
		this.halted = true;
		this.listeners = new ArrayList<CpuEventListener>();
	}
	
	public void addCpuEventListener(CpuEventListener e){
		if (!listeners.contains(e))
			listeners.add(e);
	}
	
	public void removeCpuEventListener(CpuEventListener e){
		listeners.remove(e);
	}
	
	
	/*-----------------------------------------------------------------------------*
	 *       Events                                                                *
	 *-----------------------------------------------------------------------------*/
	
	private void notificarPcAdvance(int currentPC){
		Iterator<CpuEventListener> it = listeners.iterator();
		CpuEventListener elemento;
		
		while(it.hasNext()){
			elemento = it.next();
			elemento.onPcAdvance(currentPC);
		}
	}

	private void notificarCpuHalt(){
		Iterator<CpuEventListener> it = listeners.iterator();
		CpuEventListener elemento;
		
		while(it.hasNext()){
			elemento = it.next();
			elemento.onHalt();
		}
	}
	
	
	/*-----------------------------------------------------------------------------*
	 *       Methods                                                               *
	 *-----------------------------------------------------------------------------*/
	public int getPc() {
		return pc;
	}

	public void setPc(int newPc) {
		this.nextPc = newPc;
	}

	public void addToPc(int jump) {
		this.nextPc = this.pc + jump;
	}
	
	public void advancePc(){
		this.pc = this.nextPc;
		this.nextPc = this.pc+1;
		this.notificarPcAdvance(this.pc);
	}

	public boolean isHalted() {
		return this.halted;
	}

	public void setHalted(boolean h) {
		this.halted = h;
		if(halted)
			this.notificarCpuHalt();
	}

	public void resetProgram() {
		this.pc = 0;
		this.nextPc = this.pc+1;
	}
}
