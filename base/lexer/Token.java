package lexer;

public class Token {
	TokenType type;
	String lexeme;
	int start;
	int end;
	boolean isEmpty;

	/**
	 * @param type Tipo de Token representado pela nova instância
	 */
	public Token(TokenType type) {
		this.type = type;
		this.isEmpty = false;
	}

	/**
	 * Fluent interface para o construtor Token(TokenType)
	 * @param type Tipo de Token representado pela nova instância
	 * @return 
	 */
	public static Token FromType(TokenType type) {
		Token result = new Token(type);
		return result;
	}
	
	/**
	 * Fluent interface para definir o atributo lexeme.
	 * @param lexeme Símbolo do código fonte deste token
	 * @return Retorna esta própria instância
	 */
	public Token Of(String lexeme) {
		this.lexeme = lexeme;
		return this;
	}
	
	/**
	 * Fluent interface para definir o atributo start.
	 * @param start Índice do primeiro caractere do lexeme no código fonte
	 * @return Retorna esta própria instância
	 */
	public Token BeginsAt(int start) {
		this.start = start;
		return this;
	}
	
	/**
	 * Fluent interface para definir o atributo end.
	 * @param end Índice do último caractere do lexeme no código fonte
	 * @return Retorna esta própria instância
	 */
	public Token EndsAt(int end) {
		this.end = end;
		return this;
	}
	
	/**
	 * @return Retorna um Token sem significado
	 */
	public static Token getEmpty()
	{
		Token result = new Token(TokenType.EMPTY);
		result.isEmpty = true;
		result.start = -1;
		result.end = -1;
		return result;
	}

	/**
	 * @return Retorna True quando o Token não possui significado ou, caso contrário, False.
	 */
	public boolean getIsEmpty()
	{
		return isEmpty;
	}
	
	public TokenType getType()
	{
		return this.type;
	}
	public String getLexeme()
	{
		return this.lexeme;
	}
	public int getStart()
	{
		return this.start;
	}
	public int getEnd()
	{
		return this.end;
	}
}
