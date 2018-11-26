package edu.unlam.tpa_COMANDOSCLIENTE;

import java.io.IOException;

import edu.unlam.tpa_PAQUETES.Comando;
import edu.unlam.tpa_PAQUETES.PaqueteSala;

public class EliminarSala extends ComandoCliente {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = cliente.getPaqueteSala();
		paqueteSala.setComando(Comando.ELIMINARSALA);
		try {
			cliente.getSalida().writeObject(gson.toJson(paqueteSala));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
