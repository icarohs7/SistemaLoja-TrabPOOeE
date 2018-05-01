package sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sistemavendas.autenticacao.Operador;
import sistemavendas.view.util.ActionButton;
import sistemavendas.view.util.EnterKeyListenerField;
import sistemavendas.view.util.EnterKeyListenerPassField;
import sistemavendas.view.util.EnterListener;
import sistemavendas.view.util.FontLabel;
import sistemavendas.view.util.ViewUtil;

/**
 * Instanciar CadastroGerenteView
 */
public class CadastroOperadorView extends JFrame {
	/**
	 * Root.
	 */
	private JPanel root;
	/**
	 * Login field.
	 */
	private JTextField loginField;
	/**
	 * Senha field.
	 */
	private JPasswordField senhaField;
	/**
	 * Senha field 2.
	 */
	private JPasswordField senhaField2;
	
	/**
	 * Instantiates a new Cadastro view operador.
	 *
	 * @param s s
	 */
	public CadastroOperadorView( String s ) {
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
	 * Criar componentes
	 */
	private void criarComponentes() {
		/* Instanciar painel raiz */
		root = new JPanel( new MigLayout( "fillx" ) );
		
		/* Criar label título */
		JLabel titulo = new FontLabel( "Cadastrar Operador", ViewUtil.FONT_H1 );
		
		/* Label login */
		JLabel loginLabel = new JLabel( "login" );
		/* Campo de login com ação para o pressionar da tecla enter */
		loginField = new EnterKeyListenerField( new EnterListener( this::cadastrar ) );
		
		/* Label senha */
		JLabel senhaLabel = new JLabel( "senha" );
		/* Campo senha com ação para o pressionar da tecla enter */
		senhaField = new EnterKeyListenerPassField( new EnterListener( this::cadastrar ) );
		
		/* Segunda label senha */
		JLabel senhaLabel2 = new JLabel( "senha" );
		/* Segundo campo senha com ação para o pressionar da tecla enter */
		senhaField2 = new EnterKeyListenerPassField( new EnterListener( this::cadastrar ) );
		
		/* Botão cadastrar */
		JButton cadastrarBtn = new ActionButton( "Cadastrar", ( evt ) -> cadastrar() );
		
		/* Adicionar componentes ao painel */
		root.add( titulo, "center, span, wrap" );
		root.add( loginLabel );
		root.add( loginField, "grow, wrap" );
		root.add( senhaLabel );
		root.add( senhaField, "grow, wrap" );
		root.add( senhaLabel2 );
		root.add( senhaField2, "grow, wrap" );
		root.add( cadastrarBtn, "span, wrap, right, grow" );
	}
	
	/**
	 * Cadastrar.
	 */
	private void cadastrar() {
		String login = loginField.getText();
		String senha = new String( senhaField.getPassword() );
		String senha2 = new String( senhaField2.getPassword() );
		
		if ( senha.equals( senha2 ) ) {
			if ( Operador.cadastrarUsuario( login, senha ) ) {
				ViewUtil.showMessage( "Operador cadastrado com sucesso!" );
				dispose();
				new LoginOperadorView( "Sistema de vendas" );
			} else {
				ViewUtil.showMessage( "Não foi possível cadastrar o operador, usuário já existe!" );
			}
		} else {
			ViewUtil.showMessage( "As senhas devem der iguais!" );
		}
	}
}
