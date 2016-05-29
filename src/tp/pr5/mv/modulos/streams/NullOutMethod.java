package tp.pr5.mv.modulos.streams;

import java.io.IOException;

public class NullOutMethod implements OutMethod{

	public void write(int caracter) {
		//No hacer nada
	}

	public void close() throws IOException{
		//No hacer nada
	}
}