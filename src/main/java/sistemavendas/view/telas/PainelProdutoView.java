package sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import sistemavendas.catalogo.CatalogoProdutos;

/**
 * Tela PainelProdutoView
 */
public class PainelProdutoView extends JFrame {
	/**
	 * Painel raiz da aplicação
	 */
	private JPanel root;
	
	/**
	 * Criar a tela
	 *
	 * @param s Titulo
	 */
	public PainelProdutoView( String s ) {
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
		setSize( 300, getHeight() );
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
		
		/* Criar modelo da lista */
		DefaultListModel lista = new DefaultListModel();
		
		/* Popular o modelo */
		CatalogoProdutos.getInstance().getProdutos().forEach( ( produto ) -> {
			lista.addElement( produto.getDescricao() + "                  Preco: R$" + produto.getPreco() );
		} );
		
		/* Criar a lista */
		JList list = new JList();
		/* Unir a lista ao modelo */
		list.setModel( lista );
		
		/* Adicionar lista ao painel */
		root.add( list, "grow" );
	}
}
