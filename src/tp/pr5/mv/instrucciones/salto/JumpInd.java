package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.instrucciones.Instruction;

public class JumpInd extends NoParamJumpOperation{

	public String getMnemonic() {
		return "JUMPIND";
	}

	protected int nextPc(int cima, int pc) {
		return cima;
	}

	protected Instruction instanciate() {
		return new JumpInd();
	}
}
