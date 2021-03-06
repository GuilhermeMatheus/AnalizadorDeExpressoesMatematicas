package expressions;

import utils.InvalidSemanticException;

/**
 * @author GuilhermeMatheus
 *
 */
public class BracketExpression extends Expression {

	Expression child;
	
	/**
	 * Inicializa uma nova inst�ncia de BracketExpression.java
	 * @param child Filho desta express�o
	 */
	public BracketExpression(Expression child) {
		addChild(child);
	}

	/* (non-Javadoc)
	 * @see expressions.Expression#getValue()
	 */
	@Override
	public double getValue() throws InvalidSemanticException {
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
		return this.child != null;
	}
}
