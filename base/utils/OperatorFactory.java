package utils;

import operators.Operator;
import operators.OperatorType;
import lexer.Token;

/**
 * @author GuilhermeMatheus
 *
 */
public final class OperatorFactory {
	
	public static Operator fromToken(Token token) throws InvalidSemanticException
	{
		switch(token.getType())
		{
			case SIGNAL_DIVISION:
				return new Operator(OperatorType.DIVISION) {
					@Override
					public double evaluate(double... params) {
						return params[0] / params[1];
					}
				};
			case SIGNAL_EXPONENTIAL:
				return new Operator(OperatorType.EXPONENTIAL) {
					@Override
					public double evaluate(double... params) {
						return Math.pow(params[0], params[1]);
					}
				};
			case SIGNAL_MINUS:
				return new Operator(OperatorType.MINUS) {
					@Override
					public double evaluate(double... params) {
						if (params.length == 1)
							return -params[0];
						return params[0] - params[1];
					}
				};
			case SIGNAL_MULTIPLICATION:
				return new Operator(OperatorType.MULTIPLICATION) {
					@Override
					public double evaluate(double... params) {
						return params[0] * params[1];
					}
				};
			case SIGNAL_PLUS:
				return new Operator(OperatorType.PLUS) {
					@Override
					public double evaluate(double... params) {
						if (params.length == 1)
							return +params[0];
						return params[0] + params[1];
					}
				};
			case FUNCTION:
				return functionHelper(token);
			case CLOSE_PARENTHESES:
			case COMMA:
			case EXPLICIT:
			case NAME:
			case OPEN_PARENTHESES:
			case UNKNOWN:
			default:
				break;
		}
		return null;
	}
	static Operator functionHelper(final Token token) throws InvalidSemanticException
	{
		String name = token.getLexeme().toUpperCase();
		switch(name)
		{
			case "COS":
				return new Operator(OperatorType.FUNCTION) {
					@Override
					public double evaluate(double... params) throws InvalidSemanticException {
						assertParametersCount(token, 1, params);
						return Math.cos(params[0]);
					}
				};
			case "SEN":
				return new Operator(OperatorType.FUNCTION) {
					@Override
					public double evaluate(double... params) throws InvalidSemanticException {
						assertParametersCount(token, 1, params);
						return Math.sin(params[0]);
					}
				};
			case "SQRT":
			return new Operator(OperatorType.FUNCTION) {
				@Override
				public double evaluate(double... params) throws InvalidSemanticException {
					assertParametersCount(token, 1, params);
					return Math.sqrt(params[0]);
				}
			};
			case "MAX":
			return new Operator(OperatorType.FUNCTION) {
				@Override
				public double evaluate(double... params) throws InvalidSemanticException {
					double max = Double.MIN_VALUE;
					for(double d: params)
						max = max < d ? d : max;
					
					return max;
				}
			};
			case "MIN":
				return new Operator(OperatorType.FUNCTION) {
					@Override
					public double evaluate(double... params) throws InvalidSemanticException {
						double min = Double.MAX_VALUE;
						for(double d: params)
							min = min > d ? d : min;
						
						return min;
					}
				};
			case "FACTORIAL":
			return new Operator(OperatorType.FUNCTION) {
				@Override
				public double evaluate(double... params) throws InvalidSemanticException {
					assertParametersCount(token, 1, params);
					int x, fact = 1;
			        for ( x = (int)params[0]; x > 1; x--)
					     fact *= x;
			        return fact;
				}
			};
		}
		
		throw new InvalidSemanticException("Função com nome " + name + " desconhecida.", token);
	}
	
	static boolean assertParametersCount(Token token, int desiredValue, double... params) throws InvalidSemanticException
	{
		if(params.length != desiredValue)
		{
			String msg = String.format("Função '%s' exige %d parâmetro(s). A expressão encontrada possui %d.", token.getLexeme().toUpperCase(), desiredValue, params.length);	
			throw new InvalidSemanticException(msg, token);
		}
		return true;
	}
	
}
