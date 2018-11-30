package edu.unlam.tpa_PAINTER;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
