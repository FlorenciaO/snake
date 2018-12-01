package edu.unlam.tpa_COMUNICACION;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import edu.unlam.tpa_COMANDOSCLIENTE.ComandoCliente;
import edu.unlam.tpa_GUI.VentanaJuego;
import edu.unlam.tpa_GUI.VentanaLobby;
import edu.unlam.tpa_GUI.VentanaLogin;
import edu.unlam.tpa_GUI.VentanaSala;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaquetePartida;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteTecla;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteUsuario;
import edu.unlam.tpa_UTILES.Jugador;

public class Cliente extends Thread {
	private Socket cliente;
	private static String miIp;
		
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	
	private PaqueteUsuario paqueteUsuario = new PaqueteUsuario();
	private PaqueteSala paqueteSala = new PaqueteSala();
	private PaquetePartida paquetePartida = new PaquetePartida();
	private PaqueteTecla paqueteTecla = new PaqueteTecla();

	private Map<String, VentanaSala> salasActivas = new HashMap<>();
	private VentanaJuego ventanaJuego;
	private VentanaLobby ventanaLobby;

	private int accion; 
	

	public Cliente(String newIp, int newPort) {

		try {
			cliente = new Socket(newIp, newPort);
			miIp = cliente.getInetAddress().getHostAddress();
			entrada = new ObjectInputStream(cliente.getInputStream()); 
			salida = new ObjectOutputStream(cliente.getOutputStream()); 
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Error al iniciar la app, chequee la conexion al Server" );
			System.exit(1);
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		synchronized (this) {
			try {
				new VentanaLogin(this);

				this.wait();

				ComandoCliente comando;
				Paquete paquete = new Paquete();
				
				EscuchaServer es = new EscuchaServer(this);
				es.start();
				
				while (true) {
					paquete.setComando(accion);
					comando = (ComandoCliente) paquete.getObjeto("edu.unlam.tpa_COMANDOSCLIENTE");
					comando.setCliente(this);
					comando.ejecutar();

					salida.flush();
					
					this.wait();
				}

			} catch (Exception  e) {
				JOptionPane.showMessageDialog(null, "Fallo la conexion del Cliente.");
				e.printStackTrace();
				System.exit(1);
			} 
		}
	}

	public void setAccion(int accion) {
		this.accion = accion;
	}

	public int getAccion() {
		return accion;
	}

	public Socket getSocket() {
		return cliente;
	}

	public void setSocket(final Socket cliente) {
		this.cliente = cliente;
	}

	public static String getMiIp() {
		return miIp;
	}

	public void setMiIp(final String miIp) {
		Cliente.miIp = miIp;
	}

	public ObjectInputStream getEntrada() {
		return entrada;
	}

	public void setEntrada(final ObjectInputStream entrada) {
		this.entrada = entrada;
	}

	public ObjectOutputStream getSalida() {
		return salida;
	}

	public void setSalida(final ObjectOutputStream salida) {
		this.salida = salida;
	}

	public PaqueteUsuario getPaqueteUsuario() {
		return paqueteUsuario;
	}


	public Map<String, VentanaSala> getSalasActivas() {
		return salasActivas;
	}

	public PaqueteSala getPaqueteSala() {
		return paqueteSala;
	}

	public void setPaqueteSala(PaqueteSala paqueteSala) {
		this.paqueteSala = paqueteSala;
	}
	
	public PaquetePartida getPaquetePartida() {
		return paquetePartida;
	}

	public void setPaquetePartida(PaquetePartida paquetePartida) {
		this.paquetePartida = paquetePartida;
	}
	

	public VentanaJuego getVentanaJuego() {
		return ventanaJuego;
	}

	public void setVentanaJuego(VentanaJuego ventanaJuego) {
		this.ventanaJuego = ventanaJuego;
	}

	public static void main(String args[]) {
		new Cliente("localHost", 1234).start();
	}

	public void actualizarVentanaJuego() {
		ventanaJuego.getPanelJuego().repaint();
		ventanaJuego.getControlsPanel().actualizarPuntajes();
	}

	public PaqueteTecla getPaqueteTecla() {
		return paqueteTecla;
	}

	public void setPaqueteTecla(PaqueteTecla paqueteTecla) {
		this.paqueteTecla = paqueteTecla;
	}

	public Jugador obtenerGanador() {
		int max = - 1;
		int indiceMax = -1;
		ArrayList<Jugador> jugadores = paquetePartida.getJugadores(); 
		for(int i = 0; i < jugadores.size(); i++) {
			if(jugadores.get(i).getPuntos() > max) {
				max = jugadores.get(i).getPuntos();
				indiceMax = i;
			}				
		}
		return jugadores.get(indiceMax);
	}

	public VentanaLobby getVentanaLobby() {
		return ventanaLobby;
	}

	public void setVentanaLobby(VentanaLobby ventanaLobby) {
		this.ventanaLobby = ventanaLobby;
	}
	
}
