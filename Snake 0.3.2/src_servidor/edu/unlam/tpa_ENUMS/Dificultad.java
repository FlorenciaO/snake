package edu.unlam.tpa_ENUMS;

public enum Dificultad {
	FACIL(10), NORMAL(25), DIFICIL(50); //Cantidad de bloques u obstaculos
	
	private int cantidad;
	
	Dificultad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
}
