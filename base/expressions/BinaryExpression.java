package expressions;

import operators.*;
import utils.InvalidSemanticException;

public class BinaryExpression extends Expression {
	
	Operator operator;
	
	Expression right;
	Expression left;
	
	/**
	 * Inicializa uma nova inst�ncia de BinaryExpression
	 * @param left Express�o para ser adicionada no ramo da esquerda deste n�
	 * @param right Express�o para ser adicionada no ramo da direita deste n�
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
	 * Adiciona uma express�o filha desta.
	 * @param child Express�o a ser adicionada
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
		
		throw new IllegalStateException("Tentativa de se adicionar n� � uma express�o bin�ria j� cheia.");
	}

	/**
	 * @return Retorna true se esta Express�o estiver com espa�o dispon�vel para mais um n� ou false, caso contr�rio
	 */
	@Override
	public boolean getIsAvailable() {
		return this.left == null || this.right == null;
	}
}
