package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.OneParamInstruction;
import tp.pr5.mv.instrucciones.excepciones.InstructionExecutionException;
import tp.pr5.mv.modulos.Memory;
import tp.pr5.mv.modulos.OperandStack;
import tp.pr5.mv.modulos.ProgramCounter;
import tp.pr5.mv.modulos.streams.InMethod;
import tp.pr5.mv.modulos.streams.OutMethod;

public class Jump extends OneParamInstruction {

	public Jump(int parametro){
		this.param = parametro;
	}
	
	protected Instruction instanciate(int param){
		return new Jump(param);
	}
	
	public String getMnemonic() {
		return "JUMP";
	}

	public void execute(OperandStack pilaOperandos, Memory memoria,
			ProgramCounter pc, InMethod input, OutMethod output) throws InstructionExecutionException{
		pc.setPc(getParam());
	}
}
