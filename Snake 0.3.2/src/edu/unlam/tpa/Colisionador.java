package edu.unlam.tpa;

public class Colisionador {
	
	public void resolverConflicto(Mapa mapa, Snake snake) {
		Posicion nextPos = snake.getNextPos();		
		Fruta frAux;
		
		snake.tieneConflictos();
		
		if(mapa.estaFueraDelMapa(nextPos))
			snake.morirse();		
		else if(mapa.hayVibora(nextPos, snake)) {
			snake.morirse();
		}
		else if((frAux = mapa.hayFruta(nextPos)) != null){
			snake.crecer();
			frAux.morir();
		}
		else {
			snake.noTieneConflictos();
		}
	}
}
