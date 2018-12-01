package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.unlam.tpa_GUI.VentanaLobby;
import edu.unlam.tpa_GUI.VentanaLogin;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteDeUsuariosYSalas;

public class Registro extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		Paquete paquete = gson.fromJson(cadenaLeida, Paquete.class);
		if (paquete.getMsj().equals(Paquete.msjExito)) {
			JOptionPane.showMessageDialog(null, "Registro exitoso.");
			PaqueteDeUsuariosYSalas paqueteUS = gson.fromJson(cadenaLeida, PaqueteDeUsuariosYSalas.class);
			ArrayList<String> salas = paqueteUS.getSalas();
			escuchaServer.getCliente().getPaqueteUsuario().setListaDeSalas(salas);
			new VentanaLobby(escuchaServer.getCliente());
			escuchaServer.actualizarListaSalas();
		} else {
			if (paquete.getMsj().equals(Paquete.msjFracaso)) {
				JOptionPane.showMessageDialog(null, "No se pudo registrar.");
			} else {
				JOptionPane.showMessageDialog(null, "El usuario ya se encuentra en uso.");
			}
			new VentanaLogin(escuchaServer.getCliente());
		}
	}

}
