package userInterface;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WinUpdateDirector extends JDialog {
	private WinMenu menu;
	private JTextField textNombre;
	private JTextField textField;
	private JComboBox comboBox;
	String[] directores;
	GestionUI gestion = new GestionUI();
	private JLabel lblIntroduzcaElNuevo;

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

		textField = new JTextField();
		textField.setBounds(139, 144, 209, 22);
		getContentPane().add(textField);
		textField.setColumns(10);

		directores = gestion.cargarDirector();
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(directores));
		comboBox.setBounds(187, 35, 161, 22);
		getContentPane().add(comboBox);

		JLabel lblSeleccioneDirector = new JLabel("Seleccione Director");
		lblSeleccioneDirector.setBounds(58, 38, 117, 16);
		getContentPane().add(lblSeleccioneDirector);

		lblIntroduzcaElNuevo = new JLabel("*Introduzca el nuevo Nombre y Apellidos del Director");
		lblIntroduzcaElNuevo.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblIntroduzcaElNuevo.setBounds(58, 78, 290, 16);
		getContentPane().add(lblIntroduzcaElNuevo);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(139, 179, 97, 25);
		getContentPane().add(btnUpdate);

		this.setTitle("Update Director");
		this.setModal(true);
		this.setSize(450, 300);
		this.setVisible(true);
	}
}
