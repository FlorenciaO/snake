package edu.unlam.tpa_COMANDOS;


import edu.unlam.tpa_COMUNICACION.Servidor;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;

public class NewSala extends ComandoServer {

	@SuppressWarnings("unused")
	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = (PaqueteSala) (gson.fromJson(cadenaLeida, PaqueteSala.class));
		try {

			Servidor.agregarSalaDisponible(paqueteSala);
			synchronized (Servidor.getAtencionNuevasSalas()) {
				Servidor.getAtencionNuevasSalas().notify();
			}
		} catch (Exception e) {
			Servidor.getLog()
					.append("Error al intentar informar al usuario " + escuchaCliente.getPaqueteUsuario().getUsername()
							+ " que no se pudo crear la sala " + paqueteSala.getNombreSala() + System.lineSeparator());
		}
	}

}
