package expressions;

public class ExplicitExpression extends Expression {

	double internalValue;
	
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
