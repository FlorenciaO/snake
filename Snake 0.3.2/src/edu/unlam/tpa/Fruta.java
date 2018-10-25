package edu.unlam.tpa;

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

	public void morir() {		
	}
	
}
