package edu.unlam.tpa_GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.unlam.tpa_COMUNICACION.Cliente;
import edu.unlam.tpa_PAQUETESCLIENTE.Comando;

public class VentanaConfigurarSala extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8632608467086017446L;
	
	private JTextField textFieldNombreSala;
	private String name;
	
	public VentanaConfigurarSala(Cliente cliente) {
		
		setResizable(false);
		setBounds(100, 100, 306, 154);
		setForeground(Color.WHITE);
		getContentPane().setForeground(Color.BLACK);		
		getContentPane().setLayout(null);addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setTitle("Personalizá tu entorno!");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
			}
		});
		
//		Boton confirmacion
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(Color.BLACK);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldNombreSala.getText().equals("")){
					name = textFieldNombreSala.getText();
					cliente.getPaqueteSala().setNombreSala(name);
					cliente.getPaqueteSala().setOwnerSala(cliente.getPaqueteUsuario().getUsername());
					cliente.getPaqueteSala().setTipo(0); 					
					cliente.setAccion(Comando.NEWSALA);
					synchronized (cliente) {
						cliente.notify();
					}
					dispose();
				}
			}
		});
		btnConfirmar.setBounds(33, 65, 102, 23);
		getContentPane().add(btnConfirmar);
		
	
//		Boton Cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Vuelve al looby
				dispose();
			}
		});
		btnCancelar.setBounds(162, 65, 102, 23);
		getContentPane().add(btnCancelar);
		
		
//		LABEL DEL NOMBRE DE LA SALA
		JLabel lblNombreSala = new JLabel("Nombre de la sala");
		lblNombreSala.setVerticalAlignment(SwingConstants.TOP);
		lblNombreSala.setBounds(10, 24, 102, 14);
		getContentPane().add(lblNombreSala);
		
		textFieldNombreSala = new JTextField();
		textFieldNombreSala.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(!textFieldNombreSala.getText().equals("")){
						name = textFieldNombreSala.getText();
						cliente.getPaqueteSala().setNombreSala(name);
						cliente.getPaqueteSala().setOwnerSala(cliente.getPaqueteUsuario().getUsername());
						cliente.getPaqueteSala().setTipo(0); 					
						cliente.setAccion(Comando.NEWSALA);
						synchronized (cliente) {
							cliente.notify();
						}
						dispose();
					}
				}
			}
		});
		textFieldNombreSala.setBounds(134, 21, 86, 20);
		getContentPane().add(textFieldNombreSala);
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	
	protected void confirmarCierreVentana() {
		int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea volver a la sala de espera?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
		if(respuesta == JOptionPane.NO_OPTION) {
			System.exit(0);
		} else if (respuesta == JOptionPane.YES_OPTION){
			dispose();
		}
	}

	public String obtenerNombreSala() {
		return textFieldNombreSala.getText();
	}
	
}
