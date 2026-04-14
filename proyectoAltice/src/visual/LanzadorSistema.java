package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logica.Servidor;
import logica.ServidorAltice;

public class LanzadorSistema extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		try {
			LanzadorSistema frame = new LanzadorSistema();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LanzadorSistema() {
		setTitle("Panel de Control - Altice");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 250);
		setLocationRelativeTo(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 10, 10));

		JLabel lblTitulo = new JLabel("Centro de Control de Servidores");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitulo);

		JButton btnServidorDatos = new JButton("1. Iniciar Servidor de Datos (Puerto 5000)");
		btnServidorDatos.setBackground(new Color(255, 228, 181));
		btnServidorDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					ServidorAltice.main(null);
				}).start();
				
				btnServidorDatos.setText("Servidor de Datos: ACTIVO");
				btnServidorDatos.setBackground(new Color(144, 238, 144));
				btnServidorDatos.setEnabled(false);
			}
		});
		contentPane.add(btnServidorDatos);

		JButton btnServidorChat = new JButton("2. Iniciar Servidor de Chat (Puerto 7000)");
		btnServidorChat.setBackground(new Color(255, 228, 181));
		btnServidorChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					Servidor.main(null);
				}).start();
				
				btnServidorChat.setText("Servidor de Chat: ACTIVO");
				btnServidorChat.setBackground(new Color(144, 238, 144));
				btnServidorChat.setEnabled(false);
			}
		});
		contentPane.add(btnServidorChat);

		JButton btnAbrirApp = new JButton("3. Abrir Aplicación Altice (Cliente)");
		btnAbrirApp.setBackground(new Color(173, 216, 230));
		btnAbrirApp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAbrirApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegContratos ventana = new RegContratos();
				ventana.setVisible(true);
			}
		});
		contentPane.add(btnAbrirApp);
	}
}