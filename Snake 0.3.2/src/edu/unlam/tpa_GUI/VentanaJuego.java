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
	private static final long serialVersionUID = 1L;
	private PanelJuego mainPanel;
	private PanelDePuntajes controlsPanel;

	public VentanaJuego() {
		mainPanel = new PanelJuego(this);
		controlsPanel = new PanelDePuntajes(mainPanel);

		getContentPane().add(controlsPanel, BorderLayout.EAST);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		setTitle("Snake");
		setResizable(false);	
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		mainPanel.requestFocus();
		
	}

	public PanelDePuntajes getControlsPanel() {
		return controlsPanel;
	}
	
	public static void main(String[] args) {
		VentanaJuego frame = new VentanaJuego();
	}

}
