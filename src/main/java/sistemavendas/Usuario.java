package sistemavendas;

import java.util.ArrayList;

/**
 * The type Usuario.
 */
public abstract class Usuario {
	/**
	 * Id.
	 */
	private String id;
	/**
	 * Senha.
	 */
	private String senha;
	/**
	 * Usuarios cadastrados.
	 */
	protected static ArrayList<Usuario> usuariosCadastrados;
	
	/**
	 * Instantiates a new Usuario.
	 *
	 * @param id    the id
	 * @param senha the senha
	 */
	Usuario( String id, String senha ) {
		this.id = id;
		this.senha = senha;
	}
	
	/**
	 * Alterar senha boolean.
	 *
	 * @param antiga the antiga
	 * @param nova   the nova
	 *
	 * @return the boolean
	 */
	abstract boolean alterarSenha( String antiga, String nova );
	/**
	 * Verifica se a conta de usuário informada é válida
	 *
	 * @param login Nome de usuário
	 * @param senha Senha do usuário
	 *
	 * @return verdadeiro se a conta for válida e falso do contrário
	 */
	abstract boolean fazerLogin( String login, String senha );
}
