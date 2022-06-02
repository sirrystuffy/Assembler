package bit;

public class rippleAdder {

	//Add two longwords and return the result in a longword
	public static longword add(longword a, longword b) {
		//this longword will store the resulting bits from the addition
		longword c = new longword(0);
		//initialize the carry to 0 so that first comparison doesn't carry anything
		bit carry = new bit(0);
		//Start from rightmost bit to leftmost bit
		for (int i = 31; i > -1; i--) {
			if (carry.getValue() == 1) {
				//xor the first bit and carry 
				bit temp = carry.xor(a.getBit(i));
				//final answer will be the result of xor of temp and second bit
				bit value = temp.xor(b.getBit(i));
				c.setBit(i, value);
				//if both a and b is 0 then carry changes to 0 since (1, 0, 0) is the only set that doesn't carry anything (or carries 0) 
				if (a.getBit(i).getValue() != 1 && b.getBit(i).getValue() != 1) {
					carry = new bit(0);
				} 
			} else { 
				//carry = 0
				bit value = a.getBit(i).xor(b.getBit(i));
				c.setBit(i, value);
				//only change if both values are 1 because (1, 1) will carry 1 
				if (a.getBit(i).getValue() == 1 && b.getBit(i).getValue() == 1) {
					carry = new bit(1);
				} 
			}
		}
		return c;
	}
	//Find the 2's complement of longword b and then add the two longwords
	public static longword subtract(longword a, longword b) {
		//this longword will store the resulting bits from the addition
		longword c = new longword(0);
		//check when 1 is added
		int counter = 0;
		//Re-writing longword b to its two's complement
		for (int i = 31; i > -1; i--) {
			//not() every bit in b 
			bit value = b.getBit(i).not();
			//"Add 1", if applicable, to complete 2's complement
			if (value.getValue() == 0 && counter == 0) {
				value.set(1);
				counter++;//increment to stop adding 1s
			} 
			//If counter is still 0 then I haven't added one yet, so set the current bit to 0
			else if (counter == 0){ 
				value.set(0);
			}
			//setting the bits of the second longword
			b.setBit(i, value);
		}
		//return the sum
		c = rippleAdder.add(a, b);
		return c;
	}
}
