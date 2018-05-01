package sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import sistemavendas.Loja;
import sistemavendas.view.util.FontLabel;
import sistemavendas.view.util.ViewUtil;

/**
 * Tela PainelEstoqueView
 */
public class PainelEstoqueView extends JFrame {
	/**
	 * Painel raiz da aplicação
	 */
	private JPanel root;
	
	/**
	 * Criar a tela
	 *
	 * @param s Titulo
	 */
	public PainelEstoqueView( String s ) {
		super( s );
		/* Procedimento de definição de componentes */
		criarComponentes();
		/* Definir painel contendo os componentes */
		setContentPane( root );
		/* Ajustar tamanho da janela aos componentes */
		pack();
		/* Definir a operação de fechamento da janela */
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
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
		
		/* Título */
		JLabel titulo = new FontLabel( "Produtos em estoque", ViewUtil.FONT_H1 );
		
		/* Lista com o estoque */
		DefaultListModel listModel = new DefaultListModel();
		/* Popular itens da lista */
		Loja.getInstance().getEstoque().getProdutos().forEach( ( produto, quantidade ) -> {
			listModel.addElement( "Produto: " + produto.getDescricao() + "     Quantidade: " + quantidade );
		} );
		/* Gerar a lista */
		JList listaProdutos = new JList( listModel );
		
		/* Adicionar elementos ao painel */
		root.add( titulo, "center,span,gapbottom 20,wrap" );
		root.add( listaProdutos, "span,grow" );
	}
}