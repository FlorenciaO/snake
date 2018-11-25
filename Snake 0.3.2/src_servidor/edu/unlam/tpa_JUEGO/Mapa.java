package edu.unlam.tpa_JUEGO;

import java.util.ArrayList;

public class Mapa {
	private int cantFil;
	private int cantCol;
	private ArrayList<Snake> snakes;
	private ArrayList<Fruta> frutas;

	public Mapa(int fil, int col) {
		this.cantFil = fil;
		this.cantCol = col;
		this.snakes = new ArrayList<>();
		this.frutas = new ArrayList<>();
	}

	public void mostrarMapa() {
		boolean cabeza = false;
		System.out.println("\n");
		for (int j = 0; j < cantFil - 1; j++) {
			for (int i = 0; i < cantCol; i++) {
				if (hayViboraParaMostrar(new Posicion(i, j))) {
					for (Snake snake : snakes) {
						if (snake.laCabezaEstaAhi(new Posicion(i, j))) {
							System.out.print("| O |");
							cabeza = true;
						}
					}
					if (!cabeza) {
						System.out.print("| X |");						
					}
					cabeza = false;
				} else if (hayFruta(new Posicion(i, j)) != null)
					System.out.print("| * |");
				else
					System.out.print("|   |");
			}
			System.out.print("\n");
		}
		System.out.print("--------------------------------------------------\n");
	}

	private boolean hayViboraParaMostrar(Posicion posicion) {
		for (Snake snake : snakes) {
			if (snake.estasAhi(posicion)) {
				return true;
			}
		}
		return false;
	}

	public void addSnake(Snake snake) {
		this.snakes.add(snake);
	}

	public void addFruta(Fruta fruta) {
		this.frutas.add(fruta);
	}

	public boolean estaFueraDelMapa(Posicion pos) {
		if (pos.getX() <= 0 || pos.getX() >= this.cantFil || pos.getY() <= 0 || pos.getY() >= this.cantCol) {
			return true;
		}
		return false;
	}

	public boolean hayVibora(Posicion pos, Snake actSnake) {
		Snake aux;

		for (Snake snake : snakes) {
			if (actSnake != null && actSnake.equals(snake)) {
				if (snake.cuerpoVaAestarAhi(pos)) {
					return true;
				}
			} else {
				if (vaAcrecer(snake)) {
					if(snake.laCabezaVaAEstarAhi(pos)) {
						return true;
					}
					aux = new Snake(snake);
					aux.crecer();
					if (aux.estasAhi(pos))
						return true;
				} else {
					if (snake.vasAestarAhi(pos))
						return true;
				}
			}
		}
		return false;
	}

	private boolean vaAcrecer(Snake snake) {
		Posicion nextPos = snake.getNextPos();
		if (hayFruta(nextPos) != null) {
			return true;
		}
		return false;
	}

	public Fruta hayFruta(Posicion pos) {
		for (Fruta fruta : frutas) {
			if (fruta.estasAhi(pos))
				return fruta;
		}
		return null;
	}

	public int getFil() {
		return cantFil;
	}

	public int getCol() {
		return cantCol;
	}

	public void removeSnake(Snake snake) {
		this.snakes.remove(snake);
	}


	public ArrayList<Snake> getSnakes() {
		return this.snakes;
	}
	
	public ArrayList<Fruta> getFrutas() {
		return this.frutas;
	}
	
	
}
