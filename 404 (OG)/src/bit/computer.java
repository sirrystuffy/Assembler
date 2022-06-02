package bit;

import java.util.Stack;

public class computer {
	private Memory member = new Memory();
	//Program Counter
	private longword pc = new longword(0);
	// 16 registers 
	private longword[] register = new longword[16];
	private bit halt = new bit(1);//Default OFF
	//longwords 
	longword currentInstruction, opCode = new longword(0), op1 = new longword(0), op2 = new longword(9999), op3 = new longword(0), result = new longword(0);
	//bit array for compare instruct -- default not equal
	bit[] theseBits = {new bit(0), new bit(0)};
	//Stack Pointer - points an address in memory to write the next stack 
	longword sp = new longword(1020);
	public void run() {
		//Initialize to 0 to not get Null Pointer Exception
		for (int i = 0; i < register.length; i++) {
			register[i] = new longword(0);
		}
		//Run while loop as long as bit is not 0
		while (halt.getValue() != 0) {
			fetch();
			decode();
			execute();
			store();
		}
	}
	
	private void fetch() {
		//Read the next longword in memory -- from memory[0] to memory [32]
		currentInstruction = Memory.read(pc);	
		//System.out.println("current instruction " + currentInstruction);
		//Increment PC by 4 after every call for next instruction
		pc = rippleAdder.add(pc, new longword(4));
	}
	
	private void decode() {
		//Start at index 16 because we only care about last 16 bits 
		int start = 16;
		//We just need the 4 bits to determine what operation to do 
		opCode.set(getAndSet(start, 4).getSigned()); //Operation 
		start += 4; //Increment by 4 because previous values were already set
		
		if (compareBits(opCode, 1)) {
			//move instruction 
			
			//Locate the target register where the value will go
			op1.set(getAndSet(start, 4).getSigned());
			start += 4;
			//the rest of the 8-bits will be the value to move into the target register
			op2.set(getAndSet(start, 8).getSigned());
		} else if (compareBits(opCode, 3)) {
			//This is the code for JUMP
			
			//The next 12 bits represent the value of the address we want to jump to
			op3.set(getAndSet(start, 12).getSigned());
		} else if (compareBits(opCode, 4)) {
			//This is the code for COMPARE
			
			op1 = register[getAndSet(start, 8).getSigned()]; // The longword value at register[x] is op1
			start += 8; //Increment by 8 because we already read these bits
			op2 = register[getAndSet(start, 4).getSigned()]; // The longword value at register[x] is op2
			
		} else if (compareBits(opCode, 5)) {
			//This is the code for BRANCH
			
			//Have op1 hold the next two bits which will determine which branch instruction this is -- easy to check using compareBits()
			op1 = getAndSet(start, 2);
			start += 2; //We already checked these bits !
			//op2 is going to hold the value of the address we need to branch 
			op2 = getAndSet(start, 10);
			
		} else if (compareBits(opCode, 6)) {
			//This is the opCode for CALL/RETURN/PUSH/POP 
			op1 = (getAndSet(start, 2)); //returns a longword containing the value of the next two bits
			start += 2; //We already checked these bits !
			//op2 is going to hold the register/address bits
			op2 = getAndSet(start, 10);
		}
		else {
			//Store the values of the registers from instruction as longwords 
			op1 = register[getAndSet(start, 4).getSigned()]; // The longword value at register[x] is op1
			start += 4; //Increment by 4
			
			op2 = register[getAndSet(start, 4).getSigned()]; // The longword value at register[x] is op2
			start += 4; //Increment by 4
			
			//No need to access register here because this value is obtained after execute()
			//We just need the 4 bits in a longword type to know where the third register is located
			op3.set(getAndSet(start, 4).getSigned()); //Target Register
		}
	}
	private void execute() {
		
		//Check if the instruction is a HALT instruction (0, 0, 0, 0) or 
		if (compareBits(opCode, 0)) {
			halt = new bit(0); //Turn halt off so that computer doesn't loop again.
		} else 
		//Check if the instruction is a MOVE instruction (0, 0, 0, 1) or 
		if (compareBits(opCode, 1)) {
			result = op2;
		} else 
		//Check if the instruction is an INTERRUPT instruction (0, 0, 1, 0) 
		if (compareBits(opCode, 2)) {
			//check op3 to know which interrupt it is (hard or soft)
			if (compareBits(op3, 0)) {
				System.out.println("Interrupt(0) performed !\n-------------------------");
				//print all registers to the screen
				for (int i = 0; i < register.length; i++) {
					System.out.println("Register(" + i + ") : " + register[i]);
				}
			} else if (compareBits(op3, 1)) {
				//print all 1024 bytes of memory 
				System.out.println("\nOutputting Memory . . .\n");
				for (int i = 0; i < 1024; i += 4) {
					System.out.println(Memory.read(new longword(i)).toString());
				}
				op3 = new longword(0); //reset op3 before store()
			}
			result = new longword(0); //reset result before store()
		} else if (compareBits(opCode, 3)) {
			//This is a JUMP instruction
			//Set result value to jump value 
			result = op3;
		} else if (compareBits(opCode, 4)) {
			//This is a COMPARE instruction
			// do (register x) - (register y)
			int difference = op1.getSigned() - op2.getSigned();
			//int difference = (rippleAdder.subtract(op1, op2)).getSigned();
			if (difference > 0) {
				//op1 (register x) is greater than op2 (register y)
				// (1, 0) means x > y
				theseBits[0] = new bit(1); //Only care about this bit
				theseBits[1] = new bit(0);
			} else if (difference == 0) {
				//op1 (register x) is equal to op2 (register y)
				theseBits[0] = new bit(1);
				theseBits[1] = new bit(1); //Only care about this bit
			} else {
				//x is less than y and x is not equal to y
				theseBits[0] = new bit(0);
				theseBits[1] = new bit(0); //Only care about this bit
			}
			//We don't need to check for less than because if x < y, we can just compare y > x to see if x < y is true. 
			
			System.out.println("We are comparing values " + op1.getSigned() + " and " + op2.getSigned() 
								+"\nthe difference is : " + difference 
								+"\nThe bit array is currently: " + theseBits[0] + ", " + theseBits[1]);
		} else if (compareBits(opCode, 5)) {
			//This is the code for BRANCH
			/* 
			 * SHORTCUT KEY TO UNDERSTANDING WHAT THE BITS REPRESENT 
			 * -------------------------------------------------------
			 * IF FIRST BIT IS 0 : X < Y 
			 * IF FIRST BIT IS 1 : X > Y
			 * 
			 * IF SECOND BIT IS 0 : X != Y
			 * IF SECOND BIT IS 1 : X == Y
			 * 
			 * IF CONDITION CODE IN THE INSTRUCTION IS 01, CONDITION TO CHECK FOR IS : X >= Y
			 * HERE WE WILL FIRST CHECK IF X > Y AND THEN CHECK IF X == Y --- IF EITHER IS TRUE WE WILL BRANCH 
			 */
			
			//Remember, op1 stores the condition code !!
			if (compareBits(op1, 3)) {
				//[1, 1]
				//this is a branchIfEqual operation
				//Check if the second bit value is a 1 to determine whether the two operands are equal.
				if (theseBits[1].getValue() == 1) {
					//The branch is taken because x == y, 
					//so let's store the value of the address to "result" and go to store() to change pc to advance forward/backward.
					result = op2;
				} else {
					//Error -- exit the method 
					//result = null;
					return;
				}
				
			} else if (compareBits(op1, 2)) {
				//[1, 0]
				//this is a branchIfGreaterThan operation
				//Check if the first bit value is 1 !
				if (theseBits[0].getValue() == 1) {
					//The branch is taken because x > y, 
					//so let's store the value of the address to "result" and go to store() to change pc to advance forward/backward.
					result = op2;
				} else {
					//Error, did not get expected values -- exit the method 
					return;
				}
				
			} else if (compareBits(op1, 1)) {
				//[0, 1]
				//WE DONT CHECK IF BIT ARRAY CONTAINS [0, 1] !
				//this is a branchIfGreaterThanOREQUAL operation -- x >= y
				//the condition code is 0 and 1, now we have to check if the bit array contains: [1, 0] or [1, 1]. EITHER of these will result in a branch
				//Testing the two conditions above ^^ in the same method 
				if (theseBits[0].getValue() == 1) {
					//The branch is taken because x is greater than y, 
					//so let's store the value of the address to "result" and go to store() to change pc to advance forward/backward.
					result = op2;
				} else if (theseBits[1].getValue() == 1) {
					//The branch is taken because x is equal to y.
					result = op2;
				} else {
					//Error, did not get expected values (1, 0) -- exit the method 
					return;
				}
			} else if (compareBits(op1, 0)) {
				//[0, 0]
				//this is a branchIfNotEqual operation
				//Check if the second bit value is 0
				if (theseBits[1].getValue() == 0) {
					//The branch is taken because 
					// *** x is less than y OR x is greater than y ***
					//so let's store the value of the address to "result" and go to store() to change pc to advance forward/backward.
					result = op2;
				} else {
					//Error: x is equal to y -- exit the method !
					return;
				}
			}
			
		} else if (compareBits(opCode, 6)) {
			//This is the code for PUSH/POP/CALL/RETURN
			if(compareBits(op1, 0)) {
				//This is a PUSH
				//set result to the value of the register (value contained in R1, R4, R10, etc. )
				result = register[op2.getSigned()];
			} else if (compareBits(op1, 1)) {
				//This is a POP
				//set result to the number of the register (R1, R4, R10, etc. ) which we are going to populate from stack.
				result = op2;
			} else if (compareBits(op1, 2)) {
				//This is a CALL
				//this is how the next instruction will be pushed into stack :
				//Since the pc is incremented by 4 due to fetch(), pc immediately points to the next instruction so: Push current pc onto stack
				//After the called instruction is completed, we want to jump back to the instruction after the call
				//We need to push pc to stack before it gets updated to the address we want to jump to, or else we won't get the right instruction
				Memory.write(sp, pc);
				//Result now stores the address we want 
				result = op2;
				
			} else if (compareBits(op1, 3)) {
				//This is a RETURN 
			} 
		} else {
			//This instruction is an ALU instruction
			//Create a bit array to store the opCode values 
			bit[] controlBits = new bit[4];
			//Stores from right to left -- just for bitArray for the ALU
			for (int i = 0; i < controlBits.length ; i++) {
				controlBits[i] = opCode.getBit(28 + i);
			}
			//Store result of ALU operation in a result longword
			result = ALU.doOP(controlBits, op1, op2);
			System.out.println("result is " + result);
		}
		// or . . . get rid of for loop and create a new bit array in doOP call :
		//result = ALU.doOP(new bit[] {opCode.getBit(28), opCode.getBit(29), opCode.getBit(30), opCode.getBit(31)}, op1, op2);
	}
	private void store() {
		//Storing result from ALU into the appropriate register given by instruction
		//Don't do anything for halt instruction or interrupts and COMPARE FOR NOW
		if (halt.equals(new bit(0)) || compareBits(opCode, 2) || compareBits(opCode, 4)) {
			return;
		} else if (compareBits(opCode, 3)) {
			//JUMP instruction . . .
			//We need to change the pc based on the jump value (which is stored in result)
			//fetch will read the instruction at memory address pc and return the longword (currentInstruction) at that address
			//If jump value is less than current pc value, we will infinitely loop the string array -- Prof said this is okay 
			pc = new longword(result.getSigned());
			
		} else if (compareBits(opCode, 5)) {
			//BRANCHING INSTRUCTION was valid since result is not null
			//THIS IS WHERE WE WILL JUMP/BRANCH TO THE ADDRESS -- >  
			
			//fetch() increments by 4 automatically, so we need to subtract 4 before we branch so that the user doesn't have to account for it
			
			if (result.getSigned() > 0) {
				//positive sign, so we will move forward
				pc = rippleAdder.subtract(pc, new longword(4));
				pc = rippleAdder.add(pc, result);
				
			} else if (result.getSigned() < 0) {
				//negative sign extension, so we will go backwards
				pc = rippleAdder.subtract(pc, new longword(4));
				pc = rippleAdder.subtract(pc, result);
			} else {
				//result is 0; pc doesn't change
				
				//System.out.println("pc is : " + pc.getSigned());
			}
			
		} else if (compareBits(opCode, 6)) {
			if (compareBits(op1, 0)) {
				System.out.println("jelly");
				// -- PUSH -- 
				//stack pointer is just a point in memory - push will be writing to that point in memory
				Memory.write(sp, result);
				//We are reading/writing 4 bytes into memory so our stack pointer goes down (increasing size of stack) - points to the next place to write.
				sp = rippleAdder.subtract(sp, new longword(4));
			} else if (compareBits(op1, 1)) {
				// -- POP -- 
				//Take the value in the stack and move it to the register
				//I have to add 4 to sp to get the first thing in stack ! Since push makes space for another push.
				sp = rippleAdder.add(sp, new longword(4));
				register[result.getSigned()] = Memory.read(sp);
				//By writing 0 to memory, we are essentially clearing the value of the address so stack won't contain the value we just read to register. 
				Memory.write(sp, new longword(0));
			} else if (compareBits(op1, 2)) {
				// -- CALL -- 
				//jump to the address that we are calling
				if (result.getSigned() > 0) {
					//positive sign, so we will move forward
					pc = rippleAdder.subtract(pc, new longword(4)); //Subtract 4 from pc because it's easier to see where we jump to, instead of subtracting 4 every time we jump. 
					pc = rippleAdder.add(pc, result);
					
				} else if (result.getSigned() < 0) {
					//negative sign extension, so we will go backwards
					pc = rippleAdder.subtract(pc, new longword(4));
					pc = rippleAdder.subtract(pc, result);
				} else {
					//result is 0; pc doesn't change
				}
				//Make space for another push -- only needed because when we call an actual function, that function will pop the first thing on the stack to get to the parameters
				//So, we have to make our call jump to a pop, to reset the stack pointer.
				sp = rippleAdder.subtract(sp, new longword(4));
			} else if (compareBits(op1, 3)) {
				// -- RETURN -- 
				//We pop from the stack -- at this point it should be an address (pc) -- and make the address our next read 
				sp = rippleAdder.add(sp, new longword(4));
				longword originalAddress = Memory.read(sp);
				System.out.println("addy : " + originalAddress.getSigned());
				//jump back to original address by updating pc
				pc = originalAddress;
				//Remove value from the stack
				Memory.write(sp, new longword(0));
			} 
	
		} else if (compareBits(opCode, 1)) {
			//THIS IS FOR MOVE
			//getSigned is not used here to check opCode, rather it is used to find target register 
			register[op1.getSigned()].set(result.getSigned());
		} else {
			//getSigned is not used here to check opCode, rather it is used to find target register 
			register[op3.getSigned()].set(result.getSigned());
		}
		//reset
		op1 = new longword(0);
		op2 = new longword(0);
		op3 = new longword(0);
		result = new longword(0);
	}
	public void preload(String[] strArray) {
		//address where we want to start writing memory to
		longword addy = new longword(0);
		//We want to recursively get the next string and write it to memory
		for (int i = 0; i < strArray.length; i++) {
			int value = stringToIntNew(strArray[i]);
			Memory.write(addy, new longword(value));
			//Increment address so we write to a different index of memory every time
			addy = rippleAdder.add(addy, new longword(4));
		}
		//if odd amount of instructions, write a "0" longword
		if (strArray.length % 2 != 0) {
			Memory.write(addy, new longword(0));
		}

	}
	//Stores a number of bits from instruction longword, starting at the index passed, into the last 4 (or 8) indexes of another longword
	private longword getAndSet(int startingIndex, int limit) {
		//Create and initialize a longword set to 0
		longword temp = new longword(0);
		/* Error - when checking if the first bit is 1, it doesn't check the sign bit when the binary number is greater than the allocated bits.
		For value 10, its binary number is 1010 and we allocated 8 bits for it, so we read 00001010. Signed complement is 0000000000001010.
		For value 1000, its binary number is 1111101000. More bits than we can allocate (10>8) so only 11101000 is read. Signed complement is 0000001111101000. 
		I want to read the signed complement value instead of the binary value. */
		
		//Implementing the sign extension for negative values in move instruction / branch instruction
		if (currentInstruction.getBit(startingIndex).getValue() == 1 && (limit == 8 || limit == 10)) {
			//Value is negative, so we want to sign extend . . .
			for (int i = 0; i < startingIndex; i++) {
				//Extend sign by making all other bits 1
				temp.setBit(i, new bit(1));
			}
		} 
		//value is positive and 0s are already initialized
		//Set bits from instruction to temp
		for (int i = 0; i < limit; i++) {
			temp.setBit(32 - limit + i, currentInstruction.getBit(startingIndex + i));

		}
		//returns a longword of the 4 bits we took from currentInstruction 
		return temp;
	}
	private int stringToInt(String str) {
	// * * ** THIS IS FOR TESTING WITH LONGWORD.TOSTRING AND THIS METHOD ACCOUNTS FOR COMMAS !! * * * 
		
		longword temp = new longword(0);
		//add a counter to keep track of temp index
		int counter = 0;
		//string has commas so we have to start at 32 instead of 16 ***change this to 16 when assembler is done bc it doesn't have commas***
		for (int i = 32; i < str.length(); i++) {
			if (str.charAt(i) == '0') {
				//Found a 0 !
				temp.setBit(16 + counter, new bit(0));
				counter++; //Only increment counter when we find a 0 or 1, instead of incrementing with the index

			} else if (str.charAt(i) == '1') {
				//Found a 1 !
				temp.setBit(16 + counter, new bit(1));
				counter++; //Only increment counter when we find a 0 or 1, instead of incrementing with the index
			}
		}
		return temp.getSigned();
	}
	private int stringToIntNew(String str) {
		longword temp = new longword(0);
		
		for (int i = 0; i < str.length() ; i++) {
			
			if (str.charAt(i) == '0') {
				//Found a 0 !
				temp.setBit(16 + i, new bit(0));

			} else if (str.charAt(i) == '1') {
				//Found a 1 !
				temp.setBit(16 + i, new bit(1));
			}
			
		}
		
		return temp.getSigned();
	}
	public static boolean compareBits(longword temp, int value) {
		//compare each bit of the longword to the value longword and return true if they match
		longword valueLongword = new longword(value);
		for (int i = 0; i < 32; i++) {
			//If even one bit is wrong, it must not be true
			if (temp.getBit(i).getValue() != valueLongword.getBit(i).getValue()) {
				return false;
			}
		}
		return true;
	}
}
