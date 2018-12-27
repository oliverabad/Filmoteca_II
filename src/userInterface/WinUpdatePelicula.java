package userInterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.DirectorDAO;
import dao.PeliculaDAO;
import entidades.Director;
import entidades.Pelicula;

public class WinUpdatePelicula extends JDialog {
	WinMenu menu;
	private JTextField textPais;
	private JTextField textDuracion;
	private JTextField textGenero;
	private JButton btnUpdate;
	private JLabel lblSelectTitulo;
	private JComboBox comboBoxDirector;
	private JComboBox comboBoxPelicula;
	String[] directores;
	String[] peliculas;
	GestionUI gestion = new GestionUI();
	Director director;
	Pelicula pelicula;
	PeliculaDAO peliDAO = new PeliculaDAO();
	DirectorDAO dirDAO = new DirectorDAO();
	ArrayList<Pelicula> listPeli = new ArrayList<Pelicula>();
	ArrayList<Director> misDirectores = new ArrayList<Director>();
	private JTextField textTitulo;

	public WinUpdatePelicula(WinMenu menu) throws ClassNotFoundException, SQLException {
		this.menu = menu;
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().setLayout(null);

		comboBoxPelicula = new JComboBox();
		comboBoxDirector = new JComboBox();
		directores = gestion.cargarDirector();
		comboBoxDirector.setModel(new DefaultComboBoxModel(directores));
		director = gestion.selectItemDirector(comboBoxDirector);
		peliculas = gestion.cargarPeliculaDirector(director.getId());
		comboBoxPelicula.setModel(new DefaultComboBoxModel(peliculas));

		comboBoxDirector.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				dirPeliItemStateChange(evt);
			}
		});
		comboBoxDirector.setBounds(198, 23, 208, 22);
		getContentPane().add(comboBoxDirector);

		comboBoxPelicula.setBounds(198, 58, 208, 22);
		getContentPane().add(comboBoxPelicula);

		JLabel lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setBounds(45, 90, 56, 16);
		getContentPane().add(lblTitulo);

		JLabel lblSeleccioneDirector = new JLabel("Seleccione Director ");
		lblSeleccioneDirector.setBounds(45, 26, 138, 16);
		getContentPane().add(lblSeleccioneDirector);

		JLabel lblPas = new JLabel("Pa\u00EDs");
		lblPas.setBounds(45, 119, 56, 16);
		getContentPane().add(lblPas);

		JLabel lblDuracin = new JLabel("Duraci\u00F3n");
		lblDuracin.setBounds(45, 151, 56, 16);
		getContentPane().add(lblDuracin);

		JLabel lblGnero = new JLabel("G\u00E9nero");
		lblGnero.setBounds(45, 183, 56, 16);
		getContentPane().add(lblGnero);

		textTitulo = new JTextField();
		textTitulo.setBounds(100, 84, 306, 22);
		getContentPane().add(textTitulo);
		textTitulo.setColumns(10);

		textPais = new JTextField();
		textPais.setBounds(100, 116, 306, 22);
		getContentPane().add(textPais);
		textPais.setColumns(10);

		textDuracion = new JTextField();
		textDuracion.setBounds(100, 148, 306, 22);
		getContentPane().add(textDuracion);
		textDuracion.setColumns(10);

		textGenero = new JTextField();
		textGenero.setBounds(100, 180, 306, 22);
		getContentPane().add(textGenero);
		textGenero.setColumns(10);

		lblSelectTitulo = new JLabel("Seleccione T\u00EDtulo Pel\u00EDcula");
		lblSelectTitulo.setBounds(45, 61, 151, 16);
		getContentPane().add(lblSelectTitulo);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new BtnUpdateActionListener());
		btnUpdate.setBounds(198, 215, 97, 25);
		getContentPane().add(btnUpdate);

		this.setTitle("Update Película");
		this.setModal(true);
		this.setSize(450, 300);
		this.setVisible(true);
	}

	public JTextField getTextPais() {
		return textPais;
	}

	public void setTextPais(JTextField textPais) {
		this.textPais = textPais;
	}

	public JTextField getTextDuracion() {
		return textDuracion;
	}

	public void setTextDuracion(JTextField textDuracion) {
		this.textDuracion = textDuracion;
	}

	public JTextField getTextGenero() {
		return textGenero;
	}

	public void setTextGenero(JTextField textGenero) {
		this.textGenero = textGenero;
	}

	private class BtnUpdateActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			director = gestion.selectItemDirector(comboBoxDirector);
			String titulo = gestion.selectItem(comboBoxPelicula);
			pelicula = peliDAO.findPeliByTitulo(titulo, director);
			pelicula.setTitulo(textTitulo.getText());
			pelicula.setPais(textPais.getText());
			pelicula.setDuracion(textDuracion.getText());
			pelicula.setGenero(textGenero.getText());
			peliDAO.UpdatePelicula(pelicula);
			dispose();
		}
	}

	private void dirPeliItemStateChange(java.awt.event.ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			director = gestion.selectItemDirector(comboBoxDirector);
			peliculas = gestion.cargarPeliculaDirector(director.getId());
			this.comboBoxPelicula.setModel(new DefaultComboBoxModel(peliculas));

		}
	}
}
