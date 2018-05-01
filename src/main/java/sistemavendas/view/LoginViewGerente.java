package sistemavendas.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sistemavendas.autenticacao.Gerente;
import sistemavendas.exceptions.SenhaIncorretaException;
import sistemavendas.exceptions.UsuarioNaoExisteException;

/**
 * The type Login view.
 */
public class LoginViewGerente extends JFrame {
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
	public LoginViewGerente( String s ) {
		super( s );
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
		
		/* Listener utilizado para reconhecer o pressionar da tecla enter */
		EnterListener enterListener = new EnterListener( this::realizarLogin );
		
		/* Criar label título */
		JLabel titulo = new JLabel( "Informe um login de gerente" );
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
		registrarButton.addActionListener(
				( evt ) -> {
					dispose();
					new CadastroViewGerente( "Sistema de Vendas - Cadastrar Gerente" );
				} );
		
		/* Botão de entrar */
		JButton entrarButton = new JButton( "Entrar" );
		/* Adicionar ação ao clique do botão */
		entrarButton.addActionListener(
				( evt ) -> realizarLogin() );
		
		/* Adicionar componentes à raiz */
		root.add( titulo, "span, center, wrap, gapbottom 20" );
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
			Gerente gerente = new Gerente( login, senha );
			/* Entrar no sistema */
			JOptionPane.showMessageDialog( null, "Logado com sucesso!" );
			dispose();
			new AppViewGerente( "Sistema de Vendas", gerente );
		} catch ( SenhaIncorretaException e ) {
			JOptionPane.showMessageDialog( null, "A senha está incorreta" );
		} catch ( UsuarioNaoExisteException e ) {
			JOptionPane.showMessageDialog( null, "O usuário informado não existe" );
		}
	}
}