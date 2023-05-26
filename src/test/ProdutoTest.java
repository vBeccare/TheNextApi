
import static org.junit.Assert.assertNull;

import java.util.*;

import org.junit.*;

public class ProdutoTest {
	private GerenciadoraClientes gerClientes;
	private Long idProduto1 = 1;
	private Long idProduto2 = 2;

	@Before
	public void setUp() {
		Produto produto01 = new Produto(idProduto1, "S22", null, "Celular com 128GB", 4, "", 12000, 10, true);
		Produto cliente02 = new Produto(idProduto2, "Maria", 34, "maria@gmail.com", 2, true);

		List<Produto> produtos = new ArrayList<>();
		produtos.add(produto01);
		produtos.add(cliente02);

	}

	@Test
	public void testPesquisaProduto() {

		Produto produto = produtos.find(idProduto1);

		Assert.assertEquals(produto.getId(), idProduto1);
	}

}
