package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import logica.AlticeSistema;
import logica.User;

public class RegUsuarios extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtRegistrarUsuario;
	private JTextField txtNombre;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JComboBox<String> cmbTipo;

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
		setTitle("Registrar Usuario de Sistema");
		setBounds(100, 100, 290, 420);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.activeCaptionBorder);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtRegistrarUsuario = new JTextField();
		txtRegistrarUsuario.setBounds(-24, 0, 320, 31);
		panel.add(txtRegistrarUsuario);
		txtRegistrarUsuario.setForeground(SystemColor.desktop);
		txtRegistrarUsuario.setFont(new Font("Times New Roman", Font.BOLD, 21));
		txtRegistrarUsuario.setText("            Registrar Usuario");
		txtRegistrarUsuario.setBackground(SystemColor.activeCaptionBorder);
		txtRegistrarUsuario.setEditable(false);
		txtRegistrarUsuario.setColumns(10);

		// --- CAMPOS DE FORMULARIO ---
		JLabel lblNombre = new JLabel("Nombre Completo:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(24, 55, 150, 15);
		panel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNombre.setBounds(24, 75, 215, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblUsername = new JLabel("Nombre de Usuario (Login):");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(24, 110, 180, 15);
		panel.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUsername.setBounds(24, 130, 215, 20);
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(24, 165, 150, 15);
		panel.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPassword.setBounds(24, 185, 215, 20);
		panel.add(txtPassword);

		JLabel lblTipo = new JLabel("Tipo de Acceso:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipo.setBounds(24, 220, 150, 15);
		panel.add(lblTipo);

		cmbTipo = new JComboBox<String>();
		cmbTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "<Seleccione>", "Admin", "Trabajador" }));
		cmbTipo.setBounds(24, 240, 150, 25);
		panel.add(cmbTipo);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Registrar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
					String nombre = txtNombre.getText();
					String username = txtUsername.getText();
					String password = new String(txtPassword.getPassword());
					String tipo = cmbTipo.getSelectedItem().toString();

					User nuevoUsuario = new User(nombre, username, password, tipo);
					AlticeSistema.getInstance().regUser(nuevoUsuario);
					
					clean();
					JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	private boolean validarCampos() {
		boolean aux = true;
		
		String password = new String(txtPassword.getPassword());

		if (txtNombre.getText().isEmpty() || txtUsername.getText().isEmpty() || password.isEmpty() || cmbTipo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
			aux = false;
		}
		else if (!txtNombre.getText().matches("[a-zA-Z áéíóúÁÉÍÓÚñÑ]+")) {
			JOptionPane.showMessageDialog(null, "El nombre completo no debe contener números ni caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
			aux = false;
		}
		else if (txtUsername.getText().contains(" ")) {
			JOptionPane.showMessageDialog(null, "El nombre de usuario (login) no debe contener espacios.", "Error", JOptionPane.ERROR_MESSAGE);
			aux = false;
		}

		return aux;
	}

	private void clean() {
		txtNombre.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
		cmbTipo.setSelectedIndex(0);
	}
}