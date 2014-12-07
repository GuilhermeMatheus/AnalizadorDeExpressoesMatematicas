package expressions;

import operators.Operator;
import utils.InvalidSemanticException;

public class UnaryExpression extends Expression {
	Operator operator;
	Expression child;

	/**
	 * Inicializa uma nova inst�ncia de UnaryExpression
	 * @param operator Operador de sinal para este n�
	 * @param child Express�o filha deste n�
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
			throw new IllegalStateException("Tentativa de se adicionar n� � uma express�o un�ria j� cheia.");
		
		this.child = child;
	}

	@Override
	public boolean getIsAvailable() {
		return this.child == null;
	}
}