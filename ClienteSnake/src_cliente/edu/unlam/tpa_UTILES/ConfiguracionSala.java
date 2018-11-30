package edu.unlam.tpa_UTILES;

import edu.unlam.tpa_ENUMS.Dificultad;
import edu.unlam.tpa_ENUMS.Modo;
import edu.unlam.tpa_ENUMS.Velocidad;

public class ConfiguracionSala {
	
	private Velocidad velocidad;
	private Modo modo;
	private Dificultad dificultad;
	
	
	public ConfiguracionSala(Velocidad velocidad, Modo modo, Dificultad dificultad) {
		super();
		this.velocidad = velocidad;
		this.modo = modo;
		this.dificultad = dificultad;
	}


	public Velocidad getVelocidad() {
		return velocidad;
	}


	public Modo getModo() {
		return modo;
	}


	public Dificultad getDificultad() {
		return dificultad;
	}
	
	

}
