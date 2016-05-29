package tp.pr5.mv.vistas.swing.Botones;


import java.awt.event.ActionListener;

import tp.pr5.mv.modulos.controladores.ControladorSwing;


public class BtnPause extends CpuActionButton{
    
	private static final long serialVersionUID = 1L;

	public BtnPause(ControladorSwing control,ActionListener listener) {
		super("Pause", "imagenes/pause.png", control);
		this.addActionListener(listener);
	}
}
