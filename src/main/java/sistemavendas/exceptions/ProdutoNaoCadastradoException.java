package sistemavendas.exceptions;

/**
 * The type Produto nao cadastrado exception.
 */
public class ProdutoNaoCadastradoException extends RuntimeException {
	/**
	 * Instantiates a new Produto nao cadastrado exception.
	 *
	 * @param s s
	 */
	public ProdutoNaoCadastradoException( String s ) {
		super( s );
	}
}
