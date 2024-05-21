package Telas.Produto;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Modelo.Produto;
import Repositorio.ProdutoRepositorio;

public class TelaProdutoEditar extends ProdutoFormulario{
	 private JButton editarButton;
	 private Produto produto;

    public TelaProdutoEditar(int produtoId) {
        editarButton = new JButton("Editar Produto");
        this.produto = ProdutoRepositorio.BuscarProdutoPorId(produtoId);
        criarTela();
    }
    
    @Override
    protected void ConfigButton() {
    	super.ConfigButton();
    	
    	editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarProduto();
            }
        });

    }
    
    private void EditarProduto() {
    	if(validaCampos()) {
    		 	String nomeProduto = nomeProdutoField.getText();
    	        int quantidade = Integer.parseInt(quantidadeField.getText());
    	        BigDecimal preco = new BigDecimal(precoField.getText());
    	        BigDecimal custo = new BigDecimal(custoField.getText());
    	        
    	        produto.atualizaProduto(nomeProduto, quantidade, preco, custo);
    	        ProdutoRepositorio.AtualizarProduto(produto);
    	        
    	        new TelaProdutoTabela();
    	        frame.dispose();
    	}
    }
    
    @Override
    protected void criarCampos(GridBagConstraints constraints, JPanel panel) {
    	super.criarCampos(constraints, panel);
    	nomeProdutoField.setText(produto.getNome());
    	quantidadeField.setText(String.valueOf(produto.getQuantEstoque()));
    	precoField.setText(produto.getPrecoVenda().toString());
    	custoField.setText(produto.getPrecoCompra().toString());

    }
    
    @Override
    protected void cofiguraLayout(JPanel panel) {
    	super.cofiguraLayout(panel);
    	
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(editarButton, constraints);
    }
    
    @Override
    protected boolean validaNome() {
    	String novoNome = formataNome();
	    for(Produto p: listaProdutos) {  	
	    	if (p.getNome().equals(novoNome)&& p.getId() != this.produto.getId()) {
	    		JOptionPane.showMessageDialog(null, "Erro: Já existe um produto com esse nome", "Erro", 0);
	    		return false;
	    	}
		}
	    return super.validaNome();

    }

    public static void main(String[] args) {
		new TelaProdutoAdicionar();
	}
}
