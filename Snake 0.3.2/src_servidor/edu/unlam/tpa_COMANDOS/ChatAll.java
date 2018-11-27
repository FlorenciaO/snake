package edu.unlam.tpa_COMANDOS;

import java.io.IOException;
import java.net.Socket;

import edu.unlam.tpa_COMUNICACION.EscuchaCliente;
import edu.unlam.tpa_COMUNICACION.Servidor;
import edu.unlam.tpa_PAQUETES.PaqueteMensaje;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;


public class ChatAll extends ComandoServer {

	@Override
	public void ejecutar() {
		PaqueteMensaje paqueteMensaje = (PaqueteMensaje) (gson.fromJson(cadenaLeida, PaqueteMensaje.class));
		try {
			paqueteMensaje.setComando(Comando.CHATALL);
			
			Socket s1 = Servidor.getMapConectados().get(paqueteMensaje.getUserEmisor());
			for (EscuchaCliente conectado : Servidor.getClientesConectados()) {
				if(conectado.getSocket() != s1)	{
					conectado.getSalida().writeObject(gson.toJson(paqueteMensaje));
				}
			}
		} catch (IOException e) {
			Servidor.getLog().append("Error al intentar mandar el mensaje de "+ paqueteMensaje.getUserEmisor() + System.lineSeparator());
		}
		
	}

}
