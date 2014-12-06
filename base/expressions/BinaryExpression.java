/**
 * 
 */
package expressions;

import operators.*;

/**
 *
 */
public class BinaryExpression extends Expression {
	
	Operator operator;
	
	Expression right;
	Expression left;
	
	public BinaryExpression(Expression left, Expression right, Operator operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public boolean getIsAvailable() {
		return this.left == null || this.right == null;
	}
}
