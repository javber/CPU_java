package tp.pr5.mv.modulos.streams;

import java.io.IOException;

public class StdInMethod implements InMethod{

	public int read() throws IOException {
		int caracter;
				
		caracter = System.in.read();
		if( caracter == (int)'\n') 	// Casting para que me devuelva el int 
			caracter = -1;			// correspondiente a \n en la codificación actual
			
		return caracter;
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub	
	}

}
