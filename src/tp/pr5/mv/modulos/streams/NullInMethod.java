package tp.pr5.mv.modulos.streams;

import java.io.IOException;

public class NullInMethod implements InMethod{


	public int read() throws IOException {
		return -1;
	}

	public void close() throws IOException {
		// No debe hacer nada
	}

}
