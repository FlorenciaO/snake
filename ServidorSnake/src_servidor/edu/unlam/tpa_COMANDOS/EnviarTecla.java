package edu.unlam.tpa_COMANDOS;

import edu.unlam.tpa_COMUNICACION.Servidor;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteTecla;
import edu.unlam.tpa_UTILES.HiloPartida;

public class EnviarTecla extends ComandoServer {

	@Override
	public void ejecutar() {
		
		PaqueteTecla paqueteTecla = (PaqueteTecla) (gson.fromJson(cadenaLeida, PaqueteTecla.class));
		boolean encontreJugador = false;
		try
		{
			for(HiloPartida partida: Servidor.partidas) {
				if(partida.buscarJugadorYcambiarTecla(paqueteTecla.getNombreJugador(), paqueteTecla.getTeclaPresionada())) {
					encontreJugador = true;
					break;
				}		
			}
			if(!encontreJugador) {
				throw new Exception("No existe jugador en ninguna partida");
			}
			
		} catch(Exception e) {
			Servidor.getLog().append(e.getMessage());
		}
	}

}
