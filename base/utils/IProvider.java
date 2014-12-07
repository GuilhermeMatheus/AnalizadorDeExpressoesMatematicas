package utils;

public interface IProvider<T> {
	/**
	 * Verifica se a fonte de dados possui próximo item do tipo <b>T</b>
	 * @return Retorna True quando há, no mínimo, mais um registro na fonte de dados ou, caso contrário, retorna False.
	 */
	boolean hasNext();
	/**
	 * Busca próximo item do tipo <b>T</b> e atualiza a posição do ponteiro.
	 * @return Retorna o próximo item da sequência. 
	 */
	T next();
	/**
	 * Busca próximo item do tipo <b>T</b> e não atualiza a posição do ponteiro.
	 * @return Retorna o próximo item da sequência.
	 */
	T peek();
}
