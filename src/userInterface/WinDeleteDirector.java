package userInterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.DirectorDAO;
import dao.PeliculaDAO;
import entidades.Director;
import entidades.Pelicula;

public class WinDeleteDirector extends JDialog {
	private WinMenu menu;
	private JComboBox comboBoxDirectores;
	private String[] directores;
	private JButton btnDelete;
	Director director;
	DirectorDAO dirDAO = new DirectorDAO();
	PeliculaDAO peliDAO = new PeliculaDAO();
	GestionUI gestion = new GestionUI();
	ArrayList<Pelicula> misPeliculas;

	public WinDeleteDirector(WinMenu menu) throws ClassNotFoundException, SQLException {
		this.menu = menu;
		getContentPane().setLayout(null);

		JLabel lblBorrarUnDirector = new JLabel("Eliminar Director de nuestra FILMOTECA");
		lblBorrarUnDirector.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBorrarUnDirector.setBounds(12, 13, 408, 16);
		getContentPane().add(lblBorrarUnDirector);

		directores = gestion.cargarDirector();
		comboBoxDirectores = new JComboBox();
		comboBoxDirectores.setModel(new DefaultComboBoxModel(directores));
		comboBoxDirectores.setBounds(127, 87, 265, 22);
		getContentPane().add(comboBoxDirectores);

		JLabel lblSeleccioneElDirector = new JLabel("Seleccione Director");
		lblSeleccioneElDirector.setBounds(12, 90, 117, 16);
		getContentPane().add(lblSeleccioneElDirector);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new BtnDeleteActionListener());
		btnDelete.setBounds(127, 122, 97, 25);
		getContentPane().add(btnDelete);

		JLabel lblInfo = new JLabel("*Recuerda que el director a eliminar no tenga pel\u00EDculas asociadas .");
		lblInfo.setFont(new Font("Arial", Font.ITALIC, 13));
		lblInfo.setBounds(12, 61, 408, 16);
		getContentPane().add(lblInfo);

		this.setTitle("Eliminar Director");
		this.setModal(true);
		this.setSize(450, 300);
		this.setVisible(true);

	}

	private class BtnDeleteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			director = gestion.selectItemDirector(comboBoxDirectores);
			misPeliculas = peliDAO.listPeliDirector(director.getId());
			if (misPeliculas == null || misPeliculas.isEmpty()) {
				dirDAO.deleteDirector(director);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Director no se puede borrar de la base de datos");
			}

		}
	}
}
