package sistemavendas.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Instanciar AppViewOperador
 */
public class AppViewOperador extends JFrame {
	/**
	 * Painel raiz da aplicação
	 */
	private JPanel root;
	
	/**
	 * Instantiates a new App view operador.
	 *
	 * @param s s
	 */
	public AppViewOperador( String s ) {
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
	}
}
