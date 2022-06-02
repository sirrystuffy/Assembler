package bit;

public class ALU_test {
	public static void main(String[] args) throws Exception {
		runTests();
		bit_test.runTests();
		longword_test.runTests();
		rippleAdder_test.runTests();
		Multiplier_test.runTests();
	}
	public static void runTests() throws Exception{
		testALU();
	}
	public static void testALU() throws Exception {
		//Fill a bit array with values corresponding to an operation, then pass it to doOP() and check results
		//and operation
		bit[] x = {new bit(1), new bit(0), new bit(0), new bit(0)};
		if (ALU.doOP(x, new longword(100), new longword(50)).getSigned() != 32) {
			throw new Exception ("ALU 1 Failed");
		} else {
			System.out.println("HEllo1");
		}
		//or operation
		x = new bit[] {new bit(1), new bit(0), new bit(0), new bit(1)};
		if (ALU.doOP(x, new longword(1234), new longword(980)).getSigned() != 2006) {
			throw new Exception ("ALU 2 Failed");
		} else {
			System.out.println("HEllo2");
		}
		//xor operation
		x = new bit[] {new bit(1), new bit(0), new bit(1), new bit(0)};
		if (ALU.doOP(x, new longword(555), new longword(33)).getSigned() != 522) {
			throw new Exception ("ALU 3 Failed");
		} else {
			System.out.println("HEllo3");
		}
		//not operation
		x = new bit[] {new bit(1), new bit(0), new bit(1), new bit(1)};
		if (ALU.doOP(x, new longword(0), new longword(11)).getUnsigned() != 4294967295L) {
			throw new Exception ("ALU 4 Failed");
		} else {
			System.out.println("HEllo4");
		}
		//left shift operation
		x = new bit[] {new bit(1), new bit(1), new bit(0), new bit(0)};
		if (ALU.doOP(x, new longword(999), new longword(3)).getSigned() != 24) {
			throw new Exception ("ALU 5 Failed");
		} else {
			System.out.println("HEllo5");
		}
		//right shift operation
		x = new bit[] {new bit(1), new bit(1), new bit(0), new bit(1)};
		if (ALU.doOP(x, new longword(999), new longword(3)).getSigned() != 28) {
			throw new Exception ("ALU 6 Failed");
		} else {
			System.out.println("HEllo6");
		}
		//Add operation
		x = new bit[] {new bit(1), new bit(1), new bit(1), new bit(0)};
		if (ALU.doOP(x, new longword(555), new longword(100)).getSigned() != 655) {
			throw new Exception ("ALU 7 Failed");
		} else {
			System.out.println("HEllo7");
		}
		//Subtract operation
		x = new bit[] {new bit(1), new bit(1), new bit(1), new bit(1)};
		if (ALU.doOP(x, new longword(777), new longword(555)).getSigned() != 222) {
			throw new Exception ("ALU 8 Failed");
		} else {
			System.out.println("HEllo8");
		}
		//Multiply operation
		x = new bit[] {new bit(0), new bit(1), new bit(1), new bit(1)};
		if (ALU.doOP(x, new longword(99), new longword(12)).getSigned() != 1188) {
			throw new Exception ("ALU 9 Failed");
		} else {
			System.out.println("HEllo9");
		}
		//Invalid operation (0001) -- should throw error
		x = new bit[] {new bit(0), new bit(0), new bit(0), new bit(1)};
		if (ALU.doOP(x, new longword(99), new longword(12)).getSigned() != 1188) {
			throw new Exception ("ALU 10 Failed");
		} else {
			System.out.println("HEllo10");
		}
	}
}
