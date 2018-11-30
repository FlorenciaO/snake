package edu.unlam.tpa_GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.PaquetePartida;

public class PanelDePuntajes extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2361773272360190852L;
	private JButton btnJugar = new JButton();
	private JLabel lblPuntajes = new JLabel();
	
	private PanelJuego panelJuego;
	private JLabel snake1ScoreLabel;
	private JLabel snake2ScoreLabel;
	
	private int width = 200;
	private int height = 500;
	
	
	public PanelDePuntajes(Cliente cliente){

		setBounds(500, 0, width, height);
		this.setBackground(Color.DARK_GRAY);
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		this.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		this.setLayout(null);
		
		Font font = new Font("Tahoma", Font.PLAIN, 24);
		
		lblPuntajes.setText("PUNTAJES");
		lblPuntajes.setFont(font);
		lblPuntajes.setForeground(Color.LIGHT_GRAY);
		lblPuntajes.setBounds(35, 40, 150, 20);
		this.add(lblPuntajes);
		
		JLabel snake1Label = new JLabel("Snake");
		snake1Label.setFont(font);
		snake1Label.setForeground(Color.RED);
		snake1Label.setBounds(30, 90, 100, 20);
		this.add(snake1Label);
		
		JLabel snake2Label = new JLabel("Snake");
		snake2Label.setFont(font);
		snake2Label.setForeground(Color.MAGENTA);
		snake2Label.setBounds(30, 130, 100, 20);
		this.add(snake2Label);
		
		this.snake1ScoreLabel = new JLabel("0");
		snake1ScoreLabel.setFont(font);
		snake1ScoreLabel.setForeground(Color.RED);
		snake1ScoreLabel.setBounds(140, 90, 46, 20);
		this.add(snake1ScoreLabel);
		
		this.snake2ScoreLabel = new JLabel("0");
		snake2ScoreLabel.setFont(font);
		snake2ScoreLabel.setForeground(Color.MAGENTA);
		snake2ScoreLabel.setBounds(140, 130, 46, 20);
		this.add(snake2ScoreLabel);
		

	}
	
	
	public void setSnake1ScoreLabel(String puntos) {
		this.snake1ScoreLabel.setText(puntos);
	}
	public void setSnake2ScoreLabel(String puntos) {
		this.snake2ScoreLabel.setText(puntos);
	}
}
