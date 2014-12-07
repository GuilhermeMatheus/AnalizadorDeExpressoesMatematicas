package expressions;

import utils.InvalidSemanticException;

public abstract class Expression {
	Expression parent;
	
	/**
	 * Inicializa uma nova inst�ncia de Expression 
	 */
	public Expression() { }
	
	/**
	 * Calcula o valor que esta sub-�rvore de express�es possui
	 * @return Retorna o valor desta express�o.
	 * @throws InvalidSemanticException 
	 */
	public abstract double getValue() throws InvalidSemanticException;

	/**
	 * @return Retorna true se esta Express�o estiver com espa�o dispon�vel para mais um n� ou false, caso contr�rio
	 */
	public abstract boolean getIsAvailable();
	
	/**
	 * Adiciona uma express�o filha desta.
	 * @param child Express�o a ser adicionada
	 */
	public abstract void addChild(Expression child);

	/**
	 * @return Retorna o n� pai desta express�o ou nulo quando este � uma raiz de �rvore de express�o
	 */
 	public Expression getParent()
	{
		return parent;
	}
 	
 	/**
 	 * M�todo vis�vel apenas para membros do Package 'expressions', pois apenas um n� pode alterar a posi��o do outro de forma segura
 	 * @param parent Novo n� pai desta express�o
 	 */
 	void setParent(Expression parent)
 	{
 		this.parent = parent;
 	}
}
