package edu.sistemavendas.view.util;

import java.awt.Font;

import javax.swing.JLabel;

/**
 * Label criada para aceitar a fonte em seu construtor
 */
public class FontLabel extends JLabel {
	/**
	 * Instantiates a new Font label.
	 *
	 * @param s    s
	 * @param font font
	 */
	public FontLabel( String s, Font font ) {
		super( s );
		setFont( font );
	}
}
