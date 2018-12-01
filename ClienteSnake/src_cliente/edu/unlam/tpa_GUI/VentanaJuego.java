/**
  * Snake
 *
 * @author Marc Oliveras Galvez <admin@oligalma.com> 
 * @link http://www.oligalma.com
 * @copyright 2017 Oligalma
 * @license GPL License v3
 */

package edu.unlam.tpa_GUI;


import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;
import edu.unlam.tpa_PAQUETESCLIENTE.PaqueteSala;

public class VentanaJuego extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5465548797480954343L;
	private PanelJuego panelJuego;
	private PanelDePuntajes panelPuntajes;
	
	private static Map<Integer, Color> colores;

	public VentanaJuego(Cliente cliente) {
		
		cargarColores();
		panelJuego = new PanelJuego(cliente);
		panelPuntajes = new PanelDePuntajes(cliente);
		cliente.setVentanaJuego(this);
		getContentPane().setLayout(null);
		getContentPane().add(panelPuntajes);
		getContentPane().add(panelJuego);
		setBounds(0, 0, 700, 525);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Snake");
		setResizable(false);	
		
		setVisible(true);
		panelJuego.requestFocus();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (abrirVentanaConfirmaSalir()) {
					synchronized (cliente) {
						PaqueteSala paqueteSala = new PaqueteSala(cliente.getPaqueteSala().getNombreSala(), cliente.getPaqueteUsuario().getUsername());
						cliente.setPaqueteSala(paqueteSala);
						cliente.setAccion(Comando.ABANDONARPARTIDA);
						cliente.notify();
					}
				}
			}
		});
		
		setVisible(true);
	}

	public PanelJuego getPanelJuego() {
		return panelJuego;
	}
	
	public PanelDePuntajes getControlsPanel() {
		return panelPuntajes;
	}
	
	public static void cargarColores() {
		colores = new HashMap<>();
		int indice = 0;
		colores.put(indice++, Color.ORANGE);
		colores.put(indice++, Color.BLUE);
		colores.put(indice++, Color.RED);
		colores.put(indice++, Color.YELLOW);
		colores.put(indice++, Color.CYAN);
		colores.put(indice++, Color.MAGENTA);
		colores.put(indice++, Color.PINK);
		colores.put(indice++, new Color(120, 40, 140));
		colores.put(indice++, new Color(182, 149, 192));
		colores.put(indice++, new Color(234, 190, 63));
		colores.put(indice++, Color.WHITE);
		colores.put(indice, Color.LIGHT_GRAY);
	}
	
	public static Map<Integer, Color> obtenerColores() {
		return colores;
	}
	
	private boolean abrirVentanaConfirmaSalir() {
		int opcion = JOptionPane.showConfirmDialog(this, "¿Desea salir de la sala?", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}
}
