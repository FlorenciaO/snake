package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.PaquetePartida;

public class Paint extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		Cliente cliente = escuchaServer.getCliente();
		PaquetePartida paquetePartida = gson.fromJson(cadenaLeida, PaquetePartida.class);
		cliente.setPaquetePartida(paquetePartida);
		cliente.actualizarVentanaJuego();
	}
	
}
