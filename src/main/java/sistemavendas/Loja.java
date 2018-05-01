package sistemavendas;

/**
 * Singleton para representar as variáveis globais da loja
 */
public class Loja {
	/**
	 * The constant INSTANCE.
	 */
	private static final Loja INSTANCE;
	
	static {
		INSTANCE = new Loja();
	}
	
	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static Loja getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Estoque.
	 */
	private Estoque estoque;
	
	/**
	 * Instantiates a new sistemavendas.Loja.
	 */
	private Loja() {
		estoque = new Estoque();
	}
	
	/**
	 * Gets estoque.
	 *
	 * @return the estoque
	 */
	public Estoque getEstoque() {
		return estoque;
	}
}
