package edu.sistemavendas.view.util;

import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Botão criado para aceitar o listener em seu construtor
 */
public class ActionButton extends JButton {
	/**
	 * Instantiates a new Action button.
	 *
	 * @param s      s
	 * @param action action
	 */
	public ActionButton( String s, ActionListener action ) {
		super( s );
		addActionListener( action );
	}
}
