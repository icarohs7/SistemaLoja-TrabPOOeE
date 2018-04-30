package sistemavendas.view;

import java.awt.Font;

/**
 * Classe utilitária com ações comuns para a interface gráfica
 */
abstract class ViewUtils {
	/**
	 * Font h 1.
	 */
	static final Font FONT_H1;
	
	static {
		FONT_H1 = new Font( "SansSerif", Font.BOLD, 20 );
	}
}
