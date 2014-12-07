package expressions;

import java.util.*;

import operators.Operator;
import utils.InvalidSemanticException;

public class FunctionInvokerExpression extends Expression {
	Expression internalExpression;
	LinkedList<Expression> parameters;
	String name;
	Operator operator;
	
	/**
	 * Inicializa uma nova inst�ncia de FunctionInvokerExpression.java
	 * @param name Nome desta fun��o
	 */
	public FunctionInvokerExpression(String name, Operator operator) {
		this.name = name;
		this.operator = operator;
	}

	public String getName()
	{
		return name;
	}
	
	@Override
	public double getValue() throws InvalidSemanticException {
		double[] params = new double[parameters.size()];
		int i = 0;
		for(Expression e: parameters)
			params[i++] = e.getValue();
		
		return operator.evaluate(params);
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
