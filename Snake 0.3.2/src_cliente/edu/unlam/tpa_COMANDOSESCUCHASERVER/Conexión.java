package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import java.util.ArrayList;

import edu.unlam.tpa_PAQUETES.PaqueteDeUsuariosYSalas;

public class Conexión extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		ArrayList<String> usuariosConectados = new ArrayList<String>();
		usuariosConectados = (ArrayList<String>) gson.fromJson(cadenaLeida, PaqueteDeUsuariosYSalas.class)
				.getUsuarios();

		usuariosConectados.remove(escuchaServer.getCliente().getPaqueteUsuario().getUsername());
		escuchaServer.getCliente().getPaqueteUsuario().setListaDeConectados(usuariosConectados);
		escuchaServer.actualizarLista();
	}

}
