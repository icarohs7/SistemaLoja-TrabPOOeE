package sistemavendas.view.util;

import java.awt.event.KeyListener;

import javax.swing.JTextField;

/**
 * Classe gerando um textField aceitando um KeyListener em seu construtor
 */
public class EnterKeyListenerField extends JTextField {
	/**
	 * Instantiates a new Enter key listener field.
	 *
	 * @param listener listener
	 */
	public EnterKeyListenerField( KeyListener listener ) {
		addKeyListener( listener );
	}
}
