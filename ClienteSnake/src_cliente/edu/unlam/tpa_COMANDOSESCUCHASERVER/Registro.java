package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteDeUsuariosYSalas;

public class Registro extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		Paquete paquete = gson.fromJson(cadenaLeida, Paquete.class);
		if (paquete.getMsj().equals(Paquete.msjExito)) {
//			JOptionPane.showMessageDialog(null, "Registro exitoso.");
//			PaqueteDeUsuariosYSalas paqueteUS = gson.fromJson(cadenaLeida, PaqueteDeUsuariosYSalas.class);
//			ArrayList<String> salas = paqueteUS.getSalas();
//			escuchaServer.getCliente().getPaqueteUsuario().setListaDeSalas(salas);
//			new VentanaPrincipal(escuchaServer.getCliente());
//			escuchaServer.actualizarListaSalas();
			System.out.println("Me registre");
		} else {
			if (paquete.getMsj().equals(Paquete.msjFracaso)) {
				JOptionPane.showMessageDialog(null, "No se pudo registrar.");
			} else {
				JOptionPane.showMessageDialog(null, "El usuario ya se encuentra en uso.");
			}
			escuchaServer.getCliente().getPaqueteUsuario().setUsername(null);
//			new MenuInicio(escuchaServer.getCliente());
		}
	}

}
