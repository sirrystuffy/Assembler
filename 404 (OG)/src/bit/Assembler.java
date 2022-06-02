package bit;

import java.util.ArrayList;
import java.util.List;

public class Assembler {
	public static String[] assemble(String[] array) {
		String[] binaryInstructions = new String[100]; //Can hold 100 instructions for now.
		for (int i = 0; i < binaryInstructions.length; i++) {
			binaryInstructions[i] = "0";
		}
		List<Operation> finalList = new ArrayList<Operation>();
		//Accept an array of strings, which will be the bit instructions and call the lexer on each string
		Lexer lexing = new Lexer();
		for (int i = 0; i < array.length; i++) {
			try {
				new Parsing(lexing.lex(array[i]));
				List<Operation> parsedList = Parsing.parse();
				//have one big list hold all the parsed statements
				finalList.addAll(parsedList);
				//System.out.println("PARSER OUTPUT: " + parsedList + "\n---------------------------");
				//System.out.println(lexing.lex(array[i]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Finale OUTPUT: " + finalList + "\n---------------------------");
		System.out.println("Binary Instructions: ");
		
		int index = 0; //A way to keep track of the index so we can add 
		for (Operation op : finalList) { 
			String currentInstruction = "";
			if (op instanceof MoveOp) {
				//add the bits corresponding to move instruction
				currentInstruction += "0001";
				int register = ((MoveOp) op).getRegister();
				int number = ((MoveOp) op).getValue();
				//System.out.println(getBits(Integer.toBinaryString(99), 4));
				//Appending register bits to instruction string
				currentInstruction += getBits(Integer.toBinaryString(register), 4);
				currentInstruction += getBits(Integer.toBinaryString(number), 8);
				//System.out.println();
			} else if (op instanceof AndOp) {
				currentInstruction += "1000";
				int register1 = ((AndOp) op).getRegister1();
				int register2 = ((AndOp) op).getRegister2();
				int resultRegister = ((AndOp) op).getResultRegister();
				currentInstruction += getBits(Integer.toBinaryString(register1), 4);
				currentInstruction += getBits(Integer.toBinaryString(register2), 4);
				currentInstruction += getBits(Integer.toBinaryString(resultRegister), 4);
			} else if (op instanceof OrOp) {
				currentInstruction += "1001";
				int register1 = ((OrOp) op).getRegister1();
				int register2 = ((OrOp) op).getRegister2();
				int resultRegister = ((OrOp) op).getResultRegister();
				currentInstruction += getBits(Integer.toBinaryString(register1), 4);
				currentInstruction += getBits(Integer.toBinaryString(register2), 4);
				currentInstruction += getBits(Integer.toBinaryString(resultRegister), 4);
			} else if (op instanceof xorOp) {
				currentInstruction += "1010";
				int register1 = ((xorOp) op).getRegister1();
				int register2 = ((xorOp) op).getRegister2();
				int resultRegister = ((xorOp) op).getResultRegister();
				currentInstruction += getBits(Integer.toBinaryString(register1), 4);
				currentInstruction += getBits(Integer.toBinaryString(register2), 4);
				currentInstruction += getBits(Integer.toBinaryString(resultRegister), 4);
			} else if (op instanceof NotOp) {
				currentInstruction += "1011";
				int register1 = ((NotOp) op).getRegister1();
				int resultRegister = ((NotOp) op).getResultRegister();
				currentInstruction += getBits(Integer.toBinaryString(register1), 4);
				currentInstruction += getBits(Integer.toBinaryString(resultRegister), 8);
			} else if (op instanceof leftShiftOp) {
				currentInstruction += "1100";
				int register1 = ((leftShiftOp) op).getRegister1();
				int register2 = ((leftShiftOp) op).getRegister2();
				int resultRegister = ((leftShiftOp) op).getResultRegister();
				currentInstruction += getBits(Integer.toBinaryString(register1), 4);
				currentInstruction += getBits(Integer.toBinaryString(register2), 4);
				currentInstruction += getBits(Integer.toBinaryString(resultRegister), 4);
			} else if (op instanceof rightShiftOp) {
				currentInstruction += "1101";
				int register1 = ((rightShiftOp) op).getRegister1();
				int register2 = ((rightShiftOp) op).getRegister2();
				int resultRegister = ((rightShiftOp) op).getResultRegister();
				currentInstruction += getBits(Integer.toBinaryString(register1), 4);
				currentInstruction += getBits(Integer.toBinaryString(register2), 4);
				currentInstruction += getBits(Integer.toBinaryString(resultRegister), 4);
			} else if (op instanceof addOp) {
				currentInstruction += "1110";
				int register1 = ((addOp) op).getRegister1();
				int register2 = ((addOp) op).getRegister2();
				int resultRegister = ((addOp) op).getResultRegister();
				currentInstruction += getBits(Integer.toBinaryString(register1), 4);
				currentInstruction += getBits(Integer.toBinaryString(register2), 4);
				currentInstruction += getBits(Integer.toBinaryString(resultRegister), 4);
			} else if (op instanceof subtractOp) {
				currentInstruction += "1111";
				int register1 = ((subtractOp) op).getRegister1();
				int register2 = ((subtractOp) op).getRegister2();
				int resultRegister = ((subtractOp) op).getResultRegister();
				currentInstruction += getBits(Integer.toBinaryString(register1), 4);
				currentInstruction += getBits(Integer.toBinaryString(register2), 4);
				currentInstruction += getBits(Integer.toBinaryString(resultRegister), 4);
			} else if (op instanceof multiplyOp) {
				currentInstruction += "0111";
				int register1 = ((multiplyOp) op).getRegister1();
				int register2 = ((multiplyOp) op).getRegister2();
				int resultRegister = ((multiplyOp) op).getResultRegister();
				currentInstruction += getBits(Integer.toBinaryString(register1), 4);
				currentInstruction += getBits(Integer.toBinaryString(register2), 4);
				currentInstruction += getBits(Integer.toBinaryString(resultRegister), 4);
			} else if (op instanceof haltOp) {
				currentInstruction += "0000000000000000";
			} else if (op instanceof interruptOp) {
				int value = ((interruptOp) op).getZeroOrOne();
				if (value == 0) {
					//this is a SOFT interrupt : Print all registers to screen
					currentInstruction += "0010000000000000";
				} else if (value == 1) {
					//this is a HARD interrupt : Print all 1024 bytes of memory to screen
					currentInstruction += "0010000000000001";
				} else {
					System.out.println("INVALID VALUE INSIDE INTERRUPT : " + value);
				}

			} else if (op instanceof jumpOp) {
				//jump instruction
				currentInstruction += "0011";
				int address = ((jumpOp) op).getAddressValue();
				//jump instruction next 12 bits will be the value of the address
				currentInstruction += getBits(Integer.toBinaryString(address), 12);
			} else if (op instanceof compareOp) {
				//compare instruction
				currentInstruction += "0100";
				int register1 = ((compareOp) op).getRegisterValue1();
				int register2 = ((compareOp) op).getRegisterValue2();
				//first 8 is register 1 because it takes the 4 0s before it as well 
				// 0100 0000 xxxx yyyy -- [0000 xxxx] is all register 1; getBits will also auto populate the missing 0s
				currentInstruction += getBits(Integer.toBinaryString(register1), 8);
				currentInstruction += getBits(Integer.toBinaryString(register2), 4);
			} else if (op instanceof branchEqualOp) {
				//Branch if equal instruction
				currentInstruction += "010111";
				int addressValue = ((branchEqualOp) op).getAddressValue();
				//Last 10 bits represents the amount to jump to
				// 0101 11xx xxxx xxxx -- x is all value
				currentInstruction += getBits(Integer.toBinaryString(addressValue), 10);
			} else if (op instanceof branchGreaterOp) {
				//Branch if greater instruction
				currentInstruction += "010110";
				int addressValue = ((branchGreaterOp) op).getAddressValue();
				//Last 10 bits represents the amount to jump to
				// 0101 10xx xxxx xxxx -- x is all value
				currentInstruction += getBits(Integer.toBinaryString(addressValue), 10);
			} else if (op instanceof branchGreaterEqOp) {
				//Branch if greater than or equal to instruction
				currentInstruction += "010101";
				int addressValue = ((branchGreaterEqOp) op).getAddressValue();
				//Last 10 bits represents the amount to jump to
				// 0101 01xx xxxx xxxx -- x is all value
				currentInstruction += getBits(Integer.toBinaryString(addressValue), 10);
			} else if (op instanceof branchNotEqualOp) {
				//Branch if not equal instruction
				currentInstruction += "010100";
				int addressValue = ((branchNotEqualOp) op).getAddressValue();
				//Last 10 bits represents the amount to jump to
				// 0101 00xx xxxx xxxx -- x is all value
				currentInstruction += getBits(Integer.toBinaryString(addressValue), 10);
			} else if (op instanceof pushOp) {
				//Push instruction
				currentInstruction += "011000";
				int register = ((pushOp) op).getRegister();
				//Last 4 bits represents the register, but the other 6 are 0s so it doesn't matter
				// 0110 00xx xxxx xxxx -- x is all value
				currentInstruction += getBits(Integer.toBinaryString(register), 10);
			} else if (op instanceof popOp) {
				//Pop instruction
				currentInstruction += "011001";
				int register = ((popOp) op).getRegister();
				//Last 4 bits represents the register, but the other 6 are 0s so it doesn't matter
				// 0110 01xx xxxx xxxx -- x is all value
				currentInstruction += getBits(Integer.toBinaryString(register), 10);
			} else if (op instanceof callOp) {
				//Call instruction
				currentInstruction += "011010";
				int addressValue = ((callOp) op).getAddress();
				//Last 10 bits represents the address to jump to
				// 0110 10xx xxxx xxxx -- x is all value
				currentInstruction += getBits(Integer.toBinaryString(addressValue), 10);
			} else if (op instanceof returnOp) {
				//Return instruction
				currentInstruction += "0110110000000000";
			} else {
				throw new IllegalArgumentException("Invalid Instruction");
			}
			//Adding the instruction to the string Array
			binaryInstructions[index] = currentInstruction;
			//Testing 
			System.out.println(binaryInstructions[index]);
			index ++;
		}
		return binaryInstructions; 
	}
	
	public static String getBits(String binary, int amount) {
		//Take a binary and returns a specific amount of bits (usually 4 or 8 or 12)
		String value = "";
		if (amount == binary.length()) {
			return binary;
		} else if (amount > binary.length()) {
			int difference = amount - binary.length();
			for (int i = 0; i < difference; i++) {
				value += "0";
			}
			value += binary;
		} else if (amount < binary.length()) {
			int difference = binary.length() - amount;
			value = binary.substring(difference);
		}
		return value;
	}
}
