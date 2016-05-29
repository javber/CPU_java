package tp.pr5.mv.modulos.streams;

import java.io.IOException;

public abstract class OutMethodDecorator implements OutMethod{

	protected OutMethod father;

	protected OutMethodDecorator(OutMethod father){
		this.father = father;
	}
	
	public void write(int caracter) throws IOException{
		if(father != null)
			father.write(caracter);
		this.writeOperation(caracter);
	}
	
	public void close() throws IOException{
		if(father != null)
			father.close();
		this.closeOperation();
	}
	
	protected abstract void writeOperation(int caracter) throws IOException;
	protected abstract void closeOperation() throws IOException;
}
