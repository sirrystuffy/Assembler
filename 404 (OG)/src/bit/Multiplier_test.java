package bit;

public class Multiplier_test {
	public static void main(String[] args) throws Exception {
		runTests();
		//bit_test.runTests();
		//longword_test.runTests();
		//rippleAdder_test.runTests();
	}
	public static void runTests() throws Exception{
		testMultiply();
	}
	
	public static void testMultiply() throws Exception{
		//Multiply 0 and 0 to get 0
		if(Multiplier.multiply(new longword(0), new longword(0)).getSigned() != 0) {
			throw new Exception("Multiply1 : ERROR");
		} 
		//Multiply 100 and 100 to get 10000
		if(Multiplier.multiply(new longword(100), new longword(100)).getSigned() != 10000) {
			throw new Exception("Multiply2 : ERROR");
		} 
		//Multipy -50 and -50 to get -100
		if(Multiplier.multiply(new longword(-50), new longword(2)).getSigned() != -100) {			
			throw new Exception("Multiply3 : ERROR");
		} 
		//Multipy -10 and -11 to get 110
		if(Multiplier.multiply(new longword(-10), new longword(-11)).getSigned() != 110) {
			throw new Exception("Multiply4 : ERROR");
		} 
		//Multipy 2147483647 and 2147483647 to get 1
		if(Multiplier.multiply(new longword(2147483647), new longword(2147483647)).getSigned() != 1) {
			throw new Exception("Multiply5 : ERROR");
		} 
		//Multipy -2147483647 and -2147483647 to get 1
		if(Multiplier.multiply(new longword(-2147483647), new longword(-2147483647)).getSigned() != 1) {
			throw new Exception("Multiply6 : ERROR");
		} 
		//Multipy -99 and 0 to get 0
		if(Multiplier.multiply(new longword(-99), new longword(0)).getSigned() != 0) {
			throw new Exception("Multiply6 : ERROR");
		} 
		System.out.println("HI");
	}
}
