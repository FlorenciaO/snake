package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import java.util.ArrayList;

import javax.swing.JOptionPane;


import edu.unlam.tpa_GUI.VentanaLobby;
import edu.unlam.tpa_GUI.VentanaLogin;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteDeUsuariosYSalas;


public class InicioSesion extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		PaqueteDeUsuariosYSalas paqueteUS = gson.fromJson(cadenaLeida, PaqueteDeUsuariosYSalas.class);

		if (paqueteUS.getMsj().equals(Paquete.msjExito)) {

			ArrayList<String> salas = paqueteUS.getSalas();
			escuchaServer.getCliente().getPaqueteUsuario().setListaDeSalas(salas);
			escuchaServer.getCliente().setVentanaLobby(new VentanaLobby(escuchaServer.getCliente()));
			escuchaServer.actualizarListaSalas();			

		} else {
		
			if (paqueteUS.getMsj().equals(Paquete.msjFracaso)) {
				JOptionPane.showMessageDialog(null, "Error al iniciar sesion. Revise el usuario y la contraseña");
			} else {
				JOptionPane.showMessageDialog(null, "Ya existe una sesion iniciada con ese usuario.");
			} 
			new VentanaLogin(escuchaServer.getCliente());
		}
	}

}
