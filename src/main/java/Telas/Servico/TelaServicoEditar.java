package Telas.Servico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.Cliente;
import Modelo.Oferta;
import Modelo.Produto;
import Modelo.Reparo;
import Modelo.Servico;
import Repositorio.ClienteRepositorio;
import Repositorio.ProdutoRepositorio;
import Repositorio.ServicoRepositorio;

public class TelaServicoEditar extends ServicoFormulario{
	protected JButton editarServicoButton;
	protected Servico servico;
	protected int servicoId;
	protected List<Integer> idOfertasDeletadas;
	protected List<Integer> idReparosDeletados;
	
	public TelaServicoEditar(int id) {
		servicoId = id;
		super.criarTela();
		prencherCampos();
	}
	
	@Override
	protected void iniciaVariaveis() {
		super.iniciaVariaveis();
		
		editarServicoButton = new JButton("Editar");
		
		servico = ServicoRepositorio.BuscarServicoPorId(servicoId);
		idOfertasDeletadas = new ArrayList<>();
		idReparosDeletados = new ArrayList<>();
			}
	
	@Override
	protected void ConfigButtons() {
		super.ConfigButtons();
		editarServicoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(validaCpfComboBox()) {
	            	editarServico();
            	}
            }
	    });
	}
	
	@Override
	protected void criarPanelInfeior() {
		super.criarPanelInfeior();
		this.panelInferior.add(editarServicoButton);
	}
	
	@Override
	protected void removerLinhaTable(JTable table) {
    	int linhaSelecionada = table.getSelectedRow();
		int id = (Integer) table.getValueAt(linhaSelecionada, 5);
		if (table == tableProduto) {
			idOfertasDeletadas.add(id);
		} else {
			idReparosDeletados.add(id);
		}
		
		super.removerLinhaTable(table);
	}

	protected void editarServico() {
		Cliente cliente = ClienteRepositorio.BuscarClientePorCPF((String)this.cpfSelecionado);

		java.util.Date dataSelecionada = dataCompraCalendar.getDate();
		java.sql.Date data = new java.sql.Date(dataSelecionada.getTime());
		
		this.servico.setData(data);
		this.servico.setCliente(cliente);
		
		ServicoRepositorio.AtualizarServico(servico);
		
		editaProdutos();
		editarReparos();
		
		new TelaServicoTabela();
        frame.dispose();
	}
	
	protected void editaProdutos() {
		//adiciona novos Produtos
		for (int row = 0; row < tableProduto.getRowCount(); row++) {
			int idOferta = (Integer) tableProduto.getValueAt(row, 5);
			if(idOferta == 0) {
				int idProduto = (Integer) tableProduto.getValueAt(row, 0);
				Produto produto = ProdutoRepositorio.BuscarProdutoPorId(idProduto);
				
				int quantidade = (Integer) tableProduto.getValueAt(row, 2);
				
				this.servico.ComprarProduto(produto, quantidade);
				
			} 
		}
		//Deletar produtos no DB
		for(int id : idOfertasDeletadas) {
			if(id != 0) {
				this.servico.deletarOferta(id);
			}
		}
	}
	
	protected void editarReparos() {
		for (int row = 0; row < tableReparo.getRowCount(); row++) {
			int idReparo = (Integer) tableReparo.getValueAt(row, 5);
			if(idReparo == 0) {
				String descricao = (String) tableReparo.getValueAt(row, 0);
				String mecanico = (String) tableReparo.getValueAt(row, 1);
				String modelo = (String) tableReparo.getValueAt(row, 2);
				String placa = (String) tableReparo.getValueAt(row, 3);
		        BigDecimal Preco = (BigDecimal) tableReparo.getValueAt(row, 4);
		        
		        this.servico.FazerReparo(Preco, placa, descricao, mecanico, modelo);
			}
		}
		//Deletar reparo no DB
		for(int id : idReparosDeletados) {
			if(id != 0) {
				this.servico.deletarReparo(id);
			}
		}
	}
	
	protected void prencherCampos() {
		initPanelSuperior();
		initTableProduto();
		initTebaleReparo();
	}
	
	protected void initPanelSuperior() {
		
		String cpfDoServico = servico.getCliente().getCPF();

		for (int i = 1; i < cpfComboBox.getItemCount(); i++) {
		    String item = cpfComboBox.getItemAt(i); // Obtém o item "CPF / Nome"
		    String cpfItem = item.split(" / ")[0];  // Extrai apenas o CPF

		    if (cpfItem.equals(cpfDoServico)) {
		        cpfComboBox.setSelectedItem(item); // Define o item correspondente
		        break;
		    }
		}
		
		nomeTextField.setText(servico.getCliente().getNome());
		dataCompraCalendar.setDate(servico.getData());
		
		valorTotal = servico.valorTotal();
		valorTotalfield.setText(valorTotal.toString());
	}	
	
	protected void initTableProduto() {
    	DefaultTableModel model = (DefaultTableModel)tableProduto.getModel();
		for(Oferta oferta : servico.getListaOrfeta()) {
			
			Produto produto = oferta.getProduto();
			BigDecimal valorTotal = produto.getPrecoVenda().multiply(BigDecimal.valueOf(oferta.getQuantidade()));
	        
			Object[] dados = {produto.getId(), produto.getNome(), oferta.getQuantidade(), 
	        		produto.getPrecoVenda(), valorTotal, oferta.getId()};
			
			model.addRow(dados);
		}
	}
	
	protected void initTebaleReparo() {
		DefaultTableModel model = (DefaultTableModel) tableReparo.getModel();
		for(Reparo reparo : servico.getListaReparo()) {
			
	        Object[] dados = {reparo.getDescricao(), reparo.getMecanico(), reparo.getModelo(),
	        		reparo.getPlaca(), reparo.getPreco(), reparo.getId()};
	        
	        model.addRow(dados);

		}
	}

	public static void main(String[] args) {
		new TelaServicoAdicionar();
	}
}
