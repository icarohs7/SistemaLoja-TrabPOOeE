package sistemavendas;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import sistemavendas.view.LoginViewGerente;

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
			UIManager.setLookAndFeel( new GraphiteLookAndFeel() );
		} catch ( UnsupportedLookAndFeelException ignored ) {
		}
		/* Iniciar aplicação */
		new LoginViewGerente( "Sistema de Vendas" ).setVisible( true );
	}
}
