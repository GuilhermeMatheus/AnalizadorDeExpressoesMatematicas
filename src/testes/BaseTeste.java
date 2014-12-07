package testes;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import utils.InvalidSemanticException;

public abstract class BaseTeste {
	
	public abstract boolean executarTeste() throws InvalidSemanticException;
	
	/**
	 * @param value String a ser definida em um Stream
	 * @return Retorna um ByteArrayInputStream com os Bytes de <b>value</b>
	 */
	protected InputStream getStreamFromString(String value)
	{
		return new ByteArrayInputStream(value.getBytes(Charset.defaultCharset()));
	}
	
	protected boolean assertResult(double result, double expected)
	{
		return result != expected;
	}
}
