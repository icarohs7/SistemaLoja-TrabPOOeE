package sistemavendas;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * The type Venda.
 */
public class Venda {
	/**
	 * Produtos.
	 */
	private HashMap<Produto, Integer> produtos;
	/**
	 * Fechada.
	 */
	private boolean fechada;
	/**
	 * Data.
	 */
	private LocalDateTime data;
	/**
	 * Valor total.
	 */
	private double valorTotal;
	
	/**
	 * Estoque.
	 */
	private Estoque estoque;
	
	/**
	 * Instantiates a new Venda.
	 *
	 * @param produtoInicial O primeiro produto adicionado à venda
	 * @param quantidade     the quantidade
	 * @param estoque        estoque
	 *
	 * @throws EstoqueInsuficienteException  estoque insuficiente exception
	 * @throws ProdutoNaoCadastradoException produto nao cadastrado exception
	 */
	public Venda( Produto produtoInicial, int quantidade, Estoque estoque )
			throws EstoqueInsuficienteException, ProdutoNaoCadastradoException {
		fechada = false;
		produtos = new HashMap<>();
		produtos.put( produtoInicial, quantidade );
		data = LocalDateTime.now();
		valorTotal = produtoInicial.getPreco() * quantidade;
		this.estoque = estoque;
		adicionarProduto( produtoInicial, quantidade, estoque );
	}
	
	/**
	 * Adicionar produto.
	 *
	 * @param produto    the produto
	 * @param quantidade the quantidade
	 * @param estoque    the estoque
	 *
	 * @throws EstoqueInsuficienteException  the estoque insuficiente exception
	 * @throws ProdutoNaoCadastradoException the produto nao cadastrado exception
	 */
	public void adicionarProduto( Produto produto, int quantidade, Estoque estoque )
			throws EstoqueInsuficienteException, ProdutoNaoCadastradoException {
		if ( estoque.getProdutos().get( produto ) < quantidade ) {
			throw new EstoqueInsuficienteException( "Não há quantidade suficiente do produto em estoque" );
		} else if ( !estoque.getProdutos().containsKey( produto ) ) {
			throw new ProdutoNaoCadastradoException( "O produto não está cadastrado no sistema" );
		}
		
		if ( produtos.containsKey( produto ) ) {
			produtos.replace( produto, produtos.get( produto ) + quantidade );
		} else {
			produtos.put( produto, quantidade );
		}
	}
	
	/**
	 * Gets valor total.
	 *
	 * @return valor total
	 */
	public double getValorTotal() {
		valorTotal = 0;
		
		produtos.forEach( ( produto, quantidade ) -> valorTotal += ( produto.getPreco() * quantidade ) );
		
		return valorTotal;
	}
	
	/**
	 * Fechar venda.
	 */
	private void fecharVenda() {
		fechada = true;
		produtos.forEach( ( produto, quantidade ) -> estoque.reduzirEstoque( produto, quantidade ) );
		VendasCompletadas.adicionarVenda( this );
	}
	
	/**
	 * Gets data.
	 *
	 * @return data data
	 */
	public LocalDateTime getData() {
		return data;
	}
}
