package tp.pr5.mv.parsers;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.modulos.ProgramMV;

import java.io.*;

public class ASMFileParser {

	
	public static ProgramMV parseFile(String filename) throws FileNotFoundException, IOException, ASMCodeParseException{
		ProgramMV programa = new ProgramMV();
		BufferedReader reader;
		String texto;
		int linea = 1;
		Instruction instruccion;
		int finalCadena;
			
		reader = new BufferedReader(new FileReader(filename));
		
		try{
			texto = reader.readLine().trim();
			while(texto!=null){
				finalCadena = texto.indexOf(';');
				if(finalCadena != 0 && !texto.isEmpty()){
					if(finalCadena > 0)
						instruccion = InstructionParser.parse( texto.substring(0, finalCadena) );
					else
						instruccion = InstructionParser.parse(texto);
			
					if(instruccion == null){
						reader.close();
						throw new ASMCodeParseException(texto, linea);
					}
					programa.agregarInstruccion(instruccion);
				}
				texto = reader.readLine();
				linea++;
			}
		}
		catch(IOException e){
			throw e;
		}
		finally{
			reader.close();	// Para cerrar el stream de lectura aunque ocurra una IOException
		}
		
		return programa;
	}
}
