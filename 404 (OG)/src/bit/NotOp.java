package bit;

public class NotOp extends Operation{
	private int register1, resultRegister;
	
	public NotOp(int reg1, int resultReg) {
		this.register1 = reg1;
		this.resultRegister = resultReg;
		
	}
	
	public int getRegister1() {
		return register1;
	}

	public int getResultRegister() {
		return resultRegister;
	}
	@Override 
	public String toString() {
		return "NOT(" + getRegister1() + ", " + getResultRegister() + ")";
	}
}
