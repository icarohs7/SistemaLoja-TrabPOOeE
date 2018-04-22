package sistemavendas;

import java.util.HashMap;

/**
 * Classe representando o estoque da loja
 */
public class Estoque {
	/**
	 * Lista de produtos cadastrados e sua quantidade em estoque
	 */
	private HashMap<Produto, Integer> produtos = new HashMap<>();
	
	/**
	 * Reduzir em 1 a quantidade em estoque de um produto
	 *
	 * @param produto    O produto do qual será reduzido o estoque
	 * @param quantidade A quantidade a ser reduzida do estoque
	 */
	public void reduzirEstoque( Produto produto, int quantidade ) {
		produtos.replace( produto, produtos.get( produto ) - quantidade );
	}
	
	/**
	 * Incrementar a quantidade em estoque de um determinado produto
	 *
	 * @param produto    O produto que terá sua quantidade em estoque incrementada
	 * @param quantidade A quantidade adicionada ao estoque
	 */
	public void incrementarEstoque( Produto produto, int quantidade ) {
		produtos.replace( produto, produtos.get( produto ) + quantidade );
	}
	
	/**
	 * Cadastra um novo produto no estoque
	 *
	 * @param produto O produto a ser cadastrado
	 */
	public void cadastrarProduto( Produto produto ) {
		produtos.put( produto, 0 );
	}
	
	/**
	 * Gets produtos.
	 *
	 * @return the produtos
	 */
	public HashMap<Produto, Integer> getProdutos() {
		return produtos;
	}
}
