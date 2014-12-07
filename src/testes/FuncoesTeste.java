package testes;

import java.io.InputStream;

import lexer.Lexer;
import parser.Parser;
import utils.InvalidSemanticException;

public class FuncoesTeste extends BaseTeste {

	/**
	 * Executa o teste das funções disponíveis em ExpressionFactory com o uso de um ByteArrayInputStream
	 */
	@Override
	public boolean executarTeste() throws InvalidSemanticException
	{
		InputStream inputStream = getStreamFromString("sqrt(5)");
		Lexer lexer = new Lexer(inputStream);
		Parser parser = new Parser(lexer);
		
		parser.buildExpressionTree();
		double treeResult = parser.getGeneratedExpressionTree().getValue();
		
		if (assertResult(treeResult, Math.sqrt(5)))
			System.out.println("Funç");
		
		return false;
	}

}
