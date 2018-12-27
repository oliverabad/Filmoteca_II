package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Director;

public class DirectorDAO {
	private AccesoDB dao = new AccesoDB();
	Director director;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	public void createDirector(Director d) {
		String query = "Insert into director(nombre, apellidos) values(?,?)";
		try {
			pstmt = dao.connectDB().prepareStatement(query);
			pstmt.setString(1, d.getNombre());
			pstmt.setString(2, d.getApellidos());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("Fila insertada");
			} else {
				System.out.println("Error insertar fila");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dao.closeDB();
		}

	}

	public ArrayList<Director> listDirector() {
		ArrayList<Director> misDirectores = new ArrayList<Director>();
		String query = "Select * from director";
		try {
			stmt = dao.connectDB().createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				director = new Director(rs.getInt("id_director"), rs.getString("nombre"), rs.getString("apellidos"));
				misDirectores.add(director);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dao.closeDB();
		}
		return misDirectores;
	}

	public void updateDirector(Director d) {
		String query = "update director set nombre = ?, apellidos = ? where id_director = ?";
		try {
			pstmt = dao.connectDB().prepareStatement(query);
			pstmt.setString(1, d.getNombre());
			pstmt.setString(2, d.getApellidos());
			pstmt.setInt(3, d.getId());
			pstmt.executeUpdate();
			pstmt.close();
			dao.closeDB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public Director findDirectorById(int id) {
		String query = "Select * from director where id_director = " + id;
		try {
			stmt = dao.connectDB().createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				director = new Director(rs.getInt("id_director"), rs.getString("nombre"), rs.getString("apellidos"));
				return director;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dao.closeDB();
		}
		return null;

	}

	public Director findDirector(String nom, String apellidos) {
		String query = "Select * from director where nombre = '" + nom + "' and apellidos = '" + apellidos + "'";
		try {
			stmt = dao.connectDB().createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				director = new Director(rs.getInt("id_director"), rs.getString("nombre"), rs.getString("apellidos"));
				return director;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dao.closeDB();
		}
		return null;

	}

	public void deleteDirector(Director d) {
		String query = "delete from director where id_director = ?";
		try {
			pstmt = dao.connectDB().prepareStatement(query);
			pstmt.setInt(1, d.getId());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.closeDB();
	}

}
