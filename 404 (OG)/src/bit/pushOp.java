package bit;

public class pushOp extends Operation {
	private int register; 
	public pushOp(int regi)	{
		this.register = regi;
	}
	
	public int getRegister() {
		return register;
	}
	
	@Override 
	public String toString() {
		return "PUSH(R" + getRegister() + ")";
	}
}
