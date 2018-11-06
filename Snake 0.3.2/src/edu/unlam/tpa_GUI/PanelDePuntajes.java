package edu.unlam.tpa_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelDePuntajes extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton startButton = new JButton();
	private JLabel scoreLabel = new JLabel();
	
	private PanelJuego panelJuego;
	private JLabel snake1ScoreLabel;
	private JLabel snake2ScoreLabel;
	private JLabel snake3ScoreLabel;
	private JLabel snake4ScoreLabel;
	
//	
	public PanelDePuntajes(final PanelJuego panel){
		this.panelJuego = panel;
		this.setPreferredSize(new Dimension(200, 300));

		
		this.setBackground(Color.BLACK);
		this.setForeground(new Color(0, 0, 0));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
		this.setLayout(null);
		
		Font font = new Font("Tahoma", Font.PLAIN, 24);
		
		JLabel snake1Label = new JLabel("Snake");
		snake1Label.setFont(font);
		snake1Label.setForeground(Color.GREEN);
		snake1Label.setBounds(10, 50, 100, 20);
		this.add(snake1Label);
		
		JLabel snake2Label = new JLabel("Snake");
		snake2Label.setFont(font);
		snake2Label.setForeground(Color.ORANGE);
		snake2Label.setBounds(10, 90, 100, 20);
		this.add(snake2Label);
		
		JLabel snake3Label = new JLabel("Snake");
		snake3Label.setFont(font);
		snake3Label.setForeground(Color.CYAN);
		snake3Label.setBounds(10, 130, 100, 20);
//		this.add(snake3Label);
		
		JLabel snake4Label = new JLabel("Snake");
		snake4Label.setFont(font);
		snake4Label.setForeground(Color.MAGENTA);
		snake4Label.setBounds(10, 170, 100, 20);
//		this.add(snake4Label);
		
		this.snake1ScoreLabel = new JLabel("0");
		snake1ScoreLabel.setFont(font);
		snake1ScoreLabel.setForeground(Color.GREEN);
		snake1ScoreLabel.setBounds(120, 50, 46, 20);
		this.add(snake1ScoreLabel);
		
		this.snake2ScoreLabel = new JLabel("0");
		snake2ScoreLabel.setFont(font);
		snake2ScoreLabel.setForeground(Color.YELLOW);
		snake2ScoreLabel.setBounds(120, 90, 46, 20);
		this.add(snake2ScoreLabel);
		
		this.snake3ScoreLabel = new JLabel("0");
		snake3ScoreLabel.setFont(font);
		snake3ScoreLabel.setForeground(Color.CYAN);
		snake3ScoreLabel.setBounds(120, 130, 46, 20);
//		this.add(snake3ScoreLabel);
		
		this.snake4ScoreLabel = new JLabel("0");
		snake4ScoreLabel.setFont(font);
		snake4ScoreLabel.setForeground(Color.MAGENTA);
		snake4ScoreLabel.setBounds(120, 170, 46, 20);
//		this.add(snake4ScoreLabel);
		
//		startButton.setPreferredSize(new Dimension(85, 40));
//		startButton.setText("Play Again");
//		btn.setRequestFocusEnabled(false);		//Cuando se agrega un boton sin esto no anda
//		startButton.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				mainPanel.init();
//			}
//		});
//		scoreLabel.setPreferredSize(new Dimension(70, 40));
//		scoreLabel.setText("000");
//		scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
//		this.add(scoreLabel);
		
	}
	
	
	public void setSnake1ScoreLabel(String puntos) {
		this.snake1ScoreLabel.setText(puntos);
	}
	public void setSnake2ScoreLabel(String puntos) {
		this.snake2ScoreLabel.setText(puntos);
	}
	public void setSnake3ScoreLabel(String puntos) {
		this.snake3ScoreLabel.setText(puntos);
	}
	public void setSnake4ScoreLabel(String puntos) {
		this.snake4ScoreLabel.setText(puntos);
	}
}
