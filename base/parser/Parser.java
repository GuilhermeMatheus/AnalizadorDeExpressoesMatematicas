package parser;

import java.util.*;

import utils.*;
import expressions.*;
import lexer.*;

/**
 *
 */
public class Parser {
	Stack<Token> tokenStack;
	IProvider<Token> tokenProvider;
	
	Expression root;
	
	/**
	 * Inicializa uma nova instância de Parser
	 * @param tokenProvider Provider guia para o parsing de Token's em Expression's
	 */
	public Parser(IProvider<Token> tokenProvider)
	{
		this.tokenStack = new Stack<>();
		this.tokenProvider = tokenProvider;
	}
	
	/**
	 * Constrói a árvore de expressão para <b>tokenProvider</b>
	 * @throws InvalidSemanticException Exceção lançada quando há erros na semântica da sequência gerada pelo TokenProvider
	 */
	public void buildExpressionTree() throws InvalidSemanticException
	{
		root = parseAdditive();
	}
	
	/**
	 * Lê a sequência de Tokens que respeitam a gramática:
	 * ArgumentList ::= Expression | Expression ',' ArgumentList
	 */
	LinkedList<Expression> parseArgumentList() throws InvalidSemanticException
	{
		LinkedList<Expression> result = new LinkedList<>();
		
		while(true)
		{
			result.add(parseAdditive());
			if (tokenProvider.peek().getType() != TokenType.COMMA)
				break;
			
			tokenProvider.next();
		}
		
		return result;
	}
	/**
	 * Lê a sequência de Tokens que respeitam a gramática:
	 * FunctionCall ::= Identifier '(' ')' || Identifier '(' ArgumentList ')'
	 */
	Expression parseFunctionCall(Token token) throws InvalidSemanticException
	{
		String name = token.getLexeme();
		Token t = tokenProvider.next();
		FunctionInvokerExpression result = new FunctionInvokerExpression(name, OperatorFactory.fromToken(token));
		
		if (t.getType() != TokenType.OPEN_PARENTHESES)
			throw new ExpectedTokenNotFoundException(token, Token.FromType(TokenType.OPEN_PARENTHESES));
		
		t = tokenProvider.peek();
		if (t.getType() != TokenType.CLOSE_PARENTHESES)
			for(Expression e: parseArgumentList())
				result.addChild(e);
		
		t = tokenProvider.next();
		if (t.getType() != TokenType.CLOSE_PARENTHESES)
			throw new ExpectedTokenNotFoundException(token, Token.FromType(TokenType.CLOSE_PARENTHESES));
		
		return result;
	}	
	/**
	 * Lê a sequência de Tokens que respeitam a gramática:
	 * Primary ::= Identifier | Number | '(' Assignment ')' | FunctionCall | Name
	 * @throws InvalidSemanticException 
	 */
	Expression parsePrimary() throws InvalidSemanticException
	{
		debug("parsePrimary");
		Token token = tokenProvider.peek();
		TokenType t = token.getType();
		
		debug("parsePrimary encontrou um " + t);
		
		if(t == TokenType.FUNCTION)
		{
			tokenProvider.next();
			return parseFunctionCall(token);
		}
		
		if (t == TokenType.EXPLICIT)
		{
			tokenProvider.next();
			return ExpressionFactory.Explicit(token.getLexeme());
		}
		
		if (t == TokenType.OPEN_PARENTHESES)
		{
			tokenProvider.next();
			Expression additive = parseAdditive();
			token = tokenProvider.next();
			if (token.getType() != TokenType.CLOSE_PARENTHESES)
				throw new ExpectedTokenNotFoundException(token, Token.FromType(TokenType.CLOSE_PARENTHESES));
			
			return new BracketExpression(additive);
		}
		
		if (t == TokenType.NAME)
		{
			tokenProvider.next();
			return new ConstantExpression(token.getLexeme());
		}
		
		throw new InvalidSemanticException("Token " + token.getType() + " não pode ser processado", token);
	}
	/**
	 * Lê a sequência de Tokens que respeitam a gramática:
	 * Unary ::= Primary | '-' Unary
	 */
	Expression parseUnary() throws InvalidSemanticException
	{
		debug("parseparseUnary");
		Token token = tokenProvider.peek();
		TokenType t = token.getType();
		
		if (t == TokenType.SIGNAL_MINUS || t == TokenType.SIGNAL_PLUS) {
			debug("parseUnary encontrou um " + t);
			tokenProvider.next();
			Expression unary = parseUnary();
			return new UnaryExpression(OperatorFactory.fromToken(token), unary);
		}
		
		return parsePrimary();
	}
	
	/**
	 * Lê a sequência de Tokens que respeitam a gramática:
	 * Multiplicative ::= Unary | Multiplicative '*' Unary | Multiplicative '/' Unary
	 */
	Expression parseMultiplicative() throws InvalidSemanticException
	{
		debug("parseMultiplicative");
		Expression expression = parseUnary();
		Token token;
		
		while((token = tokenProvider.peek()).getType() == TokenType.SIGNAL_MULTIPLICATION ||
				   					   token.getType() == TokenType.SIGNAL_DIVISION ||
				   					   token.getType() == TokenType.SIGNAL_EXPONENTIAL)
		{
			debug("parseMultiplicative encontrou um " + token.getType());
			token = tokenProvider.next();
			expression = new BinaryExpression(expression, parseMultiplicative(), OperatorFactory.fromToken(token));
		}
		
		return expression;
	}
	/**
	 * Lê a sequência de Tokens que respeitam a gramática:
	 * Additive ::= Multiplicative | Additive '+' Multiplicative | Additive '-' Multiplicative
	 */
	Expression parseAdditive() throws InvalidSemanticException
	{
		debug("parseAdditive");
		Expression expression = parseMultiplicative();
		Token token;
		
		while((token = tokenProvider.peek()).getType() == TokenType.SIGNAL_PLUS ||
									   token.getType() == TokenType.SIGNAL_MINUS)
		{
			debug("parseAdditive encontrou um " + token.getType());
			token = tokenProvider.next();
			expression = new BinaryExpression(expression, parseMultiplicative(), OperatorFactory.fromToken(token));
		}

		return expression;
	}
	
	/**
 	 * @return Após executar <b>buildExpressionTree</b>, retorna a Expression gerada
	 */
	public Expression getGeneratedExpressionTree()
	{
		return root;
	}

	void debug(String message)
	{
		//System.out.println(message);
	}
}
