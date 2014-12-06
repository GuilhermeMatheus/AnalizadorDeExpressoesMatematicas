package utils;

import lexer.Token;

/**
 * @author GuilhermeMatheus
 *
 */
public class ExpectedTokenNotFoundException extends InvalidSemanticException {
	/**
	 * UID de Exception gerado pelo Eclipse.
	 */
	private static final long serialVersionUID = 2560479036498885591L;

	/**
	 * Inicializa uma nova instância de ExpectedTokenNotFoundException
	 * @param token Token onde a exceção foi gerada.
	 * @param expectedToken Token esperado, pode ser nulo.
	 */
	public ExpectedTokenNotFoundException(Token token, Token expectedToken) {
		super(getMessage(token, expectedToken), token);
		// TODO Auto-generated constructor stub
	}
	
	static String getMessage(Token token, Token expectedToken){
		if (expectedToken == null)
			return "Token do tipo " + token.getType() + " não esperado.";
		else
			return "Token do '" + token.getType() + "' não esperado. Espera-se token do tipo '" + expectedToken.getType() + "'";
	}
}
