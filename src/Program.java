import java.io.*;

import expressions.Expression;
import parser.Parser;
import utils.InvalidSemanticException;
import lexer.Lexer;
import lexer.Token;


public class Program {

	static String path = "C://Users//GuilhermeMatheus//Documents//Paralelo ao OneDrive//JavaWorkspace//MatComp//expressao.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		InputStream inputStream = new FileInputStream(path);
		testarParser();

		/*while(lexer.hasNext())
		{
			Token t = lexer.next();
			System.out.println(t.getType() + " :  " + t.getLexeme());
		}*/
		
	}
	static void testarParser() throws FileNotFoundException
	{
		InputStream inputStream = new FileInputStream(path);
		Lexer lexer = new Lexer(inputStream);
		Parser parser = new Parser(lexer);
		
		try {
			parser.buildExpressionTree();
			parser.getGeneratedExpressionTree();
		} catch (InvalidSemanticException e) {
			System.out.println(e.getTokenInformation().getStart());
			e.printStackTrace();
		}
	}
	static void printExpressionTree(Expression e)
	{
		
	}
}
