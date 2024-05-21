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

public class TelaProdutoAdicionar extends ProdutoFormulario {
    private JButton adicionarButton;

    public TelaProdutoAdicionar() {
        adicionarButton = new JButton("Adicionar ao Estoque");
        criarTela();
    }
    
    @Override
    protected void ConfigButton() {
    	super.ConfigButton();
    	
    	adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoEstoque();
            }
        });
    }
    
    private void adicionarAoEstoque() {
    	if(validaCampos()) {
    		//busca os valores inseridos no campo de forma formatada
		    String nomeProduto = formataNome();
		    int quantidade = formataQuant();
		    BigDecimal preco = formataPreco();
		    BigDecimal custo = formataCusto();
		   
		    //adiciona os valores a um produto no banco de dados
	        Produto novoProduto = new Produto(nomeProduto, quantidade, preco, custo);
	    	ProdutoRepositorio.CriarProduto(novoProduto);
		         
		
		    // Muda para a tela da tabela
	    	new TelaProdutoTabela();
            frame.dispose();
    	}
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
        panel.add(adicionarButton, constraints);
    }
    
    @Override
    protected boolean validaNome() {
    	String nome = formataNome();
	    for(Produto produto: listaProdutos) {  	
	    	if (produto.getNome().equals(nome)) {
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