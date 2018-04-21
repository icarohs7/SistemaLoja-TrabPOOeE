package sistemavendas;

import java.util.ArrayList;

/**
 * The type Vendas completadas.
 */
public class VendasCompletadas {
	/**
	 * The constant vendas.
	 */
	private static final ArrayList<Venda> vendas;
	
	/* Inicialização da lista de vendas */
	static {
		vendas = new ArrayList<>();
	}
	
	/**
	 * Adicionar venda.
	 *
	 * @param venda venda
	 */
	public static void adicionarVenda( Venda venda ) {
		vendas.add( venda );
	}
}
