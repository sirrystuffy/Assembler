package bit;

public class branchGreaterOp extends Operation{
	private int address;
	public branchGreaterOp(int value) {
		this.address = value;
	}
	public int getAddressValue() {
		return address;
	}
	
	@Override 
	public String toString() {
		return "BRANCHGREATER(" +  getAddressValue() + ")";
	}
}
