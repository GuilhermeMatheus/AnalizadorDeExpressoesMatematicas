package expressions;

import operators.*;
import utils.InvalidSemanticException;

public class BinaryExpression extends Expression {
	
	Operator operator;
	
	Expression right;
	Expression left;
	
	/**
	 * Inicializa uma nova instância de BinaryExpression
	 * @param left Expressão para ser adicionada no ramo da esquerda deste nó
	 * @param right Expressão para ser adicionada no ramo da direita deste nó
	 * @param operator Operador que conecta os dois ramos
	 */
	public BinaryExpression(Expression left, Expression right, Operator operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	@Override
	public double getValue() throws InvalidSemanticException
	{
		if(left == null)
			return right.getValue();
		
		if (right == null)
			return left.getValue();
		
		double leftValue = left.getValue();
		double rightValue = right.getValue();
		return operator.evaluate(leftValue, rightValue);
	}

	public void setRight(Expression value)
	{
		value.setParent(this);
		this.right = value;
	}
	public Expression getRight()
	{
		return this.right;
	}
	
	public void setLeft(Expression value)
	{
		value.setParent(this);
		this.left = value;
	}
	public Expression getLeft()
	{
		return this.left;
	}

	/**
	 * Adiciona uma expressão filha desta.
	 * @param child Expressão a ser adicionada
	 */
	@Override
	public void addChild(Expression child) {
		if (this.left != null)
		{
			this.setLeft(child);
			return;
		}
		if (this.right != null)
		{
			this.setRight(child);
			return;
		}
		
		throw new IllegalStateException("Tentativa de se adicionar nó à uma expressão binária já cheia.");
	}

	/**
	 * @return Retorna true se esta Expressão estiver com espaço disponível para mais um nó ou false, caso contrário
	 */
	@Override
	public boolean getIsAvailable() {
		return this.left == null || this.right == null;
	}
}
