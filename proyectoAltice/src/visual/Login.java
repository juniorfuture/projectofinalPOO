package visual;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logica.AlticeSistema;
import logica.User;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_PRIMARIO = new Color(31, 111, 235);
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(220, 225, 230);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream empresa;
				FileOutputStream empresa2;
				ObjectInputStream empresaRead;
				ObjectOutputStream empresaWrite;

				try {
					empresa = new FileInputStream("empresa1.dat");
					empresaRead = new ObjectInputStream(empresa);
					AlticeSistema temp = (AlticeSistema) empresaRead.readObject();
					AlticeSistema.setSistema(temp);
					empresa.close();
					empresaRead.close();
				} catch (FileNotFoundException e) {
					try {
						empresa2 = new FileOutputStream("empresa1.dat");
						empresaWrite = new ObjectOutputStream(empresa2);

						User admin = new User("Administrador", "Admin", "Admin", "Admin");
						AlticeSistema.getInstance().regUser(admin);

						User trabajador = new User("Juan Perez", "Juan", "1234", "Trabajador");
						AlticeSistema.getInstance().regUser(trabajador);

						empresaWrite.writeObject(AlticeSistema.getInstance());
						empresa2.close();
						empresaWrite.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}

				if (!AlticeSistema.getInstance().tieneUsuarios()) {
					User admin = new User("Administrador", "Admin", "Admin", "Admin");
					AlticeSistema.getInstance().regUser(admin);
				}

				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setTitle("Sistema Altice");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 540);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(COLOR_FONDO);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(new Color(31, 111, 235));
		panelIzquierdo.setBounds(20, 20, 410, 461);
		panelIzquierdo.setLayout(null);
		contentPane.add(panelIzquierdo);

		JLabel lblSistema = new JLabel("Sistema Altice");
		lblSistema.setForeground(Color.WHITE);
		lblSistema.setFont(new Font("Segoe UI", Font.BOLD, 32));
		lblSistema.setBounds(35, 45, 300, 40);
		panelIzquierdo.add(lblSistema);

		JLabel lblDescripcion = new JLabel(
				"<html>Accede al sistema de gestión.</html>");
		lblDescripcion.setForeground(new Color(230, 238, 255));
		lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDescripcion.setBounds(35, 95, 320, 80);
		panelIzquierdo.add(lblDescripcion);

		JLabel lblIcono = new JLabel();
		lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcono.setBounds(65, 180, 280, 220);
		lblIcono.setIcon(cargarIcono("/imagenes/usuario.png", 160, 160));
		panelIzquierdo.add(lblIcono);

		JPanel panelDerecho = new JPanel();
		panelDerecho.setBackground(COLOR_PANEL);
		panelDerecho.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(20, 20, 20, 20)));
		panelDerecho.setBounds(455, 20, 430, 461);
		panelDerecho.setLayout(null);
		contentPane.add(panelDerecho);

		JLabel lblTituloLogin = new JLabel("Iniciar sesión");
		lblTituloLogin.setForeground(COLOR_TEXTO);
		lblTituloLogin.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblTituloLogin.setBounds(40, 45, 250, 40);
		panelDerecho.add(lblTituloLogin);

		JLabel lblSubLogin = new JLabel("Ingresa tus credenciales para continuar");
		lblSubLogin.setForeground(COLOR_SECUNDARIO);
		lblSubLogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSubLogin.setBounds(40, 85, 280, 25);
		panelDerecho.add(lblSubLogin);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(COLOR_TEXTO);
		lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblUsuario.setBounds(40, 145, 120, 20);
		panelDerecho.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtUsuario.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(8, 12, 8, 12)));
		txtUsuario.setBounds(40, 170, 340, 42);
		panelDerecho.add(txtUsuario);

		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setForeground(COLOR_TEXTO);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPassword.setBounds(40, 235, 120, 20);
		panelDerecho.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtPassword.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(8, 12, 8, 12)));
		txtPassword.setBounds(40, 260, 340, 42);
		panelDerecho.add(txtPassword);

		JButton btnLogin = new JButton("Entrar al sistema");
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(COLOR_PRIMARIO);
		btnLogin.setFocusPainted(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(40, 335, 340, 46);
		panelDerecho.add(btnLogin);
		getRootPane().setDefaultButton(btnLogin);

		JLabel lblInfo = new JLabel(
				"<html><center>Utiliza tu usuario registrado.</center></html>");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setForeground(COLOR_SECUNDARIO);
		lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblInfo.setBounds(40, 400, 340, 45);
		panelDerecho.add(lblInfo);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});

		txtPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});
	}

	private void iniciarSesion() {
		String usuario = txtUsuario.getText().trim();
		String pass = new String(txtPassword.getPassword());

		if (usuario.isEmpty() || pass.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"Por favor, complete el usuario y la contraseña.",
					"Campos incompletos",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (AlticeSistema.getInstance().confirmLogin(usuario, pass)) {
			Principal frame = new Principal();
			frame.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"Usuario o contraseña incorrectos.",
					"Error de acceso",
					JOptionPane.ERROR_MESSAGE);
			txtPassword.setText("");
			txtPassword.requestFocus();
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