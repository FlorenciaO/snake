package edu.unlam.tpa.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import edu.unlam.tpa_JUEGO.Direccion;
import edu.unlam.tpa_JUEGO.Fruta;
import edu.unlam.tpa_JUEGO.Posicion;
import edu.unlam.tpa_JUEGO.Snake;
import edu.unlam.tpa_PAQUETESCLIENTE.PaquetePartida;
import edu.unlam.tpa_UTILES.Jugador;
import edu.unlam.tpa_UTILES.ObtenedorDePuntos;

public class TestComu {
	Gson gson = new Gson();

	@Test
	public void testPaquetePartida() throws Exception {
		ArrayList<Jugador> jugadores = new ArrayList<>();
		jugadores.add(new Jugador("jugador1", 69));
		jugadores.add(new Jugador("jugador2", 420));
		jugadores.get(0).setSnake(new Snake(2, 5, Direccion.DERECHA).getCuerpo());
		jugadores.get(1).setSnake(new Snake(2, 10, Direccion.DERECHA).getCuerpo());
		ArrayList<Fruta> frutasO = new ArrayList<>();
		frutasO.add(new Fruta(10, 10));
		frutasO.add(new Fruta(12, 14));
		int row_column = 20;
		/**/
		Map<Integer, ArrayList<Posicion>> snakes = new HashMap<>();
		for (Jugador jugador : jugadores) {
			snakes.put(jugador.getColore(), jugador.getSnake());
		}
		ArrayList<Posicion> frutas = ObtenedorDePuntos.obtenerPuntosFrutas(frutasO);

		PaquetePartida paquetePartida = new PaquetePartida(jugadores, frutas, snakes, row_column);
		String s = gson.toJson(paquetePartida, PaquetePartida.class);

		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(s).getAsJsonObject();

		// Esta ok esto
		int rowC = object.getAsJsonPrimitive("row_column").getAsInt();		
		System.out.println(rowC);

		Map<Integer, ArrayList<Posicion>> mapaSnakes = new HashMap<>();		
		JsonObject snakesJ = object.get("snakes").getAsJsonObject();  
		Set<String> keys = snakesJ.keySet();
		System.out.println(keys);		
		for (String key : keys) {			
			ArrayList<Posicion> cuerpoide = new ArrayList<>();
			JsonArray cuerpo = snakesJ.get(key).getAsJsonArray();
			for (JsonElement jsonElement : cuerpo) {
				JsonObject cuerpito = jsonElement.getAsJsonObject();
				cuerpoide.add(new Posicion(cuerpito.get("x").getAsInt(), cuerpito.get("y").getAsInt()));				
			}
			mapaSnakes.put(Integer.valueOf(key), cuerpoide);			
		}		
		System.out.println(mapaSnakes);

		ArrayList<Posicion> frutasJson = new ArrayList<>();
		JsonArray frutasJ = object.getAsJsonArray("frutas");
		for (JsonElement jsonElement : frutasJ) {
			JsonObject frutita = jsonElement.getAsJsonObject();
			frutasJson.add(new Posicion(frutita.get("x").getAsInt(), frutita.get("y").getAsInt()));
		}					
		System.out.println(frutasJson);

		ArrayList<Jugador> jugadoresJson = new ArrayList<>();
		JsonArray jugadoresJ = object.getAsJsonArray("jugadores");
		for (JsonElement jsonElement : jugadoresJ) {
//			System.out.println(jsonElement);
			String user = jsonElement.getAsJsonObject().get("userName").getAsString();
			System.out.print(user + " ");
			int colore = jsonElement.getAsJsonObject().get("colore").getAsInt();
			System.out.print(colore + " ");
			int ultimaTeclaPresionada = jsonElement.getAsJsonObject().get("ultimaTeclaPresionada").getAsInt();
			System.out.print(ultimaTeclaPresionada + " ");
			int puntos = jsonElement.getAsJsonObject().get("puntos").getAsInt();
			System.out.println(puntos + " ");

			ArrayList<Posicion> cuerpoide = new ArrayList<>();
			JsonArray cuerpo = jsonElement.getAsJsonObject().get("snake").getAsJsonArray();
			for (JsonElement parte : cuerpo) {
				JsonObject cuerpito = parte.getAsJsonObject();
				cuerpoide.add(new Posicion(cuerpito.get("x").getAsInt(), cuerpito.get("y").getAsInt()));
			}
			System.out.println(cuerpoide);
			Jugador j = new Jugador(user, colore);
			j.setSnake(cuerpoide);
			jugadoresJson.add(j);
		}
		System.out.println(jugadoresJson);
		@SuppressWarnings("unused")
		PaquetePartida paquetePartida2 = new PaquetePartida(jugadoresJson, frutasJson, mapaSnakes, rowC);
	}

//	@Test
	public void deApoco() {
		Snake sn = new Snake(2, 4, Direccion.ARRIBA);
		String s = gson.toJson(sn);
		System.out.println(s);
		Snake snake = gson.fromJson(s, Snake.class);
		System.out.println(snake.getCuerpo());
		System.out.println(snake.getColor());
	}
}
