package edu.unlam.tpa_GUI;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class VentanaLooby extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1759792510152830837L;
	private JList<VentanaSala> listaSalas;
	DefaultListModel<VentanaSala> modelo;

	public VentanaLooby(Cliente cliente) {
		
		
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
		
		modelo = new DefaultListModel<VentanaSala>();
		this.listaSalas = new JList<VentanaSala>();
		this.listaSalas.setModel(modelo);
		this.listaSalas.setBounds(283, 36, 141, 211);
		
		JScrollPane scrollPane = new JScrollPane(this.listaSalas);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 269, 443, -524);
		getContentPane().add(scrollPane);
		
		getContentPane().add(this.listaSalas);
		
		
		JLabel lblSalasDisponibles = new JLabel("Salas Disponibles");
		lblSalasDisponibles.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSalasDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalasDisponibles.setBounds(283, 11, 141, 22);
		getContentPane().add(lblSalasDisponibles);
		
		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VentanaConfigurarSala(cliente);
			}
		});
		btnCrearSala.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		btnCrearSala.setBounds(62, 72, 124, 35);
		getContentPane().add(btnCrearSala);
		
		JButton btnIngresarASala = new JButton("Unirse a la Sala");
		btnIngresarASala.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		btnIngresarASala.setBounds(39, 136, 173, 35);
		getContentPane().add(btnIngresarASala);
		btnIngresarASala.setEnabled(false);
		
		JLabel lblBienvenida = new JLabel("Bienvenid@!");
		lblBienvenida.setBounds(24, 11, 203, 22);
		getContentPane().add(lblBienvenida);
		
		setLocationRelativeTo(null);
		
	}
	
	private boolean abrirVentanaConfirmaSalir() {
		int opcion = JOptionPane.showConfirmDialog(this, "Â¿Desea salir del Chat?", "ConfirmaciÃ³n",
				JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}	

}
