package Telas.Produto;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
=======
import java.math.BigDecimal;
>>>>>>> 9805c6cf1c9ef51e01d6cb9809c32ad0e30de7ff
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
<<<<<<< HEAD
=======
import javax.swing.JOptionPane;
>>>>>>> 9805c6cf1c9ef51e01d6cb9809c32ad0e30de7ff
import javax.swing.JPanel;
import javax.swing.JTextField;

import Modelo.Produto;
<<<<<<< HEAD
=======
import Repositorio.ProdutoRepositorio;
>>>>>>> 9805c6cf1c9ef51e01d6cb9809c32ad0e30de7ff

public class ProdutoFormulario {
	protected JTextField nomeProdutoField;
	protected JTextField quantidadeField;
	protected JTextField precoField;
	protected JTextField custoField;
	protected JButton voltarButton;
	protected List<Produto> listaProdutos;
	protected JFrame frame;

	protected void criarTela() {
	
	    // Criação dos componentes
	    nomeProdutoField = new JTextField(15);
	    quantidadeField = new JTextField(5);
	    precoField = new JTextField(10);
	    custoField = new JTextField(10);
	    voltarButton = new JButton("Voltar");
<<<<<<< HEAD
=======
        listaProdutos = ProdutoRepositorio.BuscarTodosOsProdutos();
>>>>>>> 9805c6cf1c9ef51e01d6cb9809c32ad0e30de7ff
	
	    // Configuração do layout
		JPanel panel = new JPanel(new GridBagLayout());
	    cofiguraLayout(panel);
	    
	    // Criação da janela
	    frame = criarJanela(panel);
	
	    // Configuração dos eventos
	    ConfigButton();
	    
	}

	protected void ConfigButton() {
		
		//Botão Voltar
		voltarButton.addActionListener(new ActionListener() {	
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaProdutoTabela();
	            frame.dispose();
				
			}
		});
	}

	protected JFrame criarJanela(JPanel panel) {
		JFrame frame = new JFrame("Controle de Estoque");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
<<<<<<< HEAD
=======
	    frame.setLocationRelativeTo(null);
	    frame.setSize(800, 300); // Defina o tamanho desejado (largura x altura)
>>>>>>> 9805c6cf1c9ef51e01d6cb9809c32ad0e30de7ff
	    frame.add(panel);
	    frame.pack();
	    frame.setVisible(true);
	    return frame;
	}

	protected void cofiguraLayout(JPanel panel) {
	    GridBagConstraints constraints = new GridBagConstraints();
	    constraints.insets = new Insets(5, 5, 5, 5);
	    constraints.anchor = GridBagConstraints.WEST;
	    
	    criarCampos(constraints, panel);
	    
	    constraints.gridx = 1;
	    constraints.gridy = 4;
	    constraints.gridwidth = 1;
	    constraints.anchor = GridBagConstraints.CENTER;
	    panel.add(voltarButton, constraints);
	
	}

	protected void criarCampos(GridBagConstraints constraints, JPanel panel) {
	    criarCampo(panel, constraints, 0, 0, "Nome do Produto:", nomeProdutoField);
	    criarCampo(panel, constraints, 0, 1, "Quantidade:", quantidadeField);
	    criarCampo(panel, constraints, 0, 2, "Preço:", precoField);
	    criarCampo(panel, constraints, 0, 3, "Custo:", custoField);
	}
	
	protected void criarCampo(JPanel panel, GridBagConstraints constraints, int gridx, int gridy, String labelText, Component field) {
	    constraints.gridx = gridx;
	    constraints.gridy = gridy;
	    panel.add(new JLabel(labelText), constraints);

	    constraints.gridx = gridx + 1;
	    panel.add(field, constraints);
<<<<<<< HEAD
	}
	
=======
	}	
	
	//Codigos de formatação dos campos
	
	protected String formataNome() {
		String nomeProduto = nomeProdutoField.getText();
        String nomeFormatado = nomeProduto.substring(0, 1).toUpperCase() + nomeProduto.substring(1);
        return nomeFormatado;
	}
	
	protected int formataQuant() {
	    int quantidade = Integer.parseInt(quantidadeField.getText());
	    return quantidade;
	}
	
	protected BigDecimal formataPreco() {
	    BigDecimal preco = new BigDecimal(precoField.getText());
	    return preco;
	}
	
	protected BigDecimal formataCusto() {
	    BigDecimal preco = new BigDecimal(custoField.getText());
	    return preco;
	}
	
	//Codigos de Validações dos campos
	
	protected boolean validaCampos() {
		if(camposPrenchidos() && validaNome() && validaQuant() && validaPreco() && validaCusto()) {
			return true;
		}
		return false;
	}
	
	protected boolean camposPrenchidos() {
		String[][] campos = {
	            {nomeProdutoField.getText().trim(), "Nome do Produto"},
	            {quantidadeField.getText().trim(), "Quantidade"},
	            {precoField.getText().trim(), "Preço"},
	            {custoField.getText().trim(), "Custo"}
	        };

	        for (String[] campo : campos) {
	            if (campo[0].isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Erro: O campo '" + campo[1] + "' é de preenchimento obrigatório", "Erro", JOptionPane.ERROR_MESSAGE);
	                return false;
	            }
	        }
	        return true;
	}
	
	protected boolean validaNome() {
		String nome = formataNome();
		boolean result = true;
		
		if(!nome.matches(".*[a-zA-Z].*")) {
			JOptionPane.showMessageDialog(null, "Erro: O nome do produto tem que conter pelo menos uma letra", "Erro", 0);
    		result = false;
		}
	return result;
	}
	
	@SuppressWarnings("finally")
	protected boolean validaQuant() {
		boolean result = true;
		try {
			formataQuant();
		} catch (NumberFormatException e) {
		    // Tratamento da exceção (por exemplo, exibir uma mensagem de erro)
		    JOptionPane.showMessageDialog(null, "Erro: A quantidade tem que ser um valor inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
		    result = false;
		}finally {
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	protected boolean validaPreco() {
		boolean result = true;
		try {
			formataPreco();
		} catch(NumberFormatException e) {
			if(precoField.getText().contains(",")) {
			    JOptionPane.showMessageDialog(null, "Erro: A formatação do preço está errada!"
			    		+ " Use '.' em vez de ',' (por exemplo, 2500.50).", "Erro", JOptionPane.ERROR_MESSAGE);
			}else {
			    JOptionPane.showMessageDialog(null, "Erro: O campo de preço so pode ter numeros.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			result = false;
		} finally {
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	protected boolean validaCusto() {
		boolean result = true;
		
		try {
			formataCusto();
		} catch(NumberFormatException e) {
			if(custoField.getText().contains(",")) {
			    JOptionPane.showMessageDialog(null, "Erro: A formatação do preço está errada!"
			    		+ " Use '.' em vez de ',' (por exemplo, 2500.50).", "Erro", JOptionPane.ERROR_MESSAGE);
			}else {
			    JOptionPane.showMessageDialog(null, "Erro: O campo de preço so pode ter numeros.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			result = false;
		} finally {
			return result;
		}
	}

>>>>>>> 9805c6cf1c9ef51e01d6cb9809c32ad0e30de7ff
}
