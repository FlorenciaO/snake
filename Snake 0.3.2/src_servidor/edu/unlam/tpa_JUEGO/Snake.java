package edu.unlam.tpa_JUEGO;

import java.io.Serializable;
import java.util.ArrayList;

public class Snake {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6734444690353883589L;


	private CuerpoSnake cuerpo;
	private Direccion dir;

	private boolean sinConflictos;
	private String estado;
	
	public Snake(int x, int y, Direccion dir) {
		this.cuerpo = new CuerpoSnake(x, y);
		this.dir = dir;
		crecer();
		this.estado = "viva";
	}
	
	public Snake(Snake snake) {
		this.cuerpo = new CuerpoSnake(snake.cuerpo);
		this.dir = snake.dir;

		this.estado = snake.estado;
	}

	public void moverse() {
		this.cuerpo.mover(this.dir);
		this.estado = "viva";
	}

	public void crecer() {

		if("muerta".equalsIgnoreCase(this.estado))
			return;
		this.cuerpo.crecer(this.dir);
		this.estado = "crecio";
	}
	
	public ArrayList<Posicion> getCuerpo() {
		return cuerpo.getCuerpo();
	}

	public void morirse() {
		this.estado = "muerta";
//		System.out.println("\n\tse cago muriendo");
	}

	public void cambiarDireccion(Direccion dir) {
		if(this.estado.equals("muerta"))
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
//		return viva;
		return !"muerta".equalsIgnoreCase(this.estado);
	}
	
	public String getEstado() {
		return estado;
	}

	public boolean vasAestarAhi(Posicion pos) {
		Snake aux = new Snake(this);
		aux.moverse();
		if(aux.cuerpo.estasAhi(pos))
			return true;
		return false;
	}
	
	public boolean cuerpoVaAestarAhi(Posicion pos) {
		Snake aux = new Snake(this);
		aux.moverse();
		if(aux.cuerpo.cuerpoSinCabezaEstaAhi(pos))
			return true;
		return false;
	}
	
	public boolean laCabezaVaAEstarAhi(Posicion pos) {
		return cuerpo.laCabezaVaAEstarAhi(pos,this.dir);
	}
	
	public boolean laCabezaEstaAhi(Posicion pos) {
		return cuerpo.laCabezaEstaAhi(pos);
	}
	
	public boolean estasAhi(Posicion posicion) {
		return cuerpo.estasAhi(posicion);
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
		this.estado = "viva";
		this.sinConflictos = false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuerpo == null) ? 0 : cuerpo.hashCode());
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
		result = prime * result + (sinConflictos ? 1231 : 1237);
//		result = prime * result + (viva ? 1231 : 1237);
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
		Snake other = (Snake) obj;
		if (cuerpo == null) {
			if (other.cuerpo != null)
				return false;
		} else if (!cuerpo.equals(other.cuerpo))
			return false;
		if (dir != other.dir)
			return false;
		if (sinConflictos != other.sinConflictos)
			return false;
//		if (viva != other.viva)
//			return false;
		return true;
	}


}
