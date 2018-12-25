package userInterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class WinMenu extends JFrame {

	private JButton btnCreateDirector;
	private JButton btnDeleteDirector;
	private JButton btnUpdateDirector;
	private JButton btnCreatePelicula;
	private JButton btnUpdatePelicula;

	public WinMenu() {
		addWindowListener(new ThisWindowListener());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblDirector = new JLabel("Director");
		lblDirector.setBounds(42, 158, 56, 16);
		getContentPane().add(lblDirector);

		JLabel lblPelicula = new JLabel("Película");
		lblPelicula.setBounds(298, 158, 56, 16);
		getContentPane().add(lblPelicula);

		btnCreateDirector = new JButton("Insertar Director");
		btnCreateDirector.addActionListener(new BtnCreateDirActionListener());
		btnCreateDirector.setBounds(42, 186, 140, 25);
		getContentPane().add(btnCreateDirector);

		btnCreatePelicula = new JButton("Insertar Película");
		btnCreatePelicula.addActionListener(new BtnCreatePeliculaActionListener());
		btnCreatePelicula.setBounds(298, 186, 140, 25);
		getContentPane().add(btnCreatePelicula);

		btnDeleteDirector = new JButton("Eliminar Director");
		btnDeleteDirector.addActionListener(new BtnEliminarDirActionListener());
		btnDeleteDirector.setBounds(42, 224, 140, 25);
		getContentPane().add(btnDeleteDirector);

		JButton btnEliminarPelicula = new JButton("Eliminar Película");
		btnEliminarPelicula.setBounds(298, 224, 140, 25);
		getContentPane().add(btnEliminarPelicula);

		btnUpdateDirector = new JButton("Modificar Director");
		btnUpdateDirector.addActionListener(new BtnUpdateDirActionListener());
		btnUpdateDirector.setBounds(42, 262, 140, 25);
		getContentPane().add(btnUpdateDirector);

		btnUpdatePelicula = new JButton("Modificar Película");
		btnUpdatePelicula.addActionListener(new BtnUpdatePeliculaActionListener());
		btnUpdatePelicula.setBounds(298, 262, 140, 25);
		getContentPane().add(btnUpdatePelicula);

		JLabel lblBienvenida = new JLabel("Bienvenidos a nuestra aplicaci\u00F3n FILMOTECA");
		lblBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBienvenida.setBounds(42, 13, 363, 16);
		getContentPane().add(lblBienvenida);

		JButton btnVerFilmoteca = new JButton("Ver Filmoteca");
		btnVerFilmoteca.setBounds(176, 79, 116, 25);
		getContentPane().add(btnVerFilmoteca);

		this.setTitle("Menu");
		this.setBounds(400, 200, 500, 400);
		setVisible(true);

	}

	private class BtnCreateDirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			WinCreateDirector createDir = new WinCreateDirector(WinMenu.this);
		}
	}

	private class BtnUpdateDirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			try {
				WinUpdateDirector upDir = new WinUpdateDirector(WinMenu.this);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class BtnEliminarDirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				WinDeleteDirector delDir = new WinDeleteDirector(WinMenu.this);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class BtnUpdatePeliculaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				WinUpdatePelicula wup = new WinUpdatePelicula(WinMenu.this);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			int opcion = JOptionPane.showConfirmDialog(WinMenu.this, "¿Quieres salir de la APLICACIÓN?");
			if (opcion == 0) {
				System.exit(DISPOSE_ON_CLOSE);
			}
		}
	}

	private class BtnCreatePeliculaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				WinCreatePelicula createPeli = new WinCreatePelicula(WinMenu.this);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
