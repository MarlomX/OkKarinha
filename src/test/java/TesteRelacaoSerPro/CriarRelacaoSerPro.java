package TesteRelacaoSerPro;

import Modelo.Produto;
import Modelo.Servico;
import Repositorio.ProdutoRepositorio;
import Repositorio.ServicoRepositorio;

public class CriarRelacaoSerPro {
	public static void main(String[] args) {		
		Servico servico = ServicoRepositorio.BuscarServicoPorId(3);
		
		Produto produto = ProdutoRepositorio.BuscarProdutoPorId(5);
		
		servico.ComprarProduto(produto, 3);
	}
}
