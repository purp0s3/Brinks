package br.edu.exemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.exemplo.model.User;
import br.edu.exemplo.util.ConnectionFactory;

public class UserDAO {
    private Connection conn; // conexão com banco de dados
    private PreparedStatement ps; // executa script SQL
    private ResultSet rs; // representa as tabelas
    private User user;

    // classe dao
    public UserDAO() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
        } catch (Exception e) {
            throw new Exception("erro: \n" + e.getMessage());
        }
    }
    
    // get usuário pelo username
    public User getUserByUsername(String username) {
        User user = null;

        try {
            String query = "SELECT * FROM usuarios WHERE username = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setCod(rs.getInt("cod"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // incluir usuário
    public boolean insertUser(User user) {
        try {
            String query = "INSERT INTO usuarios (username, password) VALUES (?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            int rowsInserted = ps.executeUpdate();
            ps.close();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
	// listar usuários
	public List<User> todosUsuarios() throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM usuarios");
			rs = ps.executeQuery();
			List<User> list = new ArrayList<User>();
			while (rs.next()) {
				int cod = rs.getInt("cod");
				String username = rs.getString("username");
				String password = rs.getString("password");
				list.add(new User(cod, username, password));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
	
	// atualizar
	public void atualizarU(User user) throws Exception {
		if (user == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "UPDATE usuarios SET username=?, password=? WHERE cod=?";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getCod());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao alterar dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	// excluir
	public void excluirU(User user) throws Exception {
		if (user == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "DELETE FROM usuarios WHERE cod = ?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, user.getCod());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao excluir dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	// procurar
	public User procurarUsuario(int idUsuario) throws Exception {

		try {
			String SQL = "SELECT  * FROM usuarios WHERE cod=?";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, idUsuario);			
			rs = ps.executeQuery();
			if (rs.next()) {
				int cod = rs.getInt("cod");
				String username = rs.getString("username");
				String password = rs.getString("password");				
				user = new User(cod, username, password);
			}
			return user;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
}