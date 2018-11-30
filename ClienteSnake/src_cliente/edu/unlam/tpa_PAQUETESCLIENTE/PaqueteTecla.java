package edu.unlam.tpa_PAQUETESCLIENTE;

import java.io.Serializable;

public class PaqueteTecla extends Paquete implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4122811576509221833L;
	
	private int teclaPresionada;
	private String nombreJugador;
	
	public PaqueteTecla() {
		
	}
	
	public PaqueteTecla(int tecla, String jugador) {
		this.teclaPresionada = tecla;
		this.nombreJugador = jugador;
	}

	public int getTeclaPresionada() {
		return teclaPresionada;
	}

	public void setTeclaPresionada(int teclaPresionada) {
		this.teclaPresionada = teclaPresionada;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}


	

}
