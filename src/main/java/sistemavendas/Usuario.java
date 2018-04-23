package sistemavendas;

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
	 * Instantiates a new Usuario.
	 *
	 * @param id    the id
	 * @param senha the senha
	 */
	public Usuario( String id, String senha ) {
		this.id = id;
		this.senha = senha;
	}
	
	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId( String id ) {
		this.id = id;
	}
	
	/**
	 * Gets senha.
	 *
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}
	
	/**
	 * Sets senha.
	 *
	 * @param senha the senha
	 */
	public void setSenha( String senha ) {
		this.senha = senha;
	}
	
	/**
	 * Verifica se a conta de usuário informada é válida
	 *
	 * @param login Nome de usuário
	 * @param senha Senha do usuário
	 *
	 * @return verdadeiro se a conta for válida e falso do contrário
	 */
	public abstract boolean verificarConta( String login, String senha );
}
