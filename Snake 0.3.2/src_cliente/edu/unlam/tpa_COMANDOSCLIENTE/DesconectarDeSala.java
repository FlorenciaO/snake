package edu.unlam.tpa_COMANDOSCLIENTE;

import java.io.IOException;

import edu.unlam.tpa_PAQUETES.Comando;
import edu.unlam.tpa_PAQUETES.PaqueteSala;

public class DesconectarDeSala extends ComandoCliente {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = cliente.getPaqueteSala();
		paqueteSala.setComando(Comando.DESCONECTARDESALA);
		try {
			cliente.getSalida().writeObject(gson.toJson(paqueteSala));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
