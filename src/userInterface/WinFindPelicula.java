package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.DirectorDAO;
import dao.PeliculaDAO;
import entidades.Director;
import entidades.Pelicula;

public class WinFindPelicula extends JDialog {
	WinMenu menu;
	GestionUI gestion = new GestionUI();
	private JTable tblPelis;
	private JComboBox comboBoxDirector;
	private JComboBox comboBoxGenero;
	private JScrollPane scrollPane;
	private JButton btnSearch;
	String[] generos;
	String[] directores;
	ArrayList<Pelicula> misPelis;
	ArrayList<String> datosPelis;
	PeliculaDAO peliDAO = new PeliculaDAO();
	DirectorDAO dirDAO = new DirectorDAO();
	Director directorItem;
	String generoItem;

	public WinFindPelicula(WinMenu menu) {
		this.menu = menu;
		getContentPane().setLayout(null);

		JLabel lblSeleccionarDirector = new JLabel("Seleccionar Director");
		lblSeleccionarDirector.setBounds(22, 52, 218, 16);
		getContentPane().add(lblSeleccionarDirector);

		JLabel lblSeleccionarGneroPelcula = new JLabel("Seleccionar g\u00E9nero Pel\u00EDcula");
		lblSeleccionarGneroPelcula.setBounds(22, 106, 167, 16);
		getContentPane().add(lblSeleccionarGneroPelcula);

		comboBoxDirector = new JComboBox();
		directores = gestion.cargarDirector();
		comboBoxDirector.setModel(new DefaultComboBoxModel(directores));
		comboBoxDirector.setSelectedIndex(-1);
		comboBoxDirector.setBounds(22, 71, 268, 22);
		getContentPane().add(comboBoxDirector);

		comboBoxGenero = new JComboBox();
		generos = gestion.cargarGeneroPeli();
		comboBoxGenero.setModel(new DefaultComboBoxModel(generos));
		comboBoxGenero.setSelectedIndex(-1);
		comboBoxGenero.setBounds(22, 127, 268, 22);
		getContentPane().add(comboBoxGenero);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new BtnSearchPeliActionListener());
		btnSearch.setBounds(92, 175, 97, 25);
		getContentPane().add(btnSearch);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(302, 56, 409, 160);
		getContentPane().add(scrollPane);

		this.setTitle("Find Película");
		this.setModal(true);
		this.setSize(741, 340);
		this.setVisible(true);
	}

	private class BtnSearchPeliActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (comboBoxDirector.getSelectedIndex() == -1 & comboBoxGenero.getSelectedIndex() == -1) {
				tblPelis = new JTable();
				misPelis = peliDAO.listPeliculas();
				gestion.construirTabla(tblPelis, scrollPane, gestion.cargarDatosJTable(misPelis));

			} else if (comboBoxDirector.getSelectedIndex() != -1 & comboBoxGenero.getSelectedIndex() == -1) {
				directorItem = gestion.selectItemDirector(comboBoxDirector);
				tblPelis = new JTable();
				misPelis = peliDAO.listPeliDirector(directorItem.getId());
				gestion.construirTabla(tblPelis, scrollPane, gestion.cargarDatosJTable(misPelis));
			} else if (comboBoxDirector.getSelectedIndex() == -1 & comboBoxGenero.getSelectedIndex() != -1) {
				generoItem = gestion.selectItem(comboBoxGenero);
				tblPelis = new JTable();
				misPelis = peliDAO.listPeliGenero(generoItem);
				gestion.construirTabla(tblPelis, scrollPane, gestion.cargarDatosJTable(misPelis));
			} else if (comboBoxDirector.getSelectedIndex() != -1 & comboBoxGenero.getSelectedIndex() != -1) {
				directorItem = gestion.selectItemDirector(comboBoxDirector);
				generoItem = gestion.selectItem(comboBoxGenero);
				tblPelis = new JTable();
				misPelis = peliDAO.listPeliDirectorGenero(directorItem.getId(), generoItem);
				gestion.construirTabla(tblPelis, scrollPane, gestion.cargarDatosJTable(misPelis));
			} else {
				JOptionPane.showMessageDialog(null, "Error al cargar datos!!!");
			}
			comboBoxDirector.setSelectedIndex(-1);
			comboBoxGenero.setSelectedIndex(-1);
		}
	}
}
