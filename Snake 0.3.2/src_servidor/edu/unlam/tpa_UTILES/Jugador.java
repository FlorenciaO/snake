package edu.unlam.tpa_UTILES;

import java.awt.Color;

import edu.unlam.tpa_JUEGO.Snake;

public class Jugador {
	
	private String userName;
	private Color color;
	private Snake snake;
	private int puntos;
	private int ultimaTeclaPresionada;
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public void setUltimaTeclaPresionada(int ultimaTeclaPresionada) {
		this.ultimaTeclaPresionada = ultimaTeclaPresionada;
	}

	public String getUserName() {
		return userName;
	}

	public int getUltimaTeclaPresionada() {
		return ultimaTeclaPresionada;
	}

	public int getPuntos() {
		return puntos;
	}
	
	public void sumarPuntos(int puntos) {
		this.puntos += puntos;
	}

	public Jugador(String nombreUsuario, Color color) {
		this.userName = nombreUsuario;
		this.color = color;
		this.puntos = 0;
	}
	
	public void setSnake(Snake snake) {
		this.snake = snake;
	}
	
	public String getNombreUsuario() {
		return userName;
	}

	public Color getColor() {
		return color;
	}

	public Snake getSnake() {
		return snake;
	}

	
}
