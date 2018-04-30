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

/**
 * Instanciar CadastroViewGerente
 */
public class CadastroViewGerente extends JFrame {
	/**
	 * Root.
	 */
	private JPanel root;
	
	/**
	 * Instantiates a new Cadastro view gerente.
	 *
	 * @param s s
	 */
	public CadastroViewGerente( String s ) {
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
		JLabel titulo = new JLabel( "Cadastrar Gerente" );
		/* Definir fonte do título */
		titulo.setFont( ViewUtils.FONT_H1 );
		
		/* Label login */
		JLabel loginLabel = new JLabel( "login" );
		/* Campo de login */
		JTextField loginField = new JTextField();
		
		/* Label senha */
		JLabel senhaLabel = new JLabel( "senha" );
		/* Campo senha */
		JPasswordField senhaField = new JPasswordField();
		
		/* Segunda label senha */
		JLabel senhaLabel2 = new JLabel( "senha" );
		/* Segundo campo senha */
		JPasswordField senhaField2 = new JPasswordField();
		
		/* Botão cadastrar */
		JButton cadastrarBtn = new JButton( "Cadastrar" );
		/* Ação do botão cadastrar */
		cadastrarBtn.addActionListener( ( evt ) -> {
			String login = loginField.getText();
			String senha = new String( senhaField.getPassword() );
			String senha2 = new String( senhaField2.getPassword() );
			
			if ( senha.equals( senha2 ) ) {
				if ( Gerente.cadastrarUsuario( login, senha ) ) {
					JOptionPane.showMessageDialog( null, "Gerente cadastrado com sucesso!" );
					dispose();
					new LoginViewGerente( "Sistema de vendas" );
				} else {
					JOptionPane.showMessageDialog( null,
					                               "Não foi possível cadastrar o gerente, usuário já existe!"
					);
				}
			} else {
				JOptionPane.showMessageDialog( null, "As senhas devem der iguais!" );
			}
		} );
		
		/* Listener para pressionar da tecla enter */
		EnterListener enterListener = new EnterListener( cadastrarBtn::doClick );
		/* Adicionar listener aos campos de texto */
		loginField.addKeyListener( enterListener );
		senhaField.addKeyListener( enterListener );
		senhaField2.addKeyListener( enterListener );
		
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
}
