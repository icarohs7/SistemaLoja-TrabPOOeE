package edu.sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.sistemavendas.Loja;
import edu.sistemavendas.catalogo.CatalogoProdutos;
import edu.sistemavendas.catalogo.Produto;
import edu.sistemavendas.view.util.ActionButton;
import edu.sistemavendas.view.util.EnterKeyListenerField;
import edu.sistemavendas.view.util.EnterListener;
import edu.sistemavendas.view.util.FontLabel;
import edu.sistemavendas.view.util.ViewUtil;

/**
 * Tela AlterarEstoqueView
 */
public class AlterarEstoqueView extends JFrame {
	/**
	 * Painel raiz da aplicação
	 */
	private JPanel root;
	
	/**
	 * Incremento estoque field.
	 */
	private JTextField incrementoEstoqueField;
	/**
	 * Indice combo.
	 */
	private JComboBox indiceCombo;
	
	/**
	 * Criar a tela
	 *
	 * @param s Titulo
	 */
	public AlterarEstoqueView( String s ) {
		super( s );
		/* Procedimento de definição de componentes */
		criarComponentes();
		/* Definir painel contendo os componentes */
		setContentPane( root );
		/* Ajustar tamanho da janela aos componentes */
		pack();
		/* Definir a operação de fechamento da janela */
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		/* Centralizar a janela em seu pai */
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
		JLabel titulo = new FontLabel( "Escolha o indice e incremento em estoque do item", ViewUtil.FONT_H1 );
		
		/* Lista */
		DefaultListModel listModel = new DefaultListModel();
		int i = 0;
		for ( Produto produto : CatalogoProdutos.getInstance().getProdutos() ) {
			listModel.addElement( "Indice: " + i + "   Produto: " + produto.getDescricao() );
			i++;
		}
		JList listaItens = new JList( listModel );
		
		/* Combo box para índices */
		String[] indices = new String[i];
		for ( int j = 0; j < i; j++ ) {
			indices[j] = Integer.toString( j );
		}
		indiceCombo = new JComboBox( indices );
		
		/* Campo de texto com incremento no estoque */
		incrementoEstoqueField = new EnterKeyListenerField( new EnterListener( this::incrementarEstoque ) );
		
		/* Botão incrementar */
		JButton incrementoEstoqueBtn = new ActionButton( "Incrementar", ( evt ) -> {
			incrementarEstoque();
		} );
		
		/* Adicionar elementos ao painel */
		root.add( titulo, "center, span, gapbottom 20, wrap" );
		root.add( listaItens, "span,grow,wrap" );
		root.add( new JLabel( "índice:" ) );
		root.add( indiceCombo );
		root.add( new JLabel( "Incremento no estoque:" ), "gap unrelated" );
		root.add( incrementoEstoqueField, "grow,span,wrap" );
		root.add( incrementoEstoqueBtn, "grow,span" );
	}
	
	/**
	 * Incrementar estoque.
	 */
	private void incrementarEstoque() {
		try {
			Loja.getInstance().getEstoque().incrementarEstoque(
					CatalogoProdutos.getInstance().getProdutos().get( indiceCombo.getSelectedIndex() ),
					Integer.valueOf( incrementoEstoqueField.getText() )
			);
			ViewUtil.showMessage( "Atualizado com sucesso!" );
			indiceCombo.setSelectedIndex( 0 );
			incrementoEstoqueField.setText( "" );
		} catch ( NumberFormatException e ) {
			ViewUtil.showMessage( "Incremento invalido" );
		}
	}
}
