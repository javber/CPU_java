package tp.pr5.mv.vistas.swing.Botones;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tp.pr5.mv.modulos.controladores.ControladorSwing;

@SuppressWarnings("serial")
public class BtnPush extends CpuActionButton implements ActionListener{
   
	private JTextField area;
	
	public BtnPush(ControladorSwing control, JTextField area) {
		super("Push", null, control);
		this.area = area;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		int valor;
		try {
			valor = Integer.parseInt(this.area.getText());
			control.executePush(valor);
		}
		catch (NumberFormatException e){
			if(area.getText().length() == 0)
				JOptionPane.showMessageDialog(null, "El campo valor no puede estar vacío", "Cuidado!", JOptionPane.WARNING_MESSAGE );
			else
				JOptionPane.showMessageDialog(null, "El valor debe ser numérico.", "Cuidado!", JOptionPane.WARNING_MESSAGE );
		}
	}
}
