package tp.pr5.mv.vistas.swing.Botones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tp.pr5.mv.modulos.controladores.ControladorSwing;

@SuppressWarnings("serial")
public class BtnPop extends CpuActionButton implements ActionListener{

	public BtnPop(ControladorSwing control) {
		super("Pop", null, control);
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		control.executePop();
	}

}
