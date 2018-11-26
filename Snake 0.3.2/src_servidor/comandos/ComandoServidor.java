package comandos;

import edu.unlam.tpa_ENUMS.Comando;

public abstract class ComandoServidor {
	
	public Comando comando;
	
	public ComandoServidor(Comando comando) {
		this.comando = comando;
	}

}
