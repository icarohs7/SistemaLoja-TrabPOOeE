package edu.sistemavendas.view.util;

import java.awt.event.KeyListener;

import javax.swing.JPasswordField;

/**
 * Classe gerando um PasswordField aceitando um KeyListener em seu construtor
 */
public class EnterKeyListenerPassField extends JPasswordField {
	/**
	 * Instantiates a new Enter key listener p field.
	 *
	 * @param listener listener
	 */
	public EnterKeyListenerPassField( KeyListener listener ) {
		addKeyListener( listener );
	}
}
