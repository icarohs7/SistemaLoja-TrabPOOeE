package sistemavendas.catalogo;

import java.util.ArrayList;

import sistemavendas.exceptions.ProdutoJaCadastradoException;

/**
 * The type Catalogo produtos.
 */
public class CatalogoProdutos {
	/**
	 * The constant INSTANCE.
	 */
	private static final CatalogoProdutos INSTANCE;
	
	static {
		INSTANCE = new CatalogoProdutos();
	}
	
	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static CatalogoProdutos getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Produtos.
	 */
	private ArrayList<Produto> produtos;
	
	/**
	 * Instantiates a new Catalogo produtos.
	 */
	private CatalogoProdutos() {
		produtos = new ArrayList<>();
	}
	
	/**
	 * Gets produtos.
	 *
	 * @return the produtos
	 */
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	
	/**
	 * Cadastrar produto.
	 *
	 * @param produto the produto
	 */
	public void CadastrarProduto( Produto produto ) {
		if ( produtos.contains( produto ) ) {
			throw new ProdutoJaCadastradoException(
					"O produto " + produto.getDescricao() + " já se encontra cadastrado" );
		}
		
		produtos.add( produto );
	}
}
