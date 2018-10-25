package edu.unlam.tpa;

public class Posicion {
	int x;
	int y;
	
	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}	
	
	public Posicion(Posicion pos) {
		this.x = pos.x;
		this.y = pos.y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	

	public void setY(int y) {
		this.y = y;
	}
	

	@Override
	public String toString() {
		return "" + this.x + " " + this.y; 
	}
	
	public boolean equals(Posicion obj) {
		return this.x == obj.x && this.y == obj.y;
	}
}
