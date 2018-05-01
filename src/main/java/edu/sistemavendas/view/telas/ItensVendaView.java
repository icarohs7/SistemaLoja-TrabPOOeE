package edu.sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import edu.sistemavendas.catalogo.Produto;

/**
 * Tela ItensVendaView
 */
public class ItensVendaView extends JFrame {
	/**
	 * List.
	 */
	HashMap<Produto, Integer> list;
	/**
	 * Painel raiz da aplicação
	 */
	private JPanel root;
	
	/**
	 * Instantiates a new Itens venda view.
	 *
	 * @param s    s
	 * @param list list
	 */
	public ItensVendaView( String s, HashMap<Produto, Integer> list ) {
		super( s );
		/* Definir lista */
		this.list = list;
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
		root = new JPanel( new MigLayout( "fillx" ) );
		DefaultListModel listModel = new DefaultListModel();
		list.forEach( ( elem, qtd ) -> {
			listModel.addElement(
					"Produto: " + elem.getDescricao() +
					"    Preço unit.: R$" + elem.getPreco() +
					"    Quantidade: " + qtd );
		} );
		JList listaElems = new JList( listModel );
		root.add( listaElems, "grow,span" );
	}
}
