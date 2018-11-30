package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import javax.swing.JOptionPane;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_GUI.VentanaJuego;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaquetePartida;

public class IniciarPartida extends ComandoEscuchaServer{

	@Override
	public void ejecutar() {
		Cliente cliente = escuchaServer.getCliente();
		PaquetePartida paquetePartida = gson.fromJson(cadenaLeida, PaquetePartida.class);
		cliente.setPaquetePartida(paquetePartida);
		if (paquetePartida.getMsj().equals(Paquete.msjExito)) {
			new VentanaJuego(cliente);
			// cambiar estado salas
			
		} else {
			JOptionPane.showMessageDialog(null, "Error al intentar entrar en la partida " + cliente.getPaqueteSala().getNombreSala());
		}	
		
	}

}
