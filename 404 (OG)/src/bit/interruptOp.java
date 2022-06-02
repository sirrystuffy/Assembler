package bit;

public class interruptOp extends Operation{
	private int zeroOrOne;
	public interruptOp(int value) {
		this.zeroOrOne = value;
	}
	//Accessor
	public int getZeroOrOne() {
		return zeroOrOne;
	}
	@Override
	public String toString() {
		return "INTERRUPT(" + getZeroOrOne() + ")";
	}
}
