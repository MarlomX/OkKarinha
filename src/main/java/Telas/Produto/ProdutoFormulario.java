package Telas.Produto;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Modelo.Produto;

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
	}
}
