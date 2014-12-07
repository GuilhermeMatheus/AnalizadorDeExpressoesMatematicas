package expressions;

import utils.InvalidSemanticException;

public abstract class Expression {
	Expression parent;
	
	/**
	 * Inicializa uma nova instância de Expression 
	 */
	public Expression() { }
	
	/**
	 * Calcula o valor que esta sub-árvore de expressões possui
	 * @return Retorna o valor desta expressão.
	 * @throws InvalidSemanticException 
	 */
	public abstract double getValue() throws InvalidSemanticException;

	/**
	 * @return Retorna true se esta Expressão estiver com espaço disponível para mais um nó ou false, caso contrário
	 */
	public abstract boolean getIsAvailable();
	
	/**
	 * Adiciona uma expressão filha desta.
	 * @param child Expressão a ser adicionada
	 */
	public abstract void addChild(Expression child);

	/**
	 * @return Retorna o nó pai desta expressão ou nulo quando este é uma raiz de árvore de expressão
	 */
 	public Expression getParent()
	{
		return parent;
	}
 	
 	/**
 	 * Método visível apenas para membros do Package 'expressions', pois apenas um nó pode alterar a posição do outro de forma segura
 	 * @param parent Novo nó pai desta expressão
 	 */
 	void setParent(Expression parent)
 	{
 		this.parent = parent;
 	}
}
