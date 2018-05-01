package sistemavendas.view.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Listener utilizado para realizar uma ação ao pressionar da tecla enter
 */
public class EnterListener implements KeyListener {
	/**
	 * Acao que ocorrerá ao pressionar ta tecla enter.
	 */
	private Runnable acao;
	
	/**
	 * Instantiates a new Enter listener.
	 *
	 * @param acao acao
	 */
	public EnterListener( Runnable acao ) {
		this.acao = acao;
	}
	
	/**
	 * Key typed.
	 *
	 * @param keyEvent the key event
	 */
	@Override
	public void keyTyped( KeyEvent keyEvent ) {
	}
	
	/**
	 * Key pressed.
	 *
	 * @param keyEvent the key event
	 */
	@Override
	public void keyPressed( KeyEvent keyEvent ) {
		if ( keyEvent.getKeyCode() == KeyEvent.VK_ENTER ) {
			acao.run();
		}
	}
	
	/**
	 * Key released.
	 *
	 * @param keyEvent the key event
	 */
	@Override
	public void keyReleased( KeyEvent keyEvent ) {
	}
}
