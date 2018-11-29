package edu.unlam.tpa_PAINTER;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;

import edu.unlam.tpa_JUEGO.Posicion;

public class SnakePainter {

	private Map<Color, List<Posicion>> viboras;
	private int tileSize;

	public SnakePainter(Map<Color, List<Posicion>> viboras, int tileSize) {
		this.viboras = viboras;
		this.tileSize = tileSize;
	}

	public SnakePainter(int tileSize2) {
		this.tileSize = tileSize2;
	}

	public void paint(Graphics2D g2) {
		
		for (Map.Entry<Color, List<Posicion>> entry : viboras.entrySet()) {
			g2.setColor(entry.getKey());
			for(Posicion parte : entry.getValue())
				g2.fillRect(parte.getX() * tileSize, parte.getY() * this.tileSize, this.tileSize, this.tileSize);
		}		
	}
	
	public void setSnakes(Map<Color, List<Posicion>> viboras) {
		this.viboras = viboras;
	}
}
