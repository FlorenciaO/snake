package edu.unlam.tpa_COMANDOS;

import edu.unlam.tpa_COMUNICACION.EscuchaCliente;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;



public abstract class ComandoServer extends Comando{
	protected EscuchaCliente escuchaCliente;

	public void setEscuchaCliente(EscuchaCliente escuchaCliente) {
		this.escuchaCliente = escuchaCliente;
	}
	

}
