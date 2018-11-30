package edu.unlam.tpa_PAINTER;


import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Map;

import edu.unlam.tpa_GUI.VentanaJuego;
import edu.unlam.tpa_JUEGO.Posicion;

public class SnakePainter {

	private Map<Integer, ArrayList<Posicion>> viboras;
	private int tileSize;

	public SnakePainter(Map<Integer, ArrayList<Posicion>> viboras, int tileSize) {
		this.viboras = viboras;
		this.tileSize = tileSize;
	}

	public SnakePainter(int tileSize2) {
		this.tileSize = tileSize2;
	}

	public void paint(Graphics2D g2, Map<Integer, ArrayList<Posicion>> snakes) {
		this.viboras = snakes;
		for (Map.Entry<Integer, ArrayList<Posicion>> entry : viboras.entrySet()) {
			g2.setColor(VentanaJuego.obtenerColores().get(entry.getKey()));
			for(Posicion parte : entry.getValue())
				g2.fillRect(parte.getX() * tileSize, parte.getY() * this.tileSize, this.tileSize, this.tileSize);
		}		
	}
	
	public void setSnakes(Map<Integer, ArrayList<Posicion>> viboras) {
		this.viboras = viboras;
	}
}
