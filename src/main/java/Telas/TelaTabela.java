package Telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
}
