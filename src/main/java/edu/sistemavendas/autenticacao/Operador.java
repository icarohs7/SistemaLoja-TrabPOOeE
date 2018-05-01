package edu.sistemavendas.autenticacao;

import java.util.ArrayList;

import edu.sistemavendas.Venda;
import edu.sistemavendas.catalogo.Produto;
import edu.sistemavendas.exceptions.PagamentoChequeException;
import edu.sistemavendas.exceptions.PagamentoDinheiroException;
import edu.sistemavendas.exceptions.SenhaIncorretaException;
import edu.sistemavendas.exceptions.StringVaziaException;
import edu.sistemavendas.exceptions.UsuarioNaoExisteException;

/**
 * The type Operador.
 */
public class Operador extends Usuario {
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
		if ( login.equals( "" ) ) {
			throw new StringVaziaException( "O login do operador não pode ser vazio" );
		} else if ( senha.equals( "" ) ) {
			throw new StringVaziaException( "A senha do operador não pode ser vazia" );
		}
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
	 * Retorna o número de operadores cadastrados
	 *
	 * @return the num cadastrados
	 */
	public static int getNumCadastrados() {
		return usuariosCadastrados.size();
	}
	
	/**
	 * Venda em andamento.
	 */
	private Venda vendaEmAndamento;
	
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
					throw new SenhaIncorretaException( "A senha informada esta incorreta." );
				}
			}
		}
		throw new UsuarioNaoExisteException( "O usuario utilizado nao esta cadastrado" );
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
	 * Fechar venda.
	 */
	public void fecharVenda() {
		vendaEmAndamento.fecharVenda();
		vendaEmAndamento = null;
	}
	
	/**
	 * Gets venda em andamento.
	 *
	 * @return the venda em andamento
	 */
	public Venda getVendaEmAndamento() {
		return vendaEmAndamento;
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
	
	
	/**
	 * Registrar item.
	 *
	 * @param produto the produto
	 */
	public void registrarItem( Produto produto ) {
		if ( vendaEmAndamento == null ) {
			registrarVenda( produto, 1 );
		} else {
			vendaEmAndamento.registrarItem( produto, 1 );
		}
	}
	
	/**
	 * Registrar venda.
	 *
	 * @param produto    the produto
	 * @param quantidade the quantidade
	 */
	public void registrarVenda( Produto produto, int quantidade ) {
		vendaEmAndamento = new Venda( produto, quantidade );
	}
	
	/**
	 * Receber pagamento dinheiro double.
	 *
	 * @param quantia the quantia
	 *
	 * @return the double
	 */
	public double receberPagamentoDinheiro( double quantia ) {
		if ( quantia < vendaEmAndamento.getValorTotal() ) {
			throw new PagamentoDinheiroException( "A quantia nao capaz de cobrir o valor total." );
		}
		quantia -= vendaEmAndamento.getValorTotal();
		return quantia;
	}
	
	/**
	 * Receber pagamento cartao boolean.
	 *
	 * @param numCartao   the num cartao
	 * @param senhaCartao the senha cartao
	 *
	 * @return the boolean
	 */
	public boolean receberPagamentoCartao( String numCartao, String senhaCartao ) {
		return true;
	}
	
	/**
	 * Receber pagamento cheque boolean.
	 *
	 * @param identidade the identidade
	 *
	 * @return the boolean
	 */
	public boolean receberPagamentoCheque( String identidade ) {
		if ( identidade.length() == 13
		     && identidade.charAt( 2 ) == '-'
		     && identidade.charAt( 5 ) == '.'
		     && identidade.charAt( 9 ) == '.' ) {
			return true;
		} else {
			throw new PagamentoChequeException( "Numero de identidade invalido" );
		}
	}
	
}
