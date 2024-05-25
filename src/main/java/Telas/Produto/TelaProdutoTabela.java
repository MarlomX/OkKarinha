package Telas.Produto;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Telas.TelaTabela;

import Modelo.Produto;
import Repositorio.ProdutoRepositorio;

public class TelaProdutoTabela extends TelaTabela{
	 private List<Produto> listaProduto;

    public TelaProdutoTabela() {
    	super.InitTable("Produtos");
    }
    
    @Override
    protected DefaultTableModel CreateModel() {
    	String[] columnNames = {"Id", "Nome", "Quantidade", "Preço", "Custo"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        listaProduto = ProdutoRepositorio.BuscarTodosOsProdutos();
        
        for(Produto produto: listaProduto) {
        	model.addRow(new Object[] {produto.getId(), produto.getNome(), produto.getQuantEstoque(),
        			produto.getPrecoVenda(), produto.getPrecoCompra()});
        }
        return model;
    }
    
    @Override
    protected void AddFuncoesButtons(final JFrame frame) {
    	//Button Add function
    	addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new TelaProdutoAdicionar();
                frame.dispose();
            }
        });
    	
    	//Button Edit function
    	editButton.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
            	if(itenSelecionado("Selecione um produto")) {
	            	int produtoId =((Integer) getIdSelecionado()).intValue();
	            	new TelaProdutoEditar(produtoId);
	                frame.dispose();
            	}
            }
        });
    	//Button Delete function
    	deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(itenSelecionado("Selecione um produto")) {
	            	int produtoId =((Integer) getIdSelecionado()).intValue();
	            	ProdutoRepositorio.DeletarProduto(produtoId);
	            	new TelaProdutoTabela();
	            	frame.dispose();
            	}
            }
    	});
    }   
    
    public static void main(String[] args) {
    	new TelaProdutoTabela();
	}
}
