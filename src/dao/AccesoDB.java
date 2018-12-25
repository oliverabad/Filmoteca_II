package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDB {
	Connection connect;
	private final String url = "jdbc:mysql://localhost:3306/filmotecaII";
	private final String user = "root";
	private final String password = "1234";

	public Connection connectDB() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, user, password);
			if (connect != null) {
				System.out.println("Conectado a la base de datos FILMOTECA_II");
			}

		} catch (SQLException e) {
			System.out.println("Error de conexión a la BASE de DATOS " + e.getMessage());
			e.printStackTrace();
		}
		return connect;
	}

	public void closeDB() {
		try {
			connect.close();
			System.out.println("BASE DE DATOS CERRADA!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
