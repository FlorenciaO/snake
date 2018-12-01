package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import java.io.IOException;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_GUI.VentanaPartidaFinalizada;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.PaquetePartida;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;
import edu.unlam.tpa_UTILES.Jugador;

public class TerminarPartida extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		PaquetePartida paquetePartida = gson.fromJson(cadenaLeida, PaquetePartida.class);
		Cliente cliente = escuchaServer.getCliente();
		
		PaqueteSala paqueteSala = cliente.getPaqueteSala();
		paqueteSala.setComando(Comando.ABANDONARPARTIDA);
		
		// Llama al abandonar partida de comando servidor
		try {
			cliente.getVentanaJuego().dispose(); // Cierra la ventana de juego
			cliente.setPaquetePartida(paquetePartida);
			Jugador ganador = cliente.obtenerGanador(); // Busca el ganador en la lista de jugadores de paquete partida y lo retorna
			cliente.getSalida().writeObject(gson.toJson(paqueteSala));
			new VentanaPartidaFinalizada(ganador, cliente);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
