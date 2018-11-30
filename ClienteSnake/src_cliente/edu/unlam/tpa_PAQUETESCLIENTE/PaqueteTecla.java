package edu.unlam.tpa_PAQUETESCLIENTE;

import java.io.Serializable;

public class PaqueteTecla extends Paquete implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4122811576509221833L;
	
	private int teclaPresionada;
	private int idJugador;
	
	public PaqueteTecla(int tecla, int jugador) {
		this.teclaPresionada = tecla;
		this.idJugador = jugador;
	}

	public int getTeclaPresionada() {
		return teclaPresionada;
	}

	public void setTeclaPresionada(int teclaPresionada) {
		this.teclaPresionada = teclaPresionada;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	} 
	

}
