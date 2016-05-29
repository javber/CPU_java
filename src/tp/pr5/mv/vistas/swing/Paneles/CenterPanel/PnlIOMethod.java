package tp.pr5.mv.vistas.swing.Paneles.CenterPanel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
//import javax.swing.BoxLayout;
import javax.swing.JTextArea;

import tp.pr5.mv.modulos.controladores.ControladorSwing;
import tp.pr5.mv.vistas.swing.Paneles.Panel;

@SuppressWarnings("serial")
public class PnlIOMethod extends Panel{
   private JTextArea text;
   
	public PnlIOMethod(ControladorSwing control, String label) {
		super(control,label);
		init();
	}
    
	public void init(){
		text = new JTextArea(8,65);
		text.setEditable(false);
		text.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		this.setLayout(new GridLayout(1,1));
		this.add(text);
	}
	
	public JTextArea getTextArea(){
		return this.text;
		
	}

}
