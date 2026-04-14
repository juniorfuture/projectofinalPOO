package visual;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatSoporte extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	Socket sfd = null;
	DataInputStream EntradaSocket;
	DataOutputStream SalidaSocket;
	
	JTextField salida;
	JTextArea entrada;
	String texto;

	public ChatSoporte() {
		setTitle("Chat Interno - Soporte Altice");
		setSize(400, 300);
		setLocationRelativeTo(null); 
		
		salida = new JTextField();
		salida.addActionListener(this);

		entrada = new JTextArea();
		entrada.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(entrada);

		getContentPane().add(salida, BorderLayout.SOUTH);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		conectarAlServidor();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				desconectar();
				dispose(); 
			}
		});
		
		setVisible(true);
	}

	private void conectarAlServidor() {
		try {

			sfd = new Socket("127.0.0.1", 7000); 
			EntradaSocket = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
			SalidaSocket = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
			
			Thread hiloEscucha = new Thread(() -> {
				while (true) {
					try {
						String linea = EntradaSocket.readUTF();
						entrada.append(linea + "\n");
						entrada.setCaretPosition(entrada.getDocument().getLength());
					} catch (IOException ioe) {
						entrada.append(">> Te has desconectado del servidor.\n");
						break; 
					}
				}
			});
			hiloEscucha.start();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor de chat.", "Error de red", JOptionPane.ERROR_MESSAGE);
			dispose(); 
		}
	}

	private void desconectar() {
		if (sfd != null && !sfd.isClosed()) {
			try {
				sfd.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar conexión: " + e);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		texto = salida.getText();
		salida.setText("");
		try {
			if (SalidaSocket != null) {
				SalidaSocket.writeUTF(texto);
				SalidaSocket.flush();
			}
		} catch (IOException ioe) {
			System.out.println("Error al enviar: " + ioe);
		}
	}
}