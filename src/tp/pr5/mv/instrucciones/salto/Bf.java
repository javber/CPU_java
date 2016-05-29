package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.instrucciones.Instruction;

public class Bf extends OneParamJumpOperation {

	public Bf(int parametro){
		this.param = parametro;
	}
	
	protected Instruction instanciate(int param){
		return new Bf(param);
	}
	
	public String getMnemonic() {
		return "BF";
	}

	protected int nextPc(int cima, int pc) {
		if (cima == 0)
			pc = getParam();
		else
			pc++;
		return pc;
	}
}
