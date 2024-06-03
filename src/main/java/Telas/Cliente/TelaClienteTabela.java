package Telas.Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import Modelo.Cliente;
import Repositorio.ClienteRepositorio;
import Telas.TelaTabela;

public class TelaClienteTabela extends TelaTabela{
	private List<Cliente> listaCliente;

    public TelaClienteTabela() {
    	super.InitTable("Clientes");
    }
    
    @Override
    protected DefaultTableModel CreateModel() {
    	String[] columnNames = {"CPF", "Nome", "Telefone", "E-mail"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        listaCliente = ClienteRepositorio.BuscarTodosOsClientes();
        
        for(Cliente cliente: listaCliente) {
        	model.addRow(new Object[] {cliente.getCPF(), cliente.getNome(), cliente.getTelefone(),
        			cliente.getEmail()});
        }
        return model;
    }
    
    @Override
    protected void AddFuncoesButtons(final JFrame frame) {
    	//Button Add
    	addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new TelaClienteAdicionar();
            	frame.dispose();
            }
    	});
    	
    	//Button Edit
    	editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
	            if(itenSelecionado("Selecione um cliente")) {
	            	String clienteCPF = String.valueOf(getIdSelecionado());
	            	new TelaClienteEditar(clienteCPF);
	            	frame.dispose();
	            }
            }
    	});
    	
    	//Button Delete function
    	deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(itenSelecionado("Selecione um cliente")) {
	            	String clienteCPF = String.valueOf(getIdSelecionado());
	            	ClienteRepositorio.DeletarCliente(clienteCPF);
	            	new TelaClienteTabela();
	            	frame.dispose();
            	}
            }
    	});
    }
    
    public static void main(String[] args) {
    	new TelaClienteTabela();
	}
}
