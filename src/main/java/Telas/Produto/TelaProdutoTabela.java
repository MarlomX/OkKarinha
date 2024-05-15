package Telas.Produto;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.Produto;
import Repositorio.ProdutoRepositorio;

public class TelaProdutoTabela {
	 private JButton addButton;
     private JButton editButton;
     private JButton deleteButton;
     private JButton backButton;
	 private List<Produto> listaProduto;
	 private JFrame frame;
	 private JTable table;

    public TelaProdutoTabela() {
    	//titulo
	    frame = new JFrame("Produto");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    //Criar a tabela e os botoes
	    CreateTable();
	    
	    CriarButtons();
	    
	    //cofigurando a tela	
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
    }
    
    private void CreateTable() {
    	String[] columnNames = {"Id", "Nome", "Quantidade", "Preço", "Custo"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        listaProduto = ProdutoRepositorio.BuscarTodosOsProdutos();
        
        for(Produto produto: listaProduto) {
        	model.addRow(new Object[] {produto.getId(), produto.getNome(), produto.getQuantEstoque(),
        			produto.getPrecoVenda(), produto.getPrecoCompra()});
        }        
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void CriarButtons() {
    	JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        addButton = new JButton("Adicionar Produto");
        editButton = new JButton("Editar Produto");
        deleteButton = new JButton("Excluir Produto");
        backButton = new JButton("Voltar");
        
        AddFuncoesButtons(frame);

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        
        
        frame.add(buttonPanel, BorderLayout.SOUTH);
;
    }
    
    private void AddFuncoesButtons(final JFrame frame) {
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
            	int produtoId = getIdProdutoSelecionado();
            	new TelaProdutoEditar(produtoId);
                frame.dispose();
            }
        });
    	//Button Delete function
    	deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int produtoId = getIdProdutoSelecionado();
            	ProdutoRepositorio.DeletarProduto(produtoId);
            	new TelaProdutoTabela();
            	frame.dispose();
            }
    	});
    }
    
    
    private int getIdProdutoSelecionado() {
    	int linhaSelecionada = table.getSelectedRow();

    	// Obter o valor na coluna "Id" da linha selecionada
    	Object valorId = table.getValueAt(linhaSelecionada, 0); // 0 é o índice da coluna "Id"
    	int produtoId = ((Integer) valorId).intValue();
    	return produtoId;

    }
    
    
    public static void main(String[] args) {
    	new TelaProdutoTabela();
	}
}
