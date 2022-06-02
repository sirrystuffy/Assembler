package bit;

public class memory_test {
	public static void main(String[] args) throws Exception {
		runTests();
		bit_test.runTests();
		longword_test.runTests();
		rippleAdder_test.runTests();
		Multiplier_test.runTests(); 
		ALU_test.runTests();
	}

	public static void runTests() throws Exception {
		testMemory();
	}
	public static void testMemory() throws Exception {
		longword addy = new longword(0);
		longword data = new longword(1234);
		Memory.write(addy, data); //Change values of addy through memory
		//Have testValue hold reference to value of read 
		longword testValue = Memory.read(addy);
		System.out.println(testValue.getSigned());
		//Read/Write Longword(1234)
		if (testValue.getSigned() != 1234) {
			throw new Exception ("Memory Failed : 1234");
		}
		//Write/Read Longword(0)
		Memory.write(addy, new longword(0));
		longword testValue2 = Memory.read(addy);
		if (testValue2.getSigned() != 0) {
			throw new Exception ("Memory Failed : 0");
		}
		//Write/Read Longword(1)
		Memory.write(addy, new longword(1));
		longword testValue3 = Memory.read(addy);
		if (testValue3.getSigned() != 1) {
			throw new Exception ("Memory Failed : 1");
		}
		//Write/Read Longword(2147483647)
		Memory.write(addy, new longword(2147483647));
		longword testValue4 = Memory.read(addy);
		if (testValue4.getSigned() != 2147483647) {
			throw new Exception ("Memory Failed : 2147483647");
		}
		//Write/Read Longword(-2147483648)
		Memory.write(addy, new longword(-2147483648));
		longword testValue5 = Memory.read(addy);
		if (testValue5.getSigned() != -2147483648) {
			throw new Exception ("Memory Failed : -2147483648");
		}
		//Write/Read Longword(1111)
		addy = new longword(-2147483648); //Writing over a negative number
		Memory.write(addy, new longword(1111));
		longword testValue6 = Memory.read(addy);
		if (testValue6.getSigned() != 1111) {
			throw new Exception ("Memory Failed : 2147483647");
		}
		for (int i = 0; i < 1024; i += 4) {
			System.out.println(Memory.read(new longword(i)).toString());
		}
		System.out.println("----");
		System.out.println(Memory.length());


	}
}
