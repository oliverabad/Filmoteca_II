package userInterface;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.DirectorDAO;
import dao.PeliculaDAO;
import entidades.Director;
import entidades.Pelicula;

public class GestionUI {
	DirectorDAO dirDAO = new DirectorDAO();
	Director director;
	PeliculaDAO peliDAO = new PeliculaDAO();
	Pelicula pelicula;
	ArrayList<Pelicula> misPeliculas;
	String[] peliculas;
	String[][] matrizPelis;

	public String[] cargarDirector() {
		ArrayList<Director> misDirectores = dirDAO.listDirector();
		String[] directores = new String[misDirectores.size()];
		for (int i = 0; i < directores.length; i++) {
			directores[i] = misDirectores.get(i).toString();
		}
		return directores;
	}

	public String[] cargarPeliculaDirector(int id_dir) {
		misPeliculas = peliDAO.listPeliDirectorTitulo(id_dir);
		peliculas = new String[misPeliculas.size()];
		for (int i = 0; i < peliculas.length; i++) {
			peliculas[i] = misPeliculas.get(i).getTitulo();
		}
		return peliculas;
	}

	public String[] cargarPelicula() {
		misPeliculas = peliDAO.listPeliculas();
		peliculas = new String[misPeliculas.size()];
		for (int i = 0; i < peliculas.length; i++) {
			peliculas[i] = misPeliculas.get(i).getTitulo();
		}
		return peliculas;
	}

	public String[] cargarGeneroPeli() {
		String[] genero = { "Comedia", "Comedia-Dramática", "Thriller", "Thriller-SCI-FI", "SCI-FI", "Western",
				"Drama-Crimen", "Drama-Basado en hechos reales" };
		return genero;
	}

	public Director selectItemDirector(JComboBox jcb) {
		String datos = (String) jcb.getSelectedItem();
		String[] list = datos.split("_");
		String nom = list[0];
		String apellidos = list[1];
		director = dirDAO.findDirector(nom, apellidos);
		return director;
	}

	public String selectItem(JComboBox jcb) {
		String dato = (String) jcb.getSelectedItem();
		return dato;
	}

	public void construirTabla(JTable miTabla, JScrollPane miBarra, String[][] matrizDAO) {
		String datos[] = { "id_pelicula", "T\u00EDtulo", "id_dir", "Pa\u00EDs", "Duraci\u00F3n", "G\u00E9nero" };
		matrizPelis = matrizDAO;

		miTabla = new JTable(matrizPelis, datos);
		miBarra.setViewportView(miTabla);
	}

	public String[][] cargarDatosJTable(ArrayList<Pelicula> listPeliculasDAO) {
		misPeliculas = listPeliculasDAO;
		matrizPelis = new String[misPeliculas.size()][6];
		for (int i = 0; i < misPeliculas.size(); i++) {
			matrizPelis[i][0] = misPeliculas.get(i).getId_pelicula() + "";
			matrizPelis[i][1] = misPeliculas.get(i).getTitulo() + "";
			matrizPelis[i][2] = misPeliculas.get(i).getId_dir() + "";
			matrizPelis[i][3] = misPeliculas.get(i).getPais() + "";
			matrizPelis[i][4] = misPeliculas.get(i).getDuracion() + "";
			matrizPelis[i][5] = misPeliculas.get(i).getGenero() + "";
		}
		return matrizPelis;
	}
}
