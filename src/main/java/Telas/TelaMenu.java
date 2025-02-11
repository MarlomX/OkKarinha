package Telas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Telas.Cliente.TelaClienteTabela;
import Telas.Produto.TelaProdutoTabela;
import Telas.Servico.TelaServicoTabela;

public class TelaMenu {
	
	private JFrame frame;
	private JPanel panelSuperior;
	private JPanel panelBotoes;
	private JButton clienteTableButton;
	private JButton produtoTableButton;
	private JButton ServicoTableButton;
	private JButton sairButton;
	
	public TelaMenu() {
		// inicia variaveis
		frame = new JFrame("Menu");
		
		panelSuperior = new JPanel();
		panelBotoes = new JPanel();
		
		clienteTableButton = new JButton("Tabela Cliente");
		produtoTableButton = new JButton("Tabela Produto");
		ServicoTableButton = new JButton("Tabela Serviço");
		sairButton = new JButton("Sair");
		
		configTela();
		
		configPanelSuperior();
        
        frame.add(panelSuperior, BorderLayout.NORTH);
        
        configPanelBotoes();
        
        frame.add(panelBotoes, BorderLayout.CENTER);
        
        frame.setVisible(true);
	}
	
	private void configPanelSuperior() {
		panelSuperior.setLayout(new BorderLayout());

        // Título em destaque
        JLabel titleLabel = new JLabel("DK MOTOS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panelSuperior.add(titleLabel, BorderLayout.NORTH);

        // Mensagem "Escolha a tabela"
        JLabel messageLabel = new JLabel("Escolha a tabela");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        panelSuperior.add(messageLabel, BorderLayout.SOUTH);
	}
	
	private void configPanelBotoes() {
		panelBotoes.setLayout(new BoxLayout(panelBotoes, BoxLayout.Y_AXIS));
        
        panelBotoes.add(Box.createVerticalGlue()); // Espaço flexível acima dos botões
        
	    addCenteredButton(panelBotoes, clienteTableButton);
	    addCenteredButton(panelBotoes, produtoTableButton);
	    addCenteredButton(panelBotoes, ServicoTableButton);
	    addCenteredButton(panelBotoes, sairButton);
	    
	    panelBotoes.add(Box.createVerticalGlue()); // Espaço flexível abaixo dos botões
		
		configButtons();
	}
	
	private void addCenteredButton(JPanel panel, JButton button) {
	    button.setAlignmentX(JButton.CENTER_ALIGNMENT);
	    panel.add(button);
	    panel.add(Box.createVerticalStrut(10)); // Espaço entre os botões
	}

	private void configButtons() {
		clienteTableButton.setPreferredSize(new Dimension(150, 40)); // Largura: 150, Altura: 40
		produtoTableButton.setPreferredSize(new Dimension(150, 40));
		ServicoTableButton.setPreferredSize(new Dimension(150, 40));
		sairButton.setPreferredSize(new Dimension(150, 40));
		
		clienteTableButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaClienteTabela();
                frame.dispose();
			}
		});
		
		produtoTableButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaProdutoTabela();
                frame.dispose();
			}
		});
		
		ServicoTableButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaServicoTabela();
                frame.dispose();
			}
		});
		
		sairButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
			}
		});
	}
	
	private void configTela() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Tamanho da tela
	}
	
	public static void main(String[] args) {
		new TelaMenu();
    }
}
