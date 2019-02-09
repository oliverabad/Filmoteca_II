package entidades;

public class Pelicula {

	private int id_pelicula;
	private String titulo;
	private int id_dir;
	private String pais;
	private String duracion;
	private String genero;

	public Pelicula() {
		super();
	}

	public Pelicula(String titulo) {
		this.titulo = titulo;
	}

	public Pelicula(int id_pelicula, String titulo, int id_dir, String pais, String duracion, String genero) {
		super();
		this.id_pelicula = id_pelicula;
		this.titulo = titulo;
		this.id_dir = id_dir;
		this.pais = pais;
		this.duracion = duracion;
		this.genero = genero;
	}

	public Pelicula(String titulo, String pais, String duracion, String genero) {
		super();
		this.titulo = titulo;
		this.pais = pais;
		this.duracion = duracion;
		this.genero = genero;
	}

	public int getId_pelicula() {
		return id_pelicula;
	}

	public void setId_pelicula(int id_pelicula) {
		this.id_pelicula = id_pelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getId_dir() {
		return id_dir;
	}

	public void setId_dir(int id_dir) {
		this.id_dir = id_dir;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Pelicula [id_pelicula=" + id_pelicula + ", titulo=" + titulo + ", id_dir=" + id_dir + ", duracion="
				+ duracion + ", genero=" + genero + "]";
	}

}
