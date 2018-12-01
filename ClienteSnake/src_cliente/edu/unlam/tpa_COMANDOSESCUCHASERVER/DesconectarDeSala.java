package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;

public class DesconectarDeSala extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = gson.fromJson(cadenaLeida, PaqueteSala.class);
		Cliente cliente = escuchaServer.getCliente();
		if(cliente.getSalasActivas().containsKey(paqueteSala.getNombreSala())) {
			cliente.getSalasActivas().remove(paqueteSala.getNombreSala());
		}
		cliente.getVentanaLobby().setVisible(true);
	}

}
