package bit;

public class Multiplier {
	//using add and shift method. 
	public static longword multiply(longword a, longword b) {
		longword temp = new longword(0);
		//Count the amount of shifts required after each line (omitting first line so start with 1)
		int count = 1;
		//temp is either going to be (longword a) or longword(0)
		if (b.getBit(31).getValue() == 1) {
			temp = a;
		} else {
			//Don't need to do anything because temp is initialized to longword(0) 
		}
		//Either add temp to longword a [with left shift] or add 0
		for (int i = 30; i > -1; i--) {
			if (b.getBit(i).getValue() == 1) {
				temp = rippleAdder.add(temp, a.leftShift(count));
			} else {
				temp = rippleAdder.add(temp, new longword(0));
			}
			count++;//increment for amount of shifts 
		}
		return temp;
	}
	
}
