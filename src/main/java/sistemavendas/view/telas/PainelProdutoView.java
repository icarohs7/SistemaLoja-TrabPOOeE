package sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sistemavendas.view.util.ActionButton;
import sistemavendas.view.util.FontLabel;
import sistemavendas.view.util.ViewUtil;

/**
 * Tela PainelProdutoView
 */
public class PainelProdutoView extends JFrame {
	/**
	 * Painel raiz da aplicação
	 */
	private JPanel root;
	
	/**
	 * Criar a tela
	 *
	 * @param s Titulo
	 */
	public PainelProdutoView( String s ) {
		super( s );
		/* Procedimento de definição de componentes */
		criarComponentes();
		/* Definir painel contendo os componentes */
		setContentPane( root );
		/* Ajustar tamanho da janela aos componentes */
		pack();
		/* Definir a operação de fechamento da janela */
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
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
		
		/* Título */
		JLabel titulo = new FontLabel( "Catálogo de Produtos", ViewUtil.FONT_H1 );
		
		/* Botão cadastrar produto */
		JButton cadastrarBtn = new ActionButton( "Cadastrar",
		                                         ( evt ) -> new CadastrarProdutoView( "Cadastrar Produto" )
		);
		
		/* Adicionar elementos ao painel */
		root.add( titulo, "center, span, gapbottom 20, wrap" );
		root.add( cadastrarBtn, "grow" );
	}
}
