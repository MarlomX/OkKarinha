package Telas.Servico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JCalendar;

import Modelo.Cliente;
import Modelo.Produto;
import Repositorio.ClienteRepositorio;
import Repositorio.ProdutoRepositorio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ServicoFormulario {
	protected JFrame frame;
	
	//variaveis tela painel principal
	protected JPanel panelPrincipal;
	protected List<Cliente> listaClientes;
	protected JComboBox<Object> cpfComboBox;
	protected JTextField nomeTextField;
	protected JCalendar dataCompraCalendar;
	protected JTextField valorTotalfield;
	protected BigDecimal valorTotal;
	
	//painel central
	protected JPanel panelCentral;
    
	//variaveis panel de produto
	protected JPanel panelProduto;
	protected JTable tableProduto;
	protected List<Produto> listaProdutos;
	protected JComboBox<Object> produtoComboBox;
	protected JButton adicionarLinhaProdutoButton;
	protected JButton removerLinhaProdutoButton;
	protected JTextField nomeProdutoField;
	protected JTextField quantidadeField;
	protected JTextField precoUnitarioField;
	protected int idProdutoAtual;

    //variaveis panel de reparo
	protected JPanel panelReparo;
	protected JTable tableReparo;
	protected JButton adicionarLinhaReparoButton;
	protected JButton removerLinhaReparoButton;
	protected JTextField descricaoReparoField;
	protected JTextField nomeMecanicoField;
	protected JTextField modeloMotoField;
	protected JTextField placaMotoField;
	protected JTextField precoReparoField;
	
	//variaveis panel inferior
	protected JPanel panelInferior;
	protected JButton voltarButton;



    public void criarTela() {
    	//inicia as variaveis
    	iniciaVariaveis();

    	//configura os botoes
    	ConfigButtons();
    	
        // Configurações básicas da janela
    	configTela();
        
        // Paineis
    	//this.frame.setLayout(new BoxLayout(panelReparo, BoxLayout.Y_AXIS));

    	
        criarPanelPrincipal();
        this.frame.add(this.panelPrincipal, BorderLayout.NORTH);
        
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        // Crie os painéis individuais
        criarPanelProduto();
        
        criarPanelReparo();
        
        // Adicione os painéis ao painel principal
        panelCentral.add(this.panelProduto);
        panelCentral.add(this.panelReparo);

        // Adicione o painel principal ao frame
        this.frame.add(panelCentral, BorderLayout.CENTER);
        
        criarPanelInfeior();
        this.frame.add(this.panelInferior, BorderLayout.SOUTH);



        // Exibe a janela
        this.frame.setVisible(true);
    }
    
    protected void iniciaVariaveis() {
    	this.frame = new JFrame("Controle de Servico");
    	
    	//variaveis painel principal
    	this.listaClientes = ClienteRepositorio.BuscarTodosOsClientes();
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));;
		this.nomeTextField = new JTextField();
		this.dataCompraCalendar = new JCalendar();
        this.valorTotalfield = new JTextField();
        this.valorTotal = BigDecimal.ZERO;
        campoSelecaoCPF();

        
        //variaveis painel central
        this.panelCentral = new JPanel();
        
        //variaveis painel produto
        this.listaProdutos =  ProdutoRepositorio.BuscarTodosOsProdutos();
		this.panelProduto = new JPanel();
		this.nomeProdutoField = new JTextField(15);
		this.quantidadeField = new JTextField(3);
		this.precoUnitarioField = new JTextField(5);
		this.precoUnitarioField.setEditable(false);
		this.adicionarLinhaProdutoButton = new JButton("Adicionar");
		this.removerLinhaProdutoButton = new JButton("Remover");
	    campoSelecaoProduto();
	    
	    //variaveis painel reparo
		this.panelReparo = new JPanel();
		this.descricaoReparoField = new JTextField(20);
		this.nomeMecanicoField = new JTextField(5);
		this.modeloMotoField = new JTextField(5);
		this.placaMotoField = new JTextField(3);
		this.precoReparoField = new JTextField(3);
		this.adicionarLinhaReparoButton = new JButton("Adicionar");
		this.removerLinhaReparoButton = new JButton("Remover");
		
		//variaveis painel inferior
		panelInferior = new JPanel();
		voltarButton = new JButton("Voltar");
    }

    protected void configTela() {
    	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.frame.setSize(400, 200);
    	this.frame.setLocationRelativeTo(null);
    }
    
	protected void ConfigButtons() {
		//botão adiciona linha de produto
		adicionarLinhaProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(validaProdutoComboBox() & validaQuantidadeField()) {
                	adicionarNovaLinhaProduto();
            	}
            }
	    });
		
		//botão remover linha de produto
		removerLinhaProdutoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	removerLinhaTable(tableProduto);
            }
	    });

		//botão adiciona linha de reparo
		adicionarLinhaReparoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(validaPrenchimentoReparoField()) {
                	adicionarNovaLinhaReparo();
            	}
            }
	    });
		
		//botão remover linha de reparo
		removerLinhaReparoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	removerLinhaTable(tableReparo);
            }
	    });
				
		//botão para voltar a tela
		voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new TelaServicoTabela();
	            frame.dispose();
            }
	    });
	}

	protected void campoSelecaoCPF() {
	     // Componente de seleção de CPF
		List<String> cpfs = new ArrayList<>(); // Exemplo de CPFs
		cpfs.add("");
		for(Cliente cliente: listaClientes) {
	 	cpfs.add(cliente.getCPF());
	 }
	 cpfComboBox = new JComboBox<>(cpfs.toArray());
	 cpfComboBox.addActionListener(new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	         // Simulação: preenche automaticamente o nome quando o CPF é selecionado
	    	 if (cpfComboBox.getSelectedIndex() == 0) {
                 nomeTextField.setText("");
	    	 }
	             String cpfSelecionado = (String) cpfComboBox.getSelectedItem();
	             for(Cliente cliente: listaClientes) {
	             	if(cpfSelecionado.equals(cliente.getCPF())){
	                     nomeTextField.setText(cliente.getNome());
	             	}
             	}
	         }
	     });
	}
	
	protected void campoSelecaoProduto() {
	     // Componente de seleção do produto
		List<String> produtos = new ArrayList<>(); // Exemplo de CPFs
		produtos.add("");
		for(Produto produto: listaProdutos) {
			produtos.add(produto.getNome());
	 }
	produtoComboBox = new JComboBox<>(produtos.toArray());
	produtoComboBox.addActionListener(new ActionListener() {
	     @Override
	     public void actionPerformed(ActionEvent e) {
	    	 if (produtoComboBox.getSelectedIndex() == 0) {
	    		 precoUnitarioField.setText("");
	    	 }
	         // Simulação: preenche automaticamente o nome quando o CPF é selecionado
	             String produtoSelecionado = (String) produtoComboBox.getSelectedItem();
	             for(Produto produto: listaProdutos) {
	             	if(produtoSelecionado.equals(produto.getNome())){
	             		idProdutoAtual = produto.getId();
	                    precoUnitarioField.setText(produto.getPrecoVenda().toString());
	             	}
	             }
	         }
	     });
	}

	protected void atualizaValorTotal(BigDecimal valor, boolean somar) {
		if(somar == true) {
			this.valorTotal = this.valorTotal.add(valor);
		}else {
			this.valorTotal = this.valorTotal.subtract(valor);
		}
		this.valorTotalfield.setText(this.valorTotal.toString());
	}

	protected void criarPanelPrincipal() {
		this.panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		
        nomeTextField.setEditable(false);
        valorTotalfield.setEditable(false);

        // Calendário para a data da compra
        // Adiciona os componentes ao painel
        this.panelPrincipal.add(new JLabel("CPF do Cliente:"));
        panelPrincipal.add(Box.createVerticalStrut(15)); // Espaço vertical de 15 pixels
        this.panelPrincipal.add(cpfComboBox);
        
        this.panelPrincipal.add(new JLabel("Nome do Cliente:"));
        panelPrincipal.add(Box.createVerticalStrut(15)); // Espaço vertical de 15 pixels
        this.panelPrincipal.add(nomeTextField);
        
        this.panelPrincipal.add(new JLabel("Valor Total:"));
        panelPrincipal.add(Box.createVerticalStrut(15)); // Espaço vertical de 15 pixels
        this.panelPrincipal.add(valorTotalfield);
        
        this.panelPrincipal.add(new JLabel("Data da Compra:"));
        panelPrincipal.add(Box.createVerticalStrut(10)); // Espaço vertical de 15 pixels
        this.panelPrincipal.add(dataCompraCalendar);
        }
	
	protected void criarPanelProduto() {
		this.panelProduto.setLayout(new BoxLayout(panelProduto, BoxLayout.Y_AXIS));
		
		//formulario servico
		
		JPanel formulario =new JPanel();

	    formulario.add(new JLabel("Nome do Produto:"));
	    formulario.add(produtoComboBox);
	    formulario.add(new JLabel("Quantidade:"));
	    formulario.add(quantidadeField);
	    formulario.add(new JLabel("Preço Unitário:"));
	    formulario.add(precoUnitarioField);
	    formulario.add(adicionarLinhaProdutoButton);
	    formulario.add(removerLinhaProdutoButton);

	    
	    //adiciona  no painel de produto
	    this.panelProduto.add(formulario);
	    
	    
	    //tabela de servico
    	String[] columnNames = {"ProdutoId", "Nome", "Quantidade", "Preço unitário", "Preço total", "OfertaId"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		this.tableProduto = new JTable(model);
		
		//Esconder colunas
		TableColumnModel columnModel = this.tableProduto.getColumnModel();
		
		//Esconder coluna ProdutoId
		TableColumn produtoId = columnModel.getColumn(0);
		produtoId.setMinWidth(0);
		produtoId.setMaxWidth(0);
		produtoId.setPreferredWidth(0);
		
		//Esconder coluna OfertaId
		TableColumn ofertaId = columnModel.getColumn(5);
		ofertaId.setMinWidth(0);
		ofertaId.setMaxWidth(0);
		ofertaId.setPreferredWidth(0);
		
		JScrollPane scrollPane = new JScrollPane(this.tableProduto);
		
	    //adiciona no painel de produto
		this.panelProduto.add(scrollPane, BorderLayout.CENTER);
    }
	
	protected void criarPanelReparo() {
		this.panelReparo.setLayout(new BoxLayout(panelReparo, BoxLayout.Y_AXIS));
		
		//formulario reparo
		
		JPanel formularioReparo =new JPanel();

	    formularioReparo.add(new JLabel("Descrição do Reparo:"));
	    formularioReparo.add(descricaoReparoField);
	    formularioReparo.add(new JLabel("Nome do Mecanico:"));
	    formularioReparo.add(nomeMecanicoField);
	    formularioReparo.add(new JLabel("Modelo da Moto:"));
	    formularioReparo.add(modeloMotoField);
	    formularioReparo.add(new JLabel("Placa da Moto:"));
	    formularioReparo.add(placaMotoField);
	    formularioReparo.add(new JLabel("Preço do Reparo:"));
	    formularioReparo.add(precoReparoField);
	    formularioReparo.add(adicionarLinhaReparoButton);
	    formularioReparo.add(removerLinhaReparoButton);

	    
	    //adiciona  no painel de reparo
	    this.panelReparo.add(formularioReparo);
	    
	    
	    //tabela de servico
    	String[] columnNames = {"Descrição", "Mecanico", "Modelo", "Placa", "Preço", "ReparoId"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		this.tableReparo = new JTable(model);
		
		//Esconder colunas
		TableColumnModel columnModel = this.tableReparo.getColumnModel();
		
		//Esconder coluna ReparoId
		
		TableColumn reparoId = columnModel.getColumn(5);
		reparoId.setMinWidth(0);
		reparoId.setMaxWidth(0);
		reparoId.setPreferredWidth(0);
		
		
		JScrollPane scrollPane = new JScrollPane(this.tableReparo);
		
	    //adiciona no painel de produto
		this.panelReparo.add(scrollPane, BorderLayout.CENTER);
	}

	protected void criarPanelInfeior() {
		this.panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
		this.panelInferior.add(voltarButton);
	}

	protected void adicionarNovaLinhaProduto() {
		String nomeProduto = (String) produtoComboBox.getSelectedItem();
        int quantidade = Integer.parseInt(quantidadeField.getText());
        BigDecimal precoUnitario = new BigDecimal(precoUnitarioField.getText());
        BigDecimal valorTotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));

        
        Object[] dados = {this.idProdutoAtual, nomeProduto, quantidade, precoUnitario, valorTotal, 0};
        
		DefaultTableModel model = (DefaultTableModel) this.tableProduto.getModel();
		
		model.addRow(dados);
		atualizaValorTotal(valorTotal, true);

        
        // Limpe os campos de texto
        nomeProdutoField.setText("");
        quantidadeField.setText("");
        precoUnitarioField.setText("");

    }

	protected void adicionarNovaLinhaReparo() {
		String descricao = (String) descricaoReparoField.getText();
		String mecanico = (String) nomeMecanicoField.getText();
		String modelo = (String) modeloMotoField.getText();
		String placa = (String) placaMotoField.getText();
        BigDecimal Preco = new BigDecimal(precoReparoField.getText());
        
        
        Object[] dados = {descricao, mecanico, modelo, placa, Preco, 0};
        
		DefaultTableModel model = (DefaultTableModel) this.tableReparo.getModel();
		
		model.addRow(dados);
		atualizaValorTotal(Preco, true);
        
        // Limpe os campos de texto
		descricaoReparoField.setText("");
		nomeMecanicoField.setText("");
		modeloMotoField.setText("");
		placaMotoField.setText("");
		precoReparoField.setText("");;
        

    }
	
	protected void removerLinhaTable(JTable table) {
    	int linhaSelecionada = table.getSelectedRow();
    	BigDecimal valorTotalDaRow = new BigDecimal(String.valueOf(table.getValueAt(linhaSelecionada, 4)));
    	atualizaValorTotal(valorTotalDaRow, false);
    	
    	DefaultTableModel model = (DefaultTableModel)table.getModel();
    	model.removeRow(linhaSelecionada);
	}
	
	//Validação de campos
	
	protected boolean validaCpfComboBox() {
		if(cpfComboBox.getSelectedIndex() == 0) {
        	JOptionPane.showMessageDialog(null, "Erro: Selecione um CPF", "Erro", 0);
        	return false;
		}
		return true;
	}
	
	protected boolean validaProdutoComboBox() {
		if(produtoComboBox.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Erro: Selecione um Produto", "Erro", 0);
        	return false;
		}
		return true;
	}
	
	protected boolean validaQuantidadeField() {
		boolean valido = false;
		if(quantidadeField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Erro: O campo 'Quantidade' e de preenchimento obrigatorio.", "Erro", 0);
		} else {
			try {
				Integer.parseInt(quantidadeField.getText());
				valido  = true;
			}catch (NumberFormatException e) {
			    JOptionPane.showMessageDialog(null, "Erro: A quantidade tem que ser um valor inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		return valido;
	}
	
	protected boolean validaPrenchimentoReparoField() {
		String[][] campos = {
            {descricaoReparoField.getText().trim(), "Descrição do Reparo"},
            {nomeMecanicoField.getText().trim(), "Nome do Mecanico"},
            {modeloMotoField.getText().trim(), "Modelo da Moto"},
            {placaMotoField.getText().trim(), "Placa da Moto"},
            {precoReparoField.getText().trim(), "Preço"}
        };

        for (String[] campo : campos) {
            if (campo[0].isEmpty()) {
                JOptionPane.showMessageDialog(null, "Erro: O campo '" + campo[1] + "' é de preenchimento obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        try {
        	new BigDecimal(precoReparoField.getText());
        }catch(NumberFormatException e) {
			if(precoReparoField.getText().contains(",")) {
			    JOptionPane.showMessageDialog(null, "Erro: A formatação do preço está errada!"
			    		+ " Use '.' em vez de ',' (por exemplo, 2500.50).", "Erro", JOptionPane.ERROR_MESSAGE);
			    return false;
			}else {
			    JOptionPane.showMessageDialog(null, "Erro: O campo de preço so pode ter numeros.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			return false;
		}
        
        return true;
	}
	
}

