package edu.unlam.tpa_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import edu.unlam.tpa_PAINTER.FrutaPainter;
import edu.unlam.tpa_PAINTER.MapaPainter;
import edu.unlam.tpa_PAINTER.SnakePainter;
//import edu.unlam.tpa_UTILES.Cliente;
import edu.unlam.tpa_UTILES.Servidor;

public class PanelJuego extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4351177139576585872L;
	private BufferedImage buffer;
	
	private VentanaJuego ventanaJuego;
	private int width_height;
	private int row_column;
	private int tileSize;
	
	private Servidor servidor; // Por ahora se comunica con el servidor directamente
//	private Cliente cliente; // El cliente que inicia el juego
	
	private MapaPainter mapaPainter;
	private FrutaPainter frutaPainter;
	private SnakePainter snakePainter;
	
	public PanelJuego(VentanaJuego frame, Servidor servidor) {
		
		width_height = 500;
		row_column = 50; // Cuantas filas y columnas
		tileSize = width_height / row_column; // Tiene que dar numero entero sino rompe todo
		
		this.setFocusable(true);
		setBounds(0, 0, width_height, width_height);
//		this.setSize(500 + tileSize, 500 + tileSize );
//		this.setPreferredSize(new Dimension(490 + tileSize, 490 + tileSize));
		this.ventanaJuego = frame;
		
		this.buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				guardarTeclaPresionada(arg0);
			}
		});
		
		this.servidor = servidor;
		this.servidor.setColumnas(row_column - 1);
		
		this.mapaPainter = new MapaPainter(width_height, width_height, tileSize);
		this.frutaPainter = new FrutaPainter(tileSize, Color.GREEN);
		this.snakePainter = new SnakePainter(tileSize);
	}
	
	
	public void guardarTeclaPresionada(KeyEvent evento) {
		int aux = evento.getKeyCode();
		switch(aux) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_S:
		case KeyEvent.VK_D:
		case KeyEvent.VK_W:
			servidor.enviarTecla(aux, 1);			
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_UP:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			servidor.enviarTecla(aux, 2);
			break;
		case KeyEvent.VK_R:
			init();
			ventanaJuego.getControlsPanel().setSnake1ScoreLabel("0");
			ventanaJuego.getControlsPanel().setSnake2ScoreLabel("0");
			break;
		}
		
	}

	public void init() {
		this.servidor.init();
	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) buffer.createGraphics();
		mapaPainter.paint(g2);
		
		if(servidor.enJuego()) {
			frutaPainter.paint(g2, this.servidor.obtenerFrutas());
			snakePainter.paint(g2, this.servidor.obtenerSnakes());
		}
		g.drawImage(buffer, 0, 0, this);
	}



}
