package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String bdName	= "jdbc:mysql://localhost/prjInterface";
	private static final String user		= "root";
	private static final String password	= "123456";

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
                    bdName, user, password);
		} catch(SQLException e) {
			System.out.println("Não foi possivel criar a conexao");
		}
		return connection;
	}

}
