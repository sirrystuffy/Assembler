package bit;

public class cpu_test2 {
	public static void main(String[] args) throws Exception {
		runTests();
		/*bit_test.runTests();
		longword_test.runTests();
		rippleAdder_test.runTests();
		Multiplier_test.runTests();
		ALU_test.runTests(); */
	}
	public static void runTests() throws Exception{
		/*jumpTest();
		compareTest();
		branchIfEqualTest();
		branchIfGreaterTest();
		branchIfGreaterThanOrEqualTest(); */
		branchIfNotEqualTest();
	}
	public static void jumpTest() throws Exception{
		//This test is successful if there is no value in register 1 
		String[] starr = {"JUMP 8", "MOVE R1 4", "INTERRUPT 0", "HALT"};
		computer cpu = new computer();
		cpu.preload(Assembler.assemble(starr));
		cpu.run();
	}
	public static void compareTest() throws Exception{
		//This test will print the compared values and the difference to console. 
		//Expected bit array result : [1, 1] , [1, 0], [0, 0]
		String[] starr = {"MOVE R5 16", "MOVE R10 16", "MOVE R1 10", "COMPARE R5 R10", "COMPARE R5 R1", "COMPARE R1 R10"};
		computer cpu = new computer();
		cpu.preload(Assembler.assemble(starr));
		cpu.run();
	}
	public static void branchIfEqualTest() throws Exception{
		//This test is successful if the value 100 is in R1 and no value in R2, since BRANCHEQ skips: { MOVE R1 4 }
		String[] starr = {"MOVE R5 16", "MOVE R10 16", "COMPARE R5 R10", "BRANCHEQ 8", "MOVE R2 4", "MOVE R1 100", "INTERRUPT 0", "HALT"};
		computer cpu = new computer();
		cpu.preload(Assembler.assemble(starr));
		cpu.run();
	}
	public static void branchIfGreaterTest() throws Exception{
		//This test is successful if interrupt is not performed !
		//BRANCHGREATER increments pc by 8 bytes and moves past the INTERRUPT instruction (byte 20) to HALT (byte 24).
		String[] starr = {"MOVE R1 10", "MOVE R2 9", "COMPARE R1 R2", "BRANCHGREATER 8", "INTERRUPT 0", "HALT"};
		computer cpu = new computer();
		cpu.preload(Assembler.assemble(starr));
		cpu.run();
	}
	public static void branchIfGreaterThanOrEqualTest() throws Exception{
		//This tests the "equal to" part of the branchIfGreaterThanOrEqual
		//Successful if : there is no value in R2, the value of 100 is in register 1, and Interrupt(0) is performed
		String[] starr = {"MOVE R5 16", "MOVE R10 16", "COMPARE R5 R10", "BRANCHGREATEREQ 8", "MOVE R2 4", "MOVE R1 100", "INTERRUPT 0", "HALT"};
		computer cpu = new computer();
		cpu.preload(Assembler.assemble(starr));
		cpu.run();
		
		//This tests the "greater than" part of the branchIfGreaterThanOrEqual
		//Successful if : there is no value in R2, the value of 1 is in register 1, and Interrupt(0) is performed
		String[] starr2 = {"MOVE R5 120", "MOVE R10 16", "COMPARE R5 R10", "BRANCHGREATEREQ 8", "MOVE R2 4", "MOVE R1 1", "INTERRUPT 0", "HALT"};
		//Need a new computer because if we use the same computer to run these instructions, it won't give the correct output
		computer cpu2 = new computer();
		cpu2.preload(Assembler.assemble(starr2));
		cpu2.run();
	}
	public static void branchIfNotEqualTest() throws Exception{
		//This test is successful if Register 0 has the value 18 (7+11), R1 is empty, and interrupt is executed since we skip the first HALT instruction
		//this tests the x > y portion of the branchIfNotEqual method
		String[] starr = {"MOVE R7 11", "MOVE R11 7", "COMPARE R7 R11", "BRANCHNOTEQ 12", "MOVE R1 4", "HALT", "ADD R7 R11 R0", "INTERRUPT 0", "HALT"};
		computer cpu = new computer();
		cpu.preload(Assembler.assemble(starr));
		cpu.run();
		//This tests the "less than" part of the branchIfNotEqual method
		//Expect the same results as the previous test; this is just to show that it also works for x < y 
		String[] starr2 = {"MOVE R11 7", "MOVE R7 11", "COMPARE R11 R7", "BRANCHNOTEQ 12", "MOVE R1 4", "HALT", "ADD R7 R11 R0", "INTERRUPT 0", "HALT"};
		//Need a new computer because if we use the same computer to run these instructions, it won't give the correct output
		computer cpu2 = new computer();
		cpu2.preload(Assembler.assemble(starr2));
		cpu2.run();
	}
	
}
