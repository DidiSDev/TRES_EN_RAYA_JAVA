
public class Interruptor {

	private boolean interruptor;
	
	Interruptor(boolean interruptor)
	{
		this.interruptor=interruptor;
	}
	Interruptor()
	{
		this.interruptor=true;
	}

	public boolean isInterruptor() {
		return interruptor;
	}

	public void setInterruptor(boolean interruptor) {
		this.interruptor = interruptor;
	}
	
	
}
