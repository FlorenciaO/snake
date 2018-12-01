package edu.unlam.tpa_COMANDOS;

import java.io.IOException;

import edu.unlam.tpa_COMUNICACION.EscuchaCliente;
import edu.unlam.tpa_COMUNICACION.Servidor;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;
import edu.unlam.tpa_UTILES.HiloPartida;

public class AbandonarPartida extends ComandoServer {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = (PaqueteSala) (gson.fromJson(cadenaLeida, PaqueteSala.class));
		
		try {
			Servidor.getSalas().get(paqueteSala.getNombreSala()).eliminarUsuario(paqueteSala.getCliente());
			paqueteSala = Servidor.getSalas().get(paqueteSala.getNombreSala());


			boolean partidaTermino = false;
			boolean elimineJugador = false;			
			for(HiloPartida partida: Servidor.partidas) {
					if(partida.buscarJugadorYeliminarLo(escuchaCliente.getPaqueteUsuario().getUsername())) {
						elimineJugador = true;
						if(partida.getJugadores().isEmpty()) {
							paqueteSala.setEnJuego();
							Servidor.eliminarSalaDisponible(paqueteSala.getNombreSala());
							paqueteSala.setComando(Comando.ELIMINARSALA);
							for(EscuchaCliente cliente : Servidor.getClientesConectados()) {
								if(!cliente.getPaqueteUsuario().getUsername().equals(escuchaCliente.getPaqueteUsuario().getUsername()))
									cliente.getSalida().writeObject(gson.toJson(paqueteSala));
							}
							partidaTermino = true;
							
						}
						break;
					}
					
			}
			
			if(!elimineJugador) {
				throw new Exception("No existe jugador en ninguna partida");
			}
			
			paqueteSala.setComando(Comando.DESCONECTARDESALA);
			escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));
			if(partidaTermino) {
				paqueteSala.setComando(Comando.ELIMINARSALA);
				escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));
				
			}
		} catch (IOException e) {
			Servidor.getLog().append("Error del usuario " + escuchaCliente.getPaqueteUsuario().getUsername() + " al abandonar partida");
		} catch (Exception e2) {
			Servidor.getLog().append(e2.getMessage());
		}
		
	}

}
