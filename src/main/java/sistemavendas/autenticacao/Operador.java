package sistemavendas.autenticacao;

import java.util.ArrayList;

import sistemavendas.Venda;
import sistemavendas.exceptions.PagamentoDinheiroExceptions;
import sistemavendas.exceptions.SenhaIncorretaException;
import sistemavendas.exceptions.UsuarioNaoExisteException;

/**
 * The type Operador.
 */
public class Operador extends Usuario {
	private Venda vendaEmAndamento;
        /**
	 * Usuarios cadastrados.
	 */
	private static ArrayList<Operador> usuariosCadastrados;
	
	static {
		usuariosCadastrados = new ArrayList<>();
	}
	
	/**
	 * Realiza o cadastro de um novo Gerente
	 *
	 * @param login Login utilizado para autenticar no sistema
	 * @param senha Senha utilizada para autenticar no sistema
	 *
	 * @return Se a operação foi bem sucedida ou não
	 */
	public static boolean cadastrarUsuario( String login, String senha ) {
		/* Verificar se o usuário informado já está cadastrado */
		for ( Usuario usuario : usuariosCadastrados ) {
			if ( usuario.id.equals( login ) ) {
				return false;
			}
		}
		
		usuariosCadastrados.add( new Operador( login, senha, 1532 ) );
		return true;
	}
	
	
	/**
	 * Fazer login
	 *
	 * @param id    id
	 * @param senha senha
	 */
	public Operador( String id, String senha ) {
		super( id, senha );
		
		/* Autenticar o usuário */
		for ( Operador usuario : usuariosCadastrados ) {
			if ( usuario.id.equals( id ) ) {
				if ( usuario.senha.equals( senha ) ) {
					this.senha = senha;
					return;
				} else {
					throw new SenhaIncorretaException( "A senha informada está incorreta." );
				}
			}
		}
		throw new UsuarioNaoExisteException( "O usuário utilizado não está cadastrado" );
	}
	
	/**
	 * Criar um novo operador(utilizado para o cadastro)
	 *
	 * @param id                   id
	 * @param senha                senha
	 * @param gambiarraPolimorfica gambiarraPolimorfica
	 */
	private Operador( String id, String senha, int gambiarraPolimorfica ) {
		super( id, senha );
		this.id = id;
		this.senha = senha;
	}
	
	/**
	 * Alterar a senha do usuário
	 *
	 * @param antiga the antiga
	 * @param nova   the nova
	 *
	 * @return Se a operação foi ou não bem sucedida
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
        
        public double receberPagamentoDinheiro(double quantia){
            if(quantia < vendaEmAndamento.getValorTotal()){
                throw new PagamentoDinheiroExceptions("A quantia n�o capaz de cobrir o valor total.");
            }
            quantia -= vendaEmAndamento.getValorTotal();
            return quantia;
        }
        
        public boolean receberPagamentoCartao(String numCartao){
            return true;
        }
        
        public boolean receberPagamentoCheque(String identidade){
            return true;
        }
        
}
