package tp.pr5.mv.vistas.swing.Botones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tp.pr5.mv.modulos.controladores.ControladorSwing;

@SuppressWarnings("serial")
public class BtnWrite extends CpuActionButton implements ActionListener{
    
	private JTextField txtValor;
	private JTextField txtPosicion;
	
	public BtnWrite(ControladorSwing control,JTextField txtValor,JTextField txtPosicion) {
		super("Write", null, control);
		this.txtValor = txtValor;
		this.txtPosicion = txtPosicion;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		int pos, valor;
		try {
			pos = Integer.parseInt(this.txtPosicion.getText());
			valor = Integer.parseInt(this.txtValor.getText());
			control.executeWrite(valor,pos);
		}
		catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null,"Los campos posición y valor deben ser numéricos");
		}
	}

}
