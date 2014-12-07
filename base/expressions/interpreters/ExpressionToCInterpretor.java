package expressions.interpreters;

import expressions.Expression;
import utils.IInterpreter;

public class ExpressionToCInterpretor implements IInterpreter<Expression, String> {

	Expression source;
	
	@Override
	public String interprete(Expression source) {
		this.source = source;
		
		return generateC();
	}
	
	String generateC()
	{
		return null;
	}
}
