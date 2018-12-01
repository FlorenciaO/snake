package edu.unlam.tpa_COMANDOS;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;

import edu.unlam.tpa_COMUNICACION.Servidor;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteDeUsuariosYSalas;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteUsuario;
import edu.unlam.tpa_UTILES.ABM;


public class InicioSesion extends ComandoServer {

	@SuppressWarnings({ "static-access", "unused" })
	@Override
	public void ejecutar() {

		PaqueteUsuario paqueteUsuario = (PaqueteUsuario) (gson.fromJson(cadenaLeida, PaqueteUsuario.class));

		try {

			if(!Servidor.getUsuariosConectados().contains(paqueteUsuario.getUsername())) {
				if (Servidor.getConector().habilitarConexion(new ABM(paqueteUsuario.getUsername(), paqueteUsuario.getPassword()))) {

						escuchaCliente.setPaqueteUsuario(paqueteUsuario);
						PaqueteDeUsuariosYSalas pus = new PaqueteDeUsuariosYSalas(Servidor.getUsuariosConectados(),
								Servidor.getNombresSalasDisponibles());
						pus.setComando(Comando.INICIOSESION);
						pus.setMsj(Paquete.msjExito);
	
						Servidor.conectarUsuario(paqueteUsuario.getUsername());
						
						escuchaCliente.getSalida().writeObject(gson.toJson(pus));
	
						synchronized (Servidor.getAtencionConexiones()) {
							Servidor.getAtencionConexiones().notify();
						}
	
					} else {
						paqueteUsuario.setMsj(Paquete.msjFracaso);
						escuchaCliente.getSalida().writeObject(gson.toJson(paqueteUsuario));
					} 
				
				} else {
					paqueteUsuario.setMsj(Paquete.msjFallo);
					escuchaCliente.getSalida().writeObject(gson.toJson(paqueteUsuario));
				}
			
				
		} catch (JsonSyntaxException | IOException e) {
			Servidor.getLog().append("Fallo al intentar informar al usuario "+ paqueteUsuario.getUsername() + " sobre su intento de inicio de sesion." + System.lineSeparator());
		} 
	}
}
