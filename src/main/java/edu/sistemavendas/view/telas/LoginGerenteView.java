package edu.sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.sistemavendas.autenticacao.Gerente;
import edu.sistemavendas.exceptions.SenhaIncorretaException;
import edu.sistemavendas.exceptions.UsuarioNaoExisteException;
import edu.sistemavendas.view.util.ActionButton;
import edu.sistemavendas.view.util.EnterKeyListenerField;
import edu.sistemavendas.view.util.EnterKeyListenerPassField;
import edu.sistemavendas.view.util.EnterListener;
import edu.sistemavendas.view.util.FontLabel;
import edu.sistemavendas.view.util.ViewUtil;

/**
 * The type Login view.
 */
public class LoginGerenteView extends JFrame {
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
	 * @param titulo titulo
	 */
	public LoginGerenteView( String titulo ) {
		super( titulo );
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
		
		/* Criar label título */
		JLabel titulo = new FontLabel( "Informe um login de gerente", ViewUtil.FONT_H1 );
		
		/* Label para login */
		JLabel loginLabel = new JLabel( "Login" );
		/* Campo de login com ação para o botão enter */
		loginField = new EnterKeyListenerField( new EnterListener( this::realizarLogin ) );
		
		/* Label para senha */
		JLabel senhaLabel = new JLabel( "Senha" );
		/* Campo de senha com ação para o botão enter */
		senhaField = new EnterKeyListenerPassField( new EnterListener( this::realizarLogin ) );
		
		/* Botão de registrar */
		JButton registrarButton = new ActionButton( "Registrar", ( evt ) -> {
			/* Ação do botão */
			setVisible( false );
			new CadastroGerenteView( "Sistema de Vendas - Cadastrar Gerente", this );
		} );
		
		/* Botão de entrar */
		JButton entrarButton = new ActionButton( "Entrar", ( evt ) -> realizarLogin() );
		
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
			new PainelGerenteView( "Painel do Gerente", gerente );
		} catch ( SenhaIncorretaException e ) {
			JOptionPane.showMessageDialog( null, "A senha esta incorreta" );
		} catch ( UsuarioNaoExisteException e ) {
			JOptionPane.showMessageDialog( null, "O usuario informado nao existe" );
		}
	}
}
