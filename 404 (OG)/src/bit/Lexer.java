package bit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bit.Token.State;

public class Lexer {
	public List<String> lex(String instruction) throws Exception {
		//implement counter to index charArray
		int index = 0;
		//Make string into array of chars
		char[] charArray = instruction.toCharArray();
		
		//Put all the tokens in this and return it
		List<String> answer = new ArrayList<String>();
		
		Token identifier = new Token();
		State currentState = State.START;
		
		String value = "";
		
		//Create Data Structure for known words 
		HashMap<String, String> knownWords = new HashMap<String, String>();
		knownWords.put("AND", "AND"); 
		knownWords.put("OR", "OR");
		knownWords.put("XOR", "XOR");
		knownWords.put("NOT", "NOT");
		knownWords.put("LEFTSHIFT", "LEFTSHIFT");
		knownWords.put("RIGHTSHIFT", "RIGHTSHIFT");
		knownWords.put("ADD", "ADD");
		knownWords.put("SUBTRACT", "SUBTRACT");
		knownWords.put("HALT", "HALT");
		knownWords.put("MOVE", "MOVE");
		knownWords.put("INTERRUPT", "INTERRUPT");
		knownWords.put("JUMP", "JUMP");
		knownWords.put("COMPARE", "COMPARE");
		knownWords.put("BRANCH", "BRANCH"); //Don't really use this . . .
		knownWords.put("STACK", "STACK");
		knownWords.put("MULTIPLY", "MULTIPLY");
		knownWords.put("CALL", "CALL");
		knownWords.put("RETURN", "RETURN");
		knownWords.put("PUSH", "PUSH");
		knownWords.put("POP", "POP");
		//Custom branch keywords
		knownWords.put("BRANCHEQ", "BRANCHEQ");                                                                                                    
		knownWords.put("BRANCHGREATEREQ", "BRANCHGREATEREQ");
		knownWords.put("BRANCHGREATER", "BRANCHGREATER");
		knownWords.put("BRANCHNOTEQ", "BRANCHNOTEQ");
		for (char c : charArray) {
			//Make a string representation of char
			String str = Character.toString(c);
			switch(currentState) {
				case START :
					if (Character.isDigit(c)) {
						value = identifier.getValue("NUM");
						value += str;
						currentState = State.NUMBER;
					} 
					//Check if next char is a digit to ENSURE it is a register
					//Notice, we don't have to check bounds because correct format will always leave a number(0-9) after 'R' -- IF IT IS A REGISTER
					else if (c == 'R' && Character.isDigit(charArray[index + 1])) {
						//The beginning of a register 
						value = identifier.getValue("REG");
						value += str;
						currentState = State.NUMBER;
					} else if (Character.isLetter(c)) {
						//Here, we check for a letter AFTER checking for the letter R so any other letter will have to be a keyword
						value = identifier.getValue("KEY");
						value += str;
						currentState = State.KEYWORD;
					} else if (c == '-') {
						//Here, we need to check for negative numbers
						//Check bounds
						if (index + 1 < instruction.length()) {
							//create temporary char and set it to the next char
							char temp = instruction.charAt(index + 1);
							//If next value after '-' is a number then it is negative else emit minus
							if (Character.isDigit(temp)) {
								// add '-' to value string because this means it is a negative sign
								value = identifier.getValue("NUM");
								value += str;
								currentState = State.NUMBER;
							} else {
								throw new Exception ("Value after '-' is not a number ! ");
							}
						}
						//out of bounds or end of statement
						//emit divide and go back to start to accept input
					} else if (c == ' ') {
						//do nothing
					} else {
						throw new IllegalArgumentException("THE CHARACTER : " + c + "IS NOT EXPECTED !");
					}
					break;
				case NUMBER :
					if (Character.isDigit(c)) {
						value += str;
						currentState = State.NUMBER;
					} else {
						value += ")";
						answer.add(value);
						currentState = State.START;
					}
					break;
				case KEYWORD :
					if (Character.isLetter(c)) {
						value += str;
					} else if (c == ' ') {
						for (String word : knownWords.values()) {
							if (value.equals(identifier.getValue("KEY") + knownWords.get(word))) {
								//value = knownWords.get(word);
								value += ")";
								answer.add(value);
								currentState = State.START;
								break; //exit after one match
							}
						}
					} else {
						throw new Exception ("KEYWORD EXPECTED, BUT NOT FOUND !" + value);
					}
					break;
				case REGISTER :
					if (Character.isDigit(c)) {
						value += str;
						currentState = State.NUMBER;
					} else if (c == ' ') {
						value += ")";
						answer.add(value);
						currentState = State.START;
					} else {
						throw new Exception ("REGISTER EXPECTED, BUT NOT FOUND !");
					}
					break;
				default :
					break;
			}
			index++;
		}
		
		//If line is not empty and value string doesn't contain a closed parenthesis then add ')' and add to list. 
		if (value != null && value != "" && !value.matches(".*[\\)]")) {
			value += ")";//close the number
			answer.add(value);
		} 
		//if there are no more chars in the string then add EndOfLine to list
		answer.add("EndOfLine");
		return answer;
	}
	
}
