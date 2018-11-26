package edu.unlam.tpa_ENUMS;

public enum Comando {
	ENTRARSALA(0), 
	SALIRSALA(1), 
	CONECTARCLIENTE(2),
	DESCONECTARCLIENTE(3), 
	ENTRARJUEGO(4),
	CREARSALA(5), 
	OBTENERSALAS(6), 
	OBTENERJUGADORES(7);
	
	private int valor;

	private Comando(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return this.valor;
	}
	
}
