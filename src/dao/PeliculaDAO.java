package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Director;
import entidades.Pelicula;

public class PeliculaDAO {
	private AccesoDB dao = new AccesoDB();
	Pelicula pelicula;
	ArrayList<Pelicula> misPelis;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;

	public int createPelicula(Pelicula p) {
		String query = "Insert into pelicula(titulo, id_dir, pais, duracion, genero) values(?,?,?,?,?)";
		int result = 0;
		try {
			pstmt = dao.connectDB().prepareStatement(query);
			pstmt.setString(1, p.getTitulo());
			pstmt.setInt(2, p.getId_dir());
			pstmt.setString(3, p.getPais());
			pstmt.setString(4, p.getDuracion());
			pstmt.setString(5, p.getGenero());
			result = pstmt.executeUpdate();
			if (result > 0) {
				return result;
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
		return 0;
	}

	public void UpdatePelicula(Pelicula p) {
		String query = "update pelicula set titulo = ? , pais = ? , duracion = ? , genero = ? where id_pelicula = ? && id_dir = ?";
		try {
			pstmt = dao.connectDB().prepareStatement(query);
			pstmt.setString(1, p.getTitulo());
			pstmt.setString(2, p.getPais());
			pstmt.setString(3, p.getDuracion());
			pstmt.setString(4, p.getGenero());
			pstmt.setInt(5, p.getId_pelicula());
			pstmt.setInt(6, p.getId_dir());
			pstmt.executeUpdate();
			pstmt.close();
			dao.closeDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Pelicula> listPeliculas() {
		misPelis = new ArrayList<Pelicula>();
		String query = "Select * from pelicula";
		try {
			stmt = dao.connectDB().createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getString("titulo"), rs.getInt("id_dir"),
						rs.getString("pais"), rs.getString("duracion"), rs.getString("genero"));
				misPelis.add(pelicula);
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
		return misPelis;

	}

	public ArrayList<Pelicula> listPeliDirectorTitulo(int id_dir) {
		misPelis = new ArrayList<Pelicula>();
		String query = "Select titulo from pelicula where id_dir = ?";
		try {
			pstmt = dao.connectDB().prepareStatement(query);
			pstmt.setInt(1, id_dir);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pelicula = new Pelicula(rs.getString("titulo"));
				misPelis.add(pelicula);
			}
			rs.close();
			pstmt.close();
			dao.closeDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return misPelis;
	}

	public ArrayList<Pelicula> listPeliDirector(int id_dir) {
		misPelis = new ArrayList<Pelicula>();
		String query = "Select * from pelicula where id_dir = ?";
		try {
			pstmt = dao.connectDB().prepareStatement(query);
			pstmt.setInt(1, id_dir);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getString("titulo"), rs.getInt("id_dir"),
						rs.getString("pais"), rs.getString("duracion"), rs.getString("genero"));
				misPelis.add(pelicula);
			}
			rs.close();
			pstmt.close();
			dao.closeDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return misPelis;
	}

	public ArrayList<Pelicula> listPeliGenero(String genero) {
		misPelis = new ArrayList<Pelicula>();
		String query = "Select * from pelicula where genero = ?";

		try {
			pstmt = dao.connectDB().prepareStatement(query);
			pstmt.setString(1, genero);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getString("titulo"), rs.getInt("id_dir"),
						rs.getString("pais"), rs.getString("duracion"), rs.getString("genero"));
				misPelis.add(pelicula);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				dao.closeDB();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return misPelis;
	}

	public ArrayList<Pelicula> listPeliDirectorGenero(int id_dir, String genero) {
		misPelis = new ArrayList<Pelicula>();
		String query = "Select * from pelicula where id_dir=? and genero = ?";

		try {
			pstmt = dao.connectDB().prepareStatement(query);
			pstmt.setInt(1, id_dir);
			pstmt.setString(2, genero);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getString("titulo"), rs.getInt("id_dir"),
						rs.getString("pais"), rs.getString("duracion"), rs.getString("genero"));
				misPelis.add(pelicula);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				dao.closeDB();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return misPelis;
	}

	public Pelicula findPeliByTitulo(String titulo, Director d) {
		String query = "select * from pelicula where titulo = '" + titulo + "' and id_dir = " + d.getId();
		try {
			stmt = dao.connectDB().createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getString("titulo"), rs.getInt("id_dir"),
						rs.getString("pais"), rs.getString("duracion"), rs.getString("genero"));
			}
			rs.close();
			stmt.close();
			dao.closeDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pelicula;
	}

	public void deletePelicula(String titulo) {
		String query = "delete from pelicula where titulo = ?";
		try {
			pstmt = dao.connectDB().prepareStatement(query);
			pstmt.setString(1, titulo);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
