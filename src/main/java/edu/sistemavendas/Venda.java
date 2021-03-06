package edu.sistemavendas;

import java.time.LocalDateTime;
import java.util.HashMap;

import edu.sistemavendas.catalogo.Produto;
import edu.sistemavendas.exceptions.EstoqueInsuficienteException;
import edu.sistemavendas.exceptions.OperacaoInvalidaException;
import edu.sistemavendas.exceptions.ProdutoNaoCadastradoException;

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
	 * Instantiates a new Venda.
	 *
	 * @param produtoInicial O primeiro produto adicionado à venda
	 * @param quantidade     the quantidade
	 *
	 * @throws EstoqueInsuficienteException  estoque insuficiente exception
	 * @throws ProdutoNaoCadastradoException produto nao cadastrado exception
	 * @throws OperacaoInvalidaException     operacao invalida exception
	 */
	public Venda( Produto produtoInicial, int quantidade )
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
		/* Adiciona o primeiro produto à venda */
		registrarItem( produtoInicial, quantidade );
	}
	
	/**
	 * Adicionar produto.
	 *
	 * @param produto    the produto
	 * @param quantidade the quantidade
	 *
	 * @throws EstoqueInsuficienteException  the estoque insuficiente exception
	 * @throws ProdutoNaoCadastradoException the produto nao cadastrado exception
	 * @throws OperacaoInvalidaException     the operacao invalida exception
	 */
	public void registrarItem( Produto produto, int quantidade )
			throws EstoqueInsuficienteException, ProdutoNaoCadastradoException, OperacaoInvalidaException {
		/* Lançar uma exceção caso o usuário tente adicionar um produto à venda depois de fechada */
		if ( fechada ) {
			throw new OperacaoInvalidaException( "Não é possível adicionas produtos a uma venda fechada!" );
		}
		/* Lançar uma exceção caso o usuário tente adicionar uma quantidade de produtos maior que a presente
		 * no estoque */
		if ( Loja.getInstance().getEstoque().getProdutos().get( produto ) < quantidade ) {
			throw new EstoqueInsuficienteException( "Não há quantidade suficiente do produto em estoque" );
		}
		/* Lançar uma exceção caso o usuário tente adicionar à venda um produto não cadastrado */
		if ( !Loja.getInstance().getEstoque().getProdutos().containsKey( produto ) ) {
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
	public void fecharVenda() {
		fechada = true;
		/* Ao fim da venda, reduzir o estoque */
		produtos.forEach( ( produto, quantidade ) -> {
			Loja.getInstance().getEstoque().reduzirEstoque( produto, quantidade );
		} );
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
