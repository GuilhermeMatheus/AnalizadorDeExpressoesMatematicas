package expressions;

import java.util.*;

/**
 * @author GuilhermeMatheus
 *
 */
public class FunctionInvokerExpression extends Expression {
	Expression internalExpression;
	LinkedList<Expression> parameters;
	String name;
	
	public FunctionInvokerExpression(String name) {
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	
	@Override
	public double getValue() {
		//Criar contexto e invocar internalExpression
		return 0;
	}
	
	@Override
	public void addChild(Expression child) {
		if (parameters == null)
			parameters = new LinkedList<>();
		
		parameters.add(child);
	}

	@Override
	public boolean getIsAvailable() {
		return true;
	}

}
