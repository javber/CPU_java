package tp.pr5.mv.vistas.swing.Botones;

import tp.pr5.mv.modulos.controladores.ControladorSwing;


public class BtnRun extends CpuActionButton{
	
	private static final long serialVersionUID = 1L;
	public BtnRun(ControladorSwing control) {
		super("run", "imagenes/run.png", control);
	}
}
