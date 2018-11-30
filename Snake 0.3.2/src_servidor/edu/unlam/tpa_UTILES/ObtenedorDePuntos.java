package edu.unlam.tpa_UTILES;

import java.util.ArrayList;

import edu.unlam.tpa_JUEGO.Fruta;
import edu.unlam.tpa_JUEGO.Posicion;
import edu.unlam.tpa_JUEGO.Snake;

public class ObtenedorDePuntos {

	public static ArrayList<Posicion> obtenerPuntosFrutas(ArrayList<Fruta> frutas) {

		ArrayList<Posicion> posiciones = new ArrayList<>();
		for (Fruta fruta : frutas) {
			posiciones.add(new Posicion(fruta.getPos()));
		}
		return posiciones;
	}

	public static ArrayList<Posicion> obtenedorDePuntosSnake(Snake vibora) {

		ArrayList<Posicion> arrayDeVibora = new ArrayList<>();
		arrayDeVibora.addAll(vibora.getCuerpo());
		return arrayDeVibora;
	}
	
}
