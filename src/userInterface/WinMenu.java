package userInterface;

import java.awt.Color;
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
	private JButton btnDeletePelicula;
	private JButton btnFindFilmoteca;

	public WinMenu() {
		getContentPane().setBackground(Color.ORANGE);
		addWindowListener(new ThisWindowListener());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblDirector = new JLabel("Director");
		lblDirector.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDirector.setBounds(42, 77, 96, 16);
		getContentPane().add(lblDirector);

		JLabel lblPelicula = new JLabel("Película");
		lblPelicula.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPelicula.setBounds(298, 77, 96, 16);
		getContentPane().add(lblPelicula);

		btnCreateDirector = new JButton("Insertar Director");
		btnCreateDirector.addActionListener(new BtnCreateDirActionListener());
		btnCreateDirector.setBounds(42, 106, 140, 25);
		getContentPane().add(btnCreateDirector);

		btnCreatePelicula = new JButton("Insertar Película");
		btnCreatePelicula.addActionListener(new BtnCreatePeliculaActionListener());
		btnCreatePelicula.setBounds(298, 106, 140, 25);
		getContentPane().add(btnCreatePelicula);

		btnDeleteDirector = new JButton("Eliminar Director");
		btnDeleteDirector.addActionListener(new BtnDeleteDirActionListener());
		btnDeleteDirector.setBounds(42, 144, 140, 25);
		getContentPane().add(btnDeleteDirector);

		btnDeletePelicula = new JButton("Eliminar Película");
		btnDeletePelicula.addActionListener(new BtnDeletePeliculaActionListener());
		btnDeletePelicula.setBounds(298, 144, 140, 25);
		getContentPane().add(btnDeletePelicula);

		btnUpdateDirector = new JButton("Modificar Director");
		btnUpdateDirector.addActionListener(new BtnUpdateDirActionListener());
		btnUpdateDirector.setBounds(42, 182, 140, 25);
		getContentPane().add(btnUpdateDirector);

		btnUpdatePelicula = new JButton("Modificar Película");
		btnUpdatePelicula.addActionListener(new BtnUpdatePeliculaActionListener());
		btnUpdatePelicula.setBounds(298, 182, 140, 25);
		getContentPane().add(btnUpdatePelicula);

		btnFindFilmoteca = new JButton("Buscar Película");
		btnFindFilmoteca.addActionListener(new BtnFindFilmotecaActionListener());
		btnFindFilmoteca.setBounds(173, 255, 126, 25);
		getContentPane().add(btnFindFilmoteca);

		JLabel lblBienvenida = new JLabel("Bienvenidos a nuestra aplicaci\u00F3n FILMOTECA");
		lblBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBienvenida.setBounds(42, 13, 363, 16);
		getContentPane().add(lblBienvenida);

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

	private class BtnDeletePeliculaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			WinDeletePelicula deletePeli = new WinDeletePelicula(WinMenu.this);
		}
	}

	private class BtnDeleteDirActionListener implements ActionListener {
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

	private class BtnCreatePeliculaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				WinCreatePelicula createPeli = new WinCreatePelicula(WinMenu.this);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class BtnFindFilmotecaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			WinFindPelicula wfp = new WinFindPelicula(WinMenu.this);
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
}
