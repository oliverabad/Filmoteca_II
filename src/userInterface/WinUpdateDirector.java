package userInterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.DirectorDAO;
import entidades.Director;

public class WinUpdateDirector extends JDialog {
	private WinMenu menu;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JComboBox comboBoxDirector;
	private JButton btnUpdate;
	DirectorDAO dirDAO = new DirectorDAO();
	GestionUI gestion = new GestionUI();
	String[] directores;
	Director director;

	public WinUpdateDirector(WinMenu menu) throws ClassNotFoundException, SQLException {
		this.menu = menu;
		getContentPane().setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(58, 107, 56, 16);
		getContentPane().add(lblNombre);

		textNombre = new JTextField();
		textNombre.setBounds(139, 104, 209, 22);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(58, 147, 56, 16);
		getContentPane().add(lblApellidos);

		textApellidos = new JTextField();
		textApellidos.setBounds(139, 144, 209, 22);
		getContentPane().add(textApellidos);
		textApellidos.setColumns(10);

		directores = gestion.cargarDirector();
		comboBoxDirector = new JComboBox();
		comboBoxDirector.setModel(new DefaultComboBoxModel(directores));
		comboBoxDirector.setBounds(187, 35, 161, 22);
		getContentPane().add(comboBoxDirector);

		JLabel lblSeleccioneDirector = new JLabel("Seleccione Director");
		lblSeleccioneDirector.setBounds(58, 38, 117, 16);
		getContentPane().add(lblSeleccioneDirector);

		JLabel lblIntroduzcaElNuevo = new JLabel("*Introduzca el nuevo Nombre y Apellidos del Director");
		lblIntroduzcaElNuevo.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblIntroduzcaElNuevo.setBounds(58, 78, 290, 16);
		getContentPane().add(lblIntroduzcaElNuevo);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new BtnUpdateDirActionListener());
		btnUpdate.setBounds(139, 179, 97, 25);
		getContentPane().add(btnUpdate);

		this.setTitle("Update Director");
		this.setModal(true);
		this.setSize(450, 300);
		this.setVisible(true);
	}

	private class BtnUpdateDirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			director = gestion.selectItemDirector(comboBoxDirector);
			director.setNombre(textNombre.getText());
			director.setApellidos(textApellidos.getText());
			if (textNombre.getText().isEmpty() || textApellidos.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "La actualización no puede contener campos nulos");
			} else {
				dirDAO.updateDirector(director);
				dispose();
			}
		}
	}
}
