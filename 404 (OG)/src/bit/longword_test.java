package bit;

public class longword_test {
	public static void main(String[] args) throws Exception{
		runTests();
		bit_test.runTests();
	}
	public static void runTests() throws Exception{
		testGetBit();
		testSetBit();
		testAnd();
		testOr();
		testXor();
		testNot();
		testRightShift();
		testLeftShift(); 
		testToString();
		testGetUnsigned();
		testGetSigned();
		testCopy();
		testSet();
	
	}
	
	public static void testGetBit() throws Exception {

		//the index we get bit from should be 0
		if (new longword(0).getBit(0).getValue() != new bit(0).getValue()) {
			throw new Exception ("Error: GetBit not returning 0");
		} 
		//the rightmost value should have a value of 1
		if (new longword(1).getBit(31).getValue() != new bit(1).getValue()) {
			throw new Exception ("Error: GetBit not returning 1");
		} 
	}
	public static void testSetBit() throws Exception {
		longword example = new longword(0);//Initialize with 0s 
		example.setBit(10, new bit(0)); //index 10 with value 0
		example.setBit(20, new bit(1)); //index 20 with value 1
		if (example.getBit(10).getValue() != new bit(0).getValue()) {
			throw new Exception ("Error: setBit(10) Failed");
		} 
		if (example.getBit(20).getValue() != new bit(1).getValue()) {
			throw new Exception ("Error: setBit(20) Failed");
		} 
	}
	public static void testAnd() throws Exception {
		//Gets the int value of the resultant longword and check if the value matches the correct and() output 
		//Test 0s and 1s cases
		if ((new longword(0).and(new longword(0))).getSigned() != 0) {
			throw new Exception ("Error: 0 and 0 Failed");
		} 
		if (new longword(0).and(new longword(1)).getSigned() != 0) {
			throw new Exception ("Error: 0 and 1 Failed");
		} 
		if (new longword(1).and(new longword(0)).getSigned() != 0) {
			throw new Exception ("Error: 1 and 0 Failed");
		} 
		if (new longword(1).and(new longword(1)).getSigned() != 1) {
			throw new Exception ("Error: 1 and 1 Failed");
		} 
		//Test random  case
		if (new longword(10).and(new longword(20)).getSigned() != 0) {
			throw new Exception ("Error: 1 and 1 Failed");
		} 
		
	}
	public static void testOr() throws Exception {
		//Gets the int value of the resultant longword and check if the value matches the correct or() output 
		//Test 0s and 1s cases
		if ((new longword(0).or(new longword(0))).getSigned() != 0) {
			throw new Exception ("Or: 0 and 0 Failed");
		} 
		if (new longword(0).or(new longword(1)).getSigned() != 1) {
			throw new Exception ("Or: 0 and 1 Failed");
		} 
		if (new longword(1).or(new longword(0)).getSigned() != 1) {
			throw new Exception ("Or: 1 and 0 Failed");
		} 
		if (new longword(1).or(new longword(1)).getSigned() != 1) {
			throw new Exception ("Or: 1 and 1 Failed");
		} 
		//Test random  case
		if (new longword(19).or(new longword(25)).getSigned() != 27) {
			throw new Exception ("Or: 1 and 1 Failed");
		}
	}
	public static void testXor() throws Exception {
		//Gets the int value of the resultant longword and check if the value matches the correct xor() output 
		//Test 0s and 1s cases
		if ((new longword(0).xor(new longword(0))).getSigned() != 0) {
			throw new Exception ("Xor: 0 and 0 Failed");
		} 
		if (new longword(0).xor(new longword(1)).getSigned() != 1) {
			throw new Exception ("Xor: 0 and 1 Failed");
		} 
		if (new longword(1).xor(new longword(0)).getSigned() != 1) {
			throw new Exception ("Xor: 1 and 0 Failed");
		} 
		if (new longword(1).xor(new longword(1)).getSigned() != 0) {
			throw new Exception ("Xor: 1 and 1 Failed");
		} 
		//Test random  case
		if (new longword(70).xor(new longword(12)).getSigned() != 74) {
			throw new Exception ("Xor: 1 and 1 Failed");
		}
	}
	public static void testNot() throws Exception {
		//Gets the int value of the resultant longword and check if the value matches the correct not() output 
		//The complement of 10 in binary form is -11 in binary form, so they must be equal using not()
		if (new longword(10).not().getSigned() != new longword(-11).getSigned()) {
			throw new Exception ("Not: 0 and 0 Failed");
		} 
		//The complement of 90 in binary form is -91 in binary form, so they must be equal using not()
		if (new longword(90).not().getSigned() != new longword(-91).getSigned()) {
			throw new Exception ("Not: 0 and 1 Failed");
		} 
	}
	public static void testRightShift() throws Exception {
		longword example = new longword(0); //Initialize with 0s
		example.setBit(5, new bit(1)); //Set index 5 to 1
		//the value of index 6 should be a bit (1) due to the right shift
		if (example.rightShift(1).getBit(6).getValue() != 1) {
			throw new Exception ("Rightshift: 1 Failed");
		} 
		//the value of index 10 should be a bit (1) due to the right shift 
		if (example.rightShift(5).getBit(10).getValue() != 1) {
			throw new Exception ("Rightshift: 1 Failed");
		}
	}
	public static void testLeftShift() throws Exception {
		longword example = new longword(0); //Initialize with 0s
		example.setBit(5, new bit(1)); //Set index 5 to 1
		//the value of index 4 should have a bit (1) due to the left shift
		if (example.leftShift(1).getBit(4).getValue() != 1) {
			throw new Exception ("Leftshift: 1 Failed");
		} 
		//the value of index 0 should have a bit (1) due to the left shift
		if (example.leftShift(5).getBit(0).getValue() != 1) {
			throw new Exception ("Leftshift: 1 Failed");
		}
	}
	public static void testToString() throws Exception {
		//Create an empty string filled with 0's
		String str = "";
		for (int i =0; i < 32; i++) {
			str += "0";
			//62 to include the 32 values and 31 commas
			if (str.length() > (62)) {
				break;
			}
			str += ",";
		} 
		//string value of this longword should be equal to the string created above
		if (new longword(0).toString().equals(str)) {
			System.out.println("toString SUCCESS");
		} else {
			throw new Exception ("ToString Failed");
		}
		
	}
	public static void testGetUnsigned() throws Exception {
		//Get long value of longword and test if it matches the appropriate value.
		longword ex = new longword(0);
		// fill longword with all 1s
		for (int i = 0; i < 32; i++) {
			ex.setBit(i, new bit(1));
		}
		//a longword with all 1s is too big for int so we test if getUnsigned can get the result as long
		if (ex.getUnsigned() != 4294967295L ) {
			throw new Exception ("Get Signed : Failed");
		} 
		
	}
	public static void testGetSigned() throws Exception {
		//Get int value of longword and test if it matches the appropriate value.
		//Test if getSigned can convert binary back to same decimal (Negative Case)
		if (new longword(-2147483647).getSigned() != -2147483647) {
			throw new Exception ("Get Signed : Failed");
		} 
		//Positive Case
		if (new longword(2147483647).getSigned() != 2147483647) {
			throw new Exception ("Get Signed : Failed");
		} 
	}
	public static void testCopy() throws Exception {
		//Create longword of 0s and copy a longword of different value to it
		longword zero = new longword(0);//Initialize with zeroes
		zero.copy(new longword(1));//longword zero is now filled with 1's
		if (zero.getSigned() != 1) {
			throw new Exception ("Copy Failed : 1");
		} 
		zero.copy(new longword(0));//zero filled with 0's again
		if (zero.getSigned() != new longword(0).getSigned()) {
			throw new Exception ("Copy Failed : 1");
		} 
	}
	public static void testSet() throws Exception {
		longword zero = new longword(0);//all 0s
		zero.set(13);//zero should be 1101
		//value should be the same as the value passed in set
		if(zero.getSigned() != 13) {
			throw new Exception ("Test Set Failed: 13");
		} 
		zero.set(-99);//should be 0011101
		//value should be the same as the value passed in set
		if(zero.getSigned() != -99) {
			throw new Exception ("Test Set Failed: 0");
		} 
	}
}
