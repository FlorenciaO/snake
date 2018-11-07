package edu.unlam.tpa_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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
//	private JLabel snake3ScoreLabel;
//	private JLabel snake4ScoreLabel;
	
	
	public PanelDePuntajes(final PanelJuego panel){
		this.panelJuego = panel;
		this.setPreferredSize(new Dimension(200, 300));
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
		
//		JLabel snake3Label = new JLabel("Snake");
//		snake3Label.setFont(font);
//		snake3Label.setForeground(Color.CYAN);
//		snake3Label.setBounds(10, 130, 100, 20);
//		this.add(snake3Label);
		
//		JLabel snake4Label = new JLabel("Snake");
//		snake4Label.setFont(font);
//		snake4Label.setForeground(Color.MAGENTA);
//		snake4Label.setBounds(10, 170, 100, 20);
//		this.add(snake4Label);
		
//		this.snake3ScoreLabel = new JLabel("0");
//		snake3ScoreLabel.setFont(font);
//		snake3ScoreLabel.setForeground(Color.CYAN);
//		snake3ScoreLabel.setBounds(120, 130, 46, 20);
//		this.add(snake3ScoreLabel);
		
//		this.snake4ScoreLabel = new JLabel("0");
//		snake4ScoreLabel.setFont(font);
//		snake4ScoreLabel.setForeground(Color.MAGENTA);
//		snake4ScoreLabel.setBounds(120, 170, 46, 20);
//		this.add(snake4ScoreLabel);
		
		btnJugar.setBounds(50, 250, 100, 40);
		btnJugar.setText("Jugar");
		btnJugar.setRequestFocusEnabled(false);		//Cuando se agrega un boton sin esto no anda
		btnJugar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				panelJuego.init();
			}
		});
		this.add(btnJugar);
	}
	
	
	public void setSnake1ScoreLabel(String puntos) {
		this.snake1ScoreLabel.setText(puntos);
	}
	public void setSnake2ScoreLabel(String puntos) {
		this.snake2ScoreLabel.setText(puntos);
	}
//	public void setSnake3ScoreLabel(String puntos) {
//		this.snake3ScoreLabel.setText(puntos);
//	}
//	public void setSnake4ScoreLabel(String puntos) {
//		this.snake4ScoreLabel.setText(puntos);
//	}
}
