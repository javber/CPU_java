package tp.pr5.mv.vistas.swing.Paneles.CenterPanel;


import java.awt.GridLayout;

import javax.swing.JPanel;

import tp.pr5.mv.modulos.controladores.ControladorSwing;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;


@SuppressWarnings("serial")
public class PnlCenter extends JPanel{
    
	@SuppressWarnings("unused")
	private ControladorSwing control;
	private PnlCpuIO pnIO;
	private PnlCpuData pnData;
	public PnlCenter(ControladorSwing control, InMethod input, OutMethod output){
		this.control = control;
		pnIO = new PnlCpuIO(control, input, output);
		pnData = new PnlCpuData(control);
		this.setLayout(new GridLayout(2,1));
		this.add(pnData);
		this.add(pnIO);
	}
}
