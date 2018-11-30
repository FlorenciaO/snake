package edu.unlam.tpa_COMANDOSCLIENTE;

import java.io.IOException;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteUsuario;


public class Desconectar extends ComandoCliente {

	@Override
	public void ejecutar() {	
		PaqueteUsuario paqueteUsuario = cliente.getPaqueteUsuario();
		paqueteUsuario.setIp(Cliente.getMiIp());
		paqueteUsuario.setComando(Comando.DESCONECTAR);
		try {
			cliente.getSalida().writeObject(gson.toJson(paqueteUsuario));
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
