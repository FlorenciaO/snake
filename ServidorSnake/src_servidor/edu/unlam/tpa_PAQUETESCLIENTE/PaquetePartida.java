package edu.unlam.tpa_PAQUETESCLIENTE;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.unlam.tpa_JUEGO.Posicion;
import edu.unlam.tpa_UTILES.Jugador;

public class PaquetePartida extends Paquete implements Serializable, Cloneable {

	private static final long serialVersionUID = -2167001524438225469L;

	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private ArrayList<Posicion> frutas = new ArrayList<>();
	private Map<Color, ArrayList<Posicion>> snakes = new HashMap<>();
	private int row_column = 0;

	public PaquetePartida() {
	}

	public PaquetePartida(ArrayList<Jugador> jugadores, ArrayList<Posicion> frutas, Map<Color, ArrayList<Posicion>> snakes,
			int row_column) {
		this.jugadores = jugadores;
		this.frutas = frutas;
		this.snakes = snakes;
		this.row_column = row_column;
	}

	public ArrayList<Posicion> getFrutas() {
		return frutas;
	}

	public Map<Color, ArrayList<Posicion>> getSnakes() {
		return snakes;
	}

	public int getRow_column() {
		return row_column;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

	public void setPaquete(ArrayList<Jugador> jugadores, ArrayList<Posicion> frutas, Map<Color, ArrayList<Posicion>> snakes) {
		this.jugadores = jugadores;
		this.frutas = frutas;
		this.snakes = snakes;
	}

}
