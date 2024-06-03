package Telas.Servico;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import Modelo.Servico;
import Repositorio.ServicoRepositorio;
import Telas.TelaTabela;

public class TelaServicoTabela extends TelaTabela{
	private List<Servico> listaServico;

    public TelaServicoTabela() {
    	super.InitTable("Servico");
    }
    
    @Override
    protected DefaultTableModel CreateModel() {
    	String[] columnNames = {"Id", "Cliente", "Valor Total", "Data"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        listaServico = ServicoRepositorio.BuscarTodosOsServicos();
        
        for(Servico servico: listaServico) {
        	model.addRow(new Object[] {servico.getId(), servico.getCliente().getNome(),servico.valorTotal(), servico.getData()});
        }
        return model;
    }
    
    @Override
    protected void AddFuncoesButtons(final JFrame frame) {

    }   
    
    public static void main(String[] args) {
    	new TelaServicoTabela();
	}
}
