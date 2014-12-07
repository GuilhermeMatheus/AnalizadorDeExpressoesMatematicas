package expressions;

public class ExplicitExpression extends Expression {

	double internalValue;
	
	/**
	 * Inicializa uma nova inst�ncia de ExplicitExpression
	 * @param value Valor desta folha
	 */
	public ExplicitExpression(double value)
	{
		this.internalValue = value;
	}
		
	@Override
	public double getValue() {
		return internalValue;
	}

	@Override
	public void addChild(Expression child) {
		throw new IllegalStateException("Tentativa de se adicionar n� � uma folha.");
	}

	@Override
	public boolean getIsAvailable() {
		return false;
	}

}
