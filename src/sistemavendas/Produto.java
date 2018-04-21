package sistemavendas;

/**
 * Classe representando um produto
 */
public class Produto {
	/**
	 * Descrição do produto
	 */
	private String descricao;
	/**
	 * Preço do produto
	 */
	private double preco;
	
	/**
	 * Construir o produto, adicionando sua descrição e preço
	 *
	 * @param descricao A descrição do produto
	 * @param preco     O preço do produto
	 */
	public Produto( String descricao, double preco ) {
		this.descricao = descricao;
		this.preco = preco;
	}
	
	/**
	 * Gets descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Sets descricao.
	 *
	 * @param descricao the descricao
	 */
	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}
	
	/**
	 * Gets preco.
	 *
	 * @return the preco
	 */
	public double getPreco() {
		return preco;
	}
	
	/**
	 * Sets preco.
	 *
	 * @param preco the preco
	 */
	public void setPreco( double preco ) {
		this.preco = preco;
	}
}
