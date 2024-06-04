<<<<<<< HEAD
package Modelo;

import java.math.BigDecimal;

public class Oferta {
	
	private int Id;
	private int Quantidade;
	private Produto produto;
	
	public Oferta(int id, int quantidade, Produto produto) {
		super();
		Id = id;
		Quantidade = quantidade;
		this.produto = produto;
	}
	
	public Oferta(int quantidade, Produto produto) {
		super();
		Id = 0;
		Quantidade = quantidade;
		this.produto = produto;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public BigDecimal valorTotal() {
		BigDecimal valorTotal = new BigDecimal(0);
		valorTotal = this.produto.getPrecoVenda().multiply(new BigDecimal(this.Quantidade));
		return valorTotal;
	}

	@Override
	public String toString() {
		return "Oferta [Id=" + Id + ", Quantidade=" + Quantidade + ", produto=" + produto + "]";
	}
	
	
}
=======
package Modelo;

public class Oferta {
	
	private int Id;
	private int Quantidade;
	private Produto produto;
	
	public Oferta(int id, int quantidade, Produto produto) {
		super();
		Id = id;
		Quantidade = quantidade;
		this.produto = produto;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Oferta [Id=" + Id + ", Quantidade=" + Quantidade + ", produto=" + produto + "]";
	}
	
	
}
>>>>>>> 812d65e (Telas Produto)
