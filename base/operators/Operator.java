package operators;

import utils.InvalidSemanticException;

public abstract class Operator {
	OperatorType type;
	
	/**
	 * Inicializa uma nova instância de Operator
	 * @param type Tipo de operação deste Operador
	 */
	public Operator(OperatorType type)
	{
		this.type = type;
	}
	
	/**
	 * Calcula os valores por <b>a</b> e <b>b</b> 
	 * @param params Parâmetros para cálculo
	 * @throws InvalidSemanticException 
	 */
	public abstract double evaluate(double... params) throws InvalidSemanticException;
}
