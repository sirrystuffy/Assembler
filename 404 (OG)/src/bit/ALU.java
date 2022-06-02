package bit;

public class ALU {
	public static longword doOP(bit[] operation, longword a, longword b) {
		if (operation.length != 4) {
			throw new IllegalArgumentException("MUST HAVE EXACTLY 4 NUMBERS ");
		}
		//empty string to put values in
		String str = "";
		//use a string to store values which represent the operation 
		for (int i = 0; i < operation.length; i++) {
			//bit array only accepts bit(1) and bit(0), so don't have to throw error here if bit value isn't 1 or 0
			if (operation[i].getValue() == 1) {
				str += "1";
			} else {
				str += "0";
			} 
		}
		//System.out.println(str);

		switch(str) {
			case "1000": 
				return a.and(b);
			case "1001" : 
				return a.or(b);
			case "1010" :
				return a.xor(b);
			case "1011" :
				return a.not();
			case "1100" :
				//left shift longword a [b amount of times] and only care about the last 5 bits so everything else is 0
				int Lamount = b.getSigned();
				if (Lamount < 0) {
					throw new IllegalArgumentException("Negatives not allowed");
				}
				longword answer = new longword(0);
				//We only care about the lowest 5 bits, so if all 5 are moved, then the longword is just 0s
				//Edit: Prof said check for 5 bits 
				if (Lamount >= 5) {
					return answer;
				} 
				//set a to its left-shift representation
				a = a.leftShift(Lamount);
				//only take the lowest 5 bits
				for (int i = 27; i < 32; i++) {
					answer.setBit(i, a.getBit(i));
				}
				return answer;
			case "1101" :
				//right shift longword a [b amount of times] and only care about the last 5 bits so everything else is 0
				int Ramount = b.getSigned();
				//Don't take any negatives
				if (Ramount < 0) {
					throw new IllegalArgumentException("Negatives not allowed");
				}
				longword result = new longword(0);
				//We only care about the lowest 5 bits, so if all 5 are moved, then the longword is just 0s
				//Edit: Prof said check for 5 bits
				if (Ramount >= 5) {
					return result;
				} 
				//set a to its right-shift representation
				a = a.rightShift(Ramount);
				//only take the lowest 5 bits
				for (int i = 27; i < 32; i++) {
					result.setBit(i, a.getBit(i));
				}
				return result;
			case "1110" :
				return rippleAdder.add(a, b);
			case "1111" :
				return rippleAdder.subtract(a, b);
			case "0111" :
				return Multiplier.multiply(a, b);
			default : 
				//Invalid operation
				System.out.println("Invalid input in bit array ");
		}
		return null;
	}
	
}
