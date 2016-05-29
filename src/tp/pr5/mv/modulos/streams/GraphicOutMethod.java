package tp.pr5.mv.modulos.streams;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JTextArea;

public class GraphicOutMethod extends OutMethodDecorator implements OutMethod{

	private JTextArea textArea;

	public GraphicOutMethod(JTextArea textArea, OutMethod father){
		super(father);
		this.textArea = textArea;
	}

	protected void writeOperation(int caracter) throws IOException {
		final byte byteCaracter[] = new byte[1];	// Esto me parece una guarrada, pero a ver si hace el trabajo...
		byteCaracter[0] = (byte)caracter;
		
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				textArea.setText(textArea.getText() + new String(byteCaracter));
			}
		});
	}

	protected void closeOperation() throws IOException {
		// No se hace nada
	}
	
}
