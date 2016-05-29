package tp.pr5.mv.modulos;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.mv.eventos.listeners.CpuMemoryEventListener;
import tp.pr5.mv.modulos.excepciones.BadMemoryAccessException;
import tp.pr5.mv.modulos.excepciones.FullMemoryException;

public class Memory {

	private ArrayList<CpuMemoryEventListener> listeners;
	private DataMemoryRegister registers[]; // array de registros ordenados por
											// dirección
	private int counter;

	/*-----------------------------------------------------------------------------*
	 *       Constructors                                                          *
	 *-----------------------------------------------------------------------------*/

	public Memory(int dimension) {
		this.registers = new DataMemoryRegister[dimension];
		this.counter = 0;
		listeners = new ArrayList<CpuMemoryEventListener>();
	}
	
	
	/*-----------------------------------------------------------------------------*
	 *       Events                                                                *
	 *-----------------------------------------------------------------------------*/
	
	public void addMemoryEventListener(CpuMemoryEventListener e){
		if( !listeners.contains(e) )
			listeners.add(e);
	}
	
	public void removeMemoryEventListener(CpuMemoryEventListener e){
		listeners.remove(e);
	}
	
	private void notificarmemoryModifyEvent(int direction, int value){
		Iterator<CpuMemoryEventListener> it = listeners.iterator();
		CpuMemoryEventListener elemento;
		
		while(it.hasNext()){
			elemento = it.next();
			elemento.onMemoryModify(value, direction);
		}
	}
	
	/*-----------------------------------------------------------------------------*
	 *       Methods                                                               *
	 *-----------------------------------------------------------------------------*/	
	
	/**
	 * Stores the value passed as parameter in the specified direction.
	 * If the direction have a value yet, its override, if not, the register is
	 * created with the specific value.
	 * 
	 * @param value
	 *            The value to be stored
	 * @param direction
	 *            The memory direction in which the value is going to be stored
	 * @return True if the store has been a success and false if not, it may be
	 *         a fail if the direction has not a value yet and the memory is
	 *         full.
	 */
	public void setRegister(int value, int direction) throws FullMemoryException {
		int regPosition = find(direction);
		// si ha devuelto una posición del array que no coincide con el valor =>
		// es la posicion OCUPADA donde debería estar
		// si ha devuelto una posicion fuera del array, (esta coincidirá con la
		// primera vacía) => es la posicion donde debería estar
		if (((regPosition < counter) && (registers[regPosition].getDirection() != direction))
				|| (regPosition >= counter)) {
			if(counter == registers.length)
				throw new FullMemoryException();
			for (int i = counter - 1; i >= regPosition; i--)
				registers[i + 1] = registers[i];
			registers[regPosition] = new DataMemoryRegister(value,direction);
			counter++;
		} // si no, es porque ha devuelto una posicion del array que coincide
			// con el valor => está en esa posición
		else
			registers[regPosition].setValue(value);
		this.notificarmemoryModifyEvent(direction, value);
	}

	/**
	 * Returns the value contained in the memory register with the specified
	 * direction.
	 * public method boolean exists(int position) must be called to know if the
	 * specified direction is in the memory
	 * 
	 * @param posicion
	 *            The specified direction
	 * @return The value of the memory register with the specified direction
	 */
	public int getRegisterValue(int direction) throws BadMemoryAccessException{
		int posicion = find(direction);
		if(posicion >= counter || registers[posicion].getDirection() != direction)
			throw new BadMemoryAccessException(direction);
		return registers[posicion].getValue();
	}

	// Indica si la posicion consultada existe... habrá que llamar a esta
	// función antes de invocar un "load"
	/*
	public boolean exists(int position) {
		boolean found = false;
		int pos = find(position);
		if (pos < counter)
			found = registers[pos].getDirection() == position;
		return found;
	}
	*/

	// Búsqueda binaria de la dirección, devuelve la posición en la que está o
	// debería estar.
	// Debe comprobarse de que caso se trata una vez obtenido el valor
	private int find(int direction) {
		int found = -1;
		int first = 0;
		int last = this.counter - 1;
		int mid;
		while ((found == -1) && (first <= last)) {
			mid = (first + last) / 2;
			if (direction > registers[mid].getDirection())
				first = mid + 1;
			else if (direction < registers[mid].getDirection())
				last = mid - 1;
			else
				found = mid;
		}
		if (found == -1)
			found = first;
		return found;
	}

	/**
	 * @return A string with the occupied memory directions and its values in the
	 *         format:
	 *         "[direction]:value" or the string "&#60vacia&#62"
	 */
	public String state() {
		String cadena = "";
		
		if(counter <= 0)
			return null;
		
		for (int i = 0; i < counter; i++)
			cadena = cadena.concat("[" + registers[i].getDirection() + "]:"
					+ Integer.toString(registers[i].getValue()) + " ");
		
		return cadena;
	}
}
