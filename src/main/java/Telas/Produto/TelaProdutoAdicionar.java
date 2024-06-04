package Telas.Produto;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
<<<<<<< HEAD
=======
import javax.swing.JOptionPane;
>>>>>>> 9805c6cf1c9ef51e01d6cb9809c32ad0e30de7ff
import javax.swing.JPanel;

import Modelo.Produto;
import Repositorio.ProdutoRepositorio;

public class TelaProdutoAdicionar extends ProdutoFormulario {
    private JButton adicionarButton;

    public TelaProdutoAdicionar() {
        adicionarButton = new JButton("Adicionar ao Estoque");
        criarTela();
    }
    
<<<<<<< HEAD
=======
    @Override
>>>>>>> 9805c6cf1c9ef51e01d6cb9809c32ad0e30de7ff
    protected void ConfigButton() {
    	super.ConfigButton();
    	
    	adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoEstoque();
<<<<<<< HEAD
                new TelaProdutoTabela();
                frame.dispose();
=======
>>>>>>> 9805c6cf1c9ef51e01d6cb9809c32ad0e30de7ff
            }
        });
    }
    
    private void adicionarAoEstoque() {
<<<<<<< HEAD
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
    
=======
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
>>>>>>> 9805c6cf1c9ef51e01d6cb9809c32ad0e30de7ff
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
<<<<<<< HEAD
=======
    
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
>>>>>>> 9805c6cf1c9ef51e01d6cb9809c32ad0e30de7ff

    public static void main(String[] args) {
		new TelaProdutoAdicionar();
	}
}