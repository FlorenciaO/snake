package edu.unlam.tpa.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.unlam.tpa_JUEGO.Direccion;
import edu.unlam.tpa_JUEGO.Fruta;
import edu.unlam.tpa_JUEGO.Mapa;
import edu.unlam.tpa_JUEGO.Partida;
import edu.unlam.tpa_JUEGO.Posicion;
import edu.unlam.tpa_JUEGO.Snake;

public class TestJuego {
	
	Snake snake;
	
//	@Test
	public void crece() {
		snake = new Snake(0, 0, Direccion.DERECHA);
		
		snake.crecer();
		snake.crecer();
		snake.crecer();
		System.out.println(snake.getCuerpo());
		ArrayList<Posicion> cuerpo = new ArrayList<>();
		cuerpo.add(0, new Posicion(0, 0));
		cuerpo.add(0, new Posicion(1, 0));
		cuerpo.add(0, new Posicion(2, 0));
		cuerpo.add(0, new Posicion(3, 0));	
		cuerpo.add(0, new Posicion(4, 0));	//kbza
		
		assertEquals(cuerpo, snake.getCuerpo());
	}
		
//	@Test
	public void seMueveCuerpo() {
		snake = new Snake(0, 0, Direccion.DERECHA);
		
		snake.crecer();
		snake.crecer();
		snake.crecer();
		snake.moverse();
		snake.cambiarDireccion(Direccion.ABAJO);
		snake.moverse();
		
		ArrayList<Posicion> cuerpo = new ArrayList<>();
		cuerpo.add(0, new Posicion(2, 0));
		cuerpo.add(0, new Posicion(3, 0));
		cuerpo.add(0, new Posicion(4, 0));
		cuerpo.add(0, new Posicion(5, 0));
		cuerpo.add(0, new Posicion(5, 1));
		
		assertEquals(cuerpo, snake.getCuerpo());
	}
	
	Mapa mapa;
	Partida partida;
	
//	@Test
	public void muereBordeArriba() {
		snake = new Snake(1, 1, Direccion.DERECHA);
		partida = new Partida(new Mapa(10, 10));
		partida.addSnake(snake);
		
		partida.actualizarPartida(true);
		snake.cambiarDireccion(Direccion.ARRIBA);
		partida.actualizarPartida();
		
		assertEquals(false, snake.estaViva());
	}
	
//	@Test
	public void muereBordeIzquierdo() {
		snake = new Snake(1, 2, Direccion.ABAJO);
		partida = new Partida(new Mapa(10, 10));
		partida.addSnake(snake);
		
		partida.actualizarPartida();
		snake.cambiarDireccion(Direccion.IZQUIERDA);
		partida.actualizarPartida();
		
		assertEquals(false, snake.estaViva());
	}
	
//	@Test
	public void muereBordeAbajo() {
		snake = new Snake(1, 9, Direccion.DERECHA);
		partida = new Partida(new Mapa(10, 10));
		partida.addSnake(snake);
		
		partida.actualizarPartida();
		snake.cambiarDireccion(Direccion.ABAJO);
		partida.actualizarPartida();
		
		assertEquals(false, snake.estaViva());
	}
	
//	@Test
	public void muereBordeDerecho() {
		snake = new Snake(9, 3, Direccion.ABAJO);
		partida = new Partida(new Mapa(10, 10));
		partida.addSnake(snake);
		
		partida.actualizarPartida();
		snake.cambiarDireccion(Direccion.DERECHA);
		partida.actualizarPartida();
		
		assertEquals(false, snake.estaViva());
	}
	
	Fruta fruta;
//	@Test
	public void comeYcrece() {
		snake = new Snake(1, 1, Direccion.DERECHA);
		fruta = new Fruta(3, 1);
		partida = new Partida(new Mapa(10, 10));
		partida.addSnake(snake);
		partida.addFruta(fruta);
		
		partida.actualizarPartida();
		partida.actualizarPartida();
		
		ArrayList<Posicion> cuerpo = new ArrayList<>();
		cuerpo.add(0, new Posicion(1, 1));
		cuerpo.add(0, new Posicion(2, 1));
		cuerpo.add(0, new Posicion(3, 1));	//kbza
		
		assertEquals(cuerpo, snake.getCuerpo());
	}
	
//	@Test
	public void noChocaConsigoMismaLoop() {
		snake = new Snake(3, 3, Direccion.DERECHA);
		partida = new Partida(new Mapa(10, 10));
		partida.addSnake(snake);
		
		snake.crecer();
		snake.crecer();
//		snake.crecer();
		snake.cambiarDireccion(Direccion.ABAJO);
		partida.actualizarPartida(true);
		

		snake.cambiarDireccion(Direccion.IZQUIERDA);
		partida.actualizarPartida(true);
		

		snake.cambiarDireccion(Direccion.ARRIBA);
		assertEquals(true, snake.estaViva());
		partida.actualizarPartida(true);
		

		assertEquals(true, snake.estaViva());
		snake.cambiarDireccion(Direccion.DERECHA);
		partida.actualizarPartida(true);
		

		assertEquals(true, snake.estaViva());
		snake.cambiarDireccion(Direccion.ABAJO);
		partida.actualizarPartida(true);
	}
	
	Snake snake2;
//	@Test
	public void chocanCabezas() {
		snake = new Snake(1, 1, Direccion.DERECHA);
		snake2 = new Snake(5, 1, Direccion.IZQUIERDA);
		partida = new Partida(new Mapa(10, 10));
		partida.addSnake(snake);
		partida.addSnake(snake2);
		
		partida.actualizarPartida();
		assertEquals(false, snake.estaViva());
		assertEquals(false, snake2.estaViva());
	}
	
//	@Test
	public void casiChocanmuereUnaDelasDos() {
		snake = new Snake(2, 1, Direccion.DERECHA);
		snake2 = new Snake(5, 1, Direccion.IZQUIERDA);
		partida = new Partida(new Mapa(10, 10));
		partida.addSnake(snake);
		partida.addSnake(snake2);
		

		assertEquals(true, snake.estaViva());
		assertEquals(true, snake2.estaViva());
		
		snake.cambiarDireccion(Direccion.ABAJO);
		partida.actualizarPartida(true);		
		assertEquals(true, snake.estaViva());
		assertEquals(false, snake2.estaViva());
	}
	
//	@Test
	public void loopean() {
		snake = new Snake(1, 1, Direccion.DERECHA);
		snake2 = new Snake(3, 1, Direccion.DERECHA);
		partida = new Partida(new Mapa(10, 10));
		partida.addSnake(snake);
		partida.addSnake(snake2);
		
		partida.actualizarPartida(true);
		partida.actualizarPartida(true);
		snake2.cambiarDireccion(Direccion.ABAJO);
		partida.actualizarPartida(true);
		partida.actualizarPartida(true);
	}
	
//	@Test
	public void loopeanYunoCome() {
		snake = new Snake(1, 1, Direccion.DERECHA);
		snake2 = new Snake(3, 1, Direccion.DERECHA);
		fruta = new Fruta(6, 1);
		partida = new Partida(new Mapa(10, 10));
		partida.addSnake(snake);
		partida.addSnake(snake2);
		partida.addFruta(fruta);
		
		partida.actualizarPartida(true);
		partida.actualizarPartida(true);
		partida.actualizarPartida(true);
		partida.actualizarPartida(true);
		partida.actualizarPartida(true);
	}
	
//	@Test
	public void loopeanCola() {
		snake = new Snake(4, 2, Direccion.DERECHA);
		snake2 = new Snake(6, 4, Direccion.IZQUIERDA);
		
		partida = new Partida(new Mapa(10, 10));
		partida.addSnake(snake);
		partida.addSnake(snake2);
		
		snake.crecer();
		snake2.crecer();
		
		snake.cambiarDireccion(Direccion.ABAJO);
		snake2.cambiarDireccion(Direccion.ARRIBA);
		snake.crecer();
		snake2.crecer();
		partida.actualizarPartida(true);
		snake.cambiarDireccion(Direccion.IZQUIERDA);
		snake2.cambiarDireccion(Direccion.DERECHA);
		partida.actualizarPartida(true);
		partida.actualizarPartida(true);

		snake.cambiarDireccion(Direccion.ARRIBA);
		snake2.cambiarDireccion(Direccion.ABAJO);
		partida.actualizarPartida(true);
		partida.actualizarPartida(true);
	}
	
//	@Test
	public void chocanCabezasPerpendicularmente()  {
		snake = new Snake(2, 1, Direccion.DERECHA);
		snake2 = new Snake(4, 3, Direccion.ARRIBA);
		
		partida = new Partida(new Mapa(10, 10));
		partida.addSnake(snake);
		partida.addSnake(snake2);
		
		partida.actualizarPartida(true);
		partida.actualizarPartida(true);
	}
		
}
