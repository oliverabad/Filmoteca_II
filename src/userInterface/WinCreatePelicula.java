package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

public class WinCreatePelicula extends JDialog {
	private JTextField textTitulo;
	private JTextField textPais;
	private JTextField textDuracion;
	private JTextField textGenero;
	private JComboBox comboBoxDirector;
	private String directores[];
	private WinMenu menu;
	PeliculaDAO peliDAO = new PeliculaDAO();
	DirectorDAO dirDAO = new DirectorDAO();
	GestionUI gestion = new GestionUI();
	Director director = new Director();

	public WinCreatePelicula(WinMenu menu) throws ClassNotFoundException, SQLException {
		this.menu = menu;
		getContentPane().setLayout(null);

		directores = gestion.cargarDirector();
		comboBoxDirector = new JComboBox();
		comboBoxDirector.setModel(new DefaultComboBoxModel(directores));
		comboBoxDirector.setBounds(192, 40, 180, 22);
		getContentPane().add(comboBoxDirector);

		JLabel lblSelecioneDirector = new JLabel("Selecione Director");
		lblSelecioneDirector.setBounds(66, 43, 114, 16);
		getContentPane().add(lblSelecioneDirector);

		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(66, 78, 56, 16);
		getContentPane().add(lblTtulo);

		JLabel lblPais = new JLabel("Pa\u00EDs");
		lblPais.setBounds(66, 113, 56, 16);
		getContentPane().add(lblPais);

		JLabel lblDuracion = new JLabel("Duraci\u00F3n");
		lblDuracion.setBounds(66, 148, 56, 16);
		getContentPane().add(lblDuracion);

		JLabel lblGenrero = new JLabel("G\u00E9nrero");
		lblGenrero.setBounds(66, 183, 56, 16);
		getContentPane().add(lblGenrero);

		textTitulo = new JTextField();
		textTitulo.setBounds(134, 75, 238, 22);
		getContentPane().add(textTitulo);
		textTitulo.setColumns(10);

		textPais = new JTextField();
		textPais.setBounds(134, 110, 238, 22);
		getContentPane().add(textPais);
		textPais.setColumns(10);

		textDuracion = new JTextField();
		textDuracion.setBounds(134, 145, 238, 22);
		getContentPane().add(textDuracion);
		textDuracion.setColumns(10);

		textGenero = new JTextField();
		textGenero.setBounds(134, 180, 238, 22);
		getContentPane().add(textGenero);
		textGenero.setColumns(10);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new BtnCreatePeliActionListener());
		btnCreate.setBounds(134, 215, 97, 25);
		getContentPane().add(btnCreate);

		this.setTitle("Create Pelicula");
		this.setModal(true);
		this.setSize(450, 300);
		this.setVisible(true);
	}

	public JTextField getTextTitulo() {
		return textTitulo;
	}

	public void setTextTitulo(JTextField textTitulo) {
		this.textTitulo = textTitulo;
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

	public JComboBox getComboBoxDirector() {
		return comboBoxDirector;
	}

	public void setComboBoxDirector(JComboBox comboBoxDirector) {
		this.comboBoxDirector = comboBoxDirector;
	}

	private class BtnCreatePeliActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			director = gestion.selectItemDirector(comboBoxDirector);
			Pelicula pelicula = new Pelicula();
			pelicula.setTitulo(textTitulo.getText());
			pelicula.setId_dir(director.getId());
			pelicula.setPais(textPais.getText());
			pelicula.setDuracion(textDuracion.getText());
			pelicula.setGenero(textGenero.getText());
			peliDAO.createPelicula(pelicula);
			dispose();
		}
	}

}
