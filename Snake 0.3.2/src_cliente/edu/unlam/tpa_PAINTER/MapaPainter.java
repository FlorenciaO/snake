package edu.unlam.tpa_PAINTER;

import java.awt.Color;
import java.awt.Graphics2D;

public class MapaPainter {
	
	private int width;
	private int height;
	private int tileSize;

	public MapaPainter(int width, int height, int tileSize) {
		this.width = width;
		this.height = height;
		this.tileSize = tileSize;
	}
	
	public void paint(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, this.width, this.height);		
		g2.setColor(Color.BLACK);		
		g2.fillRect(this.tileSize, this.tileSize, this.width - 2 * this.tileSize, this.height - 2 * this.tileSize);
	}
	
}
