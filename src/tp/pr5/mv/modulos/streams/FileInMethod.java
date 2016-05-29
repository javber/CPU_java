package tp.pr5.mv.modulos.streams;

import java.io.FileReader;
import java.io.IOException;

public class FileInMethod implements InMethod{
	FileReader aFile;
	
	public FileInMethod(String fichero) throws IOException{
		aFile = new FileReader(fichero);
	}
	

	public int read() throws IOException {
		return aFile.read();
	}

	public void close() throws IOException {
		this.aFile.close();
	}

}
