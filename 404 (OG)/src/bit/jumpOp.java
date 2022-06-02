package bit;

public class jumpOp extends Operation {
	private int address; 
	
	public jumpOp(int value) {
		this.address = value;
	}
	//Accessors 
	public int getAddressValue() {
		return address;
	}
	
	@Override 
	public String toString() {
		return "JUMP(" + getAddressValue() + ")";
	}
}
