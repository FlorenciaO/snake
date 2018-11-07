package edu.unlam.tpa_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import edu.unlam.tpa.Direccion;
import edu.unlam.tpa.Fruta;
import edu.unlam.tpa.Mapa;
import edu.unlam.tpa.Partida;
import edu.unlam.tpa.Snake;

public class PanelJuego extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	private BufferedImage buffer;
	private Thread th;
	
	private VentanaJuego ventanaJuego;
	
	private Snake snake;
	private Snake snake2;
	private Direccion dir;
	private Direccion dir2;
	
	private Fruta fruta;
	private Mapa mapa;
	private Partida partida;
	
	private static int MUY_FACIL = 3;
	private static int FACIL = 5;
	private static int NORMAL = 10;
	private static int DIFICIL = 20;
	
	private int width_height = 500;
	private int row_column = 50;
	private int tileSize = width_height / row_column; 
	private int speed = FACIL;
	
	private int puntos1 = 0;
	private int puntos2 = 0;
	
	private boolean enJuego;
	
	public void setMovimiento(KeyEvent evento){
		
		if(evento.getKeyCode() == KeyEvent.VK_A) {
			dir = Direccion.IZQUIERDA;
		}
		if(evento.getKeyCode() == KeyEvent.VK_D) {
			dir = Direccion.DERECHA;		
		}
		if(evento.getKeyCode() == KeyEvent.VK_W) {
			dir = Direccion.ARRIBA;
		}
		if(evento.getKeyCode() == KeyEvent.VK_S) {
			dir = Direccion.ABAJO;
		}
		
		if(evento.getKeyCode() == KeyEvent.VK_LEFT) {
			dir2 = Direccion.IZQUIERDA;
		}
		if(evento.getKeyCode() == KeyEvent.VK_RIGHT) {
			dir2 = Direccion.DERECHA;		
		}
		if(evento.getKeyCode() == KeyEvent.VK_UP) {
			dir2 = Direccion.ARRIBA;
		}
		if(evento.getKeyCode() == KeyEvent.VK_DOWN) {
			dir2 = Direccion.ABAJO;
		}
		
		if(evento.getKeyCode() == KeyEvent.VK_R) {
			init();
			ventanaJuego.getControlsPanel().setSnake1ScoreLabel("0");
			ventanaJuego.getControlsPanel().setSnake2ScoreLabel("0");
			ventanaJuego.getControlsPanel().setSnake3ScoreLabel("0");
			ventanaJuego.getControlsPanel().setSnake4ScoreLabel("0");
		}
		
	}
	
	public PanelJuego(VentanaJuego frame) {
		this.ventanaJuego = frame;
		this.setFocusable(true);
		setSize(500 + tileSize, 500 + tileSize );
		setPreferredSize(new Dimension(490 + tileSize, 490 + tileSize));
		buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				setMovimiento(arg0);
			}
		});

		this.enJuego = false;
//		init();
//		repaint();
	}

	public void init() {
		if(th != null)
			th.stop();
		puntos1 = 0;
		puntos2 = 0;
		dir = Direccion.DERECHA;
		dir2 = Direccion.IZQUIERDA;
		snake = new Snake(1, 1, dir);
		snake2 = new Snake(24, 24, dir2);
		fruta = new Fruta(11, 11);
		mapa = new Mapa(row_column, row_column);
		partida = new Partida(mapa);
		partida.addSnake(snake);
		partida.addSnake(snake2);
		partida.addFruta(fruta);
		
		th = new Thread(this); // Run se ejecuta en el thread
		th.start();
		
	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) buffer.createGraphics();

		// Paint background
		g2.setColor(Color.RED);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());		
		g2.setColor(Color.BLACK);		
		g2.fillRect(tileSize, tileSize, this.getWidth() - 2 * tileSize, this.getHeight() - 2 * tileSize);
		
		if(this.enJuego) {
			fruta.paint(g2, tileSize);
			if(snake.estaViva())
				snake.paint(g2, Color.GREEN, tileSize);
			if(snake2.estaViva())
				snake2.paint(g2, Color.ORANGE, tileSize);
		}
		
		g.drawImage(buffer, 0, 0, this);
	}

	@Override
	public void run() {
		this.enJuego = true;
		while (snake.estaViva() || snake2.estaViva()) {
//		while (snake.estaViva()) {	
			snake.cambiarDireccion(dir);
			snake2.cambiarDireccion(dir2);
			partida.actualizarPartida();
			if("crecio".equalsIgnoreCase(snake.getEstado())){
				puntos1 += 10;
				ventanaJuego.getControlsPanel().setSnake1ScoreLabel("" + puntos1);
			}
			if("crecio".equalsIgnoreCase(snake2.getEstado())){
				puntos2 += 10;
				ventanaJuego.getControlsPanel().setSnake2ScoreLabel("" + puntos2);
			}		
			try {
				Thread.sleep(1000 / speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			repaint();
		}
		this.enJuego = false;
		
	}

}
