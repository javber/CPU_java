package tp.pr5.mv.eventos.listeners;


public interface CpuEventListener {

	public void onStart(String[] programa);
	public void onHalt();
	public void onExecutionStart(String instructionRepresentation);
	public void onExecutionEnd(String memoryState, String stackState);
	public void onError(Exception e);
	public void onPcAdvance(int programCounter);
}
