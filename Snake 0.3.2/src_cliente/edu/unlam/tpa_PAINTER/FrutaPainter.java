package edu.unlam.tpa_PAINTER;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import edu.unlam.tpa_JUEGO.Posicion;

public class FrutaPainter {
	private ArrayList<Posicion> frutas;
	private int tileSize;
	private Color color;

	public FrutaPainter(ArrayList<Posicion> frutas, int tileSize, Color color) {
		this.frutas = frutas;
		this.tileSize = tileSize;
		this.color = color;
	}
	
	public FrutaPainter(int tileSize2, Color color) {
		this.tileSize = tileSize2;
		this.color = color;
	}

	public void setFrutas(ArrayList<Posicion> frutas) {
		this.frutas = frutas;
	}

	public void paint(Graphics2D g2, ArrayList<Posicion> arrayList) {
		setFrutas(arrayList);
		for (Posicion fruta: this.frutas) {
			g2.setColor(this.color);
			g2.fillRect(fruta.getX() * tileSize, fruta.getY() * tileSize, tileSize, tileSize);
		}
	}
}
