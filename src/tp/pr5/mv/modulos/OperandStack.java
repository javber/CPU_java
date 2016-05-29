package tp.pr5.mv.modulos;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.mv.eventos.listeners.CpuStackEventListener;
import tp.pr5.mv.modulos.excepciones.EmptyStackException;
import tp.pr5.mv.modulos.excepciones.FullStackException;

public class OperandStack {

	private ArrayList<CpuStackEventListener> listeners;
	private int pila[];
	private int cima;

	
	/*-----------------------------------------------------------------------------*
	 *       Constructors                                                          *
	 *-----------------------------------------------------------------------------*/

	public OperandStack(int dimension) {
		pila = new int[dimension];
		cima = -1;
		this.listeners = new ArrayList<CpuStackEventListener>();
	}
	
	
	/*-----------------------------------------------------------------------------*
	 *       Events                                                                *
	 *-----------------------------------------------------------------------------*/
	
	public void addStackEventListener(CpuStackEventListener e){
		if( !listeners.contains(e) )
			listeners.add(e);
	}
	
	
	public void removeStackEventListener(CpuStackEventListener e){
		listeners.remove(e);
	}
	
	
	private void notificarPushEvent(int elementValue){
		Iterator<CpuStackEventListener> it = listeners.iterator();
		CpuStackEventListener elemento;
		
		while(it.hasNext()){
			elemento = it.next();
			elemento.onPush(elementValue);
		}
	}
	
	private void notificarPopEvent(){
		Iterator<CpuStackEventListener> it = listeners.iterator();
		CpuStackEventListener elemento;
		while(it.hasNext()){
			elemento = it.next();
			elemento.onPop();
		}
	}
	
	/*-----------------------------------------------------------------------------*
	 *       Methods                                                               *
	 *-----------------------------------------------------------------------------*/
	
	/**
	 * @return true whenever the stack is empty and false when not
	 */
	/*
	public boolean isEmpty() {
		return cima < 0;
	}
	*/

	/**
	 * @return the top value of the stack
	 */
	/*
	public int getTop() throws EmptyStackException{
		try{
			return pila[cima];
		}
		catch(ArrayIndexOutOfBoundsException e){
			throw new EmptyStackException();	//por el propio funcionamiento, solo estará fuera por debajo
		}
	}
	*/
	
	
	/**
	 * 
	 */
	public int getNumElementos(){
		return cima+1;
	}
	
	/**
	 * Adds a value in the top of the stack
	 * 
	 * @param n
	 *            the value to be pushed
	 * @return true whenever it finished in success and false when not
	 */
	public void push(int n) throws FullStackException{
		if( cima == pila.length - 1)
			throw new FullStackException();
		pila[++cima] = n;
		notificarPushEvent(n);
	}

	/**
	 * Eliminates the top value of the stack
	 * 
	 * @return true whenever it finished in success and false when not
	 */
	public int pop() throws EmptyStackException{
		int valor;
		if (cima < 0)
			throw new EmptyStackException();
		valor = pila[cima--];
		notificarPopEvent();
		return valor;
	}

	// Parece que el metodo String.concat es de las formas mas eficientes de
	// concatenar
	public String state() {
		String cadena = "";
		
		if(cima < 0)
			return null;
		
		for (int i = 0; i <= cima; i++)
			cadena = cadena.concat(Integer.toString(pila[i]) + " ");

		return cadena;
	}
}
