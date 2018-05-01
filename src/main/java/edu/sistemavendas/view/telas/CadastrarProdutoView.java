package edu.sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.sistemavendas.catalogo.CatalogoProdutos;
import edu.sistemavendas.catalogo.Produto;
import edu.sistemavendas.view.util.ActionButton;
import edu.sistemavendas.view.util.EnterKeyListenerField;
import edu.sistemavendas.view.util.EnterListener;
import edu.sistemavendas.view.util.FontLabel;
import edu.sistemavendas.view.util.ViewUtil;

/**
 * Tela CadastrarProdutoView
 */
public class CadastrarProdutoView extends JFrame {
	/**
	 * Painel raiz da aplicação
	 */
	private JPanel root;
	
	/**
	 * Desc field.
	 */
	private JTextField descField;
	
	/**
	 * Preco field.
	 */
	private JTextField precoField;
	
	/**
	 * Criar a tela
	 *
	 * @param s Titulo
	 */
	public CadastrarProdutoView( String s ) {
		super( s );
		/* Procedimento de definição de componentes */
		criarComponentes();
		/* Definir painel contendo os componentes */
		setContentPane( root );
		/* Ajustar tamanho da janela aos componentes */
		pack();
		/* Definir a operação de fechamento da janela */
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		/* Ajustar tamanho da janela */
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
		
		/* Label título */
		JLabel titulo = new FontLabel( "Cadastrar produto", ViewUtil.FONT_H1 );
		
		/* Label descricao */
		JLabel descLabel = new JLabel( "Descricao" );
		/* Campo de texto para descrição com ação para o pressionar da tecla enter */
		descField = new EnterKeyListenerField( new EnterListener( this::cadastrar ) );
		
		/* Label preco */
		JLabel precolabel = new JLabel( "Preco" );
		/* Campo de texto para preço com ação para o pressionar da tecla enter */
		precoField = new EnterKeyListenerField( new EnterListener( this::cadastrar ) );
		
		/* Botão cancelar */
		JButton cancelarBtn = new ActionButton( "Cancelar", ( evt ) -> dispose() );
		
		/* Botão adicionar */
		JButton adicionarBtn = new ActionButton( "Adicionar", ( evt ) -> cadastrar() );
		
		/* Botão finalizar */
		JButton finalizarBtn = new ActionButton( "Finalizar", ( evt ) -> dispose() );
		
		/* Botão lista de produtos */
		JButton listaProdutosBtn = new ActionButton( "Lista de Produtos", ( evt ) -> {
			if ( CatalogoProdutos.getInstance().getProdutos().size() < 1 ) {
				ViewUtil.showMessage( "Nao ha produtos cadastrados!" );
			} else {
				new PainelProdutoView( "Lista de produtos" );
			}
		} );
		
		/* Adicionar elementos ao painel */
		root.add( titulo, "center, span, gapbottom 20, wrap" );
		root.add( descLabel );
		root.add( descField, "grow,span, wrap" );
		root.add( precolabel );
		root.add( precoField, "grow,span, wrap" );
		root.add( cancelarBtn, "grow" );
		root.add( adicionarBtn, "grow" );
		root.add( finalizarBtn, "grow,wrap" );
		root.add( listaProdutosBtn, "grow, span" );
	}
	
	/**
	 * Cadastrar.
	 */
	public void cadastrar() {
		String descricao = descField.getText();
		double preco;
		try {
			preco = Double.parseDouble( precoField.getText() );
		} catch ( NumberFormatException e ) {
			ViewUtil.showMessage( "O preco informado e invalido" );
			return;
		}
		
		CatalogoProdutos.getInstance().CadastrarProduto( new Produto( descricao, preco ) );
		ViewUtil.showMessage( "Produto cadastrado com sucesso!" );
		descField.setText( "" );
		precoField.setText( "" );
		descField.grabFocus();
	}
}
