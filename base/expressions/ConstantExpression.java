package expressions;

public class ConstantExpression extends Expression {
	String name;
	
	public ConstantExpression(String name) {
		this.name = name;
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
