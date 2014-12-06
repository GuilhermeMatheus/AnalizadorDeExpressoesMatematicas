package utils;

import operators.Operator;
import lexer.Token;

/**
 * @author GuilhermeMatheus
 *
 */
public final class OperatorFactory {
	
	public static Operator FromToken(Token token)
	{
		switch(token.getType())
		{
			case CLOSE_PARENTHESES:
				break;
			case COMMA:
				break;
			case EXPLICIT:
				break;
			case FUNCTION:
				break;
			case NAME:
				break;
			case OPEN_PARENTHESES:
				break;
			case SIGNAL_DIVISION:
				break;
			case SIGNAL_EXPONENTIAL:
				break;
			case SIGNAL_MINUS:
				break;
			case SIGNAL_MULTIPLICATION:
				break;
			case SIGNAL_PLUS:
				break;
			case UNKNOWN:
				break;
			default:
				break;
		}
		return null;
	}
}
