package Telas.Produto;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JPanel;

import Modelo.Produto;
import Repositorio.ProdutoRepositorio;

public class TelaProdutoAdicionar extends ProdutoFormulario {
    private JButton adicionarButton;

    public TelaProdutoAdicionar() {
        adicionarButton = new JButton("Adicionar ao Estoque");
        criarTela();
    }
    
    protected void ConfigButton() {
    	super.ConfigButton();
    	
    	adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoEstoque();
                new TelaProdutoTabela();
                frame.dispose();
            }
        });
    }
    
    private void adicionarAoEstoque() {
        String nomeProduto = nomeProdutoField.getText();
        int quantidade = Integer.parseInt(quantidadeField.getText());
        BigDecimal preco = new BigDecimal(precoField.getText());
        BigDecimal custo = new BigDecimal(custoField.getText());
        
        if (!produtoExistente(nomeProduto)) {
            Produto novoProduto = new Produto(nomeProduto, quantidade, preco, custo);
        	ProdutoRepositorio.CriarProduto(novoProduto);
        }      

        // Limpa os campos de entrada
        nomeProdutoField.setText("");
        quantidadeField.setText("");
        precoField.setText("");
        custoField.setText("");
    }
    
    private boolean produtoExistente(String nomeProduto) {
        listaProdutos = ProdutoRepositorio.BuscarTodosOsProdutos();
    	boolean existe = false;
        for (Produto p : listaProdutos) {
        	if (p.getNome().equals(nomeProduto)) {
                existe = true;
            }
        }
        return existe;
    }
    
    protected void cofiguraLayout(JPanel panel) {
    	super.cofiguraLayout(panel);
    	
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(adicionarButton, constraints);
    }

    public static void main(String[] args) {
		new TelaProdutoAdicionar();
	}
}