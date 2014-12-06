package expressions;

public class ConstantExpression extends Expression {

	public ConstantExpression(Expression dad) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addChild(Expression child) {
		throw new IllegalStateException("Tentativa de se adicionar nó à uma folha.");
	}

	@Override
	public boolean getIsAvailable() {
		return false;
	}

}
