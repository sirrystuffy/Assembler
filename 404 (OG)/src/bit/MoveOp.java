package bit;

public class MoveOp extends Operation{
	private int value, register;
	
	public MoveOp(int reg, int num) {
		this.value = num;
		this.register = reg;
		
	}
	
	public int getValue() {
		return value;
	}
	public int getRegister() {
		return register;
	}
	
	@Override 
	public String toString() {
		return "MOVE(" + getRegister() + ", " + getValue() + ")";
	}
}
