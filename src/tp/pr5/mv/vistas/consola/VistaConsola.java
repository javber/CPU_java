package tp.pr5.mv.vistas.consola;


import tp.pr5.mv.eventos.listeners.CpuEventListener;
import tp.pr5.mv.eventos.listeners.CpuMemoryEventListener;
import tp.pr5.mv.eventos.listeners.CpuStackEventListener;
import tp.pr5.mv.modulos.controladores.ControladorConsola;


/* COSAS IMPORTANTES
 * =========================
 * 
 * La vista debe enviar las peticiones al controlador, suponiendo que en
 * algún momento, el modelo le notificará, asique hay que programar su funcionamiento
 * teniendolo en cuenta, de modo que responda como se espera.
 * 	- La vista será oyente de la Cpu
 * 
 */


	// Esta clase tampoco parece gran cosa, pero abstrae lo que es una vista de consola y ahorra repetir
	// cierta funcionalidad...
public abstract class VistaConsola implements CpuEventListener, CpuMemoryEventListener, CpuStackEventListener{
    
	protected ControladorConsola controlador;
	
	
	public VistaConsola(ControladorConsola controlador){
		this.controlador = controlador;
		this.controlador.CpuAddListenerCpu(this);
		this.controlador.CpuAddListenerMemory(this);
		this.controlador.CpuAddListenerStack(this);
	}
}
