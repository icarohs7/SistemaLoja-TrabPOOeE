package edu.sistemavendas.exceptions;

/**
 * Exception mãe de todas as exceptions referentes ao sistema de vendas.
 */
public class SistemaLojaException extends RuntimeException {
	/**
	 * Instantiates a new Sistema loja exception.
	 *
	 * @param s s
	 */
	public SistemaLojaException( String s ) {
		super( s );
	}
}
