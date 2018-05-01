package edu.sistemavendas.autenticacao;

/**
 * The type Usuario.
 */
public abstract class Usuario {
	/**
	 * Id.
	 */
	String id;
	/**
	 * Senha.
	 */
	String senha;
	
	/**
	 * Fazer login, instanciando o objeto
	 *
	 * @param id    Nome de usuário
	 * @param senha Senha do usuário
	 */
	public Usuario( String id, String senha ) {
	}
	
	/**
	 * Alterar senha boolean.
	 *
	 * @param antiga the antiga
	 * @param nova   the nova
	 *
	 * @return the boolean
	 */
	public abstract boolean alterarSenha( String antiga, String nova );
}
