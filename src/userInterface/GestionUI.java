package userInterface;

import java.util.ArrayList;

import javax.swing.JComboBox;

import dao.DirectorDAO;
import dao.PeliculaDAO;
import entidades.Director;
import entidades.Pelicula;

public class GestionUI {
	DirectorDAO dirDAO = new DirectorDAO();
	Director director;
	PeliculaDAO peliDAO = new PeliculaDAO();
	Pelicula pelicula;

	public String[] cargarDirector() {
		ArrayList<Director> misDirectores = dirDAO.listDirector();
		String[] directores = new String[misDirectores.size()];
		for (int i = 0; i < directores.length; i++) {
			directores[i] = misDirectores.get(i).toString();
		}
		return directores;
	}

	public String[] cargarPelicula(int id_dir) {
		ArrayList<Pelicula> misPeliculas = peliDAO.listPeliDirector(id_dir);
		String[] peliculas = new String[misPeliculas.size()];
		for (int i = 0; i < peliculas.length; i++) {
			peliculas[i] = misPeliculas.get(i).getTitulo();
		}
		return peliculas;
	}

	public Director selectItemDirector(JComboBox jcb) {
		String datos = (String) jcb.getSelectedItem();
		String[] list = datos.split("_");
		String nom = list[0];
		String apellidos = list[1];
		director = dirDAO.findDirector(nom, apellidos);
		return director;
	}

	public String selectItemDirPeli(JComboBox jcb) {
		String titulo = (String) jcb.getSelectedItem();
		return titulo;
	}
}
