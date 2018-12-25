package userInterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.DirectorDAO;
import entidades.Director;

public class WinCreateDirector extends JDialog {
	private WinMenu menu;
	private JTextField textNom;
	private JTextField textApellidos;
	private JButton btnCrear;
	DirectorDAO dirDAO = new DirectorDAO();
	Director director = new Director();

	public WinCreateDirector(WinMenu menu) {
		this.menu = menu;
		getContentPane().setLayout(null);

		JLabel lblCrearDirector = new JLabel("Añadir un nuevo Director a nuestra FILMOTECA");
		lblCrearDirector.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCrearDirector.setBounds(12, 34, 408, 16);
		getContentPane().add(lblCrearDirector);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(95, 88, 56, 16);
		getContentPane().add(lblNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(95, 130, 56, 16);
		getContentPane().add(lblApellidos);

		textNom = new JTextField();
		textNom.setBounds(163, 85, 234, 22);
		getContentPane().add(textNom);
		textNom.setColumns(10);

		textApellidos = new JTextField();
		textApellidos.setBounds(163, 127, 234, 22);
		getContentPane().add(textApellidos);
		textApellidos.setColumns(10);

		btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(new BtnCreateActionListener());
		btnCrear.setBounds(163, 169, 97, 25);
		getContentPane().add(btnCrear);

		this.setTitle("Crear Director");
		this.setModal(true);
		this.setSize(450, 300);
		this.setVisible(true);

	}

	public JTextField getTextNom() {
		return textNom;
	}

	public void setTextNom(JTextField textNom) {
		this.textNom = textNom;
	}

	public JTextField getTextApellidos() {
		return textApellidos;
	}

	public void setTextApellidos(JTextField textApellidos) {
		this.textApellidos = textApellidos;
	}

	private class BtnCreateActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			try {
				Director dir = new Director();
				dir = dirDAO.findDirector(textNom.getText(), textApellidos.getText());
				if (dir != null) {
					JOptionPane.showMessageDialog(WinCreateDirector.this,
							"El Director ya se encuentra en nuestra Base de Datos");
				} else if (textNom.getText().isEmpty() || textApellidos.getText().isEmpty()) {
					JOptionPane.showMessageDialog(WinCreateDirector.this,
							"Debes introducir Nombre y Apellidos del Director");
				} else {
					director.setNombre(textNom.getText());
					director.setApellidos(textApellidos.getText());
					dirDAO.createDirector(director);
					dispose();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
}
