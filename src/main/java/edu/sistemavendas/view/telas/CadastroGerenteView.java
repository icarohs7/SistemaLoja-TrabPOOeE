package edu.sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.sistemavendas.autenticacao.Gerente;
import edu.sistemavendas.view.util.ActionButton;
import edu.sistemavendas.view.util.EnterKeyListenerField;
import edu.sistemavendas.view.util.EnterKeyListenerPassField;
import edu.sistemavendas.view.util.EnterListener;
import edu.sistemavendas.view.util.FontLabel;
import edu.sistemavendas.view.util.ViewUtil;

/**
 * Instanciar CadastroGerenteView
 */
public class CadastroGerenteView extends JFrame {
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
	 * Instantiates a new Cadastro view gerente.
	 *
	 * @param s s
	 */
	public CadastroGerenteView( String s ) {
		super( s );
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
	 * Criar componentes
	 */
	private void criarComponentes() {
		/* Instanciar painel raiz */
		root = new JPanel( new MigLayout( "fillx" ) );
		
		/* Criar label título */
		JLabel titulo = new FontLabel( "Cadastrar Gerente", ViewUtil.FONT_H1 );
		
		/* Label login */
		JLabel loginLabel = new JLabel( "login" );
		/* Campo de login com ação para o botão enter */
		loginField = new EnterKeyListenerField( new EnterListener( this::cadastrar ) );
		
		/* Label senha */
		JLabel senhaLabel = new JLabel( "senha" );
		/* Campo senha com ação para o botão enter */
		senhaField = new EnterKeyListenerPassField( new EnterListener( this::cadastrar ) );
		
		/* Segunda label senha */
		JLabel senhaLabel2 = new JLabel( "senha" );
		/* Segundo campo senha */
		JPasswordField senhaField2 = new JPasswordField();
		
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
			if ( Gerente.cadastrarUsuario( login, senha ) ) {
				ViewUtil.showMessage( "Gerente cadastrado com sucesso!" );
				dispose();
				new LoginGerenteView( "Sistema de vendas" );
			} else {
				ViewUtil.showMessage( "Não foi possível cadastrar o gerente, usuario ja existe!" );
			}
		} else {
			ViewUtil.showMessage( "As senhas devem der iguais!" );
		}
	}
}
