package edu.sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.sistemavendas.autenticacao.Operador;
import edu.sistemavendas.catalogo.CatalogoProdutos;
import edu.sistemavendas.catalogo.Produto;
import edu.sistemavendas.exceptions.EstoqueInsuficienteException;
import edu.sistemavendas.view.util.ActionButton;
import edu.sistemavendas.view.util.EnterKeyListenerField;
import edu.sistemavendas.view.util.EnterListener;
import edu.sistemavendas.view.util.ViewUtil;

/**
 * Tela AdicionarItemVendaView
 */
public class AdicionarItemVendaView extends JFrame {
	/**
	 * Painel raiz da aplicação
	 */
	private JPanel root;
	
	/**
	 * Operador.
	 */
	private Operador operador;
	
	/**
	 * Produtos combo.
	 */
	private JComboBox produtosCombo;
	
	/**
	 * Quantidade produtos.
	 */
	private JTextField quantidadeProdutosField;
	
	/**
	 * Criar a tela
	 *
	 * @param s        Titulo
	 * @param operador operador
	 */
	public AdicionarItemVendaView( String s, Operador operador ) {
		super( s );
		this.operador = operador;
		/* Procedimento de definição de componentes */
		criarComponentes();
		/* Definir painel contendo os componentes */
		setContentPane( root );
		/* Ajustar tamanho da janela aos componentes */
		pack();
		/* Definir a operação de fechamento da janela */
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		/* Ajustar tamanho */
		setSize( 400, getHeight() );
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
		String[] produtosNomes = new String[CatalogoProdutos.getInstance().getProdutos().size()];
		for ( int i = 0; i < produtosNomes.length; i++ ) {
			produtosNomes[i] = CatalogoProdutos.getInstance().getProdutos().get( i ).getDescricao();
		}
		produtosCombo = new JComboBox( produtosNomes );
		quantidadeProdutosField = new EnterKeyListenerField( new EnterListener( this::adicionarItem ) );
		JButton adicionarItemBtn = new ActionButton( "Adicionar item", ( evt ) -> {
			try {
				adicionarItem();
			} catch ( EstoqueInsuficienteException e ) {
				ViewUtil.showMessage( "Estoque insuficiente" );
			}
		} );
		root.add( new JLabel( "Produto" ) );
		root.add( produtosCombo, "span,grow,wrap" );
		root.add( new JLabel( "Quantidade" ) );
		root.add( quantidadeProdutosField, "span,grow,wrap" );
		root.add( adicionarItemBtn, "span,grow" );
	}
	
	/**
	 * Adicionar item.
	 */
	public void adicionarItem() {
		try {
			int indice = produtosCombo.getSelectedIndex();
			Produto produto = CatalogoProdutos.getInstance().getProdutos().get( indice );
			int quantidade = Integer.valueOf( quantidadeProdutosField.getText() );
			for ( int i = 0; i < quantidade; i++ ) {
				operador.registrarItem( produto );
			}
			ViewUtil.showMessage( "Item adicionado com sucesso!" );
			dispose();
		} catch ( NumberFormatException e ) {
			ViewUtil.showMessage( "quantidade inválida" );
		}
	}
}
