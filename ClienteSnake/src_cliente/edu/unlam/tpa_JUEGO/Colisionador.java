package edu.unlam.tpa_JUEGO;

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
		if((frAux = mapa.hayFruta(nextPos)) != null){
			snake.crecer();
			frAux.morir(mapa);
		}
		else {
			snake.noTieneConflictos();
		}
	}
}
