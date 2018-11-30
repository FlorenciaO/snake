package edu.unlam.tpa_COMANDOS;

import java.io.IOException;

import edu.unlam.tpa_COMUNICACION.EscuchaCliente;
import edu.unlam.tpa_COMUNICACION.Servidor;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;

public class EliminarSala extends ComandoServer {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = (PaqueteSala) (gson.fromJson(cadenaLeida, PaqueteSala.class));
		try {
			if(Servidor.getNombresSalasDisponibles().contains(paqueteSala.getNombreSala()) 
					&& Servidor.getSalas().get(paqueteSala.getNombreSala()).getUsuariosConectados().contains(paqueteSala.getCliente())) {
				
				for(EscuchaCliente cliente : Servidor.getClientesConectados()) {
						paqueteSala = Servidor.getSalas().get(paqueteSala.getNombreSala());	
						paqueteSala.setComando(Comando.ELIMINARSALA);
						cliente.getSalida().writeObject(gson.toJson(paqueteSala));
				}
				
				Servidor.eliminarSalaDisponible(paqueteSala.getNombreSala());

				
			} else {
				paqueteSala.setComando(Comando.ELIMINARSALA);
				paqueteSala.setMsj(Paquete.msjFracaso);
				escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));

			}
		} catch (IOException e) {
			Servidor.getLog().append("Error al intentar eliminar sala" + escuchaCliente.getPaqueteUsuario().getUsername() + " sobre su intento de desconectarse de la sala " + paqueteSala.getNombreSala() + System.lineSeparator() );
		}		
		
	}

}
