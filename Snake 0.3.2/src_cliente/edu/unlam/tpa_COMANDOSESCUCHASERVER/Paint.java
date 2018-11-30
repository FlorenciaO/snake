package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import javax.swing.JOptionPane;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_GUI.VentanaSala;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaquetePartida;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;

public class Paint extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		Cliente cliente = escuchaServer.getCliente();
		PaquetePartida paquetePartida = gson.fromJson(cadenaLeida, PaquetePartida.class);
		cliente.setPaquetePartida(paquetePartida);
		cliente.actualizarVentanaJuego();
	}
	
}
