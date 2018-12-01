package edu.unlam.tpa_COMANDOS;

import java.io.IOException;

import edu.unlam.tpa_COMUNICACION.Servidor;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;

public class EntrarSala extends ComandoServer {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = (PaqueteSala) (gson.fromJson(cadenaLeida, PaqueteSala.class));
		paqueteSala.setComando(Comando.ENTRARSALA);
		try {
			if ((Servidor.getSalasPrivadasNombresDisponibles().contains(paqueteSala.getNombreSala())
					&& !Servidor.getSalas().get(paqueteSala.getNombreSala()).getUsuariosConectados()
							.contains(paqueteSala.getCliente()))
					|| (Servidor.getNombresSalasDisponibles().contains(paqueteSala.getNombreSala())
							&& !Servidor.getSalas().get(paqueteSala.getNombreSala()).getUsuariosConectados()
									.contains(paqueteSala.getCliente()))) {
				if (!Servidor.getSalas().get(paqueteSala.getNombreSala()).enJuego()) {
					Servidor.getSalas().get(paqueteSala.getNombreSala()).agregarUsuario(paqueteSala.getCliente());
					paqueteSala = Servidor.getSalas().get(paqueteSala.getNombreSala());
					paqueteSala.setMsj(Paquete.msjExito);
					paqueteSala.setComando(Comando.ENTRARSALA);
					escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));

					synchronized (Servidor.getAtencionConexionesSalas()) {
						Servidor.getAtencionConexionesSalas().setNombreSala(paqueteSala.getNombreSala());
						Servidor.getAtencionConexionesSalas().notify();
					}
				}
				else {
					paqueteSala.setMsj(Paquete.msjFallo);
					escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));
				}

			} else {
				paqueteSala.setMsj(Paquete.msjFracaso);
				escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));
			}
		} catch (IOException e) {
			Servidor.getLog()
					.append("Error al intentar informar al usuario " + escuchaCliente.getPaqueteUsuario().getUsername()
							+ " sobre el intento de entrar a la sala " + paqueteSala.getNombreSala()
							+ System.lineSeparator());
		}

	}

}
