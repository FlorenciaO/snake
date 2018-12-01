package edu.unlam.tpa_GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingConstants;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;
import edu.unlam.tpa_UTILES.Jugador;

public class VentanaPartidaFinalizada extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5528586680251020865L;
	
	public VentanaPartidaFinalizada(Jugador ganador, Cliente cliente) {
		setTitle("Partida Finalizada");
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblGanador = new JLabel("GANADOR");
		lblGanador.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanador.setFont(new Font("Comic Sans MS", Font.BOLD, 21));
		lblGanador.setBounds(82, 21, 146, 31);
		getContentPane().add(lblGanador);
		
		JLabel lblNewLabel = new JLabel(ganador.getNombreUsuario() + " " + ganador.getPuntos() + " puntos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
		lblNewLabel.setBounds(29, 63, 251, 56);
		getContentPane().add(lblNewLabel);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
					synchronized (cliente) {
						PaqueteSala paqueteSala = new PaqueteSala(cliente.getPaqueteSala().getNombreSala(), cliente.getPaqueteUsuario().getUsername());
						cliente.setPaqueteSala(paqueteSala);
						cliente.setAccion(Comando.ELIMINARSALA);
						cliente.notify();
					}
					dispose();
					// Esto deberia llevarte al lobby, todavia no lo pense como
			}
		});
		
		setBounds(0, 0, 319, 193);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
