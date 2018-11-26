package edu.unlam.tpa_COMANDOSCLIENTE;

import java.io.IOException;

import edu.unlam.tpa_PAQUETES.Comando;
import edu.unlam.tpa_PAQUETES.PaqueteUsuario;


public class InicioSesion extends ComandoCliente {

	@Override
	public void ejecutar() {
		PaqueteUsuario paqueteUsuario = cliente.getPaqueteUsuario();
		paqueteUsuario.setComando(Comando.INICIOSESION);
		try {
			cliente.getSalida().writeObject(gson.toJson(paqueteUsuario));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
