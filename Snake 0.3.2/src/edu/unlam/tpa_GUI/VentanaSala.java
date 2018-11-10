package edu.unlam.tpa_GUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import edu.unlam.tpa_UTILES.ConfiguracionSala;
import edu.unlam.tpa_UTILES.Sala;

import javax.swing.JList;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class VentanaSala extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -534624255468832225L;
	
	private VentanaConfigurarSala vConfiguracion;

	public VentanaSala(VentanaConfigurarSala configuracion, ConfiguracionSala configSala, String nombreSala) {
		this.vConfiguracion = configuracion;
		
		getContentPane().setLayout(null);
		setTitle("Esperando jugadores...");
		setBounds(100, 100, 450, 300);
		JList listaUsuarios = new JList(); // La lista sera de usuarios, se creara la clase usuario, junto con la implementacion
											// de cliente/servidor
		listaUsuarios.setBounds(321, 33, 103, 218);
		getContentPane().add(listaUsuarios);
		
		JButton btnIniciarJuego = new JButton("Iniciar Juego");
		btnIniciarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Aparece la pantalla del juego
				setVisible(false);
				abrirVentanaJuego(configSala, nombreSala);
			}
		});
		btnIniciarJuego.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		btnIniciarJuego.setBounds(67, 81, 123, 35);
		getContentPane().add(btnIniciarJuego);
		
		JButton btnSalirSala = new JButton("Salir de la Sala");
		btnSalirSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverAlLooby();
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
		
		JLabel lblNewLabel = new JLabel("Sala: " + this.vConfiguracion.obtenerNombreSala());
		lblNewLabel.setBounds(10, 11, 236, 25);
		getContentPane().add(lblNewLabel);
		setLocationRelativeTo(null);
	}
	
	private void abrirVentanaJuego(ConfiguracionSala config, String nombre) {
		Sala nuevaSala = new Sala(config, nombre);
		new VentanaJuego(nuevaSala);
	}
	
	private void volverAlLooby() {
		setVisible(false);
		this.vConfiguracion.volverAlLooby();
	}
}
