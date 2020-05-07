package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Status;
import br.com.faculdadedelta.util.Conexao;

public class StatusDao {
	private Connection conn = null;
	private String sql = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public void incluir(Status status) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "insert into status (descricao) values (?)";
		ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, status.getDescricao().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void modificar(Status status) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "update status set descricao = ? where id = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, status.getDescricao().trim());
			ps.setLong(2, status.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(Status status) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "delete from status where id = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, status.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<Status> listar() throws Exception {
		List<Status> resp = new ArrayList<>();
		conn = Conexao.conectarNoBancoDeDados();
		sql = "select * from status order by id";
		ps = conn.prepareStatement(sql);
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				Status status = popularstatus(rs);
				resp.add(status);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return resp;
	}

	public Status pesquisarId(Long id) throws Exception {
		Status resp = new Status();
		conn = Conexao.conectarNoBancoDeDados();
		sql = "select * from status where id = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				resp = popularstatus(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return resp;
	}

	public Status popularstatus(ResultSet rs) throws SQLException {
		Status status = new Status();
		status.setId(rs.getLong("id"));
		status.setDescricao(rs.getString("descricao").trim());
		return status;
	}

}
