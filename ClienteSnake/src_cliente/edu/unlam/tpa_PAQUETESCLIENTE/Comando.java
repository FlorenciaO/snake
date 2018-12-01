package edu.unlam.tpa_PAQUETESCLIENTE;

import com.google.gson.Gson;

public abstract class Comando {
	
	public static final String[] NOMBRES = {"Conexion","Registro","InicioSesion","Desconectar",
			"ChatAll","MP","NewSala","EntrarSala","ConexionSala","ChatSala","MencionSala","DesconectarDeSala",
			"EliminarSala","IniciarPartida","Paint","EnviarTecla","TerminarPartida", "AbandonarPartida"};

	public static final int CONEXION = 0;
	public static final int REGISTRO = 1;
	public static final int INICIOSESION = 2;
	public static final int DESCONECTAR = 3;
	public static final int CHATALL = 4;
	public static final int MP = 5;	
	public static final int NEWSALA = 6;
	public static final int ENTRARSALA = 7;
	public static final int CONEXIONSALA = 8;
	public static final int CHATSALA = 9;
	public static final int MENCIONSALA = 10;
	public static final int DESCONECTARDESALA = 11;
	public static final int ELIMINARSALA = 12;
	public static final int INICIARPARTIDA = 13;
	public static final int PAINT = 14;
	public static final int ENVIARTECLA = 15; 
	public static final int TERMINARPARTIDA = 16;
	public static final int ABANDONARPARTIDA = 17;

	
	protected final Gson gson = new Gson();
	protected String cadenaLeida;
	
	public void setCadena(String cadenaLeida) {
		this.cadenaLeida = cadenaLeida;
	}

	
	public abstract void ejecutar();
}