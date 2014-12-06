package utils;

import expressions.*;

/**
 * Classe para cria��o de objetos de Express�o
 */
public final class ExpressionFactory {
		
	public static ExplicitExpression Explicit(String doubleValue)
	{
		double value = Double.parseDouble(doubleValue);
		return new ExplicitExpression(value);
	}
	
}
