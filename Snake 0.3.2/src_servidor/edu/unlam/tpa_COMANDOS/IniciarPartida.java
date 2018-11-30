package edu.unlam.tpa_COMANDOS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.unlam.tpa_COMUNICACION.EscuchaCliente;
import edu.unlam.tpa_COMUNICACION.Servidor;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;
import edu.unlam.tpa_UTILES.HiloPartida;
import edu.unlam.tpa_UTILES.Jugador;

public class IniciarPartida extends ComandoServer{

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = (PaqueteSala) (gson.fromJson(cadenaLeida, PaqueteSala.class));
		try {
			if(Servidor.getNombresSalasDisponibles().contains(paqueteSala.getNombreSala()) 
					&& Servidor.getSalas().get(paqueteSala.getNombreSala()).getUsuariosConectados().contains(paqueteSala.getCliente())) {
				List<Jugador> jugadores = new ArrayList<>();
				List<EscuchaCliente> clientesJugando = new ArrayList<>();
				
				for(EscuchaCliente cliente : Servidor.getClientesConectados()) {
					if(Servidor.getSalas().get(paqueteSala.getNombreSala()).
							getUsuariosConectados().contains(cliente.getPaqueteUsuario().getUsername())) {
						jugadores.add(new Jugador(cliente.getPaqueteUsuario().getUsername()));
						paqueteSala = Servidor.getSalas().get(paqueteSala.getNombreSala());	
						paqueteSala.setComando(Comando.INICIARPARTIDA);
						cliente.getSalida().writeObject(gson.toJson(paqueteSala));
						clientesJugando.add(cliente);
					}
				}				
				HiloPartida partida = new HiloPartida(jugadores, clientesJugando);
				Servidor.addPartida(partida);
				partida.start();
				
			} else {
				paqueteSala.setComando(Comando.INICIARPARTIDA);
				paqueteSala.setMsj(Paquete.msjFracaso);
				escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));

			}
		} catch (IOException e) {
			Servidor.getLog().append("Error al intentar comenzar partida del usuario " + escuchaCliente.getPaqueteUsuario().getUsername() + System.lineSeparator() );
		}
	}

}
