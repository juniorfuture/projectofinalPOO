package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import logica.AlticeSistema;
import logica.Cliente;
import logica.User;

public class RegUsuarios extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JTextField txtNombre;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JComboBox<String> cmbTipo;
	private JComboBox<String> cmbCliente;
	private JPanel panelClienteRelacionado;

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_PRIMARIO = new Color(31, 111, 235);
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(220, 225, 230);

	public static void main(String[] args) {
		try {
			RegUsuarios dialog = new RegUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegUsuarios() {
		setTitle("Registrar Usuario");
		setBounds(100, 100, 700, 700);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(COLOR_FONDO);

		contentPanel.setBackground(COLOR_FONDO);
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPanel.setLayout(new BorderLayout(18, 18));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		contentPanel.add(crearHeader(), BorderLayout.NORTH);
		contentPanel.add(crearContenido(), BorderLayout.CENTER);
		contentPanel.add(crearBotonera(), BorderLayout.SOUTH);
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
		icono.setIcon(cargarIcono("/imagenes/usuario.png", 48, 48));
		header.add(icono, BorderLayout.WEST);

		JPanel textos = new JPanel(new BorderLayout(0, 4));
		textos.setOpaque(false);

		JLabel titulo = new JLabel("Registrar Usuario");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setForeground(COLOR_TEXTO);

		JLabel subtitulo = new JLabel("Cree un nuevo acceso al sistema indicando el rol correspondiente.");
		subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		subtitulo.setForeground(COLOR_SECUNDARIO);

		textos.add(titulo, BorderLayout.NORTH);
		textos.add(subtitulo, BorderLayout.CENTER);

		header.add(textos, BorderLayout.CENTER);
		return header;
	}

	private JPanel crearContenido() {
		JPanel contenedor = new JPanel(new BorderLayout(18, 18));
		contenedor.setOpaque(false);

		JPanel panelFormulario = new JPanel(null);
		panelFormulario.setBackground(COLOR_PANEL);
		panelFormulario.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(10, 10, 10, 10)));

		JLabel lblDatos = new JLabel("Datos de acceso");
		lblDatos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDatos.setForeground(COLOR_TEXTO);
		lblDatos.setBounds(30, 25, 180, 25);
		panelFormulario.add(lblDatos);

		JLabel lblNombre = new JLabel("Nombre completo");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNombre.setForeground(COLOR_TEXTO);
		lblNombre.setBounds(30, 80, 140, 20);
		panelFormulario.add(lblNombre);

		txtNombre = crearTextField();
		txtNombre.setBounds(30, 105, 280, 42);
		panelFormulario.add(txtNombre);

		JLabel lblUsername = new JLabel("Nombre de usuario");
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblUsername.setForeground(COLOR_TEXTO);
		lblUsername.setBounds(340, 80, 140, 20);
		panelFormulario.add(lblUsername);

		txtUsername = crearTextField();
		txtUsername.setBounds(340, 105, 280, 42);
		panelFormulario.add(txtUsername);

		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPassword.setForeground(COLOR_TEXTO);
		lblPassword.setBounds(30, 185, 140, 20);
		panelFormulario.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPassword.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(8, 10, 8, 10)));
		txtPassword.setBounds(30, 210, 280, 42);
		panelFormulario.add(txtPassword);

		JLabel lblTipo = new JLabel("Tipo de acceso");
		lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTipo.setForeground(COLOR_TEXTO);
		lblTipo.setBounds(340, 185, 140, 20);
		panelFormulario.add(lblTipo);

		cmbTipo = new JComboBox<String>();
		cmbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbTipo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "<Seleccione>", "Admin", "Trabajador", "Cliente" }));
		cmbTipo.setBounds(340, 210, 280, 42);
		cmbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarModoCliente();
			}
		});
		panelFormulario.add(cmbTipo);

		panelClienteRelacionado = new JPanel(null);
		panelClienteRelacionado.setOpaque(false);
		panelClienteRelacionado.setBounds(30, 285, 590, 90);
		panelFormulario.add(panelClienteRelacionado);

		JLabel lblCliente = new JLabel("Cliente relacionado");
		lblCliente.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCliente.setForeground(COLOR_TEXTO);
		lblCliente.setBounds(0, 0, 140, 20);
		panelClienteRelacionado.add(lblCliente);

		cmbCliente = new JComboBox<String>();
		cmbCliente.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbCliente.setBounds(0, 25, 590, 42);
		panelClienteRelacionado.add(cmbCliente);

		JPanel panelInfo = new JPanel(new BorderLayout());
		panelInfo.setBackground(new Color(245, 248, 255));
		panelInfo.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(210, 220, 235), 1, true),
				new EmptyBorder(16, 16, 16, 16)));
		panelInfo.setBounds(30, 410, 590, 90);

		cargarClientesEnCombo();
		actualizarModoCliente();

		contenedor.add(panelFormulario, BorderLayout.CENTER);
		return contenedor;
	}

	private void cargarClientesEnCombo() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		model.addElement("<Seleccione cliente>");
		List<Cliente> clientes = AlticeSistema.getInstance().getClientes();
		for (Cliente c : clientes) {
			model.addElement(c.getId() + " - " + c.getNombre());
		}
		cmbCliente.setModel(model);
	}

	private void actualizarModoCliente() {
		boolean visible = "Cliente".equalsIgnoreCase(String.valueOf(cmbTipo.getSelectedItem()));
		panelClienteRelacionado.setVisible(visible);
	}

	private JPanel crearBotonera() {
		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		buttonPane.setOpaque(false);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFocusPainted(false);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBackground(new Color(230, 235, 240));
		btnCancelar.setForeground(COLOR_TEXTO);
		btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCancelar.setPreferredSize(new java.awt.Dimension(120, 40));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(btnCancelar);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setBorderPainted(false);
		btnRegistrar.setBackground(COLOR_PRIMARIO);
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRegistrar.setPreferredSize(new java.awt.Dimension(130, 40));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarUsuario();
			}
		});
		buttonPane.add(btnRegistrar);
		getRootPane().setDefaultButton(btnRegistrar);

		return buttonPane;
	}

	private JTextField crearTextField() {
		JTextField field = new JTextField();
		field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		field.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(8, 10, 8, 10)));
		return field;
	}

	private void registrarUsuario() {
		if (validarCampos()) {
			String nombre = txtNombre.getText().trim();
			String username = txtUsername.getText().trim();
			String password = new String(txtPassword.getPassword());
			String tipo = cmbTipo.getSelectedItem().toString();

			String idRelacionado = "";
			if (tipo.equalsIgnoreCase("Cliente")) {
				String seleccion = cmbCliente.getSelectedItem().toString();
				idRelacionado = seleccion.split(" - ")[0].trim();
			}

			User nuevoUsuario = new User(nombre, username, password, tipo, idRelacionado);
			boolean registrado = AlticeSistema.getInstance().regUser(nuevoUsuario);

			if (!registrado) {
				JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya existe.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			clean();
			JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	private boolean validarCampos() {
		String password = new String(txtPassword.getPassword());

		if (txtNombre.getText().trim().isEmpty() || txtUsername.getText().trim().isEmpty() || password.isEmpty()
				|| cmbTipo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos antes de continuar.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!txtNombre.getText().matches("[a-zA-Z áéíóúÁÉÍÓÚñÑ]+")) {
			JOptionPane.showMessageDialog(null,
					"El nombre completo no debe contener números ni caracteres especiales.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtUsername.getText().contains(" ")) {
			JOptionPane.showMessageDialog(null,
					"El nombre de usuario no debe contener espacios.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if ("Cliente".equalsIgnoreCase(String.valueOf(cmbTipo.getSelectedItem())) && cmbCliente.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null,
					"Debes seleccionar el cliente que estará vinculado a este usuario.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void clean() {
		txtNombre.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
		cmbTipo.setSelectedIndex(0);
		cmbCliente.setSelectedIndex(0);
		actualizarModoCliente();
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