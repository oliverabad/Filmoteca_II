package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import dao.PeliculaDAO;
import entidades.Director;
import entidades.Pelicula;

public class WinDeletePelicula extends JDialog {
	WinMenu menu;
	private JComboBox comboBoxPelicula;
	private JComboBox comboBoxDirector;
	private JLabel lblSelectDirector;
	private JLabel lblSelectPeli;
	private JButton btnDelete;
	GestionUI gestion = new GestionUI();
	PeliculaDAO peliDAO = new PeliculaDAO();
	Pelicula pelicula;
	String[] peliculas;
	String[] directores;
	Director director;

	public WinDeletePelicula(WinMenu menu) {
		this.menu = menu;
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
		comboBoxDirector.setBounds(93, 53, 235, 22);
		getContentPane().add(comboBoxDirector);

		comboBoxPelicula.setBounds(93, 110, 235, 22);
		getContentPane().add(comboBoxPelicula);

		lblSelectPeli = new JLabel("Seleccione la Pel\u00EDcula que desea eliminar");
		lblSelectPeli.setBounds(93, 88, 235, 16);
		getContentPane().add(lblSelectPeli);

		lblSelectDirector = new JLabel("Seleccione Director");
		lblSelectDirector.setBounds(93, 30, 235, 16);
		getContentPane().add(lblSelectDirector);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new BtnDeltePeliActionListener());
		btnDelete.setBounds(161, 164, 97, 25);
		getContentPane().add(btnDelete);

		this.setTitle("Delete Película");
		this.setModal(true);
		this.setSize(450, 300);
		this.setVisible(true);
	}

	private class BtnDeltePeliActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String titulo = gestion.selectItem(comboBoxPelicula);
			peliDAO.deletePelicula(titulo);
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
