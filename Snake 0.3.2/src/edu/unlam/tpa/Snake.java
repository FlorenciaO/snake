package edu.unlam.tpa;

import java.util.ArrayList;

public class Snake {

	private CuerpoSnake cuerpo;
	private Direccion dir;
	private boolean viva;
	private boolean sinConflictos;

	public Snake(int x, int y, Direccion dir) {
		this.cuerpo = new CuerpoSnake(x, y);
		this.dir = dir;
		this.viva = true;
		
	}
	
	public Snake(Snake snake) {
		this.cuerpo = new CuerpoSnake(snake.cuerpo);
		this.dir = snake.dir;
		this.viva = snake.viva;
		
	}

	public void moverse() {
		this.cuerpo.mover(this.dir);
	}

	public void crecer() {
		if (!viva)
			return;
		this.cuerpo.crecer(this.dir);		
	}

	public ArrayList<Posicion> getCuerpo() {
		return cuerpo.getCuerpo();
	}

	public void morirse() {
		this.viva = false;
	}

	public void cambiarDireccion(Direccion dir) {
		if (!viva)
			return;
		switch (this.dir) {
		case ARRIBA:
			if (dir != Direccion.ABAJO && dir != Direccion.ARRIBA)
				this.dir = dir;
			break;
		case ABAJO:
			if (dir != Direccion.ARRIBA && dir != Direccion.ABAJO)
				this.dir = dir;
			break;
		case IZQUIERDA:
			if (dir != Direccion.DERECHA && dir != Direccion.IZQUIERDA)
				this.dir = dir;
			break;
		case DERECHA:
			if (dir != Direccion.IZQUIERDA && dir != Direccion.DERECHA)
				this.dir = dir;
			break;
		}
	}

	public boolean estaViva() {
		return viva;
	}
	
	public boolean vasAestarAhi(Posicion pos) {
		Snake aux = new Snake(this);
		aux.moverse();
		if(this.cuerpo.estasAhi(pos))
			return true;
		return false;
	}
	
	public Posicion getNextPos() {
		return cuerpo.getCabezaNextPos(this.dir);
	}
	
	public boolean verSiNoTieneConflictos() {
		return this.sinConflictos;
	}

	public void noTieneConflictos() {
		this.sinConflictos = true;		
	}

	public void tieneConflictos() {
		this.sinConflictos = false;
	}
	

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((cuerpo == null) ? 0 : cuerpo.hashCode());
//		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
//		result = prime * result + (viva ? 1231 : 1237);
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Snake other = (Snake) obj;
//		if (cuerpo == null) {
//			if (other.cuerpo != null)
//				return false;
//		} else if (!cuerpo.equals(other.cuerpo))
//			return false;
//		if (dir != other.dir)
//			return false;
//		if (viva != other.viva)
//			return false;
//		return true;
//	}
//	

}
