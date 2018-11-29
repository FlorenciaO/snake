package edu.unlam.tpa_COMANDOSCLIENTE;

import java.io.IOException;

import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;

public class IniciarPartida extends ComandoCliente{
	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = cliente.getPaqueteSala();
		paqueteSala.setComando(Comando.INICIARPARTIDA);
		try {
			cliente.getSalida().writeObject(gson.toJson(paqueteSala));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
