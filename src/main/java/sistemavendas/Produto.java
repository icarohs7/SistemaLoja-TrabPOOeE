package sistemavendas;

import sistemavendas.exceptions.PrecoInvalidoException;

/**
 * Classe representando um produto
 */
class Produto {
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
	Produto( String descricao, double preco ) {
		this.descricao = descricao;
		setPreco( preco );
	}
	
	/**
	 * Gets descricao.
	 *
	 * @return the descricao
	 */
	String getDescricao() {
		return descricao;
	}
	
	/**
	 * Sets descricao.
	 *
	 * @param descricao the descricao
	 */
	void setDescricao( String descricao ) {
		this.descricao = descricao;
	}
	
	/**
	 * Gets preco.
	 *
	 * @return the preco
	 */
	double getPreco() {
		return preco;
	}
	
	/**
	 * Sets preco.
	 *
	 * @param preco the preco
	 */
	void setPreco( double preco ) {
		if ( preco <= 0 ) {
			throw new PrecoInvalidoException( "Não é possível cadastrar um produto sem valor ou com valor negativo" );
		}
		
		this.preco = preco;
	}
}
