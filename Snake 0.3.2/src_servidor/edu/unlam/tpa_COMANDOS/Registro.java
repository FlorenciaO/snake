package edu.unlam.tpa_COMANDOS;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;

import edu.unlam.tpa_COMUNICACION.Servidor;
import edu.unlam.tpa_PAQUETES.Comando;
import edu.unlam.tpa_PAQUETES.Paquete;
import edu.unlam.tpa_PAQUETES.PaqueteDeUsuariosYSalas;
import edu.unlam.tpa_PAQUETES.PaqueteUsuario;



public class Registro extends ComandoServer {

	@Override
	public void ejecutar() {
		PaqueteUsuario paqueteUsuario = (PaqueteUsuario) (gson.fromJson(cadenaLeida, PaqueteUsuario.class));
		try {
//			Servidor.getConector();
				
//				if (ConsultasDB.registrarUser(paqueteUsuario)){
			if(true) {

					PaqueteDeUsuariosYSalas pus = new PaqueteDeUsuariosYSalas(Servidor.getUsuariosConectados(),
							Servidor.getNombresSalasDisponibles(),Servidor.getSalasPrivadasNombresDisponibles());
					pus.setComando(Comando.REGISTRO);
					pus.setMsj(Paquete.msjExito);

//					Servidor.conectarUsuario(paqueteUsuario.getUsername());
					
					escuchaCliente.getSalida().writeObject(gson.toJson(pus));

					synchronized (Servidor.getAtencionConexiones()) {
						Servidor.getAtencionConexiones().notify();
					}
				} else {
					paqueteUsuario.setMsj(Paquete.msjFracaso);
					escuchaCliente.getSalida().writeObject(gson.toJson(paqueteUsuario));
				} 
			
		} catch (JsonSyntaxException | IOException  e) {
			Servidor.getLog().append("Fallo al intentar informar al usuario "+ paqueteUsuario.getUsername() + " sobre su intento de registro." + System.lineSeparator());
		}	
	}

}
