package lexer;

public final class TokenUtils {
	public static TokenType FromChar(char c)
	{
		switch(c)
		{
			case '-':
				return TokenType.SIGNAL_MINUS;
			case '+':
				return TokenType.SIGNAL_PLUS;
			case '*':
				return TokenType.SIGNAL_MULTIPLICATION;
			case '\\':
			case '/':
				return TokenType.SIGNAL_DIVISION;
			case '^':
				return TokenType.SIGNAL_EXPONENTIAL;
			case '(':
			case '[':
				return TokenType.OPEN_PARENTHESES;
			case ')':
			case ']':
				return TokenType.CLOSE_PARENTHESES;
			default:
				return TokenType.UNKNOWN;
		}
	}
}
