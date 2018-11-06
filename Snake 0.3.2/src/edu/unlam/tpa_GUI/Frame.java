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

public class Frame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private MainPanel mainPanel;
	private ControlsPanel controlsPanel;

	public Frame() {
		mainPanel = new MainPanel(this);
		controlsPanel = new ControlsPanel(mainPanel);

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

	public ControlsPanel getControlsPanel() {
		return controlsPanel;
	}

}
