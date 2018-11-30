package edu.unlam.tpa_JUEGO;

import java.util.ArrayList;
import java.util.Iterator;

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
	
	public void addSnakes(ArrayList<Snake> snakes) {
		for(Snake snake: snakes) {
			this.mapa.addSnake(snake);
			this.snakes.add(snake);		}
	}
	
	public void addFruta(Fruta fruta) {
		this.mapa.addFruta(fruta);
	}
	
	public void addFrutas(ArrayList<Fruta> frutas) {
		for(Fruta fruta: frutas) {
			this.mapa.addFruta(fruta);
		}
	}
	
	public void actualizarPartida() {
		
		Iterator<Snake> snakesIterator = this.snakes.iterator();
		while(snakesIterator.hasNext()) {
			colisionador.resolverConflicto(this.mapa, snakesIterator.next());
		}
		
		snakesIterator = this.snakes.iterator();
		while(snakesIterator.hasNext()) {
			Snake snake = snakesIterator.next();
			if(snake.estaViva()) {
				if(snake.verSiNoTieneConflictos()) {
					snake.moverse();
				}
			}
			else {
				snakesIterator.remove();
				mapa.removeSnake(snake);
			}
		}
	}
	
	public void actualizarPartida(boolean mostrar) {
		if(mostrar)
			mapa.mostrarMapa();
		Iterator<Snake> snakesIterator = this.snakes.iterator();
		while(snakesIterator.hasNext()) {
			colisionador.resolverConflicto(this.mapa, snakesIterator.next());
		}
		
		snakesIterator = this.snakes.iterator();
		while(snakesIterator.hasNext()) {
			Snake snake = snakesIterator.next();
			if(snake.estaViva()) {
				if(snake.verSiNoTieneConflictos()) {
					snake.moverse();
				}
			}
			else {
				snakesIterator.remove();
			}
		}
	}
}
