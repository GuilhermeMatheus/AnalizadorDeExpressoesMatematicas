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
	 * Inicializa uma nova inst�ncia de ExpectedTokenNotFoundException
	 * @param token Token onde a exce��o foi gerada.
	 * @param expectedToken Token esperado, pode ser nulo.
	 */
	public ExpectedTokenNotFoundException(Token token, Token expectedToken) {
		super(getMessage(token, expectedToken), token);
		// TODO Auto-generated constructor stub
	}
	
	static String getMessage(Token token, Token expectedToken){
		if (expectedToken == null)
			return "Token do tipo " + token.getType() + " n�o esperado.";
		else
			return "Token do '" + token.getType() + "' n�o esperado. Espera-se token do tipo '" + expectedToken.getType() + "'";
	}
}
