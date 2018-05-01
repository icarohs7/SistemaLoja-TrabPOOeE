package sistemavendas.autenticacao;

import java.util.ArrayList;

import sistemavendas.exceptions.SenhaIncorretaException;
import sistemavendas.exceptions.UsuarioNaoExisteException;
import sistemavendas.view.telas.LoginOperadorView;

/**
 * The type Gerente
 */
public class Gerente extends Usuario {
	/**
	 * Usuarios cadastrados.
	 */
	private static ArrayList<Gerente> usuariosCadastrados;
	
	static {
		usuariosCadastrados = new ArrayList<>();
		/* Cadastrar o usuario padrao */
		usuariosCadastrados.add( new Gerente( "ADMIN", "ADMIN", 1532 ) );
	}
	
	/**
	 * Realiza o cadastro de um novo Gerente
	 *
	 * @param login Login utilizado para autenticar no sistema
	 * @param senha Senha utilizada para autenticar no sistema
	 *
	 * @return Se a operacao foi bem sucedida ou nao
	 */
	public static boolean cadastrarUsuario( String login, String senha ) {
		/* Verificar se o usuário informado já está cadastrado */
		for ( Usuario usuario : usuariosCadastrados ) {
			if ( usuario.id.equals( login ) ) {
				return false;
			}
		}
		
		usuariosCadastrados.add( new Gerente( login, senha, 1532 ) );
		return true;
	}
	
	/**
	 * Fazer login
	 *
	 * @param id    the id
	 * @param senha the senha
	 */
	public Gerente( String id, String senha ) {
		super( id, senha );
		
		/* Autenticar o usuário */
		for ( Gerente usuario : usuariosCadastrados ) {
			if ( usuario.id.equals( id ) ) {
				if ( usuario.senha.equals( senha ) ) {
					this.senha = senha;
					return;
				} else {
					throw new SenhaIncorretaException( "A senha informada esta incorreta." );
				}
			}
		}
		throw new UsuarioNaoExisteException( "O usuario utilizado nao esta cadastrado" );
	}
	
	/**
	 * Criar um novo gerente(utilizado para o cadastro)
	 *
	 * @param id                   id
	 * @param senha                senha
	 * @param gambiarraPolimorfica gambiarraPolimorfica
	 */
	private Gerente( String id, String senha, int gambiarraPolimorfica ) {
		super( id, senha );
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
	@Override
	public boolean alterarSenha( String antiga, String nova ) {
		if ( !senha.equals( antiga ) ) {
			return false;
		} else {
			senha = nova;
			int indice = usuariosCadastrados.indexOf( this );
			usuariosCadastrados.get( indice ).senha = nova;
			return true;
		}
	}
	
	/**
	 * Iniciar sistema.
	 */
	public void iniciarSistema() {
		new LoginOperadorView( "Sistema de Vendas" );
	}
	
	/**
	 * Finalizar sistema.
	 */
	public void finalizarSistema() {
		System.exit( 0 );
	}
	
}
