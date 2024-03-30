package Repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conexao.ConectionFabric;
import Modelo.Produto;
import Modelo.Servico;

public class RelacaoSerProRepositorio {

	// Cria uma nova relação entre produto e servico
	public static void CriarRelacaoSerPro(Servico s, Produto p, int quantidade) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(
					"INSERT INTO relacao_servico_produto (Servico_ID, Produto_ID, Quant) VALUES(?, ?, ?)");
			stmt.setInt(1, s.getId());
			stmt.setInt(2, p.getId());
			stmt.setInt(3, quantidade);

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar criar uma linha na relacao_servico_produto: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt);
		}
	}
}
