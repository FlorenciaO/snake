package edu.unlam.tpa_UTILES;

import java.util.ArrayList;
import java.util.List;

import edu.unlam.tpa_JUEGO.Fruta;
import edu.unlam.tpa_JUEGO.Posicion;
import edu.unlam.tpa_JUEGO.Snake;

public class ObtenedorDePuntos {

	public static List<Posicion> obtenerPuntosFrutas(List<Fruta> frutas) {

		List<Posicion> posiciones = new ArrayList<>();
		for (Fruta fruta : frutas) {
			posiciones.add(new Posicion(fruta.getPos()));
		}
		return posiciones;
	}

	public static List<Posicion> obtenedorDePuntosSnake(Snake vibora) {

		List<Posicion> arrayDeVibora = new ArrayList<>();
		arrayDeVibora.addAll(vibora.getCuerpo());
		return arrayDeVibora;
	}
	
}
