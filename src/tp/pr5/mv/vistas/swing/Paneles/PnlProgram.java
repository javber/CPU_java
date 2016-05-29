package tp.pr5.mv.vistas.swing.Paneles;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import tp.pr5.mv.eventos.listeners.CpuEventListener;
import tp.pr5.mv.modulos.controladores.ControladorSwing;

@SuppressWarnings("serial")
public class PnlProgram extends Panel implements CpuEventListener {
	
    private JTextArea text;
    private String textoPrograma;
    private String instrucciones[];
    private int currentInstruction;
    
	public PnlProgram(ControladorSwing control) {
		super(control,"Programa");
		textoPrograma = "";
		init();
	}
    
	public void init(){
		JScrollPane programScroll;
		// preferiría poner 0 como valor de columna, pero entonces muestra la barra de despl. horizontal 
		// por algún motivo... así que se queda como está...
		text = new JTextArea(50,18);
		text.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		text.setEditable(false);
		programScroll = new JScrollPane(text);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(programScroll);
		this.add(new JPanel());
		this.control.CpuAddListenerCpu(this);
	}
	
	private void setProgramText(){
		String instruccion;
		textoPrograma = "";
		for(int i = 0 ; i < this.instrucciones.length ; i++){
			if(i == this.currentInstruction)
				instruccion = "*".concat(String.format("%5d", i)).concat(": ").concat(this.instrucciones[i]); 
			else
				instruccion = String.format("%7d", i).concat(": ").concat(this.instrucciones[i]);
			textoPrograma = textoPrograma.concat(instruccion).concat("\n");
		}
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				text.setText(textoPrograma);
			}
		});
	}

	
	public void onHalt() {		
	}

	public void onStart(String[] programa) {
		this.currentInstruction = 0;
		this.instrucciones = programa;
		this.setProgramText();
	}

	public void onExecutionStart(String instructionRepresentation) {
	}

	public void onExecutionEnd(String memoryState, String stackState) {	
	}

	public void onError(Exception e) {		
	}

	public void onPcAdvance(int programCounter) {
		this.currentInstruction = programCounter;
		setProgramText();
	}
}
