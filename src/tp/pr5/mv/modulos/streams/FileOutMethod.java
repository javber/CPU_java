package tp.pr5.mv.modulos.streams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutMethod implements OutMethod{

	private BufferedWriter stream;
	
	public FileOutMethod(String fichero) throws IOException{
		this.stream = new BufferedWriter(new FileWriter(fichero));
	}
	

	public void write(int caracter) throws IOException {
		this.stream.write(caracter);
	}

	public void close() throws IOException {
		this.stream.close();
	}
	
	
}
