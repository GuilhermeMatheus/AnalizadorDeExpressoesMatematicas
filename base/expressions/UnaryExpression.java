package expressions;

import operators.Operator;
import utils.InvalidSemanticException;

public class UnaryExpression extends Expression {
	Operator operator;
	Expression child;

	/**
	 * Inicializa uma nova instância de UnaryExpression
	 * @param operator Operador de sinal para este nó
	 * @param child Expressão filha deste nó
	 */
	public UnaryExpression(Operator operator, Expression child)
	{
		this.operator = operator;
		this.addChild(child);
	}
	
	@Override
	public double getValue() throws InvalidSemanticException {
		return operator.evaluate(child.getValue());		
	}

	@Override
	public void addChild(Expression child) {
		if(this.child != null)
			throw new IllegalStateException("Tentativa de se adicionar nó à uma expressão unária já cheia.");
		
		this.child = child;
	}

	@Override
	public boolean getIsAvailable() {
		return this.child == null;
	}
}