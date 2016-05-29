package tp.pr5.mv.vistas.swing.Paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import tp.pr5.mv.eventos.listeners.CpuEventListener;
import tp.pr5.mv.modulos.controladores.ControladorSwing;
import tp.pr5.mv.vistas.swing.Botones.BtnExit;
import tp.pr5.mv.vistas.swing.Botones.BtnPause;
import tp.pr5.mv.vistas.swing.Botones.BtnRun;
import tp.pr5.mv.vistas.swing.Botones.BtnStep;

@SuppressWarnings("serial")
public class PnlAction extends Panel implements ActionListener, CpuEventListener{
    
    private BtnStep step;
	private BtnRun run;
	private BtnExit exit;
	private BtnPause pause;
    private Thread worker;
    
	public PnlAction(ControladorSwing control, ActionListener exitListener){
        super(control,"Acciones");
        step = new BtnStep(control);
        run =  new BtnRun(control);
        exit = new BtnExit(control);
        pause = new BtnPause(control,this);
 
        step.setEnabled(false);
        run.setEnabled(false);
        pause.setEnabled(true);
        exit.setEnabled(true);
        
		this.add(step);	
		this.add(run);
		this.add(pause);
		this.add(exit);
		
		step.addActionListener(this);
		run.addActionListener(this);
		pause.addActionListener(this);
		exit.addActionListener(exitListener);
		control.CpuAddListenerCpu(this);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(run)){
			run.setEnabled(false);
			step.setEnabled(false);
			pause.setEnabled(true);
			worker = new Thread(new Runnable(){
				public void run() {
					try{
						while(!Thread.interrupted()){
							control.CpuStep();
							Thread.sleep(100);
						}
					}catch(InterruptedException e){}
				}
			});
			worker.start();
		}
		else if(e.getSource().equals(pause)){
			if(worker != null && worker.isAlive()){
				worker.interrupt();				
			}
			run.setEnabled(true);
			step.setEnabled(true);
			pause.setEnabled(false);
		}
		else if(e.getSource().equals(step))
			control.CpuStep();
	}

	public void onStart(String[] programa) {
		SwingUtilities.invokeLater( new Runnable(){
			public void run() {
				run.setEnabled(true);
				step.setEnabled(true);
				pause.setEnabled(false);
			}
		});
	}

	public void onHalt() {
		if( worker != null && worker.isAlive()){
			worker.interrupt();
		}
		SwingUtilities.invokeLater( new Runnable(){
			public void run() {
				run.setEnabled(false);
				step.setEnabled(false);
				pause.setEnabled(false);
			}
		});
	}

	public void onError(Exception e) {
		if( worker != null && worker.isAlive()){
			worker.interrupt();
			SwingUtilities.invokeLater( new Runnable(){
				public void run() {
					run.setEnabled(true);
					step.setEnabled(true);
					pause.setEnabled(false);
				}
			});
		}
	}

	public void onPcAdvance(int programCounter) {
	}

	public void onExecutionStart(String instructionRepresentation) {
	}

	public void onExecutionEnd(String memoryState, String stackState) {
	}
}