package edu.unlam.tpa_UTILES;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.unlam.tpa_ENUMS.Velocidad;
import edu.unlam.tpa_GUI.VentanaJuego;
import edu.unlam.tpa_JUEGO.Direccion;
import edu.unlam.tpa_JUEGO.Fruta;
import edu.unlam.tpa_JUEGO.Mapa;
import edu.unlam.tpa_JUEGO.Partida;
import edu.unlam.tpa_JUEGO.Posicion;
import edu.unlam.tpa_JUEGO.Snake;

public class Servidor implements Runnable {
	
	private Thread th;
	
	private Color colorSnake1 = Color.MAGENTA;
	private Color colorSnake2 = Color.CYAN;
	
	private Snake snake1;
	private Snake snake2;
	
	private Mapa mapa;
	private Partida partida;
	
	private Velocidad speed;
	
	private Direccion dir = Direccion.DERECHA;
	private Direccion dir2 = Direccion.ABAJO;
	
	private int puntos1 = 0;
	private int puntos2 = 0;
	
	private boolean enJuego = false;

	private int columnas;
	
	private VentanaJuego ventanaJuego;
	
	public Servidor(VentanaJuego ventanaJuego, Sala sala) {
		this.ventanaJuego = ventanaJuego;
		this.speed = sala.getConfiguracion().getVelocidad();
	}
	
	public void setColumnas(int cantColumnas) {
		this.columnas = cantColumnas;
	}

	public void enviarTecla(int aux, int jugador) {
		if(jugador == 1) {
			switch(aux) {
			case KeyEvent.VK_A:
				dir = Direccion.IZQUIERDA;
				break;
			case KeyEvent.VK_S:
				dir = Direccion.ABAJO;
				break;
			case KeyEvent.VK_D:
				dir = Direccion.DERECHA;
				break;
			case KeyEvent.VK_W:
				dir = Direccion.ARRIBA;
				break;
			}
		} else {
			switch(aux) {
			case KeyEvent.VK_DOWN:
				dir2 = Direccion.ABAJO;
				break;
			case KeyEvent.VK_UP:
				dir2 = Direccion.ARRIBA;
				break;
			case KeyEvent.VK_LEFT:
				dir2 = Direccion.IZQUIERDA;
				break;
			case KeyEvent.VK_RIGHT:
				dir2 = Direccion.DERECHA;
				break;
			}
		}
		
	}

	@Override
	public void run() {
		this.enJuego = true;
		while (snake1.estaViva() || snake2.estaViva()) {
			snake1.cambiarDireccion(dir);
			snake2.cambiarDireccion(dir2);
			partida.actualizarPartida();
			if("crecio".equalsIgnoreCase(snake1.getEstado())){
				puntos1 += 10;
				ventanaJuego.getControlsPanel().setSnake1ScoreLabel("" + puntos1);
			}
			if("crecio".equalsIgnoreCase(snake2.getEstado())){
				puntos2 += 10;
				ventanaJuego.getControlsPanel().setSnake2ScoreLabel("" + puntos2);
			}		
			try {
				Thread.sleep(1000 / speed.getValor());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ventanaJuego.getPanelJuego().repaint();
		}
		this.enJuego = false;		
	}

	public ArrayList<Posicion> obtenerFrutas() {
		return ObtenedorDePuntos.obtenerPuntosFrutas(this.mapa.getFrutas());
	}

	public Map<Color, ArrayList<Posicion>> obtenerSnakes() {
		Map<Color, ArrayList<Posicion>> map = new HashMap<>();
		for(Snake snake: this.mapa.getSnakes()) {
			map.put(snake.getColor(), ObtenedorDePuntos.obtenedorDePuntosSnake(snake));
		}
		return map;
	}

	@SuppressWarnings("deprecation")
	public void init() {
		if(th != null)
			th.stop();
		ArrayList<Fruta> frutas = new ArrayList<>();
		puntos1 = 0;
		puntos2 = 0;
		snake1 = new Snake(5, 5, dir, colorSnake1);
		snake2 = new Snake(11, 11, dir2, colorSnake2);
		frutas.add(new Fruta(9,9));
		mapa = new Mapa(this.columnas, this.columnas);
		partida = new Partida(mapa);
		partida.addSnake(snake1);
		partida.addSnake(snake2);
		partida.addFrutas(frutas);
		th = new Thread(this); // Run se ejecuta en el thread
		th.start();
	}

	public boolean enJuego() {
		return this.enJuego;
	}
}
