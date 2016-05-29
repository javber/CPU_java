package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.instrucciones.Instruction;

public class Rbf extends OneParamJumpOperation {

	public Rbf(int parametro){
		this.param = parametro;
	}
	
	protected Instruction instanciate(int param){
		return new Rbf(param);
	}
	
	public String getMnemonic() {
		return "RBF";
	}

	protected int nextPc(int cima, int pc) {
		if (cima == 0)
			pc += getParam();
		else
			pc++;
		return pc;
	}
}
