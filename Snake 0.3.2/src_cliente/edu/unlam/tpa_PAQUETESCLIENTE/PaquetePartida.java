package edu.unlam.tpa_PAQUETESCLIENTE;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edu.unlam.tpa_PAINTER.Posicion;

public class PaquetePartida extends Paquete implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	
	private List<Posicion> frutas;
	private Map<Color, Posicion> snakes;
	
	public PaquetePartida() {
	}

	public PaquetePartida(List<Posicion> frutas, Map<Color, Posicion> snakes) {
		this.frutas = frutas;
		this.snakes = snakes;
	}
	
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

}
