package edu.unlam.tpa_GUI;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_UTILES.Jugador;

public class PanelDePuntajes extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2361773272360190852L;
	
	private Cliente cliente;
	private JLabel lblPuntajes = new JLabel();
	
	private ArrayList<JLabel> labelsPuntajes;
	
	private int width = 200;
	private int height = 500;
	
	
	public PanelDePuntajes(Cliente cliente){
		this.cliente = cliente;
		setBounds(500, 0, width, height);
		this.setBackground(Color.DARK_GRAY);
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		this.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		this.setLayout(null);
		
		Font font = new Font("Tahoma", Font.PLAIN, 24);
		
		lblPuntajes.setText("PUNTAJES");
		lblPuntajes.setFont(font);
		lblPuntajes.setForeground(Color.LIGHT_GRAY);
		lblPuntajes.setBounds(46, 15, 114, 20);
		this.add(lblPuntajes);
		
		labelsPuntajes = new ArrayList<>();
		int indice = 0;
		int y = 50;
		for(Jugador jugador: cliente.getPaquetePartida().getJugadores()) {
			labelsPuntajes.add(new JLabel(jugador.getUserName() + " " + jugador.getPuntos()));
			labelsPuntajes.get(indice).setFont(font);
			labelsPuntajes.get(indice).setForeground(VentanaJuego.obtenerColores().get(jugador.getColor()));
			labelsPuntajes.get(indice).setBounds(30, y, 100, 20);
			this.add(labelsPuntajes.get(indice));
			indice++;
			y += 30;
		}
		
	}
	
	public void actualizarPuntajes() {
		int indice = 0;
		for(Jugador jugador: cliente.getPaquetePartida().getJugadores()) {
			labelsPuntajes.get(indice++).setText(jugador.getUserName() + " " + jugador.getPuntos());
		}
	}

}
