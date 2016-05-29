package tp.pr5.mv.vistas.swing.Paneles;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import tp.pr5.mv.modulos.controladores.ControladorSwing;

@SuppressWarnings("serial")
public abstract class Panel extends JPanel{

   protected ControladorSwing control;
   
   protected Panel(ControladorSwing control,String label){
      this.control=control;
      this.setBorder(BorderFactory.createTitledBorder(label));	  
   }

}