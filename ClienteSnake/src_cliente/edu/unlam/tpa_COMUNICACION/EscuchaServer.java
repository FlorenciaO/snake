package edu.unlam.tpa_COMUNICACION;

import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import edu.unlam.tpa_COMANDOSESCUCHASERVER.ComandoEscuchaServer;
import edu.unlam.tpa_GUI.VentanaLobby;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;

public class EscuchaServer extends Thread {

	private Cliente cliente;
	private ObjectInputStream entrada;

	private final Gson gson = new Gson();

	protected static ArrayList<String> usuariosConectados = new ArrayList<String>();

	public EscuchaServer(final Cliente cliente) {
		this.cliente = cliente;
		this.entrada = cliente.getEntrada();
	}

	@Override
	public void run() {
		try {

			ComandoEscuchaServer comando;
			Paquete paquete;

			String cadenaLeida = (String) entrada.readObject();
			while (!((paquete = gson.fromJson(cadenaLeida, Paquete.class)).getComando() == Comando.DESCONECTAR)) {

				comando = (ComandoEscuchaServer) paquete.getObjeto("edu.unlam.tpa_COMANDOSESCUCHASERVER");
				comando.setCadena(cadenaLeida);
				comando.setEscuchaServer(this);
				comando.ejecutar();

				synchronized (entrada) {
					cadenaLeida = (String) entrada.readObject();
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
			e.printStackTrace();
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void actualizarListaConectadosSala(PaqueteSala paqueteSala) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		synchronized (cliente) {

			cliente.getSalasActivas().get(paqueteSala.getNombreSala()).eliminarConectados();

			if (paqueteSala.getUsuariosConectados() != null) {
				paqueteSala.eliminarUsuario(cliente.getPaqueteUsuario().getUsername());
				for (String cad : paqueteSala.getUsuariosConectados()) {
					modelo.addElement(cad);
				}
				cliente.getSalasActivas().get(paqueteSala.getNombreSala()).cambiarModelo(modelo);
			}
		}
	}

	public void actualizarListaSalas() {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		synchronized (cliente) {

			VentanaLobby.eliminarSalas();
			if (cliente.getPaqueteUsuario().getListaDeSalas() != null) {
				for (String cad : cliente.getPaqueteUsuario().getListaDeSalas()) {
					modelo.addElement(cad);
				}

				VentanaLobby.cambiarModeloSalas(modelo);
			}

			if (cliente.getPaqueteUsuario().getListaDeSalasPrivadas() != null) {
				for (String cad : cliente.getPaqueteUsuario().getListaDeSalasPrivadas()) {
					modelo.addElement(cad);
				}

				VentanaLobby.cambiarModeloSalas(modelo);
			}
		}
	}
}