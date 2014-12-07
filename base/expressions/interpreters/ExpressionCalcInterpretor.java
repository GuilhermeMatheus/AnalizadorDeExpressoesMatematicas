package expressions.interpreters;

import utils.IInterpreter;
import utils.InvalidSemanticException;
import expressions.Expression;

/**
 * @author GuilhermeMatheus
 *
 */
public class ExpressionCalcInterpretor implements IInterpreter<Expression, Double> {

	/* (non-Javadoc)
	 * @see utils.IInterpreter#interprete(java.lang.Object)
	 */
	@Override
	public Double interprete(Expression source) throws InvalidSemanticException {
		return source.getValue();
	}

}
