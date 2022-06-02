package bit;

public class bit implements IBit{
	private int bit;
	
	//constructor to make bit(0) or bit(1)
	public bit(int i) {
		bit = i;
	}

	@Override
	public void set(int value) {
		// sets the value of the bit
		//Quits if value passed isn't 0 or 1
		if (value != 0) {
			if(value != 1) {
				System.out.println("ERROR: Must set bit value to 0 or 1");
				System.exit(1);
			}
		}
		bit = value;
	}

	@Override
	public void toggle() {
		// changes the value from 0 to 1 or 1 to 0
		if(bit == 0) {
			bit = 1;
		} else { 
			bit = 0;
		}
	}

	@Override
	public void set() {
		// sets the bit to 1
		bit = 1;
	}

	@Override
	public void clear() {
		// sets the bit to 0
		bit = 0;
	}

	@Override
	public int getValue() {
		// returns the current value
		return bit;
	}

	@Override
	public bit and(bit other) {
		// performs and on two bits and returns a new bit set to the result
		//Only return 1 if both bits are 1, otherwise return 0
		if (bit == 1) {
			if (other.getValue() == 1) {
				return new bit(1);
			}
		}
		return new bit(0); 
	}

	@Override
	public bit or(bit other) {
		// performs or on two bits and returns a new bit set to the result
		//Only return 0 if both bits are 0, otherwise return 1
		if (bit == 0) {
			if (other.getValue() == 0) {
				return new bit(0);
			}
		}
		return new bit(1);
	}

	@Override
	public bit xor(bit other) {
		// performs xor on two bits and returns a new bit set to the result
		if (bit == other.getValue()) {
			return new bit(0);
		}
		//The two bits are different: {0, 1} or {1, 0}, so it returns true
		return new bit(1);
	}

	@Override
	public bit not() {
		// performs not on the existing bit, returning the result as a new bit
		if (bit == 0) {
			return new bit(1);
		}
		//bit has to be 1, so return 0
		return new bit(0);
	}
	@Override
	public String toString() {
		//Returns "0" or "1"
		if (bit == 1) {
			return "1";
		}
		return "0";
	}
}
