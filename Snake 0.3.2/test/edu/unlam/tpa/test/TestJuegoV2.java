package edu.unlam.tpa.test;

import org.junit.Test;

import edu.unlam.tpa_JUEGO.Direccion;
import edu.unlam.tpa_JUEGO.Fruta;
import edu.unlam.tpa_JUEGO.Mapa;
import edu.unlam.tpa_JUEGO.Partida;
import edu.unlam.tpa_JUEGO.Snake;


public class TestJuegoV2 {
	
	Snake sn, sn2;
	Fruta fr;
	Partida partida;
	Mapa mapa;
	
//	@Test
	public void creceYseMueve() {
		sn = new Snake(4, 4, Direccion.DERECHA);
		
		sn.crecer();
		System.out.println(sn.getCuerpo());
		sn.crecer();
		System.out.println(sn.getCuerpo());
		sn.cambiarDireccion(Direccion.ABAJO);
		sn.moverse();
		System.out.println(sn.getCuerpo());
	}
	
//	@Test
	public void choca() {
		sn = new Snake(0, 2, Direccion.DERECHA);
		mapa = new Mapa(10, 10);
		partida = new Partida(mapa);
		partida.addSnake(sn);
		sn.crecer();
		partida.actualizarPartida();
		System.out.println((sn.estaViva()? "":"no ") + "esta viva\n" + sn.getCuerpo());
		partida.actualizarPartida();
		System.out.println((sn.estaViva()? "":"no ") + "esta viva\n" + sn.getCuerpo());
		sn.cambiarDireccion(Direccion.ARRIBA);
		partida.actualizarPartida();
		System.out.println((sn.estaViva()? "":"no ") + "esta viva\n" + sn.getCuerpo());
	}
	
//	@Test
	public void choca2() {
		sn = new Snake(9, 6, Direccion.DERECHA);
		mapa = new Mapa(10, 10);
		partida = new Partida(mapa);
		partida.addSnake(sn);
		sn.crecer();
		System.out.println((sn.estaViva()? "":"no ") + "esta viva\n" + sn.getCuerpo());
		partida.actualizarPartida();
		System.out.println((sn.estaViva()? "":"no ") + "esta viva\n" + sn.getCuerpo());
		partida.actualizarPartida();
		System.out.println((sn.estaViva()? "":"no ") + "esta viva\n" + sn.getCuerpo());
		partida.actualizarPartida();
		System.out.println((sn.estaViva()? "":"no ") + "esta viva\n" + sn.getCuerpo());
	}
	
	@Test
	public void choca3() {
		sn = new Snake(0, 3, Direccion.DERECHA);
		sn2 = new Snake(0, 7, Direccion.IZQUIERDA);
		mapa = new Mapa(10, 10);
		partida = new Partida(mapa);
		partida.addSnake(sn);
		partida.addSnake(sn2);
		sn.crecer();
		sn2.crecer();
		partida.actualizarPartida();
		
		partida.actualizarPartida();
		
		partida.actualizarPartida();
		
	}
	
}
