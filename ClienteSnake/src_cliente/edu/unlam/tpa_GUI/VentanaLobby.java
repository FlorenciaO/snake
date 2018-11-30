package edu.unlam.tpa_GUI;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class VentanaLobby extends JFrame{
	
	private static final long serialVersionUID = 1759792510152830837L;
	
	private String user = null;
	private Cliente cliente;

	private static JList<String> listaUsuariosChatGeneral = new JList<String>();
	private static JList<String> listaSalas = new JList<String>();
	private static SimpleAttributeSet attrs = new SimpleAttributeSet();

	public VentanaLobby(Cliente cliente) {
		
		this.cliente = cliente;
		this.user = cliente.getPaqueteUsuario().getUsername();
		setTitle("¿Dónde quieres jugar hoy?");
		setResizable(false);
		setForeground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setForeground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (abrirVentanaConfirmaSalir()) {
					synchronized (cliente) {
						cliente.setAccion(Comando.DESCONECTAR);
						cliente.notify();
					}
					dispose();
				}
			}
		});
		
		listaSalas.setBounds(283, 36, 141, 211);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 269, 443, -524);
		scrollPane.setViewportView(listaSalas);
		listaSalas.setForeground(Color.BLACK);
		listaSalas.setBackground(Color.WHITE);
		getContentPane().add(scrollPane);
		
		getContentPane().add(this.listaSalas);
		

		listaSalas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					if (listaSalas.getSelectedValue() != null) {
						if (!cliente.getSalasActivas().containsKey(listaSalas.getSelectedValue())) {
							PaqueteSala paqueteSala = new PaqueteSala(listaSalas.getSelectedValue(),cliente.getPaqueteUsuario().getUsername());
							cliente.setPaqueteSala(paqueteSala);
							synchronized (cliente) {
								cliente.setAccion(Comando.ENTRARSALA);
								cliente.notify();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Ya se encuentra en esta sala.");
						}
					}
				}
			}
		});
		
		
		JLabel lblSalasDisponibles = new JLabel("Salas Disponibles");
		lblSalasDisponibles.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSalasDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalasDisponibles.setBounds(283, 11, 141, 22);
		getContentPane().add(lblSalasDisponibles);
		
		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirVentanaConfiguracion();
			}
		});
		btnCrearSala.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		btnCrearSala.setBounds(65, 113, 124, 35);
		getContentPane().add(btnCrearSala);
		
		JLabel lblBienvenida = new JLabel("Bienvenidx " + this.user + "!");
		lblBienvenida.setBounds(24, 11, 203, 22);
		getContentPane().add(lblBienvenida);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}


	private void abrirVentanaConfiguracion() {
		new VentanaConfigurarSala(cliente);
	}
	
	private boolean abrirVentanaConfirmaSalir() {
		int opcion = JOptionPane.showConfirmDialog(this, "¿Desea salir del Lobby?", "Confirmacion",
				JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}
	

	public static JList<String> getListConectados() {
		return listaUsuariosChatGeneral;
	}

	public static JList<String> getListSalas() {
		return listaSalas;
	}

	public static void eliminarSalas() {
		listaSalas.removeAll();		
	}

	public static void cambiarModeloSalas(DefaultListModel<String> modelo) {
		listaSalas.setModel(modelo);		
	}
	
	public static void eliminarConectados() {
		listaUsuariosChatGeneral.removeAll();
	}
}
