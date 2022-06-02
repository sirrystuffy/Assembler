package bit;

public class longword implements ILongword{
		
	bit[] longword = new bit[32]; //collection of bits
	
	//longword constructor -- does the same as set(int value)
	public longword(int i) {
		//Set a number to binary and stick the binary value into longword
		int counter = 0;
		//Set bit values from right to left
		if (i < 0) {
			i += (i * -2); //make i positive
			for (int x = 31; x > -1; x--) {
				//quotient 
				int quotient = i / 2; 
				//modulo
				int modulo = i % 2; //remainder
				
				//Reverse the bits of longword
				if (modulo == 0) {
					longword[x] = new bit(1);
				} else {
					longword[x] = new bit(0);
				}
				
				//from right to left, look for the first 0 and change it to 1 (equivalent to "adding 1")
				if(longword[x].getValue() == 0 && counter == 0) {
					longword[x] = new bit(1);
					counter++;
				} else if (counter == 0) {
					//longword[x] is 1
					longword[x] = new bit(0);
				}
				//update int
				i = quotient;
			}
		
		} else {
			//Set bit values regularly 
			for (int x = 31; x > -1; x--) {
				//quotient 
				int quotient = i / 2; 
				//modulo
				int modulo = i % 2;
				
				longword[x] = new bit(modulo);
				//update value
				i = quotient;
			}
		}
	} 

	@Override
	public bit getBit(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("Negatives not allowed");
		}
		//get the bit at index i
		return longword[i];
	}

	@Override
	public void setBit(int i, bit value) {
		if (i < 0) {
			throw new IllegalArgumentException("Negatives not allowed");
		}
		//Set index i to a bit value
		longword[i] = value;
	}

	@Override
	public longword and(longword other) {		
		//Create longword to put resulting bits in 
		longword result = new longword(1);
		//Compare each bit in the longwords to each other using and()
		for (int i = 0; i < longword.length; i++) {
			if (longword[i].and(other.getBit(i)).getValue() == 1) {
				result.setBit(i, new bit(1));
			} else {
				result.setBit(i, new bit(0));
			}
		}
		//returning the resultant longword filled with bits
		return result;
	}

	@Override
	public longword or(longword other) {
		longword result = new longword(1);
		//Compare each bit in the longwords to each other using or()
		for (int i = 0; i < longword.length; i++) {
			if (longword[i].or(other.getBit(i)).getValue() == 0) {
				result.setBit(i, new bit(0));
			} else {
				result.setBit(i, new bit(1));
			}
		}
		return result;
	}

	@Override
	public longword xor(longword other) {
		longword result = new longword(1);
		//Compare each bit in the longwords to each other using xor()
		for (int i = 0; i < longword.length; i++) {
			if (longword[i].xor(other.getBit(i)).getValue() == 0) {
				result.setBit(i, new bit(0));
			} else {
				result.setBit(i, new bit(1));
			}
		}
		return result;
	}

	@Override
	public longword not() {
		longword result = new longword(0);
		//Use not() on each bit in longword and set resultant bit to the same value
		for(int i = 0; i < longword.length; i++) {
			if (longword[i].not().getValue() == 0){
				result.setBit(i, new bit(0));
			} else {
				result.setBit(i, new bit(1));
			}
		}
		return result;
	}

	@Override
	public longword rightShift(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Negatives not allowed");
		}
		//Shifts the longword a certain amount of times to the right
		longword result = new longword(0);
		//When (amount + i) equals array length, modulo will make position back to 0 so that array doesn't point out of bounds
		for (int i = 0; i < longword.length; i++) {
			int position = (amount + i) % longword.length;
			result.setBit(position, longword[i]);;
		}
		for (int i = 0; i < amount; i++) {
			result.setBit(i, new bit(0));
		} 
		return result;
	}

	@Override
	public longword leftShift(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Negatives not allowed");
		}
		//Shifts the longword a certain amount of times to the left
		longword result = new longword(0);
		//When (amount + i) equals array length, modulo will make position back to 0 so that array doesn't point out of bounds
		//I swapped the position index and i index so that the order reverses from [left -> right] to [right -> left]
		for (int i = 0; i < longword.length; i++) {
			int position = (amount + i) % longword.length;
			result.setBit(i, longword[position]);
		}
		//temp strategy to fill 0's in slots 
		for (int i = 0; i < amount; i++) {
			result.setBit(31 - i, new bit(0));
		} 
		return result;
	}
	@Override
	public String toString() {
		String s = "";
 		for (int i = 0; i < longword.length; i++) {
			s += String.valueOf(longword[i]); 
			//subtract 2 because you subtract one for the last index and subtract one for the comma that would appear at the end
			if (s.length() > (longword.length * 2 - 2)) {
				//break from loop before the last comma is added 
				break; 
			}
			s += ",";//Add comma after value 
		} 
 		return s;
	}
	@Override
	public long getUnsigned() {
		//return the value of the longword as a long
		long sum = 0L;
		long factor = 1L;
		//from right to left, turn each bit into a long and add them 
		for (int i = 31; i > -1; i--) {
			sum += (longword[i].getValue() * factor);
			factor *= 2; //power of two
		}
		return sum;
	}

	@Override
	public int getSigned() {
		//returns the value of this longword as an int
		int sum = 0;
		int factor = 1;
		//from right to left, turn each bit into an integer and add them 
		for (int i = 31; i > -1; i--) {
			sum += (longword[i].getValue() * factor);
			factor *= 2;
		}
		return sum;
	}

	@Override
	public void copy(longword other) {
		//copies bits from other longword into this longword
		for (int i = 0; i < longword.length; i++) {
			longword[i] = other.getBit(i);
		}
	}

	@Override
	public void set(int value) {
		//Add one to the value passed and then reverse the bits.
		//Set a number to binary and stick the binary value into longword
		int counter = 0;
		if (value < 0) {
			value += (value * -2); //make value into positive
			//Set bit values from right to left
			for (int x = 31; x > -1; x--) {
				//quotient 
				int quotient = value / 2; 
				//modulo = remainder
				int modulo = value % 2; //remainder determines what value to store in longword
				
				//Set the reverse bits of longword
				if (modulo == 0) {
					longword[x] = new bit(1);
				} else {
					longword[x] = new bit(0);
				}
				
				//from right to left, look for the first 0 and change it to 1 (equivalent to "adding 1")
				if(longword[x].getValue() == 0 && counter == 0) {
					longword[x] = new bit(1);
					counter++;//increment counter so that if statement won't run more than once
				} else if (counter == 0) {
					//longword[x] is 1
					longword[x] = new bit(0);
				}
				
				//update value
				value = quotient;
			}
		
		} else {
			//Set bit values regularly 
			for (int x = 31; x > -1; x--) {
				//quotient 
				int quotient = value / 2; 
				//modulo
				int modulo = value % 2;
				//remainder determines what value to store in longword
				longword[x] = new bit(modulo);
				//update value
				value = quotient;
			}
		}
	}

}
