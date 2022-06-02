package bit;

public class branchGreaterEqOp extends Operation{
	private int address;
	
	public branchGreaterEqOp(int value) {
		this.address = value;
	}
	public int getAddressValue() {
		return address;
	}
	
	@Override 
	public String toString() {
		return "BRANCHGREATEREQ(" + getAddressValue() + ")";
	}
}
