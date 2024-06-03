<<<<<<< HEAD
package Modelo;

import java.math.BigDecimal;

public class Produto {
	
	private int Id;
	private String Nome;
	private int QuantEstoque;
	private BigDecimal PrecoVenda;
	private BigDecimal PrecoCompra;
	
	public Produto(int id, String nome, int quantEstoque, BigDecimal precoVenda, BigDecimal precoCompra) {
		super();
		Id = id;
		Nome = nome;
		QuantEstoque = quantEstoque;
		PrecoVenda = precoVenda;
		PrecoCompra  = precoCompra;
	}
	public Produto(int id, String nome, int quantEstoque, BigDecimal precoVenda) {
		super();
		Id = id;
		Nome = nome;
		QuantEstoque = quantEstoque;
		PrecoVenda = precoVenda;
		PrecoCompra  = null;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public int getQuantEstoque() {
		return QuantEstoque;
	}
	public void setQuantEstoque(int quantEstoque) {
		QuantEstoque = quantEstoque;
	}
	public BigDecimal getPrecoVenda() {
		return PrecoVenda;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		PrecoVenda = precoVenda;
	}
	public BigDecimal getPrecoCompra() {
		return PrecoCompra;
	}
	public void setPrecoCompra(BigDecimal precoCompra) {
		PrecoCompra = precoCompra;
	}
	@Override
	public String toString() {
		return "Produto [Id=" + Id + ", Nome=" + Nome + ", QuantEstoque=" + QuantEstoque + ", PrecoVenda=" + PrecoVenda
				+ ", PrecoCompra=" + PrecoCompra + "]";
	}
	
	
}
=======
package Modelo;

import java.math.BigDecimal;

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
	
	//to string
	@Override
	public String toString() {
		return "Id=" + id + ", Nome=" + nome + ", Quantidade em Estoque=" + quantEstoque + ", Preço=" + precoVenda
				+ ", Custo=" + precoCompra + "";
	}
	
	
}
>>>>>>> 812d65e (Telas Produto)
