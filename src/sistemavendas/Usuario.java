package sistemavendas;

/**
 * The type Usuario.
 */
public abstract class Usuario {
	private String id;
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
	 * Verificar conta.
	 *
	 * @param nome  the nome
	 * @param senha the senha
	 */
	public void verificarConta( String nome, String senha ) {
		if ( !nome.equals( this.id ) || !senha.equals( this.senha ) ) {//login invalido
			System.out.println( "Senha invalida" );
		} else if ( id.equals( "ADMIN" ) && senha.equals( "ADMIN" ) ) {
			//vou colocar algo aqui
		} else {//login usuario
			//vou colocar algo aqui
		}
	}
}
