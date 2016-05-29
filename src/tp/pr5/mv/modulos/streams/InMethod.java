package tp.pr5.mv.modulos.streams;

import java.io.IOException;


public interface InMethod{
	
	/*
	public int read() throws IOException{
		int posicion = -1;
		int caracter;
		if(buffer != null)
			posicion = buffer.position();
		caracter = this.readOperation();
		if(graphic!=null)
		   this.graphic.readChangeText(caracter,posicion);
		return caracter;
	}
	*/
	
	public int read() throws IOException;
	
	public void close() throws IOException;

	
}
