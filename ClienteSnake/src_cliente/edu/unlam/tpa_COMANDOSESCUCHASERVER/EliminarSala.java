package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import javax.swing.JOptionPane;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;

public class EliminarSala extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		PaqueteSala paqueteSala = (PaqueteSala) gson.fromJson(cadenaLeida, PaqueteSala.class);

		if(paqueteSala.getMsj().equals(Paquete.msjExito)) {
			Cliente cliente = escuchaServer.getCliente();
			if(cliente.getSalasActivas().containsKey(paqueteSala.getNombreSala()) ) {
				
				JOptionPane.showMessageDialog(null, "La sala " + paqueteSala.getNombreSala() + " ha sido eliminada.");
				cliente.getSalasActivas().get(paqueteSala.getNombreSala()).dispose();
				cliente.getSalasActivas().remove(paqueteSala.getNombreSala());
			}
			cliente.getPaqueteUsuario().eliminarSala(paqueteSala.getNombreSala());
			escuchaServer.actualizarListaSalas();
			cliente.getVentanaLobby().setVisible(true);
		} else if(paqueteSala.getMsj().equals(Paquete.msjFracaso)) {
			JOptionPane.showMessageDialog(null, "Error al tratar de eliminar la sala.");
		}
	}
}
