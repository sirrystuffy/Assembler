package bit;

public class Assembler_test {
	public static void main(String[] args) throws Exception {
		runTests();
	}
	public static void runTests() throws Exception {
		testAssembler();
	}
	public static void testAssembler() throws Exception{
		String[] starr = {"MOVE R1 100", "AND R1 R2 R3", "OR R4 R5 R6", "XOR R7 R8 R9", "NOT R10 R11", "LEFTSHIFT R12 R13 R14", "RIGHTSHIFT R15 R1 R2", "ADD R3 R4 R10", "SUBTRACT R10 R7 R9", "MULTIPLY R3 R5 R2", "HALT", "INTERRUPT 0", "INTERRUPT 1"};
		System.out.println("Assembler Output : ");
		Assembler.assemble(starr);
		
		System.out.println("\nExpected Output :");
		System.out.println("MOVE R1 100: 0001000101100100");
		System.out.println("AND R1 R2 R3: 1000000100100011");
		System.out.println("OR R4 R5 R6 : 1001010001010110");
		System.out.println("XOR R7 R8 R9 : 1010011110001001");
		System.out.println("NOT R10 R11 : 101110101011");
		System.out.println("LEFTSHIFT R12 R13 R14 : 1100110011011110");
		System.out.println("RIGHTSHIFT R15 R1 R2: 1101111100010010");
		System.out.println("ADD R3 R4 R10 : 1110001101001010");
		System.out.println("SUBTRACT R10 R7 R9 : 1111101001111001");
		System.out.println("MULTIPLY R3 R5 R2: 0111001101010010");
		System.out.println("HALT: 0000000000000000");
		System.out.println("INTERRUPT 0: 0010000000000000");
		System.out.println("INTERRUPT 1 : 0010000000000001");
	}
	
}
