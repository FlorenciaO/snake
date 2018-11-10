package edu.unlam.tpa_GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaLogin extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2110542286099254457L;
	
	private JTextField txtUsuario;
	private JPasswordField pwdContraseña;
	private final static int LENG_USUARIO_MAX = 15;
	
	public VentanaLogin() {
		getContentPane().setLayout(null);
		setResizable(false);
		setBounds(100, 100, 434, 231);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Unite a nuestra comunidad...");
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBackground(Color.GRAY);
		lblUsuario.setToolTipText("");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 15));
		lblUsuario.setBounds(64, 11, 79, 33);
		getContentPane().add(lblUsuario);
		
		JLabel lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseña.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 15));
		lblContraseña.setBounds(42, 55, 122, 33);
		getContentPane().add(lblContraseña);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(186, 17, 170, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		pwdContraseña = new JPasswordField();
		pwdContraseña.setBounds(186, 61, 170, 20);
		getContentPane().add(pwdContraseña);
		pwdContraseña.setEnabled(false);
		
		JButton btnIniciarSesion = new JButton("Inicia sesi\u00F3n");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreUsuario = txtUsuario.getText();
				if(!nombreUsuario.isEmpty()) {
					if(nombreUsuario.length() > LENG_USUARIO_MAX) {
						mostrarWarning("Límite de nombre de usuario excedido");
						txtUsuario.setText("");
					} else {
					abrirVentanaLooby();
					}
				}
				else {
					mostrarWarning("Se debe ingresar un usuario");
				}
			}
		});
		btnIniciarSesion.setBounds(132, 114, 122, 20);
		getContentPane().add(btnIniciarSesion);
		
		JLabel lblestasAquPor = new JLabel("\u00BFEst\u00E1s aqu\u00ED por primera vez?");
		lblestasAquPor.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 13));
		lblestasAquPor.setHorizontalAlignment(SwingConstants.CENTER);
		lblestasAquPor.setBounds(10, 164, 244, 27);
		getContentPane().add(lblestasAquPor);
		
		JLabel lblRegistrate = new JLabel("Registrate");
		lblRegistrate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		lblRegistrate.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrate.setForeground(Color.BLUE);
		lblRegistrate.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 13));
		lblRegistrate.setBounds(264, 170, 79, 14);
		getContentPane().add(lblRegistrate);
		
		setLocationRelativeTo(null);
	}

	private void mostrarWarning(String mensaje) {
		JOptionPane.showConfirmDialog(this, mensaje, "ADVERTENCIA", JOptionPane.CLOSED_OPTION);
	}
	
	private void abrirVentanaLooby() {
		setVisible(false);
		new VentanaLooby(this).setVisible(true);
	}
	
	public static void main(String args[]) {
		new VentanaLogin().setVisible(true);
	}

	public String obtenerNombreUsuario() {
		return txtUsuario.getText();
	}
	
	public void limpiarNombreUsuario() {
		txtUsuario.setText("");
	}
}
