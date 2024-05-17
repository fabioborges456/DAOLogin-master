package br.com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.model.Usuario;

public class UserDao {

	private ConnectionFactory factory;
	private Connection connection;

	public UserDao() {
		factory = new ConnectionFactory();
	}

	public void insert(Usuario usuario) {
		String sql = "INSERT INTO tbUsuario " + "(usuario, senha)" + " VALUES (?,?)";

		try {
			connection = factory.getConnection();

			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, usuario.getSenha());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}
	}

	public void update(Usuario Usuario) {
		String sql = "UPDATE tbUsuario SET usuario=?, senha=? WHERE idUsuario=?";
		try {
			connection = factory.getConnection();
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, Usuario.getUsuario());
			stmt.setString(2, Usuario.getSenha());
			stmt.setLong(3, Usuario.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}
	}

	public void delete(Usuario usuario) {
		try {
			connection = factory.getConnection();
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("DELETE FROM tbUsuario WHERE idUsuario=?");
			stmt.setLong(1, usuario.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}
	}

	public Usuario getUsuario(int id) {
		String sql = "SELECT * FROM tbUsuario WHERE idUsuario = ?";

		try {
			connection = factory.getConnection();
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement(sql);

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			while (rs.next()) {
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
			}
			stmt.close();
			rs.close();
			return usuario;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}
	}

	public Usuario getUsuario(String user) {
		String sql = "SELECT * FROM tbUsuario WHERE usuario = ?";

		try {
			connection = factory.getConnection();
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement(sql);

			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			while (rs.next()) {
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
			}
			stmt.close();
			rs.close();
			return usuario;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}
	}
	
	public List<Usuario> getLista() {

		try {
			connection = factory.getConnection();
			List<Usuario> usuarios = new ArrayList<Usuario>();
			PreparedStatement stmt = (PreparedStatement) 
					this.connection.prepareStatement("SELECT * FROM tbUsuario");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("idUsuario"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
				
				usuarios.add(usuario);
			}
			rs.close();
			stmt.close();
			return usuarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
		}

	}

}
