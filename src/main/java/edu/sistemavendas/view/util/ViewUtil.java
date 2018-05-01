package edu.sistemavendas.view.util;

import java.awt.Font;

import javax.swing.JOptionPane;

/**
 * Classe utilitária com ações comuns para a interface gráfica
 */
public abstract class ViewUtil {
	/**
	 * Font h 1.
	 */
	public static final Font FONT_H1;
	
	static {
		FONT_H1 = new Font( "SansSerif", Font.BOLD, 20 );
	}
	
	/**
	 * Mostra uma caixa de dialogo com uma mensagem
	 *
	 * @param texto A mensagem a ser exibida
	 */
	public static void showMessage( String texto ) {
		JOptionPane.showMessageDialog( null, texto );
	}
}
