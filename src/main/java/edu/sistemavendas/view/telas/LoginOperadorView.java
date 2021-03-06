package edu.sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.sistemavendas.autenticacao.Operador;
import edu.sistemavendas.exceptions.SenhaIncorretaException;
import edu.sistemavendas.exceptions.UsuarioNaoExisteException;
import edu.sistemavendas.view.util.ActionButton;
import edu.sistemavendas.view.util.AlertLabel;
import edu.sistemavendas.view.util.EnterKeyListenerField;
import edu.sistemavendas.view.util.EnterKeyListenerPassField;
import edu.sistemavendas.view.util.EnterListener;
import edu.sistemavendas.view.util.FontLabel;
import edu.sistemavendas.view.util.ViewUtil;

/**
 * The type Login view.
 */
public class LoginOperadorView extends JFrame {
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
	public LoginOperadorView( String s ) {
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
		/* Tornar visivel */
		setVisible( true );
	}
	
	/**
	 * Criar componentes.
	 */
	private void criarComponentes() {
		/* Instanciar painel raiz */
		root = new JPanel( new MigLayout( "fillx" ) );
		
		/* Criar label título */
		JLabel titulo = new FontLabel( "Informe um login de operador", ViewUtil.FONT_H1 );
		
		/* Criar label para avisar sem operador cadastrados */
		JLabel semUsuariosLabel = new AlertLabel( "Nao ha nenhum operador cadastrado, registre-se antes!" );
		
		/* Label para login */
		JLabel loginLabel = new JLabel( "Login" );
		/* Campo de login */
		loginField = new EnterKeyListenerField( new EnterListener( this::realizarLogin ) );
		
		/* Label para senha */
		JLabel senhaLabel = new JLabel( "Senha" );
		/* Campo de senha */
		senhaField = new EnterKeyListenerPassField( new EnterListener( this::realizarLogin ) );
		
		/* Botão de registrar */
		JButton registrarButton = new ActionButton( "Registrar", ( evt ) -> {
			/* Ação do botão */
			setVisible( false );
			new CadastroOperadorView( "Sistema de Vendas - Cadastrar Operador", this );
		} );
		
		/* Botão de entrar */
		JButton entrarButton = new ActionButton( "Entrar", ( evt ) -> realizarLogin() );
		
		/* Adicionar componentes à raiz */
		if ( Operador.getNumCadastrados() < 1 ) {
			root.add( titulo, "center, span, wrap" );
			root.add( semUsuariosLabel, "center, span, wrap, gapbottom 20" );
		} else {
			root.add( titulo, "center, span, wrap, gapbottom 20" );
		}
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
			Operador operador = new Operador( login, senha );
			/* Entrar no sistema */
			ViewUtil.showMessage( "Logado com sucesso!" );
			/* Fechar a janela */
			dispose();
			/* Criar a tela da aplicação do operador */
			new PainelOperadorView( "Sistema de Vendas - Tela do Operador", operador );
		} catch ( SenhaIncorretaException e ) {
			ViewUtil.showMessage( "A senha está incorreta" );
		} catch ( UsuarioNaoExisteException e ) {
			ViewUtil.showMessage( "O usuario informado nao existe" );
		}
	}
	
	/**
	 * Dispose.
	 */
	@Override
	public void dispose() {
		super.dispose();
		PainelGerenteView.getInstance().setState( Frame.NORMAL );
	}
}
