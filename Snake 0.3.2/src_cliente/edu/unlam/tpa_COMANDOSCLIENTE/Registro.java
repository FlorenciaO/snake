package edu.unlam.tpa_COMANDOSCLIENTE;

import java.io.IOException;

import edu.unlam.tpa_PAQUETES.Comando;
import edu.unlam.tpa_PAQUETES.PaqueteUsuario;


public class Registro extends ComandoCliente {

	@Override
	public void ejecutar() {
		PaqueteUsuario paqueteUsuario = cliente.getPaqueteUsuario();
		paqueteUsuario.setComando(Comando.REGISTRO);
		try {
			cliente.getSalida().writeObject(gson.toJson(paqueteUsuario));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
