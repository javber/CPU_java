package tp.pr5.mv.modulos.streams;

import java.io.IOException;

public class StdOutMethod implements OutMethod{
	

	public void write(int caracter) throws IOException {
		System.out.write(caracter);
		System.out.flush();
	}

	public void close() throws IOException {
		//no hacer nada
	}
}
