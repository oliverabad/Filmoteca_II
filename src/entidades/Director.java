package entidades;

import java.util.ArrayList;

public class Director {
	private int id_director;
	private String nombre;
	private String apellidos;
	private ArrayList<Pelicula> misPeliculas;

	public Director() {
		super();
	}

	public Director(int id_director, String nombre, String apellidos) {
		super();
		this.id_director = id_director;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public Director(String nombre, String apellidos) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public int getId() {
		return id_director;
	}

	public void setId(int id) {
		this.id_director = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public ArrayList<Pelicula> getMisPeliculas() {
		return misPeliculas;
	}

	public void setMisPeliculas(ArrayList<Pelicula> misPeliculas) {
		this.misPeliculas = misPeliculas;
	}

	@Override
	public String toString() {
		return nombre + "_" + apellidos;
	}

}
