package edu.sistemavendas;

/**
 * Singleton para representar as variáveis globais da loja
 */
public class Loja {
	/**
	 * The constant INSTANCE.
	 */
	private static final Loja INSTANCE;
	
	/**
	 * The constant ID.
	 */
	private static final int ID;
	
	static {
		INSTANCE = new Loja();
		ID = 1;
		
	}
	
	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public static int getID() {
		return ID;
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
	 * Instantiates a new Loja.
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
