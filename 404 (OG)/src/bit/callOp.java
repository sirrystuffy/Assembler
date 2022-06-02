package bit;

public class callOp extends Operation{
	private int address; 
	public callOp(int addressName)	{
		this.address = addressName;
	}
	
	public int getAddress() {
		return address;
	}
	
	@Override 
	public String toString() {
		return "CALL(" + getAddress() + ")";
	}
}
