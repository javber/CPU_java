package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.instrucciones.Instruction;

public class Rbt extends OneParamJumpOperation {

	public Rbt(int parametro){
		this.param = parametro;
	}
	
	protected Instruction instanciate(int param){
		return new Rbt(param);
	}
	
	public String getMnemonic() {
		return "RBT";
	}

	protected int nextPc(int cima, int pc) {
		if(cima != 0)
			pc += getParam();
		else
			pc++;
		return pc;
	}
}
