package edu.unlam.tpa_COMANDOS;

import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;

public class IniciarPartida extends ComandoServer{

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = (PaqueteSala) (gson.fromJson(cadenaLeida, PaqueteSala.class));
		paqueteSala.setComando(Comando.INICIARPARTIDA);
		
	}

}
