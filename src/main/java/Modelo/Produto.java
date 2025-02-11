package Modelo;

import java.math.BigDecimal;
import java.util.List;

import Repositorio.OfertaRepositorio;
import Repositorio.ProdutoRepositorio;

public class Produto {
	
	private int id;
	private String nome;
	private int quantEstoque;
	private BigDecimal precoVenda;
	private BigDecimal precoCompra;
	
	//Methodos construtores
	
	public Produto(int id, String nome, int quantEstoque, BigDecimal precoVenda, BigDecimal precoCompra) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantEstoque = quantEstoque;
		this.precoVenda = precoVenda;
		this.precoCompra  = precoCompra;
	}
	
	public Produto(int id, String nome, int quantEstoque, BigDecimal precoVenda) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantEstoque = quantEstoque;
		this.precoVenda = precoVenda;
		this.precoCompra  = null;
	}
	
	public Produto(String nome, int quantEstoque, BigDecimal precoVenda, BigDecimal precoCompra) {
		super();
		this.id = 0;
		this.nome = nome;
		this.quantEstoque = quantEstoque;
		this.precoVenda = precoVenda;
		this.precoCompra  = precoCompra;
	}
	
	public Produto(String nome, int quantEstoque, BigDecimal precoVenda) {
		super();
		this.id = 0;
		this.nome = nome;
		this.quantEstoque = quantEstoque;
		this.precoVenda = precoVenda;
		this.precoCompra  = null;
	}
	
	//Geters
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public int getQuantEstoque() {
		return quantEstoque;
	}
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}
	public BigDecimal getPrecoCompra() {
		return precoCompra;
	}
	
	//Methodo de busca
	public void atualizaProduto(String nome, int quantEstoque, BigDecimal precoVenda, BigDecimal precoCompra) {
		this.nome = nome;
		this.quantEstoque = quantEstoque;
		this.precoVenda = precoVenda;
		this.precoCompra  = precoCompra;
	}
	
	public void deletarProduto() {
		List<Oferta> listaOferta = OfertaRepositorio.BuscarTodasAsOfertasDoProduto(this.id);
		for(Oferta oferta: listaOferta) {
			OfertaRepositorio.DeletarOferta(oferta.getId());
		}
		ProdutoRepositorio.DeletarProduto(this.id);
	}
	
	//to string
	@Override
	public String toString() {
		return "Id=" + id + ", Nome=" + nome + ", Quantidade em Estoque=" + quantEstoque + ", Preço=" + precoVenda
				+ ", Custo=" + precoCompra + "";
	}
	
	
}
