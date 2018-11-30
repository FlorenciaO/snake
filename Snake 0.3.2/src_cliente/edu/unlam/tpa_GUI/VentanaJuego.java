/**
  * Snake
 *
 * @author Marc Oliveras Galvez <admin@oligalma.com> 
 * @link http://www.oligalma.com
 * @copyright 2017 Oligalma
 * @license GPL License v3
 */

package edu.unlam.tpa_GUI;


import javax.swing.JFrame;
import javax.swing.WindowConstants;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_ENUMS.Velocidad;
import edu.unlam.tpa_UTILES.ConfiguracionSala;
import edu.unlam.tpa_UTILES.Sala;
import edu.unlam.tpa_UTILES.HiloPartida;

public class VentanaJuego extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5465548797480954343L;
	private PanelJuego panelJuego;
	private PanelDePuntajes panelPuntajes;
	private HiloPartida servidor;

	public VentanaJuego(Cliente cliente) {
//		this.servidor  = new HiloPartida(this,cliente);
		
		panelJuego = new PanelJuego();
		panelPuntajes = new PanelDePuntajes(cliente);

		getContentPane().setLayout(null);
		getContentPane().add(panelPuntajes);
		getContentPane().add(panelJuego);
		setBounds(0, 0, 700, 525);
		
		setLocationRelativeTo(null);
		setTitle("Snake");
		setResizable(false);	
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		panelJuego.requestFocus();
		
		setVisible(true);
	}

	public PanelJuego getPanelJuego() {
		return panelJuego;
	}
	
	public PanelDePuntajes getControlsPanel() {
		return panelPuntajes;
	}
}
