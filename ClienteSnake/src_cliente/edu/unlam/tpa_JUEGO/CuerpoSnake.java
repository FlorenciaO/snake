package edu.unlam.tpa_JUEGO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;

public class CuerpoSnake implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1992972558005892808L;
	private ArrayList<Posicion> cuerpo;

	public CuerpoSnake(int x, int y) {
		this.cuerpo = new ArrayList<>();
		this.cuerpo.add(new Posicion(x, y));
	}

	public CuerpoSnake(CuerpoSnake cuerpo2) {
		this.cuerpo = new ArrayList<>();
		for(Posicion pos : cuerpo2.cuerpo) {
			this.cuerpo.add(new Posicion(pos));
		}
		
	}

	public void mover(Direccion dir) {
		Posicion posAux = new Posicion(getCabezaPos());

		moverPos(getCabezaPos(), dir);
		for (int i = 1; i < cuerpo.size(); i++) {
			posAux = moverYavisar(posAux, i);
		}
	}

	public Posicion moverYavisar(Posicion pos, int i) {
		Posicion aux = new Posicion(this.cuerpo.get(i));
		this.cuerpo.get(i).x = pos.x;
		this.cuerpo.get(i).y = pos.y;
		return aux;
	}

	private void moverPos(Posicion pos, Direccion dir) {
		switch (dir) {
		case ARRIBA:
			pos.y--;
			break;
		case ABAJO:
			pos.y++;
			break;
		case DERECHA:
			pos.x++;
			break;
		case IZQUIERDA:
			pos.x--;
			break;
		}
	}

	public void crecer(Direccion dir) {
		Posicion pos = new Posicion(getCabezaPos());

		moverPos(pos, dir);
		this.cuerpo.add(0, pos);
	}

	public Posicion getCabezaPos() {
		return cuerpo.get(0);
	}

	public ArrayList<Posicion> getCuerpo() {
		return cuerpo;
	}

	public Posicion getCabezaNextPos(Direccion dir) {
		Posicion aux = new Posicion(getCabezaPos());
		moverPos(aux, dir);
		return aux;
	}

	public boolean estasAhi(Posicion pos) {
		for (Posicion posCuerpo : cuerpo) {
			if (pos.equals(posCuerpo))
				return true;
		}
		return false;
	}
	
	public boolean laCabezaEstaAhi(Posicion pos) {
		return cuerpo.get(0).equals(pos);
	}
	
	public boolean cuerpoSinCabezaEstaAhi(Posicion pos) {
		for(int i = 1; i < cuerpo.size(); i++) {
			if(pos.equals(cuerpo.get(i)))
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuerpo == null) ? 0 : cuerpo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuerpoSnake other = (CuerpoSnake) obj;
		if (cuerpo == null) {
			if (other.cuerpo != null)
				return false;
		} else if (!cuerpo.equals(other.cuerpo))
			return false;
		return true;
	}

	

	public boolean laCabezaVaAEstarAhi(Posicion pos, Direccion dir) {
		return getCabezaNextPos(dir).equals(pos);
	}

	

	
	
	

}
