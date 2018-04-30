package sistemavendas.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sistemavendas.autenticacao.Gerente;

/**
 * The type App view gerente.
 */
public class AppViewGerente extends JFrame {
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
	public AppViewGerente( String s, Gerente gerente ) {
		super( s );
		this.gerente = gerente;
		/* Procedimento de definição de componentes */
		criarComponentes();
		/* Definir painel contendo os componentes */
		setContentPane( root );
		/* Ajustar tamanho da janela aos componentes */
		pack();
		/* Definir a operação de fechamento da janela */
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
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
		JLabel titulo1 = new JLabel( "Bem Vindo(a)!" );
		JLabel titulo2 = new JLabel( "Selecione a operação desejada" );
		/* Definir fonte do título */
		titulo1.setFont( ViewUtils.FONT_H1 );
		titulo2.setFont( ViewUtils.FONT_H1 );
		
		/* Botão iniciar sistema */
		JButton iniciarSistemaBtn = new JButton( "Iniciar Sistema" );
		/* Ação ao clicar no botão iniciar sistema */
		iniciarSistemaBtn.addActionListener( ( evt ) -> {
			dispose();
			gerente.iniciarSistema();
		} );
		
		/* Botão finalizar sistema */
		JButton finalizarSistemaBtn = new JButton( "Finalizar Sistema" );
		/* Ação ao clicar no botão finalizar sistema */
		finalizarSistemaBtn.addActionListener(
				( evt ) -> gerente.finalizarSistema() );
		
		/* Adicionar componentes ao painel raiz */
		root.add( titulo1, "center, span, wrap" );
		root.add( titulo2, "center, span, wrap, gapbottom 20" );
		root.add( iniciarSistemaBtn, "grow" );
		root.add( finalizarSistemaBtn, "grow" );
	}
}
