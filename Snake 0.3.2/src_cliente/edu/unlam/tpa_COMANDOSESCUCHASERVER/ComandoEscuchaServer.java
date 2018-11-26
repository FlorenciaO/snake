package edu.unlam.tpa_COMANDOSESCUCHASERVER;

import edu.unlam.tpa_COMUNICACION.EscuchaServer;
import edu.unlam.tpa_PAQUETES.Comando;

public abstract class ComandoEscuchaServer extends Comando{
	protected EscuchaServer escuchaServer;

	public void setEscuchaServer(EscuchaServer escuchaServer) {
		this.escuchaServer = escuchaServer;
	}
}
