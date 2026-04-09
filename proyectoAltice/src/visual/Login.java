package visual;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;

	private Color colorFondo = new Color(255, 255, 255);
	private Color colorBoton = new Color(0, 85, 165);
	private Color colorTexto = new Color(50, 50, 50);
	private Font fuentePrincipal = new Font("Segoe UI", Font.PLAIN, 14);

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
		setTitle("Altice");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 420);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(colorFondo);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblLogo = new JLabel("altice");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 36));
		lblLogo.setForeground(Color.BLACK);
		lblLogo.setBounds(10, 30, 344, 50);
		contentPane.add(lblLogo);

		JLabel lblSubtitulo = new JLabel("Panel de Control");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSubtitulo.setForeground(Color.GRAY);
		lblSubtitulo.setBounds(10, 75, 344, 20);
		contentPane.add(lblSubtitulo);

		JLabel lblUsuario = new JLabel("Nombre de Usuario");
		lblUsuario.setFont(fuentePrincipal);
		lblUsuario.setForeground(colorTexto);
		lblUsuario.setBounds(40, 130, 200, 20);
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setFont(fuentePrincipal);
		txtUsuario.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(200, 200, 200), 1, true),
				new EmptyBorder(5, 10, 5, 10)));
		txtUsuario.setBounds(40, 155, 290, 35);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setFont(fuentePrincipal);
		lblContrasena.setForeground(colorTexto);
		lblContrasena.setBounds(40, 210, 200, 20);
		contentPane.add(lblContrasena);

		txtPassword = new JPasswordField();
		txtPassword.setFont(fuentePrincipal);
		txtPassword.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(200, 200, 200), 1, true),
				new EmptyBorder(5, 10, 5, 10)));
		txtPassword.setBounds(40, 235, 290, 35);
		contentPane.add(txtPassword);

		JButton btnLogin = new JButton("Iniciar Sesión");
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(colorBoton);
		btnLogin.setFocusPainted(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = new String(txtPassword.getPassword());

				if (AlticeSistema.getInstance().confirmLogin(txtUsuario.getText(), pass)) {
					Principal frame = new Principal();
					frame.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(40, 300, 290, 40);
		contentPane.add(btnLogin);
	}
}