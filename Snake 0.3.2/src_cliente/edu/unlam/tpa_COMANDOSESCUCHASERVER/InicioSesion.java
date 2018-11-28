package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import java.util.ArrayList;

import javax.swing.JOptionPane;

<<<<<<< HEAD
import edu.unlam.tpa_GUI.VentanaLobby;
=======
import edu.unlam.tpa_GUI.VentanaLooby;
>>>>>>> 10f222676f23e3d2c2dcb648d2d9e43ca3682085
import edu.unlam.tpa_PAQUETESCLIENTE.Paquete;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteDeUsuariosYSalas;


public class InicioSesion extends ComandoEscuchaServer {

	@Override
	public void ejecutar() {
		PaqueteDeUsuariosYSalas paqueteUS = gson.fromJson(cadenaLeida, PaqueteDeUsuariosYSalas.class);

		if (paqueteUS.getMsj().equals(Paquete.msjExito)) {

			ArrayList<String> salas = paqueteUS.getSalas();
			escuchaServer.getCliente().getPaqueteUsuario().setListaDeSalas(salas);
			ArrayList<String> salasp = paqueteUS.getSalasprivadas();//VER
			escuchaServer.getCliente().getPaqueteUsuario().setListaDeSalasPrivadas(salasp);//VER
			new VentanaLobby(escuchaServer.getCliente());
			escuchaServer.actualizarListaSalas();			

		} else {
		
			if (paqueteUS.getMsj().equals(Paquete.msjFracaso)) {
				JOptionPane.showMessageDialog(null, "Error al iniciar sesion. Revise el usuario y la contraseña");
			} else {
				JOptionPane.showMessageDialog(null, "Ya existe una sesion iniciada con ese usuario.");
			}
			escuchaServer.getCliente().getPaqueteUsuario().setUsername(null);
			new VentanaLobby(escuchaServer.getCliente());
		}
	}

}
