package tp.pr5.mv.modulos.controladores;


import tp.pr5.mv.modulos.CPU;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethodDecorator;

public class ControladorSwing extends Controlador{
   
   public ControladorSwing(CPU cpu){
	   super(cpu);  
   }

   public void addOutMethod(OutMethodDecorator newOutput){
	   cpu.addOutMethod(newOutput);   
   }
   
   public void addInMethod(InMethod newOutput){
	   cpu.addInMethod(newOutput);
   }	
}
