package tp.pr5.mv.modulos.streams;


import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JTextArea;


public class GraphicInMethod implements InMethod{
  
	private String entrada;
	private int posicion;
	private JTextArea text;
    
	public GraphicInMethod(JTextArea text, InMethod input) throws IOException{	
		this.text=text;
		this.posicion = 0;
		this.entrada = "";
		this.loadInput(input);
	}
	
	public GraphicInMethod(JTextArea text) {
		this.text=text;
		this.posicion = 0;
		this.text.setText("");
		this.entrada = "";
	}

	private void loadInput(InMethod input) throws IOException{
		int caracter;
		caracter = input.read();
		while(caracter != -1){		// no encuentro otra una sencilla de hacerlo sin cast
			entrada = entrada.concat( String.valueOf( (char)caracter ) );
			caracter = input.read();
			}
		text.setText(entrada);
	}

	public int read() throws IOException {
		int caracter;
		
		// si no quedan caracteres por leer, devuelve -1
		if(posicion >= entrada.length())
			return -1;
		
		caracter = entrada.charAt(posicion);
		// si el caracter leído es una salto de linea, no actualiza el Textarea
		if(caracter != '\n'){
			entrada = entrada.substring(0, posicion).concat("*").concat(entrada.substring(posicion+1));
			this.updateText();
		}
		posicion++;
		
		return caracter;
	}

	private void updateText(){
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				text.setText(entrada);
			}
		});
	}
	
	public void close() throws IOException {
		// TODO Auto-generated method stub
	}

}
