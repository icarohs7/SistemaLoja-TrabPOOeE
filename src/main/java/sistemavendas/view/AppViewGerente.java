package sistemavendas.view;

import net.miginfocom.swing.MigLayout;

import java.awt.Font;

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
		/* Centralizar a janela */
		setLocationRelativeTo( null );
	}
	
	/**
	 * Criar componentes.
	 */
	private void criarComponentes() {
		/* Instanciar painel raiz */
		root = new JPanel( new MigLayout() );
		
		/* Criar label título */
		JLabel titulo = new JLabel( "Selecione a operação desejada" );
		/* Fonte do título */
		Font h1 = new Font( "SansSerif", Font.BOLD, 18 );
		/* Definir fonte do título */
		titulo.setFont( h1 );
		
		/* Botão iniciar sistema */
		JButton iniciarSistemaBtn = new JButton( "Iniciar Sistema" );
		
		/* Botão finalizar sistema */
		JButton finalizarSistemaBtn = new JButton( "Finalizar Sistema" );
		/* Ação ao clicar no botão finalizar sistema */
		finalizarSistemaBtn.addActionListener( ( evt ) -> dispose() );
		
		/* Adicionar componentes ao painel raiz */
		root.add( titulo, "center, span 3, wrap" );
		root.add( iniciarSistemaBtn, "span 2" );
		root.add( finalizarSistemaBtn, "align right" );
	}
}
