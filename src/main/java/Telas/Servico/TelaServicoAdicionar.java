package Telas.Servico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;

import Modelo.Cliente;
import Modelo.Produto;
import Modelo.Servico;
import Repositorio.ClienteRepositorio;
import Repositorio.ProdutoRepositorio;
import Repositorio.ServicoRepositorio;

public class TelaServicoAdicionar extends ServicoFormulario {

	protected JButton finalizarServicoButton;
	protected Servico servico;
	
	public TelaServicoAdicionar() {
		super.criarTela();
	}
	
	@Override
	protected void iniciaVariaveis() {
		super.iniciaVariaveis();
		
		finalizarServicoButton = new JButton("Finalizar");
			}
	
	@Override
	protected void ConfigButtons() {
		super.ConfigButtons();
		finalizarServicoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(validaCpfComboBox()) {
            		finalizarServico();
            	}
            }
	    });
	}
	
	@Override
	protected void criarPanelInfeior() {
		super.criarPanelInfeior();
		this.panelInferior.add(finalizarServicoButton);
	}
	
	protected void finalizarServico() {
		Cliente cliente = ClienteRepositorio.BuscarClientePorCPF((String) cpfComboBox.getSelectedItem());

		java.util.Date dataSelecionada = dataCompraCalendar.getDate();
		java.sql.Date data = new java.sql.Date(dataSelecionada.getTime());
		
		this.servico = new Servico(cliente, data);
		this.servico = ServicoRepositorio.CriarServico(this.servico);
		salvarProdutos();
		salvarReparos();
		
		new TelaServicoTabela();
        frame.dispose();
	}
	
	protected void salvarProdutos() {
		for (int row = 0; row < tableProduto.getRowCount(); row++) {
			int idProduto = (Integer) tableProduto.getValueAt(row, 0);
			Produto produto = ProdutoRepositorio.BuscarProdutoPorId(idProduto);
			
			int quantidade = (Integer) tableProduto.getValueAt(row, 2);
			
			this.servico.ComprarProduto(produto, quantidade);
		}
	}
	
	protected void salvarReparos() {
		for (int row = 0; row < tableReparo.getRowCount(); row++) {
			String descricao = (String) tableReparo.getValueAt(row, 0);
			String mecanico = (String) tableReparo.getValueAt(row, 1);
			String modelo = (String) tableReparo.getValueAt(row, 2);
			String placa = (String) tableReparo.getValueAt(row, 3);
	        BigDecimal Preco = (BigDecimal) tableReparo.getValueAt(row, 4);
	        
	        this.servico.FazerReparo(Preco, placa, descricao, mecanico, modelo);
		}
	}

	public static void main(String[] args) {
		new TelaServicoAdicionar();
	}
}
