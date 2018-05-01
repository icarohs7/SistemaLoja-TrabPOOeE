package sistemavendas.view.telas;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sistemavendas.autenticacao.Operador;
import sistemavendas.exceptions.PagamentoChequeException;
import sistemavendas.exceptions.PagamentoDinheiroException;
import sistemavendas.view.util.ActionButton;
import sistemavendas.view.util.EnterKeyListenerField;
import sistemavendas.view.util.EnterListener;
import sistemavendas.view.util.FontLabel;
import sistemavendas.view.util.ViewUtil;

/**
 * Tela FinalizarVendaView
 */
public class FinalizarVendaView extends JFrame {
	/**
	 * Painel raiz da aplicação
	 */
	private JPanel root;
	
	/**
	 * Forma pagamento combo.
	 */
	private JComboBox formaPagamentoCombo;
	
	/**
	 * Operador.
	 */
	private Operador operador;
	
	/**
	 * Parent.
	 */
	private JFrame parent;
	
	/**
	 * Qual valor field.
	 */
	private JTextField qualValorField;
	
	/**
	 * Oque pagamento label.
	 */
	private JLabel oquePagamentoLabel;
	
	/**
	 * Criar a tela
	 *
	 * @param s        Titulo
	 * @param operador operador
	 * @param parent   parent
	 */
	public FinalizarVendaView( String s, Operador operador, JFrame parent ) {
		super( s );
		this.operador = operador;
		this.parent = parent;
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
		/* Tornar visível */
		setVisible( true );
	}
	
	/**
	 * Criar componentes
	 */
	private void criarComponentes() {
		/* Instanciar painel raiz */
		root = new JPanel( new MigLayout( "fillx" ) );
		
		oquePagamentoLabel = new JLabel( "Quantia" );
		qualValorField = new EnterKeyListenerField( new EnterListener( this::receberPagamento ) );
		String[] formasPagamento = new String[] {
				"Pagamento em dinheiro",
				"Pagamento com cartao",
				"Pagamento com cheque"
		};
		formaPagamentoCombo = new JComboBox( formasPagamento );
		formaPagamentoCombo.addActionListener( ( evt ) -> {
			switch ( formaPagamentoCombo.getSelectedIndex() ) {
				case 0:
					oquePagamentoLabel.setText( "Quantia" );
					break;
				case 1:
					oquePagamentoLabel.setText( "Num. Cartao" );
					break;
				case 2:
					oquePagamentoLabel.setText( "Identidade" );
					break;
			}
		} );
		JButton pagarBtn = new ActionButton( "Pagar", ( evt ) -> {
			receberPagamento();
		} );
		
		root.add( new FontLabel( "Receber Pagamento", ViewUtil.FONT_H1 ), "center,span,wrap" );
		root.add( new FontLabel(
				"Preco da compra: R$" + operador.getVendaEmAndamento()
						.getValorTotal(), ViewUtil.FONT_H1 ), "center, span, wrap"
		);
		root.add( new JLabel( "Forma de pagamento" ) );
		root.add( formaPagamentoCombo, "grow,span,wrap" );
		root.add( oquePagamentoLabel );
		root.add( qualValorField, "span,grow,wrap" );
		root.add( pagarBtn, "span,grow" );
	}
	
	/**
	 * Receber pagamento.
	 */
	public void receberPagamento() {
		try {
			if ( formaPagamentoCombo.getSelectedIndex() == 0 ) {
				double quantia = Double.parseDouble( qualValorField.getText() );
				double troco = operador.receberPagamentoDinheiro( quantia );
				ViewUtil.showMessage( "Pagamento feito com sucesso!, Troco: R$" + troco );
				operador.fecharVenda();
				dispose();
			} else if ( formaPagamentoCombo.getSelectedIndex() == 1 ) {
				String senha = JOptionPane.showInputDialog( "Informe a senha do cartao" );
				if ( operador.receberPagamentoCartao( qualValorField.getText(), senha ) ) {
					ViewUtil.showMessage( "Pagamento feito com sucesso!" );
					operador.fecharVenda();
					dispose();
				} else {
					ViewUtil.showMessage( "Ocorreu um erro ao processar sua requisicao" );
				}
			} else {
				operador.receberPagamentoCheque( qualValorField.getText() );
				ViewUtil.showMessage( "Pagamento feito com sucesso!" );
				operador.fecharVenda();
				dispose();
			}
		} catch ( NumberFormatException e ) {
			ViewUtil.showMessage( "Quantia invalida!" );
		} catch ( NullPointerException e ) {
			ViewUtil.showMessage( "Senha do cartao invalida!" );
		} catch ( PagamentoDinheiroException | PagamentoChequeException e ) {
			ViewUtil.showMessage( e.getMessage() );
		}
	}
	
	/**
	 * Dispose.
	 */
	@Override
	public void dispose() {
		super.dispose();
		parent.setVisible( true );
	}
}
