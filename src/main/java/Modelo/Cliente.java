<<<<<<< HEAD
package Modelo;

import java.util.List;

import Repositorio.ClienteRepositorio;
import Repositorio.ServicoRepositorio;

public class Cliente {

	private String CPF;
	private String Nome;
	private String Telefone;
	private String Email;
	
	public Cliente(String cpf, String nome, String telefone, String email) {
		CPF = cpf;
		Nome = nome;
		Telefone = telefone;
		Email = email;
	}
	public String getCPF() {
		return CPF;
	}
	public String getNome() {
		return Nome;
	}
	public String getTelefone() {
		return Telefone;
	}
	public String getEmail() {
		return Email;
	}
	
	public void atualizaCliente(String cpf, String nome, String telefone, String email) {
		CPF = cpf;
		Nome = nome;
		Telefone = telefone;
		Email = email;
	}
	
	public void deletaCliente() {
		List<Servico> listaServico = ServicoRepositorio.BuscarTodosOsServicos();
		for(Servico servico : listaServico) {
			if(servico.getCliente().getCPF().equals(this.CPF)) {
				servico.deletarServico();
			}
		}
		ClienteRepositorio.DeletarCliente(this.CPF);
	}
	
	@Override
	public String toString() {
		return "Cliente [CPF=" + CPF + ", Nome=" + Nome + ", Telefone=" + Telefone + ", Email=" + Email + "]";
	}
	
}
=======
package Modelo;

public class Cliente {

	private String CPF;
	private String Nome;
	private String Telefone;
	private String Email;
	
	public Cliente(String cPF, String nome, String telefone, String email) {
		super();
		CPF = cPF;
		Nome = nome;
		Telefone = telefone;
		Email = email;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getTelefone() {
		return Telefone;
	}
	public void setTelefone(String telefone) {
		Telefone = telefone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	@Override
	public String toString() {
		return "Cliente [CPF=" + CPF + ", Nome=" + Nome + ", Telefone=" + Telefone + ", Email=" + Email + "]";
	}
	
}
>>>>>>> 812d65e (Telas Produto)
