package br.edu.exemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.exemplo.model.Brinquedo;
import br.edu.exemplo.util.ConnectionFactory;

public class BrinquedoDAO {
	private Connection conn;   // conexão com banco de dados
	private PreparedStatement ps;  // executa script SQL
	private ResultSet rs;  // representa as tabelas
	private Brinquedo brinquedo;

	public BrinquedoDAO() throws Exception {
		// conexao
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("erro: \n" + e.getMessage());
		}
	}

	// incluir
	public void salvar(Brinquedo brinquedo) throws Exception {
		if (brinquedo == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "INSERT INTO brinquedos (descricao, categoria, marca, valor, detalhes, imagem) values "
					+ "(?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, brinquedo.getDescricao());
			ps.setString(2, brinquedo.getCategoria());
			ps.setString(3, brinquedo.getMarca());
			ps.setInt(4, brinquedo.getValor());
			ps.setString(5, brinquedo.getDetalhes());
			ps.setString(6, brinquedo.getImagem());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao inserir dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	// atualizar
	public void atualizar(Brinquedo brinquedo) throws Exception {
		if (brinquedo == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "UPDATE brinquedos SET descricao=?, categoria=?, marca=?, valor=?, detalhes=?, imagem=? WHERE id=?";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, brinquedo.getDescricao());
			ps.setString(2, brinquedo.getCategoria());
			ps.setString(3, brinquedo.getMarca());
			ps.setInt(4, brinquedo.getValor());
			ps.setString(5, brinquedo.getDetalhes());
			ps.setString(6, brinquedo.getImagem());
			ps.setInt(7, brinquedo.getId());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao alterar dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	// excluir
	public void excluir(Brinquedo brinquedo) throws Exception {
		if (brinquedo == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "DELETE FROM brinquedos WHERE id = ?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, brinquedo.getId());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao excluir dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	// procurar
	public Brinquedo procurarBrinquedo(int idBrinquedo) throws Exception {

		try {
			String SQL = "SELECT  * FROM brinquedos WHERE id=?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, idBrinquedo);			
			rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String descricao = rs.getString("descricao");
				String categoria = rs.getString("categoria");
				String marca = rs.getString("marca");
				int valor = rs.getInt("valor");
				String detalhes = rs.getString("detalhes");
				String imagem = rs.getString("imagem");
				
				brinquedo = new Brinquedo(id, descricao, categoria, marca, valor, detalhes, imagem);
			}
			return brinquedo;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	
	// listar
	public List<Brinquedo> todosBrinquedos() throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM brinquedos");
			rs = ps.executeQuery();
			List<Brinquedo> list = new ArrayList<Brinquedo>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String descricao = rs.getString("descricao");
				String categoria = rs.getString("categoria");
				String marca = rs.getString("marca");
				int valor = rs.getInt("valor");
				String detalhes = rs.getString("detalhes");
				String imagem = rs.getString("imagem");
				list.add(new Brinquedo(id, descricao, categoria, marca, valor, detalhes, imagem));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	
	// listar por categoria
	public List<Brinquedo> categoriaBrinquedos(String selectedCategory) throws Exception {
	    try {
	        ps = conn.prepareStatement("SELECT * FROM brinquedos WHERE categoria = ?");
	        ps.setString(1, selectedCategory);
	        rs = ps.executeQuery();
	
	        List<Brinquedo> list = new ArrayList<Brinquedo>();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String descricao = rs.getString("descricao");
	            String categoria = rs.getString("categoria");
	            String marca = rs.getString("marca");
	            int valor = rs.getInt("valor");
	            String detalhes = rs.getString("detalhes");
	            String imagem = rs.getString("imagem");
	            list.add(new Brinquedo(id, descricao, categoria, marca, valor, detalhes, imagem));
	        }
	        return list;
	    } catch (SQLException sqle) {
	        throw new Exception(sqle);
	    } finally {
	        ConnectionFactory.closeConnection(conn, ps, rs);
	    }
	}
	
	// mostrar brinquedo
	public List<Brinquedo> mostrarBrinquedo(String selectedBrinquedo) throws Exception {
	    try {
	        ps = conn.prepareStatement("SELECT * FROM brinquedos WHERE id = ?");
	        ps.setString(1, selectedBrinquedo);
	        rs = ps.executeQuery();
	
	        List<Brinquedo> list = new ArrayList<Brinquedo>();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String descricao = rs.getString("descricao");
	            String categoria = rs.getString("categoria");
	            String marca = rs.getString("marca");
	            int valor = rs.getInt("valor");
	            String detalhes = rs.getString("detalhes");
	            String imagem = rs.getString("imagem");
	            list.add(new Brinquedo(id, descricao, categoria, marca, valor, detalhes, imagem));
	        }
	        return list;
	    } catch (SQLException sqle) {
	        throw new Exception(sqle);
	    } finally {
	        ConnectionFactory.closeConnection(conn, ps, rs);
	    }
	}	
	
	// listar destaques
	public List<Brinquedo> destaqueBrinquedos() throws Exception {
		int limite = 10;
		try {
			ps = conn.prepareStatement("SELECT * FROM brinquedos");
			rs = ps.executeQuery();
			List<Brinquedo> list = new ArrayList<Brinquedo>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String descricao = rs.getString("descricao");
				String categoria = rs.getString("categoria");
				String marca = rs.getString("marca");
				int valor = rs.getInt("valor");
				String detalhes = rs.getString("detalhes");
				String imagem = rs.getString("imagem");
				if (list.size() < limite) {
					list.add(new Brinquedo(id, descricao, categoria, marca, valor, detalhes, imagem));
				}
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}	
	
}


