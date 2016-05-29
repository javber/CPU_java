package tp.pr5.mv.vistas.swing.Paneles.CenterPanel;


import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import tp.pr5.mv.modulos.controladores.ControladorSwing;
import tp.pr5.mv.modulos.streams.GraphicInMethod;
import tp.pr5.mv.modulos.streams.GraphicOutMethod;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

@SuppressWarnings("serial")
public class PnlCpuIO extends JPanel{
	private ControladorSwing control;
	private PnlIOMethod pnIn;
	private PnlIOMethod pnOut;
	
	public PnlCpuIO(ControladorSwing control, InMethod input, OutMethod output){
		this.control=control;
		pnIn = new PnlIOMethod(control,"Entrada del programa-p");
		pnOut = new PnlIOMethod(control,"Salida del programa-p");
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(pnIn);
		this.add(pnOut);
		
		try {
			this.control.addInMethod(new GraphicInMethod(pnIn.getTextArea(), input));
			this.control.addOutMethod(new GraphicOutMethod(pnOut.getTextArea(), output));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
