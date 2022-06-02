package bit;

public class branchEqualOp extends Operation {
	private int address;
	public branchEqualOp(int value) {
		this.address = value;
	}
	public int getAddressValue() {
		return address;
	}
	
	@Override 
	public String toString() {
		return "BRANCHEQ(" +  getAddressValue() + ")";
	}
}
