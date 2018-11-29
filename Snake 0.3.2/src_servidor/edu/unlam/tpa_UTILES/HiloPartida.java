package edu.unlam.tpa_UTILES;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import edu.unlam.tpa_ENUMS.Velocidad;
import edu.unlam.tpa_JUEGO.Direccion;
import edu.unlam.tpa_JUEGO.Fruta;
import edu.unlam.tpa_JUEGO.Mapa;
import edu.unlam.tpa_JUEGO.Partida;
import edu.unlam.tpa_JUEGO.Posicion;
import edu.unlam.tpa_JUEGO.Snake;

public class HiloPartida extends Thread {

	private Mapa mapa;
	private Partida partida;
	private List<Jugador> jugadores;
	private List<Fruta> frutas = new ArrayList<>();
	private Map<Integer, Snake> hashSnakes;
	private Velocidad speed = Velocidad.NORMAL;
	private boolean enJuego = false;
	

	public void cargarSnakes() {
		/**
		 * Hasta 12 snakes para dividir bien el mapa,  arrancarian 3 en cada borde.
		 * Si son menos se le elige una posicion random de estas.
		 */
		hashSnakes = new HashMap<>();
		int i = 0;
		hashSnakes.put(i++, new Snake(2, 5, Direccion.DERECHA));
		hashSnakes.put(i++, new Snake(2, 10, Direccion.DERECHA));
		hashSnakes.put(i++, new Snake(2, 15, Direccion.DERECHA));

		hashSnakes.put(i++, new Snake(5, 2, Direccion.ABAJO));
		hashSnakes.put(i++, new Snake(10, 2, Direccion.ABAJO));
		hashSnakes.put(i++, new Snake(15, 2, Direccion.ABAJO));

		hashSnakes.put(i++, new Snake(18, 5, Direccion.IZQUIERDA));
		hashSnakes.put(i++, new Snake(18, 10, Direccion.IZQUIERDA));
		hashSnakes.put(i++, new Snake(18, 15, Direccion.IZQUIERDA));

		hashSnakes.put(i++, new Snake(5, 18, Direccion.ARRIBA));
		hashSnakes.put(i++, new Snake(10, 18, Direccion.ARRIBA));
		hashSnakes.put(i++, new Snake(15, 18, Direccion.ARRIBA));
	}

	public Snake obtenerSnakeEnPosRandom() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, hashSnakes.size());
		Snake snakeAux = hashSnakes.get(randomNum);
		hashSnakes.remove(randomNum);
		return snakeAux;
	}

	public HiloPartida(List<Jugador> jugadores) {
		this.jugadores = jugadores;
		frutas.add(new Fruta(9, 9));
		mapa = new Mapa(20, 20);
		partida = new Partida(mapa);
		cargarSnakes();
		for (Jugador jugador : jugadores) {
			Snake s = obtenerSnakeEnPosRandom();
			jugador.setSnake(s);
			partida.addSnake(s);
		}
		for (Fruta fruta : frutas) {
			partida.addFruta(fruta);
		}
	}

	public boolean masDeUnaEstaViva() {
		int i = 0;
		for (Jugador jugador : jugadores) {
			if (jugador.getSnake().estaViva())
				i++;
			if (i > 1)
				return true;
		}
		return false;
	}

	public Direccion teclaAdireccion(int tecla) {
		switch (tecla) {
		case KeyEvent.VK_A:
			return Direccion.IZQUIERDA;
		case KeyEvent.VK_S:
			return Direccion.ABAJO;
		case KeyEvent.VK_D:
			return Direccion.DERECHA;
		case KeyEvent.VK_W:
			return Direccion.ARRIBA;
		}
		return null;
	}

	public void actualizarDirecciones() {
		for (Jugador jugador : jugadores) {
			Snake s = jugador.getSnake();
			Direccion dirAux;
			if ((dirAux = teclaAdireccion(jugador.getUltimaTeclaPresionada())) != null)
				s.cambiarDireccion(dirAux);
		}
	}

	public void actualizarPuntos() {
		for (Jugador jugador : jugadores) {
			Snake s = jugador.getSnake();
			if ("crecio".equalsIgnoreCase(s.getEstado())) {
				jugador.sumarPuntos(10);
			}
		}
	}

	@Override
	public void run() {
		this.enJuego = true;
		while (masDeUnaEstaViva()) {
			actualizarDirecciones();
			partida.actualizarPartida();
			try {
				Thread.sleep(1000 / speed.getValor());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// enviarDatos

		}
		this.enJuego = false;
	}

	public List<Posicion> obtenerFrutas() {
		return ObtenedorDePuntos.obtenerPuntosFrutas(this.mapa.getFrutas());
	}

	public Map<Color, List<Posicion>> obtenerSnakes() {
		Map<Color, List<Posicion>> map = new HashMap<>();
		for (Jugador jugador : jugadores) {
			map.put(jugador.getColor(), ObtenedorDePuntos.obtenedorDePuntosSnake(jugador.getSnake()));
		}
		return map;
	}

}
