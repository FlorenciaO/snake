package edu.unlam.tpa_GUI;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;
import edu.unlam.tpa_UTILES.ConfiguracionSala;
import edu.unlam.tpa_UTILES.Jugador;
import edu.unlam.tpa_UTILES.Sala;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class VentanaSala extends JFrame{
	

	private static final long serialVersionUID = -534624255468832225L;
	
	private JList<String> listaConectadosSala = new JList<String>();
	private String nombreSala;	
	private String ownerSala;
	private Cliente cli;

	public VentanaSala(Cliente cliente) {
		
		this.cli = cliente;

		this.nombreSala = cli.getPaqueteSala().getNombreSala();
		this.ownerSala = cli.getPaqueteSala().getOwnerSala();
		
		getContentPane().setLayout(null);
		setTitle("Esperando jugadores...");
		setBounds(100, 100, 450, 300);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (abrirVentanaConfirmaSalir()) {
					synchronized (cli) {
						PaqueteSala paqueteSala = new PaqueteSala(nombreSala, cli.getPaqueteUsuario().getUsername());
						cli.setPaqueteSala(paqueteSala);
						cli.setAccion(Comando.DESCONECTARDESALA);
						cli.notify();
					}
					dispose();
				}
			}
		});
		
		JScrollPane scrollPaneConectados = new JScrollPane();
		scrollPaneConectados.setViewportView(listaConectadosSala);
		listaConectadosSala.setForeground(Color.BLACK);
		listaConectadosSala.setBackground(Color.WHITE);
		
		listaConectadosSala.setBounds(321, 33, 103, 218);
		getContentPane().add(listaConectadosSala);
		
		JButton btnIniciarJuego = new JButton("Iniciar Juego");
		btnIniciarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Aparece la pantalla del juego
//				setVisible(false);
//				abrirVentanaJuego(configSala, nombreSala);
			}
		});
		btnIniciarJuego.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		btnIniciarJuego.setBounds(67, 81, 123, 35);
		getContentPane().add(btnIniciarJuego);
		
		JButton btnSalirSala = new JButton("Salir de la Sala");
		btnSalirSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (abrirVentanaConfirmaSalir()) {
					synchronized (cli) {
						PaqueteSala paqueteSala = new PaqueteSala(nombreSala, cli.getPaqueteUsuario().getUsername());
						cli.setPaqueteSala(paqueteSala);
						cli.setAccion(Comando.DESCONECTARDESALA);
						cli.notify();
					}
					dispose();	
				}
			}
		});
		btnSalirSala.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		btnSalirSala.setBounds(67, 148, 123, 35);
		getContentPane().add(btnSalirSala);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(533, 0, -9, 376);
		getContentPane().add(scrollPane);
		
		JLabel lblJugadores = new JLabel("Jugadores Conectados");
		lblJugadores.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblJugadores.setBounds(321, 8, 103, 14);
		getContentPane().add(lblJugadores);
		
		JLabel lblNewLabel = new JLabel("Sala: " + nombreSala);
		lblNewLabel.setBounds(10, 11, 236, 25);
		getContentPane().add(lblNewLabel);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public String getName() {
		return nombreSala;
	}

	public void setName(String name) {
		this.nombreSala = name;
	}
	
	public JList<String> getListaConectadosSala() {
		return listaConectadosSala;
	}
	public  void setListaConectadosSala(JList<String> listaConectadosSala) {
		this.listaConectadosSala = listaConectadosSala;
	}

	private boolean abrirVentanaConfirmaSalir() {
		int opcion = JOptionPane.showConfirmDialog(this, "¿Desea salir de la sala?", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}
	
	public void cambiarModelo(DefaultListModel<String> modelo) {
		this.listaConectadosSala.setModel(modelo);
	}
	
	public String getOwnerSala() {
		return ownerSala;
	}

	public void setOwnerSala(String ownerSala) {
		this.ownerSala = ownerSala;
	}
	
	public void eliminarConectados() {
		this.listaConectadosSala.removeAll();
	}
}
