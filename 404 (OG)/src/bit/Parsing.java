package bit;

import java.util.ArrayList;
import java.util.List;

public class Parsing {
	private static List<String> List;
	
	public Parsing(List<String> lexemes) {
		Parsing.List = lexemes;
	}
	public static List<Operation> parse() throws Exception {
		List<Operation> parsedList = statements();
		return parsedList;
	}
	public static String matchAndRemove(String tokenType) {
		//Checks if the token matches the first element in List,
		if (List.get(0).equals(tokenType)) {
			List.remove(0);
			return tokenType; //return token because it matched
		}
		return null; //return null because it did not match
	}
	public static List<Operation> statements() throws Exception {
		//Keep adding statements to list until there are no more valid statements
		List<Operation> operationsList = new ArrayList<Operation>();
		Operation currentStatement = statement();
		if (currentStatement == null) {
			return null;
		}
		//Statement is found !
		Operation temp = currentStatement;
		while (temp != null) {
			operationsList.add(temp);
			temp = statement();
		}
		return operationsList;
	}
	public static Operation statement() throws Exception {
		//Check every possible statement 
		Operation moveStatement = moveInstruction();
		if (moveStatement != null) {
			return moveStatement;
		}
		Operation andStatement = andInstruction();
		if (andStatement != null) {
			return andStatement;
		}
		Operation orStatement = orInstruction();
		if (orStatement != null) {
			return orStatement;
		}
		Operation xorStatement = xorInstruction();
		if (xorStatement != null) {
			return xorStatement;
		}
		Operation notStatement = notInstruction();
		if (notStatement != null) {
			return notStatement;
		}
		Operation leftShiftStatement = leftShiftInstruction();
		if (leftShiftStatement != null) {
			return leftShiftStatement;
		}
		Operation rightShiftStatement = rightShiftInstruction();
		if (rightShiftStatement != null) {
			return rightShiftStatement;
		}
		Operation addStatement = addInstruction();
		if (addStatement != null) {
			return addStatement;
		}
		Operation subtractStatement = subtractInstruction();
		if (subtractStatement != null) {
			return subtractStatement;
		}
		Operation multiplyStatement = multiplyInstruction();
		if (multiplyStatement != null) {
			return multiplyStatement;
		}
		Operation haltStatement = haltInstruction();
		if (haltStatement != null) {
			return haltStatement;
		}
		Operation interruptStatement = interruptInstruction();
		if (interruptStatement != null) {
			return interruptStatement;
		}
		Operation jumpStatement = jumpInstruction();
		if (jumpStatement != null) {
			return jumpStatement;
		}
		Operation compareStatement = compareInstruction();
		if (compareStatement != null) {
			return compareStatement;
		}
		//Stack !
		Operation pushStatement = pushInstruction();
		if (pushStatement != null) {
			return pushStatement;
		}
		Operation popStatement = popInstruction();
		if (popStatement != null) {
			return popStatement;
		}
		Operation callStatement = callInstruction();
		if (callStatement != null) {
			return callStatement;
		}
		Operation returnStatement = returnInstruction();
		if (returnStatement != null) {
			return returnStatement;
		}
		//Branches !
		Operation branchEqualStatement = branchEqualInstruction();
		if (branchEqualStatement != null) {
			return branchEqualStatement;
		}
		Operation branchGreaterStatement = branchGreaterInstruction();
		if (branchGreaterStatement != null) {
			return branchGreaterStatement;
		}
		Operation branchGreaterEqStatement = branchGreaterEqInstruction();
		if (branchGreaterEqStatement != null) {
			return branchGreaterEqStatement;
		}
		Operation branchNotEqualStatement = branchNotEqualInstruction();
		if (branchNotEqualStatement != null) {
			return branchNotEqualStatement;
		}
		
		//None of these statements were valid ? Damn, null it is :/
		return null;
	}

	
	public static MoveOp moveInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(MOVE)") != null) {
			//Move operation is found !
			//Next, Find a register to move the value to 
			int thisRegister = findRegister();
			if (thisRegister == 12345) {
				throw new Exception ("Register NOT FOUND !");
			}
			//Register Found !
			//Next, find number 
			int thisNumber = findNumber();
			if (thisNumber == 12345) {
				throw new Exception ("Number NOT FOUND !");
			}
			//Number is found !
			//Move Operation success !
			return new MoveOp(thisRegister, thisNumber);
		}
		return null;
	}
	public static AndOp andInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(AND)") != null) {
			//And operation is found !
			//Next, Find the first register
			int firstRegister = findRegister();
			if (firstRegister == 12345) {
				throw new Exception ("First Register NOT FOUND !");
			}
			//First Register found !
			//Next, find the second Register
			int secondRegister = findRegister();
			if (secondRegister == 12345) {
				throw new Exception ("Second Register NOT FOUND !");
			}
			//Second Register found !
			//Next, find the result Register
			int finalRegister = findRegister();
			if (finalRegister == 12345) {
				throw new Exception ("Result Register NOT FOUND !");
			}
			//Final Register found !
			//And Operation Success !
			return new AndOp(firstRegister, secondRegister, finalRegister);
		}
		return null;
	}
	private static OrOp orInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(OR)") != null) {
			//Or operation is found !
			//Next, Find the first register
			int firstRegister = findRegister();
			if (firstRegister == 12345) {
				throw new Exception ("First Register NOT FOUND !");
			}
			//First Register found !
			//Next, find the second Register
			int secondRegister = findRegister();
			if (secondRegister == 12345) {
				throw new Exception ("Second Register NOT FOUND !");
			}
			//Second Register found !
			//Next, find the result Register
			int finalRegister = findRegister();
			if (finalRegister == 12345) {
				throw new Exception ("Result Register NOT FOUND !");
			}
			//Final Register found !
			//Or Operation Success !
			return new OrOp(firstRegister, secondRegister, finalRegister);
		}
		return null;
	}
	private static xorOp xorInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(XOR)") != null) {
			//Xor operation is found !
			//Next, Find the first register
			int firstRegister = findRegister();
			if (firstRegister == 12345) {
				throw new Exception ("First Register NOT FOUND !");
			}
			//First Register found !
			//Next, find the second Register
			int secondRegister = findRegister();
			if (secondRegister == 12345) {
				throw new Exception ("Second Register NOT FOUND !");
			}
			//Second Register found !
			//Next, find the result Register
			int finalRegister = findRegister();
			if (finalRegister == 12345) {
				throw new Exception ("Result Register NOT FOUND !");
			}
			//Final Register found !
			//Xor Operation Success !
			return new xorOp(firstRegister, secondRegister, finalRegister);
		}
		return null;
	}
	private static NotOp notInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(NOT)") != null) {
			//Not operation is found !
			//Next, Find the first register
			int firstRegister = findRegister();
			if (firstRegister == 12345) {
				throw new Exception ("First Register NOT FOUND !");
			}
			//First Register found !
			//Next, find the final Register
			int finalRegister = findRegister();
			if (finalRegister == 12345) {
				throw new Exception ("FInal Register NOT FOUND !");
			}
			//Second Register found !
			//Not Operation Success !
			return new NotOp(firstRegister, finalRegister);
		}
		return null;
	}
	private static leftShiftOp leftShiftInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(LEFTSHIFT)") != null) {
			//Left Shift operation is found !
			//Next, Find the first register
			int firstRegister = findRegister();
			if (firstRegister == 12345) {
				throw new Exception ("First Register NOT FOUND !");
			}
			//First Register found !
			//Next, find the second Register
			int secondRegister = findRegister();
			if (secondRegister == 12345) {
				throw new Exception ("Second Register NOT FOUND !");
			}
			//Second Register found !
			//Next, find the result Register
			int finalRegister = findRegister();
			if (finalRegister == 12345) {
				throw new Exception ("Result Register NOT FOUND !");
			}
			//Final Register found !
			//leftShiftOp Operation Success !
			return new leftShiftOp(firstRegister, secondRegister, finalRegister);
		}
		return null;	
	}
	private static rightShiftOp rightShiftInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(RIGHTSHIFT)") != null) {
			//Right Shift operation is found !
			//Next, Find the first register
			int firstRegister = findRegister();
			if (firstRegister == 12345) {
				throw new Exception ("First Register NOT FOUND !");
			}
			//First Register found !
			//Next, find the second Register
			int secondRegister = findRegister();
			if (secondRegister == 12345) {
				throw new Exception ("Second Register NOT FOUND !");
			}
			//Second Register found !
			//Next, find the result Register
			int finalRegister = findRegister();
			if (finalRegister == 12345) {
				throw new Exception ("Result Register NOT FOUND !");
			}
			//Final Register found !
			//rightShiftOp Operation Success !
			return new rightShiftOp(firstRegister, secondRegister, finalRegister);
		}
		return null;	
	}
	private static addOp addInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(ADD)") != null) {
			//Add operation is found !
			//Next, Find the first register
			int firstRegister = findRegister();
			if (firstRegister == 12345) {
				throw new Exception ("First Register NOT FOUND !");
			}
			//First Register found !
			//Next, find the second Register
			int secondRegister = findRegister();
			if (secondRegister == 12345) {
				throw new Exception ("Second Register NOT FOUND !");
			}
			//Second Register found !
			//Next, find the result Register
			int finalRegister = findRegister();
			if (finalRegister == 12345) {
				throw new Exception ("Result Register NOT FOUND !");
			}
			//Final Register found !
			//addOp Operation Success !
			return new addOp(firstRegister, secondRegister, finalRegister);
		}
		return null;
	}
	private static subtractOp subtractInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(SUBTRACT)") != null) {
			//Subtract operation is found !
			//Next, Find the first register
			int firstRegister = findRegister();
			if (firstRegister == 12345) {
				throw new Exception ("First Register NOT FOUND !");
			}
			//First Register found !
			//Next, find the second Register
			int secondRegister = findRegister();
			if (secondRegister == 12345) {
				throw new Exception ("Second Register NOT FOUND !");
			}
			//Second Register found !
			//Next, find the result Register
			int finalRegister = findRegister();
			if (finalRegister == 12345) {
				throw new Exception ("Result Register NOT FOUND !");
			}
			//Final Register found !
			//subtractOp Operation Success !
			return new subtractOp(firstRegister, secondRegister, finalRegister);
		}
		return null;
	}
	private static multiplyOp multiplyInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(MULTIPLY)") != null) {
			//Multiply operation is found !
			//Next, Find the first register
			int firstRegister = findRegister();
			if (firstRegister == 12345) {
				throw new Exception ("First Register NOT FOUND !");
			}
			//First Register found !
			//Next, find the second Register
			int secondRegister = findRegister();
			if (secondRegister == 12345) {
				throw new Exception ("Second Register NOT FOUND !");
			}
			//Second Register found !
			//Next, find the result Register
			int finalRegister = findRegister();
			if (finalRegister == 12345) {
				throw new Exception ("Result Register NOT FOUND !");
			}
			//Final Register found !
			//multiplyOp Operation Success !
			return new multiplyOp(firstRegister, secondRegister, finalRegister);
		}
		return null;
	}
	private static haltOp haltInstruction() {
		if (matchAndRemove("KEYWORD(HALT)") != null) {
			//Don't really need to do anything because this takes no registers or values
			return new haltOp();
		}
		return null;
	}
	
	private static interruptOp interruptInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(INTERRUPT)") != null) {
			int softOrHard = findNumber();
			if (softOrHard == 12345) {
				throw new Exception ("No Value for Interrupt operation");
			}
			return new interruptOp(softOrHard);
		}
		return null;
	}
	private static jumpOp jumpInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(JUMP)") != null) {
			int address = findNumber();
			if (address == 12345) {
				throw new Exception ("No Address Value for Jump operation");
			}
			return new jumpOp(address);
		}
		
		return null;
	}
	private static compareOp compareInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(COMPARE)") != null) {
			//Compare operation is found !
			int register1 = findRegister();
			if (register1 == 12345) {
				throw new Exception ("Register1 NOT FOUND !");
			}
			//First Register is found !
			int register2 = findRegister();
			if (register2 == 12345) {
				throw new Exception ("Register2 NOT FOUND !");
			}
			//Second Register is found !
			
			return new compareOp(register1, register2);
		}
		return null;
	}
	private static pushOp pushInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(PUSH)") != null) {
			//PUSH operation is found !
			int targetRegister = findRegister();
			if (targetRegister == 12345) {
				throw new Exception ("Register NOT FOUND !");
			}
			//Push Operation success !
			return new pushOp(targetRegister);
		}
		return null;
	}
	private static popOp popInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(POP)") != null) {
			//POP operation is found !
			int targetRegister = findRegister();
			if (targetRegister == 12345) {
				throw new Exception ("Register NOT FOUND !");
			}
			//Pop Operation success !
			return new popOp(targetRegister);
		}
		return null;
	}
	private static callOp callInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(CALL)") != null) {
			//CALL operation is found !
			int targetAddress = findNumber();
			if (targetAddress == 12345) {
				throw new Exception ("Address NOT FOUND !");
			}
			//Call Operation success !
			return new callOp(targetAddress);
		}
		return null;
	}
	private static returnOp returnInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(RETURN)") != null) {
			//Return Operation success !
			return new returnOp();
		}
		return null;
	}
	//These are the four different branches ( == / > / >= / != )
	private static branchEqualOp branchEqualInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(BRANCHEQ)") != null) {
			//Branch if equal is found !
			int addy = findNumber();
			if (addy == 12345) {
				throw new Exception ("Memory address NOT FOUND !");
			}
			//Address value is found !
			return new branchEqualOp(addy);
		}
		return null;
	}
	private static branchGreaterOp branchGreaterInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(BRANCHGREATER)") != null) {
			//Branch greater is found !
			int addy = findNumber();
			if (addy == 12345) {
				throw new Exception ("Memory address NOT FOUND !");
			}
			//Address value is found !
			return new branchGreaterOp(addy);
		}
		return null;
	}
	
	private static branchGreaterEqOp branchGreaterEqInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(BRANCHGREATEREQ)") != null) {
			//Branch greater than equal is found !
			int addy = findNumber();
			if (addy == 12345) {
				throw new Exception ("Memory address NOT FOUND !");
			}
			//Address value is found !
			return new branchGreaterEqOp(addy);
		}
		return null;
	}
	private static branchNotEqualOp branchNotEqualInstruction() throws Exception {
		if (matchAndRemove("KEYWORD(BRANCHNOTEQ)") != null) {
			//Branch not equal is found !
			int addy = findNumber();
			if (addy == 12345) {
				throw new Exception ("Memory address NOT FOUND !");
			}
			//Address value is found !
			return new branchNotEqualOp(addy);
		}
		return null;
	}
	
	public static int findRegister() {
		//Initialize answer string
		String answer = "";
		//Make first element a char array
		String str = List.get(0);
		char[] characters = str.toCharArray();
		
		if (characters[0] != 'R') {
			return 12345;
		}
		for (int lParen = str.indexOf('('); lParen < characters.length; lParen++) {
			char currentChar = characters[lParen];
			String stringOfChar = Character.toString(currentChar);
			if (Character.isDigit(currentChar)) {
				answer += stringOfChar;
			}
		}
		
		if (matchAndRemove("REGISTER(R" + answer + ")") != null) {
			return Integer.valueOf(answer);
		}
		return 12345;
	}
	public static int findNumber(){
		//Initialize answer string
		String answer = "";
		//Make first element a char array
		String str = List.get(0);
		char[] characters = str.toCharArray();
		
		for (char c : characters) {
			String currentChar = Character.toString(c);
			//We want to take in negative numbers as well
			if (Character.isDigit(c) || c == '-') {
				answer += currentChar;
			}
		}
		//There was no number in first element, return 12345 as an invalid number
		if (answer == "") {
			return 12345;
		}
		if (matchAndRemove("NUMBER(" + answer + ")") != null) {
			return Integer.valueOf(answer);
		}
		return 12345; //12345 is basically a "null"
	}
	public static String findKeyword(){
		//Finds the known word inside the parentheses
		//Initialize answer string
		String answer = "";
		//Make first element a char array
		String str = List.get(0);
		char[] characters = str.toCharArray();
		
		for (int lParen = str.indexOf('('); lParen < characters.length; lParen++) {
			char currentChar = characters[lParen];
			String stringOfChar = Character.toString(currentChar);
			if (Character.isLetter(currentChar)) {
				answer += stringOfChar;
			}
		}
		if (matchAndRemove("KEYWORD(" + answer + ")") != null) {
			return answer;
		}
		return null;
	}
}
