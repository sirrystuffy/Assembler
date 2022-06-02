package bit;

public class leftShiftOp extends Operation{
	private int register1, register2, resultRegister;
	
	public leftShiftOp(int reg1, int reg2, int resultReg) {
		this.register1 = reg1;
		this.register2 = reg2;
		this.resultRegister = resultReg;
		
	}
	
	public int getRegister1() {
		return register1;
	}
	public int getRegister2() {
		return register2;
	}
	public int getResultRegister() {
		return resultRegister;
	}
	@Override 
	public String toString() {
		return "LSHIFT(" + getRegister1() + ", " + getRegister2() + ", " + getResultRegister() + ")";
	}
}
