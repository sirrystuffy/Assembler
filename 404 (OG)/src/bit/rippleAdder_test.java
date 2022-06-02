package bit;

public class rippleAdder_test {
	public static void main(String[] args) throws Exception {
		runTests();
		//bit_test.runTests();
		//longword_test.runTests();
	}
	public static void runTests() throws Exception {
		testAdd();
		testSubtract();
	}
	
	//Use add() method to add two longwords and check if the int/long value matches the expected value
	public static void testAdd() throws Exception {
		//System.out.println(rippleAdder.add(new longword(-2147483648), new longword(-2147483648)));
		if (rippleAdder.add(new longword(0), new longword(1)).getSigned() != 1) {
			throw new Exception ("0 + 1 = FAILED");
		} else {
			System.out.println("add1 : success");
		}
		//Positive Longword add Positive Longword
		if (rippleAdder.add(new longword(100), new longword(555)).getSigned() != 655) {
			throw new Exception ("100 + 555 = FAILED");
		} else {
			System.out.println("add2 : success");
		}
		//the value of add should be the sum of the ints passed in the longwords (2147483647 + 2147483647) = 4294967294
		if (rippleAdder.add(new longword(2147483647), new longword(2147483647)).getUnsigned() != 4294967294L){
			throw new Exception ("2147483647 + 2147483647 = FAILED");
		} else {
			System.out.println("add3 : success");
		}
		//Not enough bits to store the carry(1), so the longword is filled with 0s
		if (rippleAdder.add(new longword(-2147483648), new longword(-2147483648)).getSigned() != 0) {
			throw new Exception ("-2147483647 + -2147483647 = FAILED");
		} else {
			System.out.println("add4 : success");
		}
		//Negative longword add positive longword
		if (rippleAdder.add(new longword(-999), new longword(888)).getSigned() != -111) {
			throw new Exception ("999 + 888 = FAILED");
		} else {
			System.out.println("add5 : success");
		}
		//Positive Longword add Negative Longword
		if (rippleAdder.add(new longword(9087), new longword(-286)).getSigned() != 8801) {
			throw new Exception ("9087 + -286 = FAILED");
		} else {
			System.out.println("add6 : success");
		}
	}
	//Use subtract() method to subtract two longwords and check if the int value matches the expected value
	public static void testSubtract() throws Exception {
	
		//this returns a longword of all 1s, equivalent to -1
		if (rippleAdder.subtract(new longword(0), new longword(1)).getSigned() != -1) {
			throw new Exception ("0 - 1 = FAILED");
		} else {
			System.out.println("subtract1 : success");
		}
		//Positive longword subtract positive longword
		if (rippleAdder.subtract(new longword(999), new longword(0)).getSigned() != 999) {
			throw new Exception ("999 - 0 = FAILED");
		} else {
			System.out.println("subtract2 : success");
		}
		//Max value subtract max value
		if (rippleAdder.subtract(new longword(2147483647), new longword(2147483647)).getSigned() != 0){
			throw new Exception ("2147483647 - 2147483647 = FAILED");
		} else {
			System.out.println("subtract3 : success");
		}
		//Negative max value subtract negative max value
		if (rippleAdder.subtract(new longword(-2147483648), new longword(-2147483648)).getSigned() != 0){
			throw new Exception ("-2147483648 - -2147483648 = FAILED");
		} else {
			System.out.println("subtract4 : success");
		}
		//Negative longword subtract positive longword
		if (rippleAdder.subtract(new longword(-100), new longword(90)).getSigned() != -190){
			throw new Exception ("-100 - 90 = FAILED");
		} else {
			System.out.println("subtract5 : success");
		}
		//Positive Longword subtract negative longword
		if (rippleAdder.subtract(new longword(5000), new longword(-333)).getSigned() != 5333){
			throw new Exception ("5000 - -333 = FAILED");
		} else {
			System.out.println("subtract6 : success");
		}
	}
	
}
