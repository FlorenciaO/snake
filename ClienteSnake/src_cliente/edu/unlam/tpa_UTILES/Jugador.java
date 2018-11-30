package edu.unlam.tpa_UTILES;

import java.io.Serializable;

public class Jugador implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2576195759529993579L;
	private String userName;
	private Integer idColor;
	private Integer idSnake;
	private int puntos;
	private int ultimaTeclaPresionada;
	
	public Jugador(String userName) {
		this.userName = userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setColor(Integer color) {
		this.idColor = color;
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

	public Jugador(String nombreUsuario, Integer color, Integer idSnake) {
		this.userName = nombreUsuario;
		this.idColor = color;
		this.puntos = 0;
		this.idSnake = idSnake;
	}
	
	
	public String getNombreUsuario() {
		return userName;
	}

	public Integer getColor() {
		return idColor;
	}
	
	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public Integer getIdSnake() {
		return idSnake;
	}

	public void setIdSnake(Integer idSnake) {
		this.idSnake = idSnake;
	}
	

}
