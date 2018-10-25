package edu.unlam.tpa;

import java.util.ArrayList;

public class Partida {
	private Mapa mapa;
	private ArrayList<Snake> snakes;
	private Colisionador colisionador;

	public Partida(Mapa mapa) {
		this.mapa = mapa;
		this.snakes = new ArrayList<>();
		this.colisionador = new Colisionador();
	}
	
	public void addSnake(Snake sn) {
		this.mapa.addSnake(sn);
		this.snakes.add(sn);
	}
	
	public void addFruta(Fruta fruta) {
		this.mapa.addFruta(fruta);
	}
	
	public void actualizarPartida() {
		
		for(int i = 0; i < this.snakes.size(); i++) {
			colisionador.resolverConflicto(this.mapa, this.snakes.get(i));
		}
		
		for(Snake snake : this.snakes) {
			if(snake.estaViva()) {
				if(snake.verSiNoTieneConflictos()) {
					snake.moverse();
				}
			}
			else {
				snakes.remove(snake);
			}
		}
		
	}
}
