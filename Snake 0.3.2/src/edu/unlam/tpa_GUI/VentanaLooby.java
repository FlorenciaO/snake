package edu.unlam.tpa_GUI;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;

public class VentanaLooby extends JFrame{
	
	
	public VentanaLooby(String nombreUsario) {
		setTitle("SNAKE");
		setResizable(false);
		setForeground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setForeground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 258, 430, -513);
		getContentPane().add(scrollPane);
		
		JList list = new JList();
		list.setBounds(283, 36, 141, 211);
		getContentPane().add(list);
		
		JLabel lblSalasDisponibles = new JLabel("Salas Disponibles");
		lblSalasDisponibles.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSalasDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalasDisponibles.setBounds(283, 11, 141, 22);
		getContentPane().add(lblSalasDisponibles);
		
		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		btnCrearSala.setBounds(62, 72, 124, 35);
		getContentPane().add(btnCrearSala);
		
		JButton btnIngresarASala = new JButton("Unirse a la Sala");
		btnIngresarASala.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		btnIngresarASala.setBounds(39, 136, 173, 35);
		getContentPane().add(btnIngresarASala);
		
		JLabel label = new JLabel("Bienvenid@ " + nombreUsario + "!");
		label.setBounds(24, 11, 203, 22);
		getContentPane().add(label);
		
		setLocationRelativeTo(null);
	}
}
