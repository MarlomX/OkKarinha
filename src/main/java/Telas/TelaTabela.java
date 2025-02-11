package Telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class TelaTabela {
	protected JButton addButton;
	protected JButton editButton;
	protected JButton deleteButton;
	protected JButton backButton;
	protected JFrame frame;
	protected JTable table;

	protected void InitTable(String titulo) {
		// titulo
		frame = new JFrame(titulo);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Criar a tabela e os botoes
		CreateTable();

		CreateButtons();

		// cofigurando a tela
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	protected void CreateTable() {
		DefaultTableModel model = CreateModel();

		table = new JTable(model);
		
        TableColumnModel columnModel = this.table.getColumnModel();
		TableColumn hiddenColumn = columnModel.getColumn(0);
		hiddenColumn.setMinWidth(0);
		hiddenColumn.setMaxWidth(0);
		hiddenColumn.setPreferredWidth(0);

		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
	}

	protected DefaultTableModel CreateModel() {
		return new DefaultTableModel(0, 0);
	}

	protected void CreateButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		addButton = new JButton("Adicionar");
		editButton = new JButton("Editar");
		deleteButton = new JButton("Excluir");
		backButton = new JButton("Voltar");

		AddFuncoesButtons(frame);

		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(backButton);

		frame.add(buttonPanel, BorderLayout.SOUTH);
		;
	}

	protected void AddFuncoesButtons(final JFrame frame) {
    	//botão deletar
    	deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( itenSelecionado("Selecione um servico para deletar") )
				deletaLinhaSelecionada(getIdSelecionado());
			}
		});
    	
    	backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaMenu();
				frame.dispose();
				
			}
		});
	}

	protected boolean itenSelecionado(String menssagem){
		//verifica se foi selecionado uma linha na tabela
		if(table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, menssagem, "Erro", 0);
			return false;
		}
		return true;
	}
	
	protected Object getIdSelecionado() {
		int linhaSelecionada = table.getSelectedRow();
		Object produtoId = table.getValueAt(linhaSelecionada, 0);
		return produtoId;

	}
	
	protected void deletaLinhaSelecionada(Object id) {
		int linhaSelecionada = table.getSelectedRow();
    	DefaultTableModel model = (DefaultTableModel)table.getModel();
    	model.removeRow(linhaSelecionada);
	}
}
