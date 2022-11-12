package bit;

public class test {
	
	public static void main(String[] args) {
		bit[] array = new bit[] {new bit(0), new bit(0), new bit(0), new bit(0)};
		bit[] controlBits = new bit[4];
		controlBits[0] = new bit(0);
		controlBits[1] = new bit(0);
		controlBits[2] = new bit(0);
		controlBits[3] = new bit(0);
			
		if (controlBits.equals(array)) {
			System.out.println("Success");
		}
	}
	
	
}
