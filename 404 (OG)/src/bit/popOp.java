package bit;

public class popOp extends Operation{
	private int register; 
	public popOp(int regi)	{
		this.register = regi;
	}
	
	public int getRegister() {
		return register;
	}
	
	@Override 
	public String toString() {
		return "POP(R" + getRegister() + ")";
	}
}
