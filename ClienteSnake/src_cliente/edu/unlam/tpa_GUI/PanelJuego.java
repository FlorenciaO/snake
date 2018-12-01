package edu.unlam.tpa_GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAINTER.FrutaPainter;
import edu.unlam.tpa_PAINTER.MapaPainter;
import edu.unlam.tpa_PAINTER.SnakePainter;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteTecla;

public class PanelJuego extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4351177139576585872L;
	private BufferedImage buffer;

	
	private int width_height;
	private int tileSize;
	

	private MapaPainter mapaPainter;
	private FrutaPainter frutaPainter;
	private SnakePainter snakePainter;
	
	private Cliente cliente;

	public PanelJuego(Cliente cliente) {

		/**
		 * Tengo que recibir info del servidor ya
		 * row_column
		 * snakes
		 * frutas
		 */
		this.cliente = cliente;
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
			synchronized (cliente) {
				PaqueteTecla paqueteTecla = new PaqueteTecla(aux, cliente.getPaqueteUsuario().getUsername());
				cliente.setPaqueteTecla(paqueteTecla);
				cliente.setAccion(Comando.ENVIARTECLA);
				cliente.notify();
			}			
			break;
		}

	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) buffer.createGraphics();
		mapaPainter.paint(g2);		
		frutaPainter.paint(g2, cliente.getPaquetePartida().getFrutas());
		snakePainter.paint(g2, cliente.getPaquetePartida().getSnakes());
		g.drawImage(buffer, 0, 0, this);
	}

}
