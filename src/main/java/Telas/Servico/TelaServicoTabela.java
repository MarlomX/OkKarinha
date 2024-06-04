package Telas.Servico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    	//botão adicionar
    	addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaServicoAdicionar();
                frame.dispose();
			}
		});
    	
    	//botão editar
    	editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(itenSelecionado("Selecione um Serviço")) {
	            	int ServicoId =((Integer) getIdSelecionado()).intValue();
	            	new TelaServicoEditar(ServicoId);
	                frame.dispose();	
				}
			}
		});
    	
    	//botão de excluir e voltar
    	super.AddFuncoesButtons(frame);

    }   
    @Override
    protected void deletaLinhaSelecionada(Object id) {
    	//remove o Serviço do banco de dados
    	Servico servico = ServicoRepositorio.BuscarServicoPorId((int)id);
    	servico.deletarServico();
    	
    	//deleta a linha
    	super.deletaLinhaSelecionada(id);
    }
    
    public static void main(String[] args) {
    	new TelaServicoTabela();
	}
}
