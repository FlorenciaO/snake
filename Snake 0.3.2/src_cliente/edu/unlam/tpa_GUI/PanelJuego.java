package edu.unlam.tpa_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import edu.unlam.tpa_PAINTER.FrutaPainter;
import edu.unlam.tpa_PAINTER.MapaPainter;
import edu.unlam.tpa_PAINTER.Posicion;
import edu.unlam.tpa_PAINTER.SnakePainter;
//import edu.unlam.tpa_UTILES.Cliente;
import edu.unlam.tpa_UTILES.HiloPartida;

public class PanelJuego extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4351177139576585872L;
	private BufferedImage buffer;

	private VentanaJuego ventanaJuego;
	private int width_height;
	private int tileSize;

	private HiloPartida servidor;

	private MapaPainter mapaPainter;
	private FrutaPainter frutaPainter;
	private SnakePainter snakePainter;
	
	private List<Posicion> frutas;
	private Map<Color, Posicion> snakes;

	public PanelJuego() {

		/**
		 * Tengo que recibir info del servidor ya
		 * row_column
		 * snakes
		 * frutas
		 */

		width_height = 500;
		setBounds(0, 0, width_height, width_height);
		
		int row_column = 50;
		tileSize = width_height / row_column;
		
		this.setFocusable(true);
		
		this.buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				enviarTeclaPresionada(arg0);
			}
		});

		this.mapaPainter = new MapaPainter(width_height, width_height, tileSize);
		this.frutaPainter = new FrutaPainter(tileSize, Color.GREEN);
		this.snakePainter = new SnakePainter(tileSize);
	}

	public void enviarTeclaPresionada(KeyEvent evento) {
		int aux = evento.getKeyCode();
		switch (aux) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_S:
		case KeyEvent.VK_D:
		case KeyEvent.VK_W:
			// enviar tecla
			
			break;
		}

	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) buffer.createGraphics();
		mapaPainter.paint(g2);		
		frutaPainter.paint(g2);
		snakePainter.paint(g2);
		g.drawImage(buffer, 0, 0, this);
	}

}
