package bit;

public class cpu_test1 {
	public static void main(String[] args) throws Exception {
		runTests();
		/*bit_test.runTests();
		longword_test.runTests();
		rippleAdder_test.runTests();
		Multiplier_test.runTests();
		ALU_test.runTests(); */
	}
	public static void runTests() throws Exception{
		cpuTest1();
		//cpuTest2();
		//cpuTest3();

	}
	//Dealing with an even number of instructions, we move value(9) into R2, move value(1) into R1, print all registers - interrupt(0), and halt(0)
	public static void cpuTest1() throws Exception{
		computer cpu = new computer();
		//String[] instructions = new String[] {new longword(4617).toString(), new longword(4353).toString(), new longword(8192).toString(), new longword(0).toString()};
		String[] instructions = new String[] {new longword(4617).toString(), new longword(12288).toString(), new longword(8192).toString(), new longword(0).toString()};
		cpu.preload(instructions);
		cpu.run();
	}
	//Dealing with an odd number of instructions, we move value(-1) into R1, print all registers - interrupt(0), print memory - interrupt(1), and halt(0)
	public static void cpuTest2() throws Exception{
		computer cpu2 = new computer();
		String[] instructions = new String[] {new longword(4607).toString(), new longword(8192).toString(), new longword(8193).toString(), new longword(0).toString()};
		cpu2.preload(instructions);
		cpu2.run();
	}
	//Dealing with an even number of instructions, print all registers - interrupt(0), move value(54) to R6, move value(-73) to R8, print all registers - interrupt(0), and halt(0)
	public static void cpuTest3() {
		computer cpu3 = new computer();
		String[] instructions = new String[] {new longword(8192).toString(), new longword(5686).toString(), new longword(6327).toString(), new longword(8192).toString(), new longword(0).toString()};
		cpu3.preload(instructions);
		cpu3.run();
	}
}
