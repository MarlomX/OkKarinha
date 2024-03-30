package Modelo;

import java.sql.Date;

import Repositorio.RelacaoSerProRepositorio;

public class Servico {

	int Id;
	Cliente Cliente;
	Date Data;
	
	public Servico(int id, Modelo.Cliente cliente, Date data) {
		super();
		Id = id;
		Cliente = cliente;
		Data = data;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Date getData() {
		return Data;
	}
	public void setData(Date data) {
		Data = data;
	}
	
	public Cliente getCliente() {
		return Cliente;
	}
	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}
	@Override
	public String toString() {
		return "Servico [Id=" + Id + ", ClienteCPF=" + Cliente + ", Data=" + Data + "]";
	}
	
	public void ComprarProduto(Produto p, int quantidade) {
		RelacaoSerProRepositorio.CriarRelacaoSerPro(this, p, quantidade);
	}
	
}
