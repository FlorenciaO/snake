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
import edu.unlam.tpa_ENUMS.Modo;
import edu.unlam.tpa_ENUMS.Velocidad;
import edu.unlam.tpa_UTILES.ConfiguracionSala;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaConfigurarSala extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8632608467086017446L;
	
	private JTextField textFieldNombreSala;
	private VentanaLooby ventanaLooby;
	private JComboBox<Velocidad> comboBoxVelocidad;
	private JComboBox<Dificultad> comboBoxDificultad;
	private JComboBox<Modo> comboBoxModo;
	
	public VentanaConfigurarSala(VentanaLooby ventanaLooby) {

		this.ventanaLooby = ventanaLooby;
		
		setResizable(false);
		setBounds(100, 100, 452, 230);
		setForeground(Color.WHITE);
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Personalizá tu entorno!");
		
		
//		Confirmar cierre
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				confirmarCierreVentana();
			}
		});
		
//		Boton confirmacion
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(Color.BLACK);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreSala = textFieldNombreSala.getText();
				if(!nombreSala.isEmpty()) {
					abrirVentanaSala();
				}
				else {
					mostrarWarning();
				}
			}
		});
		btnConfirmar.setBounds(102, 117, 102, 23);
		getContentPane().add(btnConfirmar);
		
		
//		Boton Cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Vuelve al looby
				volverAlLooby();
			}
		});
		btnCancelar.setBounds(251, 117, 102, 23);
		getContentPane().add(btnCancelar);
		
//		LABELS DE CONFIGURACIONES
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
		
		
//		COMBOBOX'S DE CONFIGURACIONES
		this.comboBoxVelocidad = new JComboBox<Velocidad>();
		comboBoxVelocidad.setModel(new DefaultComboBoxModel<Velocidad>(Velocidad.values()));
		comboBoxVelocidad.setBounds(66, 70, 89, 20);
		getContentPane().add(comboBoxVelocidad);
		
		this.comboBoxDificultad = new JComboBox<Dificultad>();
		comboBoxDificultad.setModel(new DefaultComboBoxModel<Dificultad>(Dificultad.values()));
		comboBoxDificultad.setBounds(221, 70, 58, 20);
		comboBoxDificultad.setEnabled(false); // Implementar
		getContentPane().add(comboBoxDificultad);
		
		this.comboBoxModo = new JComboBox<Modo>();
		comboBoxModo.setEnabled(false); // Implementar
		comboBoxModo.setBounds(345, 70, 61, 20);
		getContentPane().add(comboBoxModo);
		
		
//		LABEL DEL NOMBRE DE LA SALA
		JLabel lblNombreSala = new JLabel("Nombre de la sala");
		lblNombreSala.setVerticalAlignment(SwingConstants.TOP);
		lblNombreSala.setBounds(10, 24, 102, 14);
		getContentPane().add(lblNombreSala);
		
		textFieldNombreSala = new JTextField();
		textFieldNombreSala.setBounds(134, 21, 86, 20);
		getContentPane().add(textFieldNombreSala);
		setLocationRelativeTo(null);
	}
	
	protected void confirmarCierreVentana() {
		int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea volver a la sala de espera?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
		if(respuesta == JOptionPane.NO_OPTION) {
			System.exit(0);
		} else if (respuesta == JOptionPane.YES_OPTION){
			volverAlLooby();
		}
		
	}

	public void volverAlLooby() {
		this.ventanaLooby.setVisible(true);
		setVisible(false);
	}

	@SuppressWarnings("unused")
	private void abrirVentanaSala() {
		setVisible(false);
		ConfiguracionSala config = new ConfiguracionSala((Velocidad)this.comboBoxVelocidad.getSelectedItem()
														, (Modo)this.comboBoxModo.getSelectedItem(), 
														(Dificultad)this.comboBoxDificultad.getSelectedItem());
		VentanaSala salaNueva = new VentanaSala(this, config, this.textFieldNombreSala.getText());
		salaNueva.setVisible(true);
		this.ventanaLooby.addSala(salaNueva);
	}
	
	
	public String obtenerNombreSala() {
		return textFieldNombreSala.getText();
	}
	
	private void mostrarWarning() {
		JOptionPane.showConfirmDialog(this, "Debe ingresar un nombre de sala", "ADVERTENCIA", JOptionPane.CLOSED_OPTION);		
	}
}
