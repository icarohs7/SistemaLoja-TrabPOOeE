package sistemavendas;

import com.jtattoo.plaf.mcwin.McWinLookAndFeel;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import sistemavendas.autenticacao.Operador;
import sistemavendas.view.telas.LoginGerenteView;

/**
 * The type Aplicacao.
 */
public class Aplicacao {
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main( String[] args ) {
		/* Definir tema da aplicação */
		try {
			McWinLookAndFeel.setTheme( "Modern" );
			UIManager.setLookAndFeel( new McWinLookAndFeel() );
		} catch ( UnsupportedLookAndFeelException ignored ) {
		}
		/* Iniciar aplicação */
		new LoginGerenteView( "Sistema de Vendas" );
		Operador.cadastrarUsuario( "icarohs7", "123456" );
		//		new PainelOperadorView(
		// "Sistema de Vendas - Painel do Operador", new Operador( "icarohs7", "123456") );
	}
}
