package lexer;

import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import utils.*;

public class Lexer implements IProvider<Token> {
	public static final int EOS = -1;
	
	int index;
	BufferedInputStream  reader;
	Queue<Token> buffer;
	
	/**
	 * Inicializa uma nova instância de Lexer
	 * @param stream
	 */
	public Lexer(InputStream stream) {
		reader = new BufferedInputStream(stream);
		reset();
	}

	public void reset() {
		this.index = 0;
		this.buffer = new LinkedBlockingQueue<>();
	}	

	@Override
	public Token next() {
		if (!buffer.isEmpty())
			return buffer.poll();
		else
			return next(false);
	}
	
	Token next(boolean isPeeking)
	{
		Token token = null;

		while(hasSymbol())
		{
			skipSpaces();
			
			if ((token = getExplicitToken()) != null)
				buffer.add(token);
			else if ((token = getSignalToken()) != null)
				buffer.add(token);			
			else if ((token = getFunctionOrName()) != null)
				buffer.add(token);			
			else if ((token = getParentheses()) != null)
				buffer.add(token);			
			else if ((token = getComma()) != null)
				buffer.add(token);
		}		

		if (token == null)
			return Token.getEmpty();
		
		if (isPeeking)
			return buffer.peek();
		else
			return buffer.poll();
	}
	
	@Override
	public boolean hasNext()
	{
		return !peek().getIsEmpty();
	}
	@Override
	public Token peek() {
		if (buffer.isEmpty())
			return next(true);
		else
			return buffer.peek();
		/*
		int indexBkp = index;
		reader.mark(10);
		
		try {
			Token result = next();
						
			reader.reset();
			index = indexBkp;
			
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Token.getEmpty();
		}*/
	}
	
	Token getExplicitToken()
	{
		boolean hasDecimalPlace = false;
		
		String number = "";
		int start = index;

		if (peekNextChar() == '.')
			number += readChar();

		while (isDigit(peekNextChar())) {
			number += readChar();
			if (peekNextChar() == '.' && !hasDecimalPlace) {
				hasDecimalPlace = true;
				number += readChar();
			}
		}
		
		//System.out.println("Entrei no EXPLICIT: " + index + ", e sai com " + number);
		if (number == "")
			return null;
		
		return Token.FromType(TokenType.EXPLICIT)
					.Of(number)
					.BeginsAt(start)
					.EndsAt(index);
	}
	Token getSignalToken()
	{
		Character c;
		switch(c = peekNextChar())
		{
			case '-':
			case '+':
			case '*':
			case '/':
			case '\\':
			case '^':
				readChar();
				TokenType type = TokenUtils.FromChar(c);
				
				return Token.FromType(type)
							.Of(c.toString())
							.BeginsAt(index-1)
							.EndsAt(index);
			default:
				return null;
		}
	}
	Token getFunctionOrName()
	{
		String name = "";
		int start = index;
		
		while (isLetter(peekNextChar()))
			name += readChar();
		
		if (name == "")
			return null;
		
		TokenType type = peekNextChar() == '(' ? TokenType.FUNCTION : TokenType.NAME;
		return Token.FromType(type)
					.Of(name)
					.BeginsAt(start)
					.EndsAt(index);
	}
	Token getParentheses()
	{
		Character c;
		
		if ((c = peekNextChar()) == '(' || c == ')')
		{
			readChar();
			TokenType type = c == '(' ? TokenType.OPEN_PARENTHESES : TokenType.CLOSE_PARENTHESES;
			return Token.FromType(type)
						.Of(c.toString())
						.BeginsAt(index-1)
						.EndsAt(index);
		}
		return null;
	}
	Token getComma()
	{
		Character c;
		if ((c = peekNextChar()) == ',')
		{
			readChar();
			return Token.FromType(TokenType.COMMA)
						.Of(c.toString())
						.BeginsAt(index-1)
						.EndsAt(index);
		}
		return null;
	}
	
	boolean hasSymbol()
	{
		boolean result = false;

		try {
			reader.mark(1);
			result = reader.read() != -1;
			reader.reset();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;	
		}
		
		return result;
	}
	char peekNextChar()
	{
		char result = 0;
		
		try {
			reader.mark(1);
			result = (char)reader.read();
			reader.reset();
		} catch (IOException e) {
			return 0;
		}
		
		return result;
	}
	char readChar()
	{
		char result = 0;
			
		try {
			result = (char)reader.read();
		} catch (IOException e) {
			return 0;
		}
		
		index++;
		return result;
	}
	
	void skipSpaces() {
		char c;
	    while ((c = peekNextChar()) == ' ' || c == '\n')
	    	readChar();
	}
	boolean isLetter(char ch) {
		return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
	}
	boolean isDigit(char ch) {
		return (ch >= '0') && (ch <= '9');
	}

}
