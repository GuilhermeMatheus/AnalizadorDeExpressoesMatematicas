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
	 * Inicializa uma nova inst�ncia de Parser
	 * @param tokenProvider Provider guia para o parsing de Token's em Expression's
	 */
	public Parser(IProvider<Token> tokenProvider)
	{
		this.tokenStack = new Stack<>();
		this.tokenProvider = tokenProvider;
	}
	
	/**
	 * Constr�i a �rvore de express�o para <b>tokenProvider</b>
	 * @throws InvalidSemanticException Exce��o lan�ada quando h� erros na sem�ntica da sequ�ncia gerada pelo TokenProvider
	 */
	public void buildExpressionTree() throws InvalidSemanticException
	{
		root = parseAdditive();
	}
	
	/**
	 * L� a sequ�ncia de Tokens que respeitam a gram�tica:
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
	 * L� a sequ�ncia de Tokens que respeitam a gram�tica:
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
	 * L� a sequ�ncia de Tokens que respeitam a gram�tica:
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
		
		throw new InvalidSemanticException("Token " + token.getType() + " n�o pode ser processado", token);
	}
	/**
	 * L� a sequ�ncia de Tokens que respeitam a gram�tica:
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
	 * L� a sequ�ncia de Tokens que respeitam a gram�tica:
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
	 * L� a sequ�ncia de Tokens que respeitam a gram�tica:
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
 	 * @return Ap�s executar <b>buildExpressionTree</b>, retorna a Expression gerada
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
