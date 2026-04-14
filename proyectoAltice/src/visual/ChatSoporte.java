package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ChatSoporte extends JFrame {

	private static final long serialVersionUID = 1L;

	private Socket sfd = null;
	private DataInputStream entradaSocket;
	private DataOutputStream salidaSocket;

	private JTextField txtMensaje;
	private JTextArea txtChat;
	private JTextField txtNombre;

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_PRIMARIO = new Color(31, 111, 235);
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(220, 225, 230);

	public ChatSoporte() {
		setTitle("Chat Interno - Soporte Altice");
		setSize(760, 560);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel contentPane = new JPanel(new BorderLayout(18, 18));
		contentPane.setBackground(COLOR_FONDO);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);

		contentPane.add(crearHeader(), BorderLayout.NORTH);
		contentPane.add(crearCentro(), BorderLayout.CENTER);
		contentPane.add(crearZonaEnvio(), BorderLayout.SOUTH);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				desconectar();
			}
		});

		conectarAlServidor();
	}

	private JPanel crearHeader() {
		JPanel header = new JPanel(new BorderLayout(15, 15));
		header.setBackground(COLOR_PANEL);
		header.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(18, 18, 18, 18)));

		JLabel icono = new JLabel();
		icono.setHorizontalAlignment(SwingConstants.CENTER);
		icono.setPreferredSize(new java.awt.Dimension(70, 70));
		icono.setIcon(cargarIcono("/imagenes/usuario.png", 44, 44));
		header.add(icono, BorderLayout.WEST);

		JPanel textos = new JPanel(new BorderLayout(0, 4));
		textos.setOpaque(false);

		JLabel titulo = new JLabel("Chat Interno de Soporte");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setForeground(COLOR_TEXTO);

		JLabel subtitulo = new JLabel("Comunicación en tiempo real mediante sockets con el servidor de chat.");
		subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		subtitulo.setForeground(COLOR_SECUNDARIO);

		textos.add(titulo, BorderLayout.NORTH);
		textos.add(subtitulo, BorderLayout.CENTER);

		header.add(textos, BorderLayout.CENTER);

		return header;
	}

	private JPanel crearCentro() {
		JPanel centro = new JPanel(new BorderLayout(12, 12));
		centro.setBackground(COLOR_PANEL);
		centro.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(16, 16, 16, 16)));

		JPanel panelNombre = new JPanel(new BorderLayout(8, 8));
		panelNombre.setOpaque(false);

		JLabel lblNombre = new JLabel("Nombre en el chat:");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNombre.setForeground(COLOR_TEXTO);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtNombre.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(8, 10, 8, 10)));

		panelNombre.add(lblNombre, BorderLayout.WEST);
		panelNombre.add(txtNombre, BorderLayout.CENTER);

		txtChat = new JTextArea();
		txtChat.setEditable(false);
		txtChat.setLineWrap(true);
		txtChat.setWrapStyleWord(true);
		txtChat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtChat.setBorder(new EmptyBorder(12, 12, 12, 12));

		JScrollPane scrollPane = new JScrollPane(txtChat);
		scrollPane.setBorder(new LineBorder(COLOR_BORDE, 1, true));

		centro.add(panelNombre, BorderLayout.NORTH);
		centro.add(scrollPane, BorderLayout.CENTER);

		return centro;
	}

	private JPanel crearZonaEnvio() {
		JPanel panelEnvio = new JPanel(new BorderLayout(10, 0));
		panelEnvio.setOpaque(false);

		txtMensaje = new JTextField();
		txtMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtMensaje.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(10, 12, 10, 12)));
		txtMensaje.addActionListener(e -> enviarMensaje());

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setFocusPainted(false);
		btnEnviar.setBorderPainted(false);
		btnEnviar.setBackground(COLOR_PRIMARIO);
		btnEnviar.setForeground(Color.WHITE);
		btnEnviar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnEnviar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEnviar.setPreferredSize(new java.awt.Dimension(130, 42));
		btnEnviar.addActionListener(e -> enviarMensaje());

		panelEnvio.add(txtMensaje, BorderLayout.CENTER);
		panelEnvio.add(btnEnviar, BorderLayout.EAST);

		return panelEnvio;
	}

	private void conectarAlServidor() {
		try {
			sfd = new Socket("127.0.0.1", 7000);
			entradaSocket = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
			salidaSocket = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));

			appendChat(">> Conectado al servidor de chat.");

			Thread hiloEscucha = new Thread(() -> {
				while (true) {
					try {
						String linea = entradaSocket.readUTF();
						appendChat(linea);
					} catch (IOException ioe) {
						appendChat(">> Conexión cerrada con el servidor.");
						break;
					}
				}
			});
			hiloEscucha.setDaemon(true);
			hiloEscucha.start();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
					"No se pudo conectar al servidor de chat.\nVerifica que el servidor esté encendido.",
					"Error de red",
					JOptionPane.ERROR_MESSAGE);
			dispose();
		}
	}

	private void enviarMensaje() {
		String nombre = txtNombre.getText().trim();
		String mensaje = txtMensaje.getText().trim();

		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"Escribe un nombre para identificarte en el chat.",
					"Nombre requerido",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (mensaje.isEmpty()) {
			return;
		}

		try {
			if (salidaSocket != null) {
				salidaSocket.writeUTF(nombre + ": " + mensaje);
				salidaSocket.flush();
				txtMensaje.setText("");
				txtMensaje.requestFocus();
			}
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(this,
					"No se pudo enviar el mensaje.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void appendChat(String texto) {
		SwingUtilities.invokeLater(() -> {
			txtChat.append(texto + "\n");
			txtChat.setCaretPosition(txtChat.getDocument().getLength());
		});
	}

	private void desconectar() {
		try {
			if (entradaSocket != null) entradaSocket.close();
		} catch (IOException e) {
		}
		try {
			if (salidaSocket != null) salidaSocket.close();
		} catch (IOException e) {
		}
		try {
			if (sfd != null && !sfd.isClosed()) sfd.close();
		} catch (IOException e) {
		}
	}

	private ImageIcon cargarIcono(String path, int ancho, int alto) {
		try {
			java.net.URL imgUrl = getClass().getResource(path);
			if (imgUrl != null) {
				ImageIcon iconOriginal = new ImageIcon(imgUrl);
				Image imgEscalada = iconOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
				return new ImageIcon(imgEscalada);
			}
		} catch (Exception e) {
		}
		return null;
	}
}