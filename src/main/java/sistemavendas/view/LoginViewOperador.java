package sistemavendas.view;

import net.miginfocom.swing.MigLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sistemavendas.autenticacao.Operador;
import sistemavendas.exceptions.SenhaIncorretaException;
import sistemavendas.exceptions.UsuarioNaoExisteException;

/**
 * The type Login view.
 */
public class LoginViewOperador extends JFrame {
	/**
	 * Root.
	 */
	private JPanel root;
	
	/**
	 * Campo de login
	 */
	private JTextField loginField;
	/**
	 * Campo de senha
	 */
	private JPasswordField senhaField;
	
	/**
	 * Instantiates a new Login view.
	 *
	 * @param s s
	 */
	public LoginViewOperador( String s ) {
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
		/* Tornar visivel */
		setVisible( true );
	}
	
	/**
	 * Criar componentes.
	 */
	private void criarComponentes() {
		/* Instanciar painel raiz */
		root = new JPanel( new MigLayout( "fillx" ) );
		
		/* Listener responsável por logar ao se pressionar a tecla enter */
		KeyListener enterListener = new KeyListener() {
			@Override
			public void keyTyped( KeyEvent keyEvent ) {
			}
			
			@Override
			public void keyPressed( KeyEvent keyEvent ) {
				if ( keyEvent.getKeyCode() == KeyEvent.VK_ENTER ) {
					realizarLogin();
				}
			}
			
			@Override
			public void keyReleased( KeyEvent keyEvent ) {
			}
		};
		
		/* Criar label título */
		JLabel titulo = new JLabel( "Informe um login de operador" );
		/* Definir fonte do título */
		titulo.setFont( ViewUtils.FONT_H1 );
		
		/* Label para login */
		JLabel loginLabel = new JLabel( "Login" );
		/* Campo de login */
		loginField = new JTextField();
		/* Adicionar o comportamento ao pressionar a tecla enter */
		loginField.addKeyListener( enterListener );
		
		/* Label para senha */
		JLabel senhaLabel = new JLabel( "Senha" );
		/* Campo de senha */
		senhaField = new JPasswordField();
		/* Adicionar o comportamento ao pressionar a tecla enter */
		senhaField.addKeyListener( enterListener );
		
		/* Botão de registrar */
		JButton registrarButton = new JButton( "Registrar" );
		/* Adicionar ação ao clique do botão */
		registrarButton.addActionListener( ( evt ) -> {
			dispose();
			new CadastroViewOperador( "Sistema de Vendas - Cadastrar Operador" );
		} );
		
		/* Botão de entrar */
		JButton entrarButton = new JButton( "Entrar" );
		/* Adicionar ação ao clique do botão */
		entrarButton.addActionListener( ( evt ) -> {
			realizarLogin();
		} );
		
		/* Adicionar componentes à raiz */
		root.add( titulo, "center, span, wrap, gapbottom 20" );
		root.add( loginLabel );
		root.add( loginField, "grow, wrap" );
		root.add( senhaLabel );
		root.add( senhaField, "grow, wrap" );
		root.add( registrarButton, "grow" );
		root.add( entrarButton, "grow" );
	}
	
	/**
	 * Realizar login.
	 */
	private void realizarLogin() {
		/* Armazenar login digitado */
		String login = loginField.getText();
		/* Armazenar senha digitada */
		String senha = new String( senhaField.getPassword() );
		/* Autenticar usuário */
		try {
			Operador gerente = new Operador( login, senha );
			/* Entrar no sistema */
			JOptionPane.showMessageDialog( null, "Logado com sucesso!" );
			/* Fechar a janela */
			dispose();
			/* Criar a tela da aplicação do operador */
			new AppViewOperador( "Sistema de Vendas" );
		} catch ( SenhaIncorretaException e ) {
			JOptionPane.showMessageDialog( null, "A senha está incorreta" );
		} catch ( UsuarioNaoExisteException e ) {
			JOptionPane.showMessageDialog( null, "O usuário informado não existe" );
		}
	}
}
