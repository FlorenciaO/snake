package edu.unlam.tpa_COMANDOSCLIENTE;

import java.io.IOException;

import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteTecla;

public class EnviarTecla extends ComandoCliente {

	@Override
	public void ejecutar() {
		PaqueteTecla paqueteTecla = cliente.getPaqueteTecla();
		paqueteTecla.setComando(Comando.ENVIARTECLA);
		try {
			cliente.getSalida().writeObject(gson.toJson(paqueteTecla));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
