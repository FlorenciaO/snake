package edu.unlam.tpa_GUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import edu.unlam.tpa_ENUMS.Dificultad;
import edu.unlam.tpa_ENUMS.Modos;
import edu.unlam.tpa_ENUMS.Velocidad;

public class VentanaConfigurarSala extends JFrame {
	private JTextField textFieldNombreSala;
	
	public VentanaConfigurarSala() {
		setBounds(100, 100, 452, 230);
		setForeground(Color.WHITE);
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setLayout(null);
		setTitle("Configuracion de la sala");
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(Color.BLACK);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreSala = textFieldNombreSala.getText();
				if(!nombreSala.isEmpty()) {
					setVisible(false);
					new VentanaSala(textFieldNombreSala.getText()).setVisible(true);
				}
				else {
					mostrarWarning();
				}
			}
		});
		
		btnConfirmar.setBounds(102, 117, 102, 23);
		getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Vuelve a la pantalla anterior
			}
		});
		btnCancelar.setBounds(251, 117, 102, 23);
		getContentPane().add(btnCancelar);
		
		JLabel lblVelocidad = new JLabel("Velocidad");
		lblVelocidad.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		lblVelocidad.setBounds(10, 73, 46, 14);
		getContentPane().add(lblVelocidad);
		
		JLabel lblDificultad = new JLabel("Dificultad");
		lblDificultad.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		lblDificultad.setBounds(165, 73, 46, 14);
		getContentPane().add(lblDificultad);
		
		JLabel lblModo = new JLabel("Modo");
		lblModo.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		lblModo.setBounds(289, 73, 46, 14);
		getContentPane().add(lblModo);
		
		JComboBox<Velocidad> comboBoxVelocidad = new JComboBox<Velocidad>();
		comboBoxVelocidad.setModel(new DefaultComboBoxModel<Velocidad>(Velocidad.values()));
		comboBoxVelocidad.setBounds(66, 70, 89, 20);
		getContentPane().add(comboBoxVelocidad);
		
		JComboBox<Dificultad> comboBoxDificultad = new JComboBox<Dificultad>();
		comboBoxDificultad.setModel(new DefaultComboBoxModel<Dificultad>(Dificultad.values()));
		comboBoxDificultad.setBounds(221, 70, 58, 20);
		getContentPane().add(comboBoxDificultad);
		
		JComboBox<Modos> comboBoxModo = new JComboBox<Modos>();
		comboBoxModo.setEnabled(false);
		comboBoxModo.setBounds(345, 70, 61, 20);
		getContentPane().add(comboBoxModo);
		
		JLabel lblNombreSala = new JLabel("Nombre de la sala");
		lblNombreSala.setVerticalAlignment(SwingConstants.TOP);
		lblNombreSala.setBounds(10, 24, 102, 14);
		getContentPane().add(lblNombreSala);
		
		textFieldNombreSala = new JTextField();
		textFieldNombreSala.setBounds(134, 21, 86, 20);
		getContentPane().add(textFieldNombreSala);
		textFieldNombreSala.setColumns(10);
		setLocationRelativeTo(null);
	}
	
	private void abrirVentanaSala() {
		new VentanaSala(textFieldNombreSala.getText()).setVisible(true);				
	}
	
	public static void main(String args[]) {
		new VentanaConfigurarSala().setVisible(true);
	}
	
	private void mostrarWarning() {
		JOptionPane.showConfirmDialog(this, "Debe ingresar un nombre de sala", "ADVERTENCIA", JOptionPane.CLOSED_OPTION);		
	}
}
