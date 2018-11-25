package edu.unlam.tpa_UTILES;

public class Sala {
	
	private ConfiguracionSala configuracion;
	private String nombre;
	
	public Sala(ConfiguracionSala configuracion, String nombre) {
		this.configuracion = configuracion;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	public ConfiguracionSala getConfiguracion() {
		return configuracion;
	}

	public String getNombre() {
		return nombre;
	}
	
	
	
}
