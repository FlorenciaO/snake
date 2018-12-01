package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import java.util.ArrayList;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteDeUsuariosYSalas;

public class InicioUnaPartida extends ComandoEscuchaServer {
	@Override
	public void ejecutar() {
		Cliente cliente = escuchaServer.getCliente();
		PaqueteDeUsuariosYSalas paqueteDUS = gson.fromJson(cadenaLeida, PaqueteDeUsuariosYSalas.class);

		ArrayList<String> listadoSalas = paqueteDUS.getSalas();
		cliente.getPaqueteUsuario().setListaDeSalas(listadoSalas);
		escuchaServer.actualizarListaSalas();

	}
}
