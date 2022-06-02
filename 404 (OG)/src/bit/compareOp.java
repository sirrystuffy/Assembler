package bit;

public class compareOp extends Operation{
	private int register1, register2;
	public compareOp(int registerValue1, int registerValue2) {
		this.register1 = registerValue1;
		this.register2 = registerValue2;
	}
	public int getRegisterValue1() {
		return register1;
	}
	public int getRegisterValue2() {
		return register2;
	}
	
	@Override 
	public String toString() {
		return "COMPARE(" + getRegisterValue1() + ", " + getRegisterValue2() + ")";
	}
}
