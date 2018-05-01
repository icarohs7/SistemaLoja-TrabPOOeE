package sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sistemavendas.Loja;
import sistemavendas.autenticacao.Operador;
import sistemavendas.catalogo.CatalogoProdutos;
import sistemavendas.catalogo.Produto;
import sistemavendas.view.util.ActionButton;
import sistemavendas.view.util.FontLabel;
import sistemavendas.view.util.ViewUtil;

/**
 * Instanciar PainelOperadorView
 */
public class PainelOperadorView extends JFrame {
	/**
	 * Painel raiz da aplicação
	 */
	private JPanel root;
	
	/**
	 * Num itens thread.
	 */
	private Thread numItensThread;
	
	/**
	 * Usuário atual
	 */
	private Operador operador;
	
	/**
	 * Instantiates a new App view operador.
	 *
	 * @param s        s
	 * @param operador operador
	 */
	public PainelOperadorView( String s, Operador operador ) {
		super( s );
		/* Definir operador utilizando o sistema */
		this.operador = operador;
		/* Procedimento de definição de componentes */
		criarComponentes();
		/* Definir painel contendo os componentes */
		setContentPane( root );
		/* Ajustar tamanho da janela aos componentes */
		pack();
		/* Definir a operação de fechamento da janela */
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		/* Ajustar tamanho da janela */
		setSize( 600, getHeight() );
		/* Centralizar a janela */
		setLocationRelativeTo( null );
		/* Tornar visível */
		setVisible( true );
	}
	
	/**
	 * Criar componentes
	 */
	private void criarComponentes() {
		/* Instanciar painel raiz */
		root = new JPanel( new MigLayout( "fillx" ) );
		
		/* Criar label título */
		JLabel titulo = new FontLabel( "Bem Vindo(a) ao sistema de vendas!", ViewUtil.FONT_H1 );
		
		/* Criar label com o número de itens na venda */
		JLabel numItensLabel = new FontLabel( "", ViewUtil.FONT_H1 );
		/* Label para preço total da venda */
		JLabel precoTotalLabel = new FontLabel( "", ViewUtil.FONT_H1 );
		/* Criar thread para verificar continuamente o número de itens da venda */
		numItensThread = new Thread( () -> {
			while ( !Thread.interrupted() ) {
				if ( operador.getVendaEmAndamento() == null
				     || operador.getVendaEmAndamento().getProdutos().size() < 1 ) {
					numItensLabel.setText( "Sem itens na venda" );
					numItensLabel.setForeground( Color.RED );
					precoTotalLabel.setText( "Valor total da venda: R$0" );
				} else {
					numItensLabel.setText( "Qtd. de Itens na venda: " + operador.getVendaEmAndamento()
							.getProdutos()
							.size() );
					numItensLabel.setForeground( Color.BLACK );
					precoTotalLabel.setText( "Valor total da venda: R$" + operador.getVendaEmAndamento()
							.getValorTotal() );
				}
				try {
					Thread.sleep( 500 );
				} catch ( InterruptedException ignored ) {
				}
			}
		} );
		/* Iniciar a thread */
		numItensThread.start();
		
		/* Botão ver itens venda */
		JButton verItensBtn = new ActionButton( "Ver itens da venda", ( evt ) -> {
			verItensVenda();
		} );
		
		/* Botão adicionar item */
		JButton adicionarItemBtn = new ActionButton( "Adicionar item a venda", ( evt ) -> {
			adicionarItemVenda();
		} );
		
		/* Botão finalizar venda */
		JButton finalizarVendaBtn = new ActionButton( "Finalizar venda", ( evt ) -> {
			finalizarVenda();
		} );
		
		/* Adicionar itens ao painel */
		root.add( titulo, "center, span, wrap" );
		root.add( numItensLabel, "center, span, wrap" );
		root.add( precoTotalLabel, "center,span,gapbottom 20,wrap" );
		root.add( verItensBtn, "span,grow" );
		root.add( adicionarItemBtn, "span,grow" );
		root.add( finalizarVendaBtn, "span,grow" );
	}
	
	/**
	 * Ver itens da venda.
	 */
	public void verItensVenda() {
		if ( operador.getVendaEmAndamento() == null || operador.getVendaEmAndamento().getProdutos().size() < 1 ) {
			ViewUtil.showMessage( "Nao ha itens na venda" );
		} else {
			new ItensVendaView( "Produtos", operador.getVendaEmAndamento().getProdutos() );
		}
	}
	
	/**
	 * Adicionar item à venda.
	 */
	public void adicionarItemVenda() {
		if ( CatalogoProdutos.getInstance().getProdutos().size() < 1 ) {
			ViewUtil.showMessage( "Nao ha produtos cadastrados, contate o administrador do sistema!" );
		} else {
			new AdicionarItemVendaView( "Adicionar Item", operador );
		}
	}
	
	/**
	 * Finalizar venda.
	 */
	public void finalizarVenda() {
		if ( operador.getVendaEmAndamento() == null || operador.getVendaEmAndamento().getValorTotal() <= 0 ) {
			ViewUtil.showMessage( "A venda nao contem itens" );
		} else {
			setVisible( false );
			new FinalizarVendaView( "Finalizar venda", operador, this );
		}
	}
	
	/**
	 * Dispose.
	 */
	@Override
	public void dispose() {
		super.dispose();
		numItensThread.interrupt();
	}
}
