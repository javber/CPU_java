package tp.pr5.mv.vistas.swing.Paneles;

import java.awt.Color;
import java.awt.Panel;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import tp.pr5.mv.eventos.listeners.CpuEventListener;
import tp.pr5.mv.eventos.listeners.CpuMemoryEventListener;
import tp.pr5.mv.eventos.listeners.CpuStackEventListener;
import tp.pr5.mv.modulos.controladores.ControladorSwing;

@SuppressWarnings("serial")
public class PnlInformation extends Panel implements CpuEventListener,CpuStackEventListener,CpuMemoryEventListener{
   
	private int contInstrucciones;
	private ControladorSwing control;
	private JLabel labelMaquina;
	private JLabel labelNombre;
	private  JLabel labelContador;
	private JCheckBox checkMemoria;
	private  JCheckBox checkPila;
	
	
   public PnlInformation(ControladorSwing control){
	   this.control=control;
	   this.contInstrucciones = 0;
	   init();
   }
   
   void init(){
	   labelMaquina = new JLabel();
	   labelNombre= new JLabel("Num.Instrucciones ejecutadas: ");
	   labelContador = new JLabel(Integer.toString(contInstrucciones));
	   checkMemoria = new JCheckBox("Memoria modificada");
	   checkPila = new JCheckBox("Pila modificada");
	   this.add(labelMaquina);
	   this.add(labelNombre);
	   this.add(labelContador);
	   this.add(checkPila);
	   this.add(checkMemoria);
	   control.CpuAddListenerCpu(this);
	   control.CpuAddListenerMemory(this);
	   control.CpuAddListenerStack(this);
   }
   
   
	public void onHalt() {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				labelMaquina.setForeground(Color.red);
				labelMaquina.setText("Maquina parada");
			}
		});		
	}

	public void onPush(int value) {
		this.actualizarCheckPila(true);
	}

	public void onPop() {
		this.actualizarCheckPila(true);
	}

	public void onMemoryModify(int value, int direction) {
		this.actualizarCheckMemoria(true);	
	}

	public void onStart(String[] programa) {
		this.actualizarCheckMemoria(false);
		this.actualizarCheckPila(false);
	}

	public void onExecutionStart(String instructionRepresentation) {
		this.actualizarCheckMemoria(false);
		this.actualizarCheckPila(false);
	}

	public void onExecutionEnd(String memoryState, String stackState) {		
	}
	
	public void onPcAdvance(int programCounter) {
		contInstrucciones++;
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				labelContador.setText(Integer.toString(contInstrucciones));
			}
		});
	}

	public void onError(Exception e) {		
	}
	
	private void actualizarCheckMemoria(final boolean check){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				checkMemoria.setSelected(check);
			}
		});
	}
	
	private void actualizarCheckPila(final boolean check){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				checkPila.setSelected(check);
			}
		});
	}
}
