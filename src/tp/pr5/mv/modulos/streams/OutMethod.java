package tp.pr5.mv.modulos.streams;

import java.io.IOException;

public interface OutMethod {		
	
	public void write(int caracter) throws IOException;
	
	public void close() throws IOException;
	
}
