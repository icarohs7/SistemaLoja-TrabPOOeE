package sistemavendas;

import java.time.LocalDateTime;
import java.util.HashMap;

import sistemavendas.catalogo.Produto;
import sistemavendas.exceptions.EstoqueInsuficienteException;
import sistemavendas.exceptions.OperacaoInvalidaException;
import sistemavendas.exceptions.ProdutoNaoCadastradoException;

/**
 * The type Venda.
 */
class Venda {
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
	 * @throws OperacaoInvalidaException     operacao invalida exception
	 */
	public Venda( Produto produtoInicial, int quantidade, Estoque estoque )
			throws EstoqueInsuficienteException, ProdutoNaoCadastradoException, OperacaoInvalidaException {
		/* Abre uma nova venda ao escanear o primeiro produto */
		fechada = false;
		/* Instancia a lista de produtos */
		produtos = new HashMap<>();
		/* Defina a data da venda para o momento em que ela é criada */
		data = LocalDateTime.now();
		/* No início, o valor total da venda equivale ao preço do produto multiplicado
		 * pela sua quantidade */
		valorTotal = produtoInicial.getPreco() * quantidade;
		/* Associa o estoque recebido à venda atual */
		this.estoque = estoque;
		/* Adiciona o primeiro produto à venda */
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
	 * @throws OperacaoInvalidaException     the operacao invalida exception
	 */
	public void adicionarProduto( Produto produto, int quantidade, Estoque estoque )
			throws EstoqueInsuficienteException, ProdutoNaoCadastradoException, OperacaoInvalidaException {
		/* Lançar uma exceção caso o usuário tente adicionar um produto à venda depois de fechada */
		if ( fechada ) {
			throw new OperacaoInvalidaException( "Não é possível adicionas produtos a uma venda fechada!" );
		}
		/* Lançar uma exceção caso o usuário tente adicionar uma quantidade de produtos maior que a presente
		 * no estoque */
		if ( estoque.getProdutos()
				     .get( produto ) < quantidade ) {
			throw new EstoqueInsuficienteException( "Não há quantidade suficiente do produto em estoque" );
		}
		/* Lançar uma exceção caso o usuário tente adicionar à venda um produto não cadastrado */
		if ( !estoque.getProdutos()
				.containsKey( produto ) ) {
			throw new ProdutoNaoCadastradoException( "O produto não está cadastrado no sistema" );
		}
		/* Se o produto já estiver presente na venda, incrementar sua quantidade */
		if ( produtos.containsKey( produto ) ) {
			produtos.replace( produto, produtos.get( produto ) + quantidade );
		}
		/* Caso não esteja, adicioná-lo */
		else {
			produtos.put( produto, quantidade );
		}
	}
	
	/**
	 * Gets valor total.
	 *
	 * @return valor total
	 */
	public double getValorTotal() {
		/* Caso seja uma venda em progresso, recalcular
		 * o valor total em cada chamada */
		if ( !fechada ) {
			valorTotal = 0;
			/* Somar o preço multiplicado pela quantidade do mesmo presente na venda ao valor total
			 * para cada produto */
			produtos.forEach( ( produto, quantidade ) -> valorTotal += ( produto.getPreco() * quantidade ) );
		}
		
		return valorTotal;
	}
	
	/**
	 * Fechar venda.
	 */
	private void fecharVenda() {
		fechada = true;
		/* Ao fim da venda, reduzir o estoque */
		produtos.forEach( ( produto, quantidade ) -> estoque.reduzirEstoque( produto, quantidade ) );
		/* Arquivar a venda atual */
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
	
	/**
	 * Gets produtos.
	 *
	 * @return the produtos
	 */
	public HashMap<Produto, Integer> getProdutos() {
		return produtos;
	}
}
