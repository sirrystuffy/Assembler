package bit;

public class branchNotEqualOp extends Operation {
	private int address;
	public branchNotEqualOp(int value) {
		this.address = value;
	}
	public int getAddressValue() {
		return address;
	}
	
	@Override 
	public String toString() {
		return "BRANCHNOTEQ(" +  getAddressValue() + ")";
	}
}
