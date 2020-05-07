package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.Genero;
import br.com.faculdadedelta.modelo.Serie;
import br.com.faculdadedelta.modelo.Status;
import br.com.faculdadedelta.util.Conexao;

public class SerieDao {
	private Connection conn = null;
	private String sql = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public void incluir(Serie serie) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "insert into series (id_genero, id_status, nome, comentario, nota_avaliacao) values (?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, serie.getGenero().getId());
			ps.setLong(2, serie.getStatus().getId());
			ps.setString(3, serie.getNome().trim());
			ps.setString(4, serie.getComentario().trim());
			ps.setInt(5, serie.getNotaAvaliacao());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void modificar(Serie serie) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "update series set id_genero = ?, id_status = ?, nome = ?, comentario = ?, nota_avaliacao = ? where id = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, serie.getGenero().getId());
			ps.setLong(2, serie.getStatus().getId());
			ps.setString(3, serie.getNome().trim());
			ps.setString(4, serie.getComentario().trim());
			ps.setInt(5, serie.getNotaAvaliacao());
			ps.setLong(6, serie.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(Serie serie) throws Exception {
		conn = Conexao.conectarNoBancoDeDados();
		sql = "delete from series where id = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, serie.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<Serie> listar() throws Exception {
		List<Serie> resp = new ArrayList<>();
		conn = Conexao.conectarNoBancoDeDados();
		sql = "select s.*, g.descricao descricao_genero, ss.descricao descricao_status from series s inner join genero g on s.id_genero = g.id inner join status ss on s.id_status = ss.id";
		ps = conn.prepareStatement(sql);
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				Serie serie = popularSerie(rs);
				resp.add(serie);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return resp;
	}

	public Serie pesquisarId(Long id) throws Exception {
		Serie resp = new Serie();
		conn = Conexao.conectarNoBancoDeDados();
		sql = "select s.*, g.descricao descricao_genero, ss.descricao descricao_status from series s inner join genero g on s.id_genero = g.id inner join status ss on s.id_status = ss.id where s.id = ?";
		ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				resp = popularSerie(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return resp;
	}

	public Serie popularSerie(ResultSet rs) throws SQLException {
		Serie serie = new Serie();
		serie.setId(rs.getLong("id"));
		serie.setNome(rs.getString("nome").trim());
		serie.setComentario(rs.getString("comentario").trim());
		serie.setNotaAvaliacao(rs.getInt("nota_avaliacao"));
		Genero genero = new Genero();
		genero.setId(rs.getLong("id_genero"));
		genero.setDescricao(rs.getString("descricao_genero").trim());
		serie.setGenero(genero);
		Status status = new Status();
		status.setId(rs.getLong("id_status"));
		status.setDescricao(rs.getString("descricao_status").trim());
		serie.setStatus(status);
		return serie;
	}

}
