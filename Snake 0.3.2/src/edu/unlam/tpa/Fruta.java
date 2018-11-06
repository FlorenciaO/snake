package edu.unlam.tpa;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Fruta {

	private Posicion pos;

	public Fruta(int x, int y) {
		this.pos = new Posicion(x, y);
	}

	public Fruta(Posicion pos) {
		this.pos = pos;
	}

	public Posicion getPos() {
		return pos;
	}

	public boolean estasAhi(Posicion pos) {
		return this.pos.equals(pos);
	}

	public void morir(Mapa mapa) {
		Random rand = new Random();
		boolean ok = false;
		while (!ok) {
			int x = rand.nextInt(mapa.getFil() - 1) + 1;
			int y = rand.nextInt(mapa.getCol() - 1) + 1;
			if (!mapa.hayVibora(new Posicion(x, y), null)) {
				this.pos.x = x;
				this.pos.y = y;
				ok = true;
			}
		}
	}

	public void paint(Graphics2D g2, int tileSize) {
		g2.setColor(Color.RED);
		g2.fillRect(pos.x * tileSize, pos.y * tileSize, tileSize, tileSize);
	}

}
