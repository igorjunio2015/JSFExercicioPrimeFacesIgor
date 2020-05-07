package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Genero;
import br.com.faculdadedelta.util.Conexao;

public class GeneroDao {
	private Connection conn = null;
	private String sql = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public void incluir(Genero genero) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "insert into genero (descricao) values (?)";
		ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, genero.getDescricao().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void modificar(Genero genero) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "update genero set descricao = ? where id = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, genero.getDescricao().trim());
			ps.setLong(2, genero.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(Genero genero) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "delete from genero where id = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, genero.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<Genero> listar() throws Exception {
		List<Genero> resp = new ArrayList<>();
		conn = Conexao.conectarNoBancoDeDados();
		sql = "select * from genero order by id";
		ps = conn.prepareStatement(sql);
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				Genero genero = populargenero(rs);
				resp.add(genero);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return resp;
	}

	public Genero pesquisarId(Long id) throws Exception {
		Genero resp = new Genero();
		conn = Conexao.conectarNoBancoDeDados();
		sql = "select * from genero where id = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				resp = populargenero(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return resp;
	}

	public Genero populargenero(ResultSet rs) throws SQLException {
		Genero genero = new Genero();
		genero.setId(rs.getLong("id"));
		genero.setDescricao(rs.getString("descricao").trim());
		return genero;
	}

}
