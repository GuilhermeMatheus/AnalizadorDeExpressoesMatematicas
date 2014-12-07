package operators;

import utils.InvalidSemanticException;

public abstract class Operator {
	OperatorType type;
	
	/**
	 * Inicializa uma nova inst�ncia de Operator
	 * @param type Tipo de opera��o deste Operador
	 */
	public Operator(OperatorType type)
	{
		this.type = type;
	}
	
	/**
	 * Calcula os valores por <b>a</b> e <b>b</b> 
	 * @param params Par�metros para c�lculo
	 * @throws InvalidSemanticException 
	 */
	public abstract double evaluate(double... params) throws InvalidSemanticException;
}
