package edu.unlam.tpa_COMANDOS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.unlam.tpa_COMUNICACION.EscuchaCliente;
import edu.unlam.tpa_COMUNICACION.Servidor;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteDeUsuariosYSalas;
import edu.unlam.tpa_PAQUETESCLIENTE.PaquetePartida;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;
import edu.unlam.tpa_UTILES.HiloPartida;
import edu.unlam.tpa_UTILES.Jugador;

public class IniciarPartida extends ComandoServer {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = (PaqueteSala) (gson.fromJson(cadenaLeida, PaqueteSala.class));
		try {
			if (Servidor.getNombresSalasDisponibles().contains(paqueteSala.getNombreSala()) && Servidor.getSalas()
					.get(paqueteSala.getNombreSala()).getUsuariosConectados().contains(paqueteSala.getCliente())) {
				ArrayList<Jugador> jugadores = new ArrayList<>();
				ArrayList<EscuchaCliente> clientesJugando = new ArrayList<>();
				System.out.println("Creando partida");
				int color = 0;
				int snake = 0;
				
//				Servidor.modificarSalaDisponible(paqueteSala.getNombreSala());
			
				for (EscuchaCliente cliente : Servidor.getClientesConectados()) {
					if (Servidor.getSalas().get(paqueteSala.getNombreSala()).getUsuariosConectados()
							.contains(cliente.getPaqueteUsuario().getUsername())) {
						jugadores.add(new Jugador(cliente.getPaqueteUsuario().getUsername(), color++, snake++));
						clientesJugando.add(cliente);
					}
					
				}
				Servidor.modificarSalaDisponible(paqueteSala.getNombreSala());

				HiloPartida partida = new HiloPartida(jugadores, clientesJugando);
				PaquetePartida paquetePartida = partida.getPaquetePartida();
				paquetePartida.setComando(Comando.INICIARPARTIDA);
				paquetePartida.setMsj(Paquete.msjExito);
				for (EscuchaCliente conectado : clientesJugando) {
					try {
						String s = gson.toJson(paquetePartida);
						conectado.getSalida().writeObject(s);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				Servidor.addPartida(partida);
				partida.start();

			} else {
				paqueteSala.setComando(Comando.INICIARPARTIDA);
				paqueteSala.setMsj(Paquete.msjFracaso);
				escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));

			}
		} catch (IOException e) {
			Servidor.getLog().append("Error al intentar comenzar partida del usuario "
					+ escuchaCliente.getPaqueteUsuario().getUsername() + System.lineSeparator());
		}
	}

}
