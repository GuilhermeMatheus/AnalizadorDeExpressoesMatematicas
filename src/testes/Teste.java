package testes;


import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

import lexer.Lexer;
import parser.Parser;
import utils.InvalidSemanticException;

public class Teste {
	
	Scanner scn;
	public void Run()
	{
		scn = new Scanner(System.in);
		
		while(true)
			switch(exibirMenu())
			{
				case 1:
					calcularExpressaoDigitada();
					break;
				case 2:
					calcularExpressaoEmArquivo();
					break;
				case 3:
					scn.close();
					return;
				default:
					Run();
			}
	}
	
	int exibirMenu()
	{
		System.out.println("\n\nDigite uma opção:");
		System.out.println("	1 : Calcular valor de expressão");
		System.out.println("	2 : Calcular valor de expressão em arquivo");
		System.out.println("	3 : Sair");
	
		return scn.nextInt();
	}
	
	void calcularExpressaoDigitada()
	{
		scn.nextLine();
		String expressao = scn.nextLine();
		
		InputStream inputStream = new ByteArrayInputStream(expressao.getBytes(Charset.defaultCharset()));
		Lexer lexer = new Lexer(inputStream);
		Parser parser = new Parser(lexer);
		
		try
		{
			parser.buildExpressionTree();
			double treeResult = parser.getGeneratedExpressionTree().getValue();
			System.out.printf("%s = %f", expressao, treeResult);
			
		} catch (InvalidSemanticException e) {
			e.printStackTrace();
		}
		
	}
	void calcularExpressaoEmArquivo()
	{
		
	}
}
