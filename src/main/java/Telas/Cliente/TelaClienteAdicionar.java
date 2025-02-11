package Telas.Cliente;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Modelo.Cliente;
import Repositorio.ClienteRepositorio;

public class TelaClienteAdicionar extends ClienteFormulario {
	private JButton adicionarButton;

    public TelaClienteAdicionar() {
        adicionarButton = new JButton("Adicionar Cliente");
        criarTela();
    }
    
    @Override
    protected void ConfigButton() {
    	super.ConfigButton();
    	
    	adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCliente();
            }
        });
    }
    
    private void adicionarCliente() {
    	if(validaCampos()) {
			//busca os valores inseridos no campo de forma formatada
		    String nomeCliente = formataNome();
		    String cpf = formataCPF();
		    String telefone = formataTelefone();
		    String email = formataEmail();
		   
		    //adiciona os valores a um produto no banco de dados
		    Cliente novoCliente = new Cliente(cpf, nomeCliente,  telefone, email);
			ClienteRepositorio.CriarCliente(novoCliente);
		         
		
		    // Muda para a tela da tabela
			new TelaClienteTabela();
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

    public static void main(String[] args) {
		new TelaClienteAdicionar();
	}
}
