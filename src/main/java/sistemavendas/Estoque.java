package sistemavendas;

import java.util.HashMap;

import sistemavendas.catalogo.CatalogoProdutos;
import sistemavendas.catalogo.Produto;
import sistemavendas.exceptions.EstoqueInsuficienteException;
import sistemavendas.exceptions.ProdutoNaoCadastradoException;

/**
 * Classe representando o estoque da loja
 */
public class Estoque {
	/**
	 * Lista de produtos cadastrados e sua quantidade em estoque
	 */
	private HashMap<Produto, Integer> produtos;
	
	{
		produtos = new HashMap<>();
	}
	
	/**
	 * Reduzir a quantidade em estoque de um produto
	 *
	 * @param produto    O produto do qual será reduzido o estoque
	 * @param quantidade A quantidade a ser reduzida do estoque
	 */
	public void reduzirEstoque( Produto produto, int quantidade ) {
		sincronizarEstoque();
		if ( !produtos.containsKey( produto ) ) {
			throw new ProdutoNaoCadastradoException(
					"O produto não está cadastrado, cadastre-o primeiro." );
		} else if ( produtos.get( produto ) < quantidade ) {
			throw new EstoqueInsuficienteException(
					"Não há produtos suficientes no estoque, reabasteça: " + produto.getDescricao() );
		}
		
		produtos.replace( produto, produtos.get( produto ) - quantidade );
	}
	
	/**
	 * Sincronizar estoque com o catálogo de produtos.
	 */
	private void sincronizarEstoque() {
		CatalogoProdutos.getInstance()
				.getProdutos()
				.forEach( ( produto ) -> {
					if ( !produtos.containsKey( produto ) ) {
						produtos.put( produto, 0 );
					}
				} );
	}
	
	/**
	 * Gets produtos.
	 *
	 * @return the produtos
	 */
	public HashMap<Produto, Integer> getProdutos() {
		return produtos;
	}
	
	/**
	 * Incrementar a quantidade em estoque de um determinado produto
	 *
	 * @param produto    O produto que terá sua quantidade em estoque incrementada
	 * @param quantidade A quantidade adicionada ao estoque
	 */
	public void incrementarEstoque( Produto produto, int quantidade ) {
		sincronizarEstoque();
		if ( !produtos.containsKey( produto ) ) {
			throw new ProdutoNaoCadastradoException( "O produto não está cadastrado, cadastre-o primeiro." );
		}
		
		produtos.replace( produto, produtos.get( produto ) + quantidade );
	}
}
