package expressions;

/**
 * @author GuilhermeMatheus
 *
 */
public class BracketExpression extends Expression {

	Expression child;
	
	/**
	 * Inicializa uma nova instância de BracketExpression.java
	 * @param child
	 */
	public BracketExpression(Expression child) {
		addChild(child);
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
			throw new IllegalStateException("Tentativa de se adicionar nó à uma expressão unária já cheia.");
		
		this.child = child;
	}

	@Override
	public boolean getIsAvailable() {
		return this.child != null;
	}
}
