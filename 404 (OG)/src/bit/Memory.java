package bit;

public class Memory {
	//memory size is byte size times amount of bytes 
	private static bit[] memory = new bit[Byte.SIZE * 1024]; //8192 bits
	//default constructor to initialize the values to zero
	public Memory() {
		for (int i = 0; i < memory.length; i++) {
			memory[i] = new bit(0);
		}
	}
	//take a longword; convert it to a number; which will be the index (8*value of address); get the next 32 bits 
	public static longword read(longword address) {
		int counter = 0;
		//resultant longword 
		longword result = new longword(0);
		//index = address * 8
		int index = address.getSigned() * 8;
		//Get the next 32 bits 
		for (int i = index; i < index + 32; i++) {
			result.setBit(counter, memory[i]); //Read bits from memory and store in longword
			counter++; //increment
		}
		return result;
	}
	public static longword write(longword address, longword value) {
		//set the address to the value
		int counter = 0;
		longword result = new longword(0);
		//Get the index
		int index = address.getSigned() * 8;
		//Loop to add the next 32 bits
		for (int i = index; i < index + 32; i++) {
			memory[i] = value.getBit(counter); // add value of bit to index in memory
			result.setBit(counter, memory[i]); // add value from memory to a longword
			counter++; //increment
		}
		return result;
	}
	public static int length() {
		return memory.length;
	}
}
