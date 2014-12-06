package utils;

import lexer.Token;

/**
 * @author GuilhermeMatheus
 *
 */
public class InvalidSemanticException extends Exception {
	/**
	 * UID de Exception gerado pelo Eclipse.
	 */
	private static final long serialVersionUID = -101044223754584142L;

	Token tokenInformation;
	
	/**
	 * Inicializa uma nova instância de InvalidSemanticException
	 * @param token Token onde a exceção foi gerada
	 */
	public InvalidSemanticException(String message, Token token)
	{
		super(message);
		this.tokenInformation = token;
	}
	
	/**
	 * @return Retorna o Token onde a exceção foi gerada.
	 */
	public Token getTokenInformation()
	{
		return tokenInformation;
	}
}
