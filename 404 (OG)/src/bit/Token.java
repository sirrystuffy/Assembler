package bit;

public class Token {
	public enum State {
		START,
		NUMBER, 
		REGISTER, 
		KEYWORD;
	}
	private String value;
	
	public String getValue(String str) {
		if (str.equals("NUM")) {
			value = "NUMBER(";
		} else if (str.equals("KEY")) {
			value = "KEYWORD(";
		} else if (str.equals("REG")) {
			value = "REGISTER(";
		}
		return value;
	} 
}
