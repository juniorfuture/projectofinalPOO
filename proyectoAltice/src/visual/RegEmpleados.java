package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import logica.Administrativo;
import logica.AlticeSistema;
import logica.Comercial;
import logica.Persona;
import logica.Trabajador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegEmpleados extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JTextField txtcodigo;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtSalario;
	private JTextField txtFecha;

	private JComboBox<String> cmbAreaTrabajador;
	private JComboBox<String> cmbAreaAdmin;
	private JComboBox<String> cmbProductoComercial;

	private JRadioButton btnTrabajo;
	private JRadioButton btnAdministrativo;
	private JRadioButton btnComercial;

	private CardLayout cardLayoutDetalle;
	private JPanel panelDetalleTipo;
	private ButtonGroup grupoTipo;

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_PRIMARIO = new Color(31, 111, 235);
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(220, 225, 230);

	public static void main(String[] args) {
		try {
			RegEmpleados dialog = new RegEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegEmpleados() {
		setTitle("Registro de Empleados");
		setBounds(100, 100, 820, 740);
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
		icono.setIcon(cargarIcono("/imagenes/empleadoregistrar.png", 48, 48));
		header.add(icono, BorderLayout.WEST);

		JPanel textos = new JPanel(new BorderLayout(0, 4));
		textos.setOpaque(false);

		JLabel titulo = new JLabel("Registrar Empleado");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setForeground(COLOR_TEXTO);

		JLabel subtitulo = new JLabel("Completa la información del empleado y selecciona su tipo dentro del sistema.");
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

		JPanel panelDatos = new JPanel(new GridLayout(1, 2, 18, 0));
		panelDatos.setOpaque(false);

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

		JLabel lblDatosBasicos = new JLabel("Datos personales");
		lblDatosBasicos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDatosBasicos.setForeground(COLOR_TEXTO);
		lblDatosBasicos.setBounds(25, 20, 200, 25);
		panelIzquierdo.add(lblDatosBasicos);

		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCodigo.setBounds(25, 65, 120, 20);
		panelIzquierdo.add(lblCodigo);

		txtcodigo = crearTextField();
		txtcodigo.setEditable(false);
		txtcodigo.setText("TRA-" + AlticeSistema.numTecnico);
		txtcodigo.setBounds(25, 88, 150, 38);
		panelIzquierdo.add(txtcodigo);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNombre.setBounds(25, 145, 120, 20);
		panelIzquierdo.add(lblNombre);

		txtNombre = crearTextField();
		txtNombre.setBounds(25, 168, 300, 38);
		panelIzquierdo.add(txtNombre);

		JLabel lblCedula = new JLabel("Cédula");
		lblCedula.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCedula.setBounds(25, 225, 120, 20);
		panelIzquierdo.add(lblCedula);

		txtCedula = crearTextField();
		txtCedula.setBounds(25, 248, 300, 38);
		panelIzquierdo.add(txtCedula);

		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTelefono.setBounds(25, 305, 120, 20);
		panelIzquierdo.add(lblTelefono);

		txtTelefono = crearTextField();
		txtTelefono.setBounds(25, 328, 300, 38);
		panelIzquierdo.add(txtTelefono);

		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDireccion.setBounds(25, 385, 120, 20);
		panelIzquierdo.add(lblDireccion);

		txtDireccion = crearTextField();
		txtDireccion.setBounds(25, 408, 300, 38);
		panelIzquierdo.add(txtDireccion);

		JLabel lblDatosLaborales = new JLabel("Datos laborales");
		lblDatosLaborales.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDatosLaborales.setForeground(COLOR_TEXTO);
		lblDatosLaborales.setBounds(25, 20, 200, 25);
		panelDerecho.add(lblDatosLaborales);

		JLabel lblSalario = new JLabel("Salario");
		lblSalario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSalario.setBounds(25, 65, 120, 20);
		panelDerecho.add(lblSalario);

		txtSalario = crearTextField();
		txtSalario.setBounds(25, 88, 300, 38);
		panelDerecho.add(txtSalario);

		JLabel lblFecha = new JLabel("Fecha de ingreso");
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblFecha.setBounds(25, 145, 140, 20);
		panelDerecho.add(lblFecha);

		txtFecha = crearTextField();
		txtFecha.setEditable(false);
		txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		txtFecha.setBounds(25, 168, 300, 38);
		panelDerecho.add(txtFecha);

		JLabel lblTipo = new JLabel("Tipo de empleado");
		lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTipo.setBounds(25, 225, 140, 20);
		panelDerecho.add(lblTipo);

		btnTrabajo = new JRadioButton("Trabajador");
		btnTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnTrabajo.setBackground(COLOR_PANEL);
		btnTrabajo.setBounds(25, 250, 110, 25);

		btnAdministrativo = new JRadioButton("Administrativo");
		btnAdministrativo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnAdministrativo.setBackground(COLOR_PANEL);
		btnAdministrativo.setBounds(140, 250, 130, 25);

		btnComercial = new JRadioButton("Comercial");
		btnComercial.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnComercial.setBackground(COLOR_PANEL);
		btnComercial.setBounds(275, 250, 100, 25);

		grupoTipo = new ButtonGroup();
		grupoTipo.add(btnTrabajo);
		grupoTipo.add(btnAdministrativo);
		grupoTipo.add(btnComercial);

		panelDerecho.add(btnTrabajo);
		panelDerecho.add(btnAdministrativo);
		panelDerecho.add(btnComercial);

		cardLayoutDetalle = new CardLayout();
		panelDetalleTipo = new JPanel(cardLayoutDetalle);
		panelDetalleTipo.setBackground(COLOR_PANEL);
		panelDetalleTipo.setBounds(25, 300, 320, 120);

		panelDetalleTipo.add(crearPanelTrabajador(), "TRABAJADOR");
		panelDetalleTipo.add(crearPanelAdministrativo(), "ADMINISTRATIVO");
		panelDetalleTipo.add(crearPanelComercial(), "COMERCIAL");

		panelDerecho.add(panelDetalleTipo);

		btnTrabajo.setSelected(true);
		cardLayoutDetalle.show(panelDetalleTipo, "TRABAJADOR");

		btnTrabajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtcodigo.setText("TRA-" + AlticeSistema.numTecnico);
				cardLayoutDetalle.show(panelDetalleTipo, "TRABAJADOR");
			}
		});

		btnAdministrativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtcodigo.setText("ADM-" + AlticeSistema.numAdministrador);
				cardLayoutDetalle.show(panelDetalleTipo, "ADMINISTRATIVO");
			}
		});

		btnComercial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtcodigo.setText("COM-" + AlticeSistema.numComercial);
				cardLayoutDetalle.show(panelDetalleTipo, "COMERCIAL");
			}
		});

		panelDatos.add(panelIzquierdo);
		panelDatos.add(panelDerecho);

		contenedor.add(panelDatos, BorderLayout.CENTER);
		return contenedor;
	}

	private JPanel crearPanelTrabajador() {
		JPanel panel = new JPanel(null);
		panel.setBackground(COLOR_PANEL);

		JLabel lbl = new JLabel("Área técnica");
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbl.setBounds(0, 5, 120, 20);
		panel.add(lbl);

		cmbAreaTrabajador = new JComboBox<>();
		cmbAreaTrabajador.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbAreaTrabajador.setModel(new DefaultComboBoxModel<>(
				new String[] { "<Seleccione>", "Cajero", "Servicio al cliente", "Tecnico", "Ingeniero", "Call Center" }));
		cmbAreaTrabajador.setBounds(0, 30, 280, 38);
		panel.add(cmbAreaTrabajador);

		JLabel info = new JLabel("Seleccione el área asignada para este trabajador.");
		info.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		info.setForeground(COLOR_SECUNDARIO);
		info.setBounds(0, 78, 300, 20);
		panel.add(info);

		return panel;
	}

	private JPanel crearPanelAdministrativo() {
		JPanel panel = new JPanel(null);
		panel.setBackground(COLOR_PANEL);

		JLabel lbl = new JLabel("Departamento");
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbl.setBounds(0, 5, 120, 20);
		panel.add(lbl);

		cmbAreaAdmin = new JComboBox<>();
		cmbAreaAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbAreaAdmin.setModel(new DefaultComboBoxModel<>(
				new String[] { "<Seleccione>", "Ventas", "Servicio al cliente", "Tecnico", "Ingeniero", "Call Center" }));
		cmbAreaAdmin.setBounds(0, 30, 280, 38);
		panel.add(cmbAreaAdmin);

		JLabel info = new JLabel("Seleccione el departamento administrativo.");
		info.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		info.setForeground(COLOR_SECUNDARIO);
		info.setBounds(0, 78, 300, 20);
		panel.add(info);

		return panel;
	}

	private JPanel crearPanelComercial() {
		JPanel panel = new JPanel(null);
		panel.setBackground(COLOR_PANEL);

		JLabel lbl = new JLabel("Producto que vende");
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbl.setBounds(0, 5, 140, 20);
		panel.add(lbl);

		cmbProductoComercial = new JComboBox<>();
		cmbProductoComercial.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbProductoComercial.setModel(new DefaultComboBoxModel<>(
				new String[] { "<Seleccione>", "Lineas telefonicas", "Servicio de internet" }));
		cmbProductoComercial.setBounds(0, 30, 280, 38);
		panel.add(cmbProductoComercial);

		JLabel info = new JLabel("Seleccione el producto asignado al comercial.");
		info.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		info.setForeground(COLOR_SECUNDARIO);
		info.setBounds(0, 78, 300, 20);
		panel.add(info);

		return panel;
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

					Persona aux = null;

					String codigo = txtcodigo.getText();
					String nombre = txtNombre.getText();
					String cedula = txtCedula.getText();
					String telefono = txtTelefono.getText();
					String direccion = txtDireccion.getText();
					double salario = Double.parseDouble(txtSalario.getText());
					String fecha = txtFecha.getText();

					if (btnTrabajo.isSelected()) {
						String area = cmbAreaTrabajador.getSelectedItem().toString();
						aux = new Trabajador(codigo, nombre, cedula, telefono, direccion, salario, fecha, area);

					} else if (btnAdministrativo.isSelected()) {
						String departamento = cmbAreaAdmin.getSelectedItem().toString();
						aux = new Administrativo(codigo, nombre, cedula, telefono, direccion, salario, fecha,
								departamento);

					} else if (btnComercial.isSelected()) {
						String producto = cmbProductoComercial.getSelectedItem().toString();
						aux = new Comercial(codigo, nombre, cedula, telefono, direccion, salario, fecha, 0, producto);
					}

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

		if (txtNombre.getText().isEmpty() || txtCedula.getText().isEmpty() || txtTelefono.getText().isEmpty()
				|| txtDireccion.getText().isEmpty() || txtSalario.getText().isEmpty() || txtFecha.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!txtNombre.getText().matches("[a-zA-Z ]+")) {
			JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!txtCedula.getText().matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "La cédula solo debe contener números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!txtTelefono.getText().matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "El teléfono solo debe contener números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!txtSalario.getText().matches("\\d+(\\.\\d+)?")) {
			JOptionPane.showMessageDialog(null, "El salario debe ser un número válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (btnTrabajo.isSelected() && cmbAreaTrabajador.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione un área técnica.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (btnAdministrativo.isSelected() && cmbAreaAdmin.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione un área administrativa.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (btnComercial.isSelected() && cmbProductoComercial.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione un producto.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void clean() {
		txtNombre.setText("");
		txtCedula.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtSalario.setText("");
		txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

		cmbAreaTrabajador.setSelectedIndex(0);
		cmbAreaAdmin.setSelectedIndex(0);
		cmbProductoComercial.setSelectedIndex(0);

		btnTrabajo.setSelected(true);
		cardLayoutDetalle.show(panelDetalleTipo, "TRABAJADOR");

		txtcodigo.setText("TRA-" + AlticeSistema.numTecnico);
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