package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logica.Servidor;
import logica.ServidorAltice;

public class LanzadorSistema extends JFrame {

	private static final long serialVersionUID = 1L;

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_PRIMARIO = new Color(31, 111, 235);
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(220, 225, 230);

	public static void main(String[] args) {
		try {
			LanzadorSistema frame = new LanzadorSistema();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LanzadorSistema() {
		setTitle("Centro de Control de Servidores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 420);
		setLocationRelativeTo(null);

		JPanel contentPane = new JPanel(new BorderLayout(18, 18));
		contentPane.setBackground(COLOR_FONDO);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);

		contentPane.add(crearHeader(), BorderLayout.NORTH);
		contentPane.add(crearCentro(), BorderLayout.CENTER);
	}

	private JPanel crearHeader() {
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(COLOR_PANEL);
		header.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(18, 20, 18, 20)));

		JLabel lblTitulo = new JLabel("Centro de Control de Servidores");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(COLOR_TEXTO);

		JLabel lblSubtitulo = new JLabel("Inicie los servicios necesarios para la demostración de sockets.");
		lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSubtitulo.setForeground(COLOR_SECUNDARIO);

		JPanel textos = new JPanel(new BorderLayout(0, 4));
		textos.setOpaque(false);
		textos.add(lblTitulo, BorderLayout.NORTH);
		textos.add(lblSubtitulo, BorderLayout.CENTER);

		header.add(textos, BorderLayout.WEST);

		return header;
	}

	private JPanel crearCentro() {
		JPanel centro = new JPanel(new GridLayout(3, 1, 14, 14));
		centro.setOpaque(false);

		JButton btnServidorDatos = crearBotonPrincipal("Iniciar Servidor de Datos (Puerto 5000)");
		btnServidorDatos.addActionListener(e -> {
			new Thread(() -> ServidorAltice.main(null)).start();
			btnServidorDatos.setText("Servidor de Datos ACTIVO");
			btnServidorDatos.setBackground(new Color(40, 167, 69));
			btnServidorDatos.setEnabled(false);
		});

		JButton btnServidorChat = crearBotonPrincipal("Iniciar Servidor de Chat (Puerto 7000)");
		btnServidorChat.addActionListener(e -> {
			new Thread(() -> Servidor.main(null)).start();
			btnServidorChat.setText("Servidor de Chat ACTIVO");
			btnServidorChat.setBackground(new Color(40, 167, 69));
			btnServidorChat.setEnabled(false);
		});

		JButton btnAbrirChat = crearBotonSecundario("Abrir Chat de Soporte");
		btnAbrirChat.addActionListener(e -> {
			ChatSoporte chat = new ChatSoporte();
			chat.setVisible(true);
		});

		centro.add(crearTarjeta("Servidor de Datos",
				"Recibe objetos del sistema por socket en el puerto 5000.",
				btnServidorDatos));
		centro.add(crearTarjeta("Servidor de Chat",
				"Permite comunicación en tiempo real entre clientes conectados al puerto 7000.",
				btnServidorChat));
		centro.add(crearTarjeta("Cliente de Chat",
				"Abre la interfaz visual para probar la comunicación del chat interno.",
				btnAbrirChat));

		return centro;
	}

	private JPanel crearTarjeta(String titulo, String descripcion, JButton boton) {
		JPanel card = new JPanel(new BorderLayout(8, 8));
		card.setBackground(COLOR_PANEL);
		card.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(16, 16, 16, 16)));

		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitulo.setForeground(COLOR_TEXTO);

		JLabel lblDesc = new JLabel("<html>" + descripcion + "</html>");
		lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDesc.setForeground(COLOR_SECUNDARIO);

		JPanel textos = new JPanel(new BorderLayout(0, 6));
		textos.setOpaque(false);
		textos.add(lblTitulo, BorderLayout.NORTH);
		textos.add(lblDesc, BorderLayout.CENTER);

		card.add(textos, BorderLayout.CENTER);
		card.add(boton, BorderLayout.EAST);

		return card;
	}

	private JButton crearBotonPrincipal(String texto) {
		JButton btn = new JButton(texto);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setBackground(COLOR_PRIMARIO);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setPreferredSize(new java.awt.Dimension(220, 46));
		return btn;
	}

	private JButton crearBotonSecundario(String texto) {
		JButton btn = new JButton(texto);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setBackground(new Color(230, 235, 240));
		btn.setForeground(COLOR_TEXTO);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setPreferredSize(new java.awt.Dimension(220, 46));
		return btn;
	}
}