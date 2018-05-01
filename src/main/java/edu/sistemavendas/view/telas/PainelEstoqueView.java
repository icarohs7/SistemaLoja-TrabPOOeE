package edu.sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import edu.sistemavendas.Loja;
import edu.sistemavendas.view.util.ActionButton;
import edu.sistemavendas.view.util.FontLabel;
import edu.sistemavendas.view.util.ViewUtil;

/**
 * Tela PainelEstoqueView
 */
public class PainelEstoqueView extends JFrame {
	/**
	 * Painel raiz da aplicação
	 */
	private JPanel root;
	
	/**
	 * Thread lista produtos.
	 */
	private Thread threadListaProdutos;
	
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
		/* Gerar a lista */
		JList listaProdutos = new JList();
		/* Thread para manter a lista atualizada */
		threadListaProdutos = new Thread( () -> {
			while ( !Thread.interrupted() ) {
				listModel.clear();
				/* Popular itens da lista */
				Loja.getInstance().getEstoque().getProdutos().forEach( ( produto, quantidade ) -> {
					listModel.addElement( "Produto: " + produto.getDescricao() + "     Quantidade: " + quantidade );
				} );
				listaProdutos.setModel( listModel );
				try {
					Thread.sleep( 2000 );
				} catch ( InterruptedException ignored ) {
				}
			}
		} );
		threadListaProdutos.start();
		
		/* Botão editar estoque */
		JButton editarEstoqueBtn = new ActionButton( "Editar Estoque", ( evt ) -> {
			new AlterarEstoqueView( "Editar estoque" );
		} );
		
		/* Adicionar elementos ao painel */
		root.add( titulo, "center,span,gapbottom 20,wrap" );
		root.add( listaProdutos, "span,grow,wrap" );
		root.add( editarEstoqueBtn, "span, grow" );
	}
	
	/**
	 * Dispose.
	 */
	@Override
	public void dispose() {
		super.dispose();
		threadListaProdutos.interrupt();
	}
}
