package tp.pr5.mv.vistas.swing.Botones;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import tp.pr5.mv.modulos.controladores.ControladorSwing;

@SuppressWarnings("serial")
public abstract class CpuActionButton extends JButton{

	protected URL url;
	protected ControladorSwing control;
	
	// IMPORTANTE !! 
	// Esto es diferente en nuestra práctica entregada
	// los cambios son para comprobar si existe la imagen antes de intentar cargarla
	protected CpuActionButton(String name, String imagen, ControladorSwing control){
		this.control = control;
		this.url = (imagen != null)? this.getClass().getResource(imagen) : null;
			
		if(this.url != null)
			this.setIcon(new ImageIcon(url));
		else 
			this.setText(name);	
	}
}