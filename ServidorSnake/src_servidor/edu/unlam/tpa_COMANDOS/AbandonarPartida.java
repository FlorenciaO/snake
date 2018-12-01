package edu.unlam.tpa_COMANDOS;

import java.io.IOException;

import edu.unlam.tpa_COMUNICACION.Servidor;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;
import edu.unlam.tpa_UTILES.HiloPartida;

public class AbandonarPartida extends ComandoServer {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = (PaqueteSala) (gson.fromJson(cadenaLeida, PaqueteSala.class));
		
		try {
			System.out.println("solamente abandona lpm");
			Servidor.getSalas().get(paqueteSala.getNombreSala()).eliminarUsuario(paqueteSala.getCliente());
			paqueteSala = Servidor.getSalas().get(paqueteSala.getNombreSala());
			paqueteSala.setComando(Comando.DESCONECTARDESALA);
							
			synchronized(Servidor.getAtencionConexionesSalas()){
				Servidor.getAtencionConexionesSalas().setNombreSala(paqueteSala.getNombreSala());
				Servidor.getAtencionConexionesSalas().notify();
			}
			
			boolean elimineJugador = false;
			System.out.println("llega aca sin error?");
			
			for(HiloPartida partida: Servidor.partidas) {
				if(partida.buscarJugadorYeliminarLo(escuchaCliente.getPaqueteUsuario().getUsername())) {
					elimineJugador = true;
					break;
				}		
			}
			
			if(!elimineJugador) {
				throw new Exception("No existe jugador en ninguna partida");
			}
			
			escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));
		} catch (IOException e) {
			Servidor.getLog().append("Error del usuario " + escuchaCliente.getPaqueteUsuario().getUsername() + " al abandonar partida");
		} catch (Exception e2) {
			Servidor.getLog().append(e2.getMessage());
		}
		
	}

}
