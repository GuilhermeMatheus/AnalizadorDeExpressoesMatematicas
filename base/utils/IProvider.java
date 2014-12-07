package utils;

public interface IProvider<T> {
	/**
	 * Verifica se a fonte de dados possui pr�ximo item do tipo <b>T</b>
	 * @return Retorna True quando h�, no m�nimo, mais um registro na fonte de dados ou, caso contr�rio, retorna False.
	 */
	boolean hasNext();
	/**
	 * Busca pr�ximo item do tipo <b>T</b> e atualiza a posi��o do ponteiro.
	 * @return Retorna o pr�ximo item da sequ�ncia. 
	 */
	T next();
	/**
	 * Busca pr�ximo item do tipo <b>T</b> e n�o atualiza a posi��o do ponteiro.
	 * @return Retorna o pr�ximo item da sequ�ncia.
	 */
	T peek();
}
