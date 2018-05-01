package sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sistemavendas.autenticacao.Gerente;
import sistemavendas.catalogo.CatalogoProdutos;
import sistemavendas.view.util.ActionButton;
import sistemavendas.view.util.FontLabel;
import sistemavendas.view.util.ViewUtil;

/**
 * The type App view gerente.
 */
public class PainelGerenteView extends JFrame {
	/**
	 * Root.
	 */
	private JPanel root;
	/**
	 * Gerente.
	 */
	private Gerente gerente;
	
	/**
	 * Instantiates a new App view gerente.
	 *
	 * @param s       s
	 * @param gerente gerente
	 */
	public PainelGerenteView( String s, Gerente gerente ) {
		super( s );
		this.gerente = gerente;
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
	 * Criar componentes.
	 */
	private void criarComponentes() {
		/* Instanciar painel raiz */
		root = new JPanel( new MigLayout( "fillx" ) );
		
		/* Criar as labels de título */
		JLabel titulo1 = new FontLabel( "Bem Vindo(a)!", ViewUtil.FONT_H1 );
		JLabel titulo2 = new FontLabel( "Selecione a operação desejada", ViewUtil.FONT_H1 );
		
		/* Label número de produtos */
		JLabel numProdutosLabel = new JLabel();
		/* Thread para manter o número de produtos atualizado */
		Thread numProdutosThread = new Thread( () -> {
			while ( !Thread.interrupted() ) {
				numProdutosLabel.setText( "Qtd. de produtos cadastrados: "
				                          + CatalogoProdutos.getInstance().getProdutos().size() );
				try {
					Thread.sleep( 1000 );
				} catch ( InterruptedException e ) {
					e.printStackTrace();
				}
			}
		} );
		numProdutosThread.start();
		
		/* Botão iniciar sistema */
		JButton iniciarSistemaBtn = new ActionButton( "Iniciar Sistema", ( evt ) -> {
			/* Ação do botão */
			setState( Frame.ICONIFIED );
			gerente.iniciarSistema();
		} );
		
		/* Botão finalizar sistema */
		JButton finalizarSistemaBtn = new ActionButton( "Finalizar Sistema", ( evt ) -> gerente.finalizarSistema() );
		
		/* Botão gerenciar produtos */
		JButton cadastrarProdutoBtn = new ActionButton( "Cadastrar Produto", ( evt ) -> {
			new CadastrarProdutoView( "Cadastrar Produto" );
		} );
		
		/* Adicionar componentes ao painel raiz */
		root.add( titulo1, "center, span, wrap" );
		root.add( titulo2, "center, span, wrap" );
		root.add( numProdutosLabel, "center, span, gapbottom 20, wrap" );
		root.add( iniciarSistemaBtn, "grow" );
		root.add( finalizarSistemaBtn, "grow, wrap" );
		root.add( cadastrarProdutoBtn, "grow" );
	}
}
