/**
 * Snake
 *
 * @author Marc Oliveras Galvez <admin@oligalma.com> 
 * @link http://www.oligalma.com
 * @copyright 2017 Oligalma
 * @license GPL License v3
 */

package edu.unlam.tpa_GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class VentanaJuego extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5465548797480954343L;
	private PanelJuego panelJuego;
	private PanelDePuntajes panelPuntajes;

	public VentanaJuego() {
		panelJuego = new PanelJuego(this);
		panelPuntajes = new PanelDePuntajes(panelJuego);

		getContentPane().add(panelPuntajes, BorderLayout.EAST);
		getContentPane().add(panelJuego, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		setTitle("Snake");
		setResizable(false);	
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		panelJuego.requestFocus();
		
	}

	public PanelDePuntajes getControlsPanel() {
		return panelPuntajes;
	}
	
	public static void main(String[] args) {
		VentanaJuego frame = new VentanaJuego();
	}

}