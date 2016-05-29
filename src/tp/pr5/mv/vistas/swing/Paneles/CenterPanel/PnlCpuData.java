package tp.pr5.mv.vistas.swing.Paneles.CenterPanel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import tp.pr5.mv.modulos.controladores.ControladorSwing;

@SuppressWarnings("serial")
public class PnlCpuData extends JPanel{
    
	private PnlStack pnStack;
	private PnlMemory pnMemory;
	private ControladorSwing control;
	public PnlCpuData(ControladorSwing control){
		this.control=control;
		init();
	}
	
	public void init(){
		this.setLayout(new GridLayout(1,2));
		pnStack = new PnlStack(control);
		pnMemory = new PnlMemory(control);
		this.add(pnStack);
		this.add(pnMemory);
	}
}
