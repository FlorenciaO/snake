package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_GUI.VentanaPartidaFinalizada;
import edu.unlam.tpa_PAQUETESCLIENTE.PaquetePartida;
import edu.unlam.tpa_UTILES.Jugador;

public class TerminarPartida extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		PaquetePartida paquetePartida = gson.fromJson(cadenaLeida, PaquetePartida.class);
		Cliente cliente = escuchaServer.getCliente();
		cliente.getVentanaJuego().dispose(); // Cierra la ventana de juego
		cliente.setPaquetePartida(paquetePartida);
		Jugador ganador = cliente.obtenerGanador(); // Busca el ganador en la lista de jugadores de paquete partida y lo retorna
		new VentanaPartidaFinalizada(ganador, cliente);
	}

}
