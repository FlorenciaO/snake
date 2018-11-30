package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import javax.swing.JOptionPane;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_GUI.VentanaJuego;
import edu.unlam.tpa_GUI.VentanaSala;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;

public class IniciarPartida extends ComandoEscuchaServer{

	@Override
	public void ejecutar() {
		Cliente cliente = escuchaServer.getCliente();
		PaqueteSala paqueteSala = gson.fromJson(cadenaLeida, PaqueteSala.class);
		cliente.setPaqueteSala(paqueteSala);
		if (paqueteSala.getMsj().equals(Paquete.msjExito)) {
			new VentanaJuego(cliente);
			// cambiar estado salas
			
				
			
		} else {
			JOptionPane.showMessageDialog(null, "Error al intentar entrar en la partida " + cliente.getPaqueteSala().getNombreSala());
		}	
		
	}

}
