package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.instrucciones.Instruction;

public class Bt extends OneParamJumpOperation {

	public Bt(int parametro){
		this.param = parametro;
	}
	
	protected Instruction instanciate(int param){
		return new Bt(param);
	}
	
	public String getMnemonic() {
		return "BT";
	}

	protected int nextPc(int cima, int pc) {
		if (cima != 0)
			pc = getParam();
		else
			pc++;
		return pc;
	}
}
