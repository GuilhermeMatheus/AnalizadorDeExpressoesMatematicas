package expressions;

import operators.Operator;

/**
 * @author GuilhermeMatheus
 *
 */
public class UnaryExpression extends Expression {
	Operator operator;
	Expression child;
	
	public UnaryExpression(Operator operator, Expression child)
	{
		this.operator = operator;
		this.addChild(child);
	}
	
	/* (non-Javadoc)
	 * @see expressions.Expression#getValue()
	 */
	@Override
	public double getValue() {
		return child.getValue();
	}

	@Override
	public void addChild(Expression child) {
		if(this.child != null)
			throw new IllegalStateException("Tentativa de se adicionar n� � uma express�o un�ria j� cheia.");
		
		this.child = child;
	}

	@Override
	public boolean getIsAvailable() {
		return this.child == null;
	}
}