package sistemavendas;

import java.util.ArrayList;

/**
 * The type Vendas completadas.
 */
class VendasCompletadas {
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
	static void adicionarVenda( Venda venda ) {
		vendas.add( venda );
	}
	
	
	/**
	 * Gets vendas.
	 *
	 * @return the vendas
	 */
	static ArrayList<Venda> getVendas() {
		return vendas;
	}
}
