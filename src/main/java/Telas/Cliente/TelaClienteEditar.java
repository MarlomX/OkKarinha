package Telas.Cliente;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Modelo.Cliente;
import Repositorio.ClienteRepositorio;
import Telas.Produto.TelaProdutoAdicionar;

public class TelaClienteEditar extends ClienteFormulario{
	private JButton editarButton;
	 private Cliente cliente;
	 
	 public TelaClienteEditar(String clienteCpf) {
	        editarButton = new JButton("Editar Produto");
	        this.cliente = ClienteRepositorio.BuscarClientePorCPF(clienteCpf);
	        criarTela();
	    }
	    
	    @Override
	    protected void ConfigButton() {
	    	super.ConfigButton();
	    	
	    	editarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                EditarCliente();
	            }
	        });

	    }
	    
	    private void EditarCliente() {
	    	if(validaCampos()) {
	    		String nomeCliente = formataNome();
			    String cpf = formataCPF();
			    String telefone = formataTelefone();
			    String email = formataEmail();
	    	        
    	        cliente.atualizaCliente(cpf, nomeCliente, telefone, email);
    	        ClienteRepositorio.AtualizarCliente(cliente);
    	        
    	        new TelaClienteTabela();
    	        frame.dispose();
	    	}
	    }
	    
	    @Override
	    protected void criarCampos(GridBagConstraints constraints, JPanel panel) {
	    	super.criarCampos(constraints, panel);
	    	nomeClienteField.setText(cliente.getNome());
	    	cpfField.setText(cliente.getCPF());
	    	telefoneField.setText(cliente.getTelefone());
	    	emailField.setText(cliente.getEmail());
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
	    

	    public static void main(String[] args) {
			new TelaProdutoAdicionar();
		}
}
