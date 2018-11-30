package edu.unlam.tpa_PAQUETESCLIENTE;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edu.unlam.tpa_JUEGO.Posicion;
import edu.unlam.tpa_UTILES.Jugador;

public class PaquetePartida extends Paquete implements Serializable, Cloneable {

	private static final long serialVersionUID = -2167001524438225469L;
	
	private List<Jugador> jugadores;
	private List<Posicion> frutas;
	private Map<Color,List<Posicion>> snakes;
	private int row_column;
	
	public PaquetePartida() {
	}
	
	public PaquetePartida(List<Jugador> jugadores, List<Posicion> frutas, Map<Color, List<Posicion>> snakes,
			int row_column) {
		this.jugadores = jugadores;
		this.frutas = frutas;
		this.snakes = snakes;
		this.row_column = row_column;
	}

	public List<Posicion> getFrutas() {
		return frutas;
	}

	public Map<Color, List<Posicion>> getSnakes() {
		return snakes;
	}

	public int getRow_column() {
		return row_column;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}


	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

	public void setPaquete(List<Jugador> jugadores, List<Posicion> frutas,
			Map<Color, List<Posicion>> snakes) {
		this.jugadores = jugadores;
		this.frutas = frutas;
		this.snakes = snakes;		
	}

}
