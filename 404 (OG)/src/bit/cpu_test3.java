package bit;

public class cpu_test3 {
	public static void main(String[] args) throws Exception {
		runTests();
		/*bit_test.runTests();
		longword_test.runTests();
		rippleAdder_test.runTests();
		Multiplier_test.runTests();
		ALU_test.runTests(); */
	}
	public static void runTests() throws Exception{
		pushTest();
		//popTest();
		//callTest();
		//returnTest();
	}
	public static void pushTest() throws Exception{
		//This test is successful if the value in R2 (9) is in memory at the last address !
		String[] starr = {"MOVE R2 9", "MOVE R1 1000", "PUSH R2", "INTERRUPT 0", "HALT"};
		computer cpu = new computer();
		cpu.preload(Assembler.assemble(starr));
		cpu.run();
	}
	public static void popTest() throws Exception{
		//This test is successful if:
		//the value in R2 (9) is not in memory at the second-to-last address !
		//Register 3 should have the same value as R2
		String[] starr = {"MOVE R2 9", "MOVE R1 4", "PUSH R1", "PUSH R2", "POP R3", "INTERRUPT 1", "INTERRUPT 0", "HALT"};
		computer cpu = new computer();
		cpu.preload(Assembler.assemble(starr));
		cpu.run();
	}
	public static void callTest() throws Exception{
		//This test is successful if: 
		//CALL skips PUSH 1 so POP R3 should take the last value in the stack. 
		//Basically, memory should be empty at the last address
		String[] starr = {"MOVE R2 9", "MOVE R1 4", "CALL 8", "PUSH R1", "POP R15","PUSH R2", "POP R3", "INTERRUPT 1", "INTERRUPT 0", "HALT"};
		computer cpu = new computer();
		cpu.preload(Assembler.assemble(starr));
		cpu.run();
	}
	public static void returnTest() throws Exception{
		//This test is successful if: 
		//There is no value in R8
		//The value 10 is in R5
		//There is no value in the stack (last address of memory)
		String[] starr = {"MOVE R2 9", "MOVE R1 4", "CALL 16", "INTERRUPT 0", "INTERRUPT 1", "HALT", "MOVE R5 10", "RETURN","MOVE R8 10", "POP R3", "INTERRUPT 1"};
		computer cpu = new computer();
		cpu.preload(Assembler.assemble(starr));
		cpu.run();
	}
}
