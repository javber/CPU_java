package tp.pr5.mv.vistas.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr5.mv.eventos.listeners.CpuEventListener;
import tp.pr5.mv.modulos.controladores.ControladorSwing;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;
import tp.pr5.mv.vistas.swing.Paneles.PnlAction;
import tp.pr5.mv.vistas.swing.Paneles.PnlInformation;
import tp.pr5.mv.vistas.swing.Paneles.PnlProgram;
import tp.pr5.mv.vistas.swing.Paneles.CenterPanel.PnlCenter;

@SuppressWarnings("serial")
public class VistaSwing extends JFrame implements WindowListener, ActionListener, CpuEventListener{

    private PnlAction botones;
	private PnlProgram program;
    private PnlCenter panelcentral;
    private ControladorSwing control;
    private PnlInformation panelInformation;
    
	public VistaSwing(ControladorSwing control, InMethod input, OutMethod output){
		super("Maquina virtual de TP");
		this.control = control;
		 // Antes de crear el resto de componentes, debo establecer la vista principal
		 // como listener, para que sea el primero en ser notificado en "onStart()", ya que
		 // es el proceso que pone la vista visible, si no es así, habrá problema con los
		 // componentes que intenten actualizarse...
		this.addWindowListener(this);
		this.control.CpuAddListenerCpu(this);
		
        botones = new PnlAction(control, this);
        program = new PnlProgram(control);
        panelcentral = new PnlCenter(control, input, output);
        panelInformation = new PnlInformation(control);
        
        this.add(botones,BorderLayout.NORTH);
    	this.add(program,BorderLayout.WEST);
    	this.add(panelcentral,BorderLayout.CENTER);
    	this.add(panelInformation,BorderLayout.SOUTH);
		this.setLocation(240, 70);
		this.setSize(1000,700);
	}
	
	

	public void windowActivated(WindowEvent arg0) {	
	}

	public void windowClosed(WindowEvent arg0) {
		this.control.halt();
		this.control.closeIO();
	}

	public void windowClosing(WindowEvent arg0) {
		int eleccion = JOptionPane.showConfirmDialog(this, "¿Seguro que desea cerrar la aplicación?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(eleccion == JOptionPane.NO_OPTION)
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		else
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}
	
	public void windowIconified(WindowEvent arg0) {
	}
	
	public void windowOpened(WindowEvent arg0) {
	}

	public void actionPerformed(ActionEvent arg0) {
		int eleccion = JOptionPane.showConfirmDialog(this, "¿Seguro que desea cerrar la aplicación?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(eleccion == JOptionPane.YES_OPTION){
			this.dispose();
		}
	}

	public void onStart(String[] programa) {
		this.setVisible(true);
	}

	public void onHalt() {
	}

	public void onExecutionStart(String instructionRepresentation) {		
	}

	public void onExecutionEnd(String memoryState, String stackState) {		
	}

	public void onError(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void onPcAdvance(int programCounter) {
	}
}
