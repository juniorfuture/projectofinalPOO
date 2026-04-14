package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logica.AlticeSistema;
import logica.Cliente;
import logica.Persona;

public class Regcliente extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JTextField txtcod;
	private JTextField txtnom;
	private JTextField txtcedula;
	private JTextField txttel;
	private JTextField txtdireccion;
	private JTextField txtRNC;

	private JComboBox<String> comboBox;
	private JComboBox<String> cmbCategoria;

	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	private ButtonGroup grupoGenero;

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_PRIMARIO = new Color(31, 111, 235);
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(220, 225, 230);

	public static void main(String[] args) {
		try {
			Regcliente dialog = new Regcliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Regcliente() {
		setTitle("Registro de Clientes");
		setBounds(100, 100, 760, 770);
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
		icono.setIcon(cargarIcono("/imagenes/iconcliente.png", 44, 44));
		header.add(icono, BorderLayout.WEST);

		JPanel textos = new JPanel(new BorderLayout(0, 4));
		textos.setOpaque(false);

		JLabel titulo = new JLabel("Registrar Cliente");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setForeground(COLOR_TEXTO);

		JLabel subtitulo = new JLabel("Completa la información del cliente para registrarlo en el sistema.");
		subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		subtitulo.setForeground(COLOR_SECUNDARIO);

		textos.add(titulo, BorderLayout.NORTH);
		textos.add(subtitulo, BorderLayout.CENTER);

		header.add(textos, BorderLayout.CENTER);

		return header;
	}

	private JPanel crearContenido() {
		JPanel contenedor = new JPanel(new GridLayout(1, 2, 18, 0));
		contenedor.setOpaque(false);

		JPanel panelIzquierdo = new JPanel(null);
		panelIzquierdo.setBackground(COLOR_PANEL);
		panelIzquierdo.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(10, 10, 10, 10)));

		JPanel panelDerecho = new JPanel(null);
		panelDerecho.setBackground(COLOR_PANEL);
		panelDerecho.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(10, 10, 10, 10)));

		JLabel lblDatosBasicos = new JLabel("Datos básicos");
		lblDatosBasicos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDatosBasicos.setForeground(COLOR_TEXTO);
		lblDatosBasicos.setBounds(25, 20, 180, 25);
		panelIzquierdo.add(lblDatosBasicos);

		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCodigo.setForeground(COLOR_TEXTO);
		lblCodigo.setBounds(25, 65, 120, 20);
		panelIzquierdo.add(lblCodigo);

		txtcod = crearTextField();
		txtcod.setText("CLI-" + AlticeSistema.numCliente);
		txtcod.setEditable(false);
		txtcod.setBounds(25, 88, 150, 38);
		panelIzquierdo.add(txtcod);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNombre.setForeground(COLOR_TEXTO);
		lblNombre.setBounds(25, 145, 120, 20);
		panelIzquierdo.add(lblNombre);

		txtnom = crearTextField();
		txtnom.setBounds(25, 168, 285, 38);
		panelIzquierdo.add(txtnom);

		JLabel lblCedula = new JLabel("Cédula");
		lblCedula.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCedula.setForeground(COLOR_TEXTO);
		lblCedula.setBounds(25, 225, 120, 20);
		panelIzquierdo.add(lblCedula);

		txtcedula = crearTextField();
		txtcedula.setBounds(25, 248, 285, 38);
		panelIzquierdo.add(txtcedula);

		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTelefono.setForeground(COLOR_TEXTO);
		lblTelefono.setBounds(25, 305, 120, 20);
		panelIzquierdo.add(lblTelefono);

		txttel = crearTextField();
		txttel.setBounds(25, 328, 285, 38);
		panelIzquierdo.add(txttel);

		JLabel lblGenero = new JLabel("Género");
		lblGenero.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblGenero.setForeground(COLOR_TEXTO);
		lblGenero.setBounds(25, 385, 120, 20);
		panelIzquierdo.add(lblGenero);

		rdbtnM = new JRadioButton("Masculino");
		rdbtnM.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		rdbtnM.setBackground(COLOR_PANEL);
		rdbtnM.setBounds(25, 410, 110, 25);
		panelIzquierdo.add(rdbtnM);

		rdbtnF = new JRadioButton("Femenino");
		rdbtnF.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		rdbtnF.setBackground(COLOR_PANEL);
		rdbtnF.setBounds(145, 410, 110, 25);
		panelIzquierdo.add(rdbtnF);

		grupoGenero = new ButtonGroup();
		grupoGenero.add(rdbtnM);
		grupoGenero.add(rdbtnF);

		JLabel lblDatosSistema = new JLabel("Datos del cliente");
		lblDatosSistema.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDatosSistema.setForeground(COLOR_TEXTO);
		lblDatosSistema.setBounds(25, 20, 180, 25);
		panelDerecho.add(lblDatosSistema);

		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDireccion.setForeground(COLOR_TEXTO);
		lblDireccion.setBounds(25, 65, 120, 20);
		panelDerecho.add(lblDireccion);

		txtdireccion = crearTextField();
		txtdireccion.setBounds(25, 88, 285, 38);
		panelDerecho.add(txtdireccion);

		JLabel lblTipoCliente = new JLabel("Tipo");
		lblTipoCliente.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTipoCliente.setForeground(COLOR_TEXTO);
		lblTipoCliente.setBounds(25, 145, 120, 20);
		panelDerecho.add(lblTipoCliente);

		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "<Seleccione>", "Normal", "Empresarial" }));
		comboBox.setBounds(25, 168, 285, 38);
		panelDerecho.add(comboBox);

		JLabel lblCategoria = new JLabel("Categoría");
		lblCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCategoria.setForeground(COLOR_TEXTO);
		lblCategoria.setBounds(25, 225, 120, 20);
		panelDerecho.add(lblCategoria);

		cmbCategoria = new JComboBox<>();
		cmbCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbCategoria.setModel(new DefaultComboBoxModel<>(new String[] { "<Seleccione>", "Fisico", "Juridico" }));
		cmbCategoria.setBounds(25, 248, 285, 38);
		panelDerecho.add(cmbCategoria);

		JLabel lblRNC = new JLabel("RNC");
		lblRNC.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblRNC.setForeground(COLOR_TEXTO);
		lblRNC.setBounds(25, 305, 120, 20);
		panelDerecho.add(lblRNC);

		txtRNC = crearTextField();
		txtRNC.setBounds(25, 328, 285, 38);
		panelDerecho.add(txtRNC);

		JLabel lblNota = new JLabel(
				"<html>Si el cliente es físico y el RNC se deja vacío, el sistema usará la cédula como referencia.</html>");
		lblNota.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNota.setForeground(COLOR_SECUNDARIO);
		lblNota.setBounds(25, 390, 300, 55);
		panelDerecho.add(lblNota);

		contenedor.add(panelIzquierdo);
		contenedor.add(panelDerecho);

		return contenedor;
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
				if (validarCampos()) {
					String genero = rdbtnM.isSelected() ? "M" : "F";
					String codigo = txtcod.getText();
					String nombre = txtnom.getText();
					String cedula = txtcedula.getText();
					String telefono = txttel.getText();
					String direccion = txtdireccion.getText();
					String tipo = comboBox.getSelectedItem().toString();
					String categoria = cmbCategoria.getSelectedItem().toString();
					String valorRNC = txtRNC.getText().trim();

					if (categoria.equalsIgnoreCase("Fisico") && valorRNC.isEmpty()) {
						valorRNC = cedula;
					}

					Persona aux = new Cliente(codigo, nombre, cedula, telefono, direccion, tipo, "Activo", valorRNC,
							genero, categoria);

					AlticeSistema.getInstance().registrarPersona(aux);

					clean();
					JOptionPane.showMessageDialog(null, "Registro exitoso", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
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

	private boolean validarCampos() {
		if (txtnom.getText().trim().isEmpty() || txtcedula.getText().trim().isEmpty() || txttel.getText().trim().isEmpty()
				|| txtdireccion.getText().trim().isEmpty() || comboBox.getSelectedIndex() == 0
				|| cmbCategoria.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos obligatorios.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!rdbtnM.isSelected() && !rdbtnF.isSelected()) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione el género.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!txtnom.getText().matches("[a-zA-Z áéíóúÁÉÍÓÚñÑ0-9]+")) {
			JOptionPane.showMessageDialog(null, "El nombre contiene caracteres inválidos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!txtcedula.getText().matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "La cédula solo debe contener números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!txttel.getText().matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "El teléfono solo debe contener números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (cmbCategoria.getSelectedItem().toString().equalsIgnoreCase("Juridico")
				&& txtRNC.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Para clientes jurídicos debe colocar el RNC.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void clean() {
		txtcod.setText("CLI-" + AlticeSistema.numCliente);
		txtnom.setText("");
		txtcedula.setText("");
		txttel.setText("");
		txtdireccion.setText("");
		txtRNC.setText("");
		comboBox.setSelectedIndex(0);
		cmbCategoria.setSelectedIndex(0);
		grupoGenero.clearSelection();
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