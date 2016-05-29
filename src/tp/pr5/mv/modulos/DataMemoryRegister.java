package tp.pr5.mv.modulos;

public class DataMemoryRegister {

	private int value;
	private int direction;

	/*-----------------------------------------------------------------------------*
	 *       Constructors                                                          *
	 *-----------------------------------------------------------------------------*/

	public DataMemoryRegister(int valor, int direccion) {
		this.value = valor;
		this.direction = direccion;
	}
	
	
	/*-----------------------------------------------------------------------------*
	 *       Methods                                                               *
	 *-----------------------------------------------------------------------------*/
	
	/**
	 * @return The value stored in the data register
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Stores a value in the data register
	 * 
	 * @param newVal
	 *            the value to be stored in the data register
	 */
	public void setValue(int newVal) {
		this.value = newVal;
	}

	/**
	 * @return The direction of the data register
	 */
	public int getDirection() {
		return this.direction;
	}
}
