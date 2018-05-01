package sistemavendas.view.util;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * Classe criando uma Label na cor vermelha
 */
public class AlertLabel extends JLabel {
	/**
	 * Instantiates a new Alert label.
	 *
	 * @param texto texto
	 */
	public AlertLabel( String texto ) {
		super( texto );
		setForeground( Color.RED );
	}
}
