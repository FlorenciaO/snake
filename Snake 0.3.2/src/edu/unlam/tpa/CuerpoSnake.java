package edu.unlam.tpa;

import java.util.ArrayList;

public class CuerpoSnake {
	
	private ArrayList<Posicion> cuerpo;

	public CuerpoSnake(int x, int y) {
		this.cuerpo = new ArrayList<>();
		this.cuerpo.add(new Posicion(x, y));
	}
	
	public Posicion moverYavisar(Posicion pos, int i) {
		Posicion aux = new Posicion(this.cuerpo.get(i));
		this.cuerpo.get(i).x = pos.x;
		this.cuerpo.get(i).y = pos.y;
		return aux;
	}
	
	public void mover(Direccion dir) {
		
		Posicion posAux = new Posicion(this.cuerpo.get(0));
		
		moverPos(this.cuerpo.get(0), dir);
		
		for(int i = 1; i < cuerpo.size(); i++) {
			posAux = moverYavisar(posAux, i);
		}
	}

	private void moverPos(Posicion pos, Direccion dir) {
		switch(dir) {
		case ARRIBA:
			pos.x--;
			break;
		case ABAJO:
			pos.x++;
			break;
		case DERECHA:
			pos.y++;
			break;
		case IZQUIERDA:
			pos.y--;
			break;
		}
	}

	public void crecer(Direccion dir) {
		Posicion pos = new Posicion(cuerpo.get(0));

		moverPos(pos, dir);
		this.cuerpo.add(0, pos);
	}
	
	public Posicion getCabezaPos() {
		return cuerpo.get(0);
	}

	public ArrayList<Posicion> getCuerpo() {
		return cuerpo;
	}
	
	
}
