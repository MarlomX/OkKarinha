package Telas.Cliente;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Modelo.Cliente;
import Repositorio.ClienteRepositorio;
import br.com.caelum.stella.validation.CPFValidator;

public class ClienteFormulario {
	protected JTextField nomeClienteField;
	protected JTextField cpfField;
	protected JTextField telefoneField;
	protected JTextField emailField;
	protected JButton voltarButton;
	protected List<Cliente> listaClientes;
	protected JFrame frame;

	protected void criarTela() {
	
	    // Criação dos componentes
	    nomeClienteField = new JTextField(15);
	    cpfField = new JTextField(10);
	    telefoneField = new JTextField(10);
	    emailField = new JTextField(15);
	    voltarButton = new JButton("Voltar");
        listaClientes = ClienteRepositorio.BuscarTodosOsClientes();
	
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
				new TelaClienteTabela();
	            frame.dispose();
			}
		});
	}

	protected JFrame criarJanela(JPanel panel) {
		JFrame frame = new JFrame("Adicionar Cliente");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setSize(400, 300); // Defina o tamanho desejado (largura x altura)
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
	    criarCampo(panel, constraints, 0, 0, "Nome", nomeClienteField);
	    criarCampo(panel, constraints, 0, 1, "CPF:", cpfField);
	    criarCampo(panel, constraints, 0, 2, "Telefone:", telefoneField);
	    criarCampo(panel, constraints, 0, 3, "E-mail:", emailField);
	}
	
	protected void criarCampo(JPanel panel, GridBagConstraints constraints, int gridx, int gridy, String labelText, Component field) {
	    constraints.gridx = gridx;
	    constraints.gridy = gridy;
	    panel.add(new JLabel(labelText), constraints);

	    constraints.gridx = gridx + 1;
	    panel.add(field, constraints);
	}	
	
	//Codigos de formatação dos campos
	
	protected String formataNome() {
		String nomeCliente = nomeClienteField.getText();
        String nomeFormatado = nomeCliente.substring(0, 1).toUpperCase() + nomeCliente.substring(1);
        return nomeFormatado;
	}
	
	protected String formataCPF() {
	    String CPF = cpfField.getText();
	    return CPF;
	}
	
	protected String formataTelefone() {
		String telefone = telefoneField.getText();
	    return telefone;
	}
	
	protected String formataEmail() {
		String email = emailField.getText();
	    return email;
	}
	
	//Codigos de Validações dos campos
	
	protected boolean validaCampos() {
		if(camposPrenchidos() && validaNome() && validaCPF() && validaTelefone() && validaEmail()) {
			return true;
		}
		return false;
	}
	
	protected boolean camposPrenchidos() {
		
		String[][] campos = {
	            {nomeClienteField.getText().trim(), "Nome"},
	            {cpfField.getText().trim(), "CPF"},
	            {telefoneField.getText().trim(), "Telefone"},
	            {emailField.getText().trim(), "Email"}
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
		
		if(nome.matches(".*[^a-zA-Z0-9\\s].*")) {
			JOptionPane.showMessageDialog(null, "Erro: O nome do Cliente não pode comter numeros ou caracteres especiais", "Erro", 0);
    		result = false;
		}
	return result;
	}
	
	protected boolean validaCPF() {
		String cpf = formataCPF();
		boolean result = true;
		
		CPFValidator cpfValidator = new CPFValidator(); 
	    if(!cpfValidator.isEligible(cpf)) {
	    		JOptionPane.showMessageDialog(null, "Erro: O CPF e invalido Ex de CPF Valido:23568992354", "Erro", 0);
		        result =  false; 
	    }
	    	
	    return result;	
		
	}
	
	protected boolean validaTelefone() {
		String telefone = formataTelefone();
		boolean result = true;
		
		if(telefone.matches(".*[a-zA-Z].*")) {
			JOptionPane.showMessageDialog(null, "Erro: O telefone não pode conter letras", "Erro", 0);
			result =  false;
		} else if (telefone.matches(".*[^a-zA-Z0-9\\s].*")) {
			JOptionPane.showMessageDialog(null, "Erro: O Telefone não pode conter espaçõs ou caracteres especiais"
					+ "Deixeapenas os numeros Ex:'61985325240'", "Erro", 0);
			result = false;
		}
		return result;
	}
	
	protected boolean validaEmail() {
		String email = formataEmail();
		boolean result = true;
		try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
        	JOptionPane.showMessageDialog(null, "Erro: O Email e invalido", "Erro", 0);
            result = false;
        }
        return result;
    }
	
}
