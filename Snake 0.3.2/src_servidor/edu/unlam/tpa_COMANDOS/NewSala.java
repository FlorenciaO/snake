package edu.unlam.tpa_COMANDOS;

import java.io.IOException;

import edu.unlam.tpa_COMUNICACION.Servidor;
import edu.unlam.tpa_PAQUETES.Comando;
import edu.unlam.tpa_PAQUETES.Paquete;
import edu.unlam.tpa_PAQUETES.PaqueteSala;


public class NewSala extends ComandoServer{

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = (PaqueteSala) (gson.fromJson(cadenaLeida, PaqueteSala.class));
		try {
//			Servidor.getConector();
			if(paqueteSala.getTipo() == 0) {
//				if (ConsultasDB.agregarSala(paqueteSala.getNombreSala())){ 
				if(true) {
					Servidor.agregarSalaDisponible(paqueteSala);
					synchronized (Servidor.getAtencionNuevasSalas()) {
						Servidor.getAtencionNuevasSalas().notify();
					}
				} else {
					paqueteSala.setComando(Comando.NEWSALA);
					paqueteSala.setMsj(Paquete.msjFracaso);
					escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));
				} 
			} else {
				
//				if (ConsultasDB.agregarSalaPrivada(paqueteSala.getNombreSala(),paqueteSala.getOwnerSala())){ 
				if(true) {
					Servidor.agregarSalaDisponible(paqueteSala);
					synchronized (Servidor.getAtencionNuevasSalas()) {
						Servidor.getAtencionNuevasSalas().notify();
					}
				} else {
					paqueteSala.setComando(Comando.NEWSALA);
					paqueteSala.setMsj(Paquete.msjFracaso);
					escuchaCliente.getSalida().writeObject(gson.toJson(paqueteSala));
				} 
				
			}
		} catch (IOException e) {
			Servidor.getLog().append("Error al intentar informar al usuario " + escuchaCliente.getPaqueteUsuario().getUsername() + " que no se pudo crear la sala " + paqueteSala.getNombreSala() + System.lineSeparator());
		}		
	}

}
