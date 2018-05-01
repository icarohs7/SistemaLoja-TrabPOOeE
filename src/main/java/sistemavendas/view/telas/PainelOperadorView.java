package sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sistemavendas.autenticacao.Operador;
import sistemavendas.view.util.FontLabel;
import sistemavendas.view.util.ViewUtil;

/**
 * Instanciar PainelOperadorView
 */
public class PainelOperadorView extends JFrame {
	/**
	 * Painel raiz da aplicação
	 */
	private JPanel root;
	
	/**
	 * Usuário atual
	 */
	private Operador operador;
	
	/**
	 * Instantiates a new App view operador.
	 *
	 * @param s s
	 */
	public PainelOperadorView( String s, Operador operador ) {
		super( s );
		/* Definir operador utilizando o sistema */
		this.operador = operador;
		/* Procedimento de definição de componentes */
		criarComponentes();
		/* Definir painel contendo os componentes */
		setContentPane( root );
		/* Ajustar tamanho da janela aos componentes */
		pack();
		/* Definir a operação de fechamento da janela */
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		/* Ajustar tamanho da janela */
		setSize( 600, getHeight() );
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
		JLabel titulo = new FontLabel( "Bem Vindo(a) ao sistema de vendas!", ViewUtil.FONT_H1 );
		
		/* Criar label com o número de itens na venda */
		JLabel numItensLabel = new FontLabel( "", ViewUtil.FONT_H1 );
		/* Criar thread para verificar continuamente o número de itens da venda */
		Thread numItensThread = new Thread( () -> {
			if ( operador.getVendaEmAndamento() == null || operador.getVendaEmAndamento().getProdutos().size() < 1 ) {
				numItensLabel.setText( "Sem itens na venda" );
				numItensLabel.setForeground( Color.RED );
			} else {
				numItensLabel.setText( "Qtd. de Itens na venda: " + operador.getVendaEmAndamento()
						.getProdutos()
						.size() );
				numItensLabel.setForeground( Color.BLACK );
			}
			try {
				Thread.sleep( 1000 );
			} catch ( InterruptedException ignored ) {
			}
		} );
		/* Iniciar a thread */
		numItensThread.start();
		
		/* Adicionar itens ao painel */
		root.add( titulo, "center, span, wrap" );
		root.add( numItensLabel, "center, span, gapbottom 20, wrap" );
	}
}
