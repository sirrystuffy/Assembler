package bit;

public class bit_test {
	public static void main(String[] args) throws Exception{
		runTests();
	}
	public static void runTests() throws Exception {
		testSetValue();
		testToggle();
		testSet();
		testClear();
		testGetValue();
		testAnd();
		testOr();
		testXor();
		testNot();
		testToString(); 
	}
	public static void testSetValue() throws Exception {
		//Use setvalue() to set bits to certain values and throw error if unexpected value occurs
		bit one = new bit(1);
		one.set(0);//set to 0
		
		if ((one.getValue()) != 0 ) {
			throw new Exception ("Failure on 0");
		} 
		one.set(1);//set to 1
		if (one.getValue() != 1 ) {
			throw new Exception ("Failure on 1");
		} 
		//one.set(90);//output error 
	}
	
	public static void testToggle() throws Exception {
		//Use toggle() to change value and throw exception if that value isn't expected
		bit one = new bit(1); 
		one.toggle(); //should be 0
		if (one.getValue() != 0) {
			throw new Exception ("Failed:0");
		} 
		one.toggle(); //should be back to 1
		if (one.getValue() != 1) {
			throw new Exception ("Failed:1");
		} 
	}
	
	public static void testSet() throws Exception {
		//Use set() to set bit to 1 and throw error if another value is found
		bit zero = new bit(0);
		zero.set();//set to 1
		if (zero.getValue() != 1) {
			throw new Exception ("Failure");
		} 
		
		bit one = new bit(1);
		one.set(); //should still be 1
		if (one.getValue() != 1) {
			throw new Exception ("Failure");
		} 
	}

	public static void testClear() throws Exception {
		//Clear the value of the bit and throw error if not 0
		bit one = new bit(1);
		one.clear(); //set to 0
		if (one.getValue() != 0) {
			throw new Exception ("Failed");
		} 
		bit zero = new bit(0);
		zero.clear(); //should still be 0
		if (zero.getValue() != 0) {
			throw new Exception ("Failed");
		} 
	}
	
	public static void testGetValue() throws Exception {
		//test if bit 1 returns 1 and bit 0 returns 0
		if (new bit(1).getValue() != 1) {
			throw new Exception ("Failed");
		} 
		if (new bit(0).getValue() != 0) {
			throw new Exception ("Failed");
		} 
	}
	
	public static void testAnd() throws Exception {
		//Only two bit 1's should return 1, otherwise throw error
		if (new bit(0).and(new bit(0)).getValue() != 0) {
			throw new Exception ("0 and 0 failed");
		} 
		if (new bit(0).and(new bit(1)).getValue() != 0) {
			throw new Exception ("0 and 1 failed");
		} 
		if (new bit(1).and(new bit(0)).getValue() != 0) {
			throw new Exception ("1 and 0 failed");
		} 
		if (new bit(1).and(new bit(1)).getValue() != 1) {
			throw new Exception ("1 and 1 failed");
		} 
	}
	public static void testOr() throws Exception {
		//Only two bit 0's should return 0, otherwise throw an error
		if (new bit(0).or(new bit(0)).getValue() != 0) {
			throw new Exception ("0 and 0 failed");
		} 
		if (new bit(0).or(new bit(1)).getValue() != 1) {
			throw new Exception ("0 and 1 failed");
		} 
		if (new bit(1).or(new bit(0)).getValue() != 1) {
			throw new Exception ("1 and 0 failed");
		}  
		if (new bit(1).or(new bit(1)).getValue() != 1) {
			throw new Exception ("1 and 1 failed");
		} 
	}
	public static void testXor() throws Exception{
		//If the bits are not equal to each other, expect 1 otherwise expect 0
		if (new bit(0).xor(new bit(0)).getValue() != 0) {
			throw new Exception ("0 and 0 failed");
		} 
		if (new bit(0).xor(new bit(1)).getValue() != 1) {
			throw new Exception ("0 and 1 failed");
		} 
		if (new bit(1).xor(new bit(0)).getValue() != 1) {
			throw new Exception ("1 and 0 failed");
		} 
		if (new bit(1).xor(new bit(1)).getValue() != 0) {
			throw new Exception ("1 and 1 failed");
		} 
	}
	public static void testNot() throws Exception {
		//Use not() to expect 0 if bit (1) or 1 if bit(0)
		if (new bit(1).not().getValue() != 0) {
			throw new Exception ("0 Failed");
		} 
		if (new bit(0).not().getValue() != 1) {
			throw new Exception ("1 Failed");
		} 
	}
	public static void testToString() throws Exception {
		//Use toString() and expect "1" if bit(1) or 0 if bit(0)
		if (!new bit(1).toString().equals("1")) {
			throw new Exception ("1 Failed");
		} 
		if (!new bit(0).toString().equals("0")) {
			throw new Exception ("0 Failed");
		} 
	}
	
}
