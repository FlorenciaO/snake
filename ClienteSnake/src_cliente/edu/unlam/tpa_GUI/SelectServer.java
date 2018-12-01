package edu.unlam.tpa_GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.unlam.tpa_COMUNICACION.Cliente;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectServer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6426407441496110607L;
	private JPanel contentPane;
	private JTextField textIp;
	private JTextField textPuerto;

	/**
	 * Create the frame.
	 */
	public SelectServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 258, 168);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel LabelIp = new JLabel("IP:");
		LabelIp.setBounds(64, 14, 24, 14);
		contentPane.add(LabelIp);

		textIp = new JTextField();
		textIp.setBounds(93, 11, 86, 20);
		contentPane.add(textIp);
		textIp.setColumns(10);
		textIp.setText("localHost");

		JLabel LabelPuerto = new JLabel("Puerto:");
		LabelPuerto.setBounds(42, 52, 46, 14);
		contentPane.add(LabelPuerto);

		textPuerto = new JTextField();
		textPuerto.setBounds(93, 49, 86, 20);
		contentPane.add(textPuerto);
		textPuerto.setColumns(10);
		textPuerto.setText("1234");

		JButton btnAceptar = new JButton("Iniciar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textIp.getText().equals("") && !textPuerto.getText().equals(""))
					new Cliente(textIp.getText(), Integer.valueOf(textPuerto.getText())).start();
					dispose();
					
			}
		});
		btnAceptar.setBounds(75, 95, 89, 23);
		contentPane.add(btnAceptar);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new SelectServer();
	}
}
