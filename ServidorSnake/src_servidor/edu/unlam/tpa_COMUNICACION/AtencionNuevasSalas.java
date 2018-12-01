package edu.unlam.tpa_COMUNICACION;

import com.google.gson.Gson;

import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteDeUsuariosYSalas;


public class AtencionNuevasSalas extends Thread {

	private final Gson gson = new Gson();

	public AtencionNuevasSalas() {
	}

	public void run() {
		synchronized (this) {
			try {
				while (true) {

					wait();

					PaqueteDeUsuariosYSalas psu =  (PaqueteDeUsuariosYSalas) new PaqueteDeUsuariosYSalas(null,Servidor.getNombresSalasDisponibles())
							.clone();
					psu.setComando(Comando.NEWSALA);
					psu.setMsj(Paquete.msjExito);
					String s = gson.toJson(psu);
					for (EscuchaCliente conectado : Servidor.getClientesConectados())
							conectado.getSalida().writeObject(s);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}