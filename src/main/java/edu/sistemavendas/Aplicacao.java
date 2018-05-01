package edu.sistemavendas;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.sistemavendas.view.telas.LoginGerenteView;

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
			UIManager.setLookAndFeel( new HiFiLookAndFeel() );
		} catch ( UnsupportedLookAndFeelException e ) {
			e.printStackTrace();
		}
		/* Iniciar aplicação */
		new LoginGerenteView( "Sistema de Vendas" );
	}
}
