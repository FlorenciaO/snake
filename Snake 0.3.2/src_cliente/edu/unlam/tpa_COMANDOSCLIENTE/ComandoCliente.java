package edu.unlam.tpa_COMANDOSCLIENTE;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;

public abstract class ComandoCliente extends Comando{
	protected Cliente cliente;

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
