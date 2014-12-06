package utils;

import expressions.*;

/**
 * Classe para criação de objetos de Expressão
 */
public final class ExpressionFactory {
		
	public static ExplicitExpression Explicit(String doubleValue)
	{
		double value = Double.parseDouble(doubleValue);
		return new ExplicitExpression(value);
	}
	
}
