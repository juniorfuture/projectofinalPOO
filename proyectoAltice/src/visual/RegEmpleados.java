package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import logica.Administrativo;
import logica.AlticeSistema;
import logica.Comercial;
import logica.Persona;
import logica.Trabajador;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class RegEmpleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JTextField txtcodigo;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField txtRegistroDeEmpleados;
	private JComboBox<String> cmbArea;
	private JTextField textField_5;
	private JComboBox<String> cmbAdmin;
	private JTextField txtfecha;
	private JRadioButton btnTrabajo;
	private JPanel panelTrabajador;
	private JPanel panelAdministrativo;
	private JRadioButton btnAdministrativo;
	private JRadioButton btnComercial;
	private JPanel panelComercial;
	private JComboBox<String> cmbArea_1;

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
		setBounds(100, 100, 633, 459);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblImagen = new JLabel("");
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		int anchoContenedor = 136;
		int altoContenedor = 136;
		lblImagen.setBounds(7, 66, 144, 150);
		try {
			java.net.URL imgUrl = RegEmpleados.class.getResource("/imagenes/empleadoregistrar.png");
			if (imgUrl != null) {
				ImageIcon iconOriginal = new ImageIcon(imgUrl);
				Image imgEscalada = iconOriginal.getImage().getScaledInstance(anchoContenedor, altoContenedor,
						Image.SCALE_SMOOTH);
				lblImagen.setIcon(new ImageIcon(imgEscalada));
			} else {
				System.err.println("No se encontró el archivo de imagen en /imagenes/empleadoregistrar.png");
				lblImagen.setText("Sin Imagen");
				lblImagen.setBorder(new EtchedBorder());
			}
		} catch (Exception e) {
			System.err.println("Error cargando/escalando imagen: " + e.getMessage());
		}
		contentPanel.add(lblImagen);
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(161, 44, 59, 23);
		contentPanel.add(lblNewLabel);
		txtcodigo = new JTextField();
		txtcodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcodigo.setText("TRA-" + AlticeSistema.numTecnico);
		txtcodigo.setEditable(false);
		txtcodigo.setColumns(10);
		txtcodigo.setBounds(209, 47, 96, 18);
		contentPanel.add(txtcodigo);

		JLabel txtnombre = new JLabel("Nombre:");
		txtnombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtnombre.setBounds(161, 80, 59, 12);
		contentPanel.add(txtnombre);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(161, 102, 205, 23);
		contentPanel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(383, 102, 205, 23);
		contentPanel.add(textField_2);

		JLabel txtcedula = new JLabel("Cedula:");
		txtcedula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtcedula.setBounds(383, 80, 44, 12);
		contentPanel.add(txtcedula);

		JLabel txttelefono = new JLabel("Telefono:");
		txttelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txttelefono.setBounds(161, 130, 59, 12);
		contentPanel.add(txttelefono);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(161, 152, 205, 23);
		contentPanel.add(textField_3);

		JLabel txtdireccion = new JLabel("Direccion:");
		txtdireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtdireccion.setBounds(383, 133, 59, 12);
		contentPanel.add(txtdireccion);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(383, 152, 205, 23);
		contentPanel.add(textField_4);

		txtRegistroDeEmpleados = new JTextField();
		txtRegistroDeEmpleados.setBackground(new Color(192, 192, 192));
		txtRegistroDeEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegistroDeEmpleados.setEditable(false);
		txtRegistroDeEmpleados.setText("Registro de Empleados");
		txtRegistroDeEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtRegistroDeEmpleados.setColumns(10);
		txtRegistroDeEmpleados.setBounds(0, 0, 619, 34);
		contentPanel.add(txtRegistroDeEmpleados);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 246, 599, 34);
		contentPanel.add(panel);
		panel.setLayout(null);

		btnTrabajo = new JRadioButton("Trabajador");
		btnTrabajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdministrativo.setSelected(false);
				btnComercial.setSelected(false);
				btnTrabajo.setSelected(true);

				panelTrabajador.setVisible(true);
				panelAdministrativo.setVisible(false);
				panelComercial.setVisible(false);

				txtcodigo.setText("TRA-" + AlticeSistema.numTecnico);
			}
		});
		btnTrabajo.setSelected(true);
		btnTrabajo.setBounds(24, 6, 102, 20);
		panel.add(btnTrabajo);

		btnAdministrativo = new JRadioButton("Administrativo");
		btnAdministrativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdministrativo.setSelected(true);
				btnComercial.setSelected(false);
				btnTrabajo.setSelected(false);

				panelTrabajador.setVisible(false);
				panelAdministrativo.setVisible(true);
				panelComercial.setVisible(false);

				txtcodigo.setText("ADM-" + AlticeSistema.numAdministrador);
			}
		});
		btnAdministrativo.setBounds(220, 6, 116, 20);
		panel.add(btnAdministrativo);

		btnComercial = new JRadioButton("Comercial");
		btnComercial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdministrativo.setSelected(false);
				btnComercial.setSelected(true);
				btnTrabajo.setSelected(false);

				panelTrabajador.setVisible(false);
				panelAdministrativo.setVisible(false);
				panelComercial.setVisible(true);

				txtcodigo.setText("COM-" + AlticeSistema.numComercial);
			}
		});
		btnComercial.setBounds(430, 6, 102, 20);
		panel.add(btnComercial);

		panelTrabajador = new JPanel();
		panelTrabajador.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTrabajador.setBounds(10, 296, 599, 66);
		contentPanel.add(panelTrabajador);
		panelTrabajador.setLayout(null);

		cmbArea = new JComboBox<String>();
		cmbArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbArea.setModel(new DefaultComboBoxModel<String>(new String[] { "<Seleccione>", "Cajero",
				"Servicio al cliente", "Tecnico", "Ingeniero", "Call Center" }));
		cmbArea.setBounds(124, 22, 159, 25);
		panelTrabajador.add(cmbArea);

		JLabel lblAreaTecnica = new JLabel("Area Tecnica");
		lblAreaTecnica.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAreaTecnica.setBounds(29, 27, 97, 12);
		panelTrabajador.add(lblAreaTecnica);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(161, 207, 205, 23);
		contentPanel.add(textField_5);

		JLabel txtsalario = new JLabel("Salario:");
		txtsalario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtsalario.setBounds(161, 185, 59, 12);
		contentPanel.add(txtsalario);

		JLabel lblFechaIngreso = new JLabel("Fecha de Ingreso:");
		lblFechaIngreso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaIngreso.setBounds(383, 188, 111, 12);
		contentPanel.add(lblFechaIngreso);

		txtfecha = new JTextField();
		txtfecha.setEditable(false);
		txtfecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtfecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		txtfecha.setColumns(10);
		txtfecha.setBounds(383, 207, 205, 23);
		contentPanel.add(txtfecha);

		panelAdministrativo = new JPanel();
		panelAdministrativo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAdministrativo.setBounds(10, 296, 599, 66);
		contentPanel.add(panelAdministrativo);
		panelAdministrativo.setLayout(null);

		JLabel labelarea = new JLabel("Area Administrativa");
		labelarea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelarea.setBounds(29, 27, 119, 12);
		panelAdministrativo.add(labelarea);

		cmbAdmin = new JComboBox<String>();
		cmbAdmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbAdmin.setModel(new DefaultComboBoxModel<String>(new String[] { "<Seleccione>", "Ventas",
				"Servicio al cliente", "Tecnico", "Ingeniero", "Call Center" }));
		cmbAdmin.setBounds(144, 22, 159, 25);
		panelAdministrativo.add(cmbAdmin);

		panelComercial = new JPanel();
		panelComercial.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelComercial.setBounds(10, 296, 599, 66);
		contentPanel.add(panelComercial);
		panelComercial.setLayout(null);

		JLabel lblProducto = new JLabel("Producto que vende");
		lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProducto.setBounds(29, 27, 146, 19);
		panelComercial.add(lblProducto);

		cmbArea_1 = new JComboBox<String>();
		cmbArea_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbArea_1.setModel(new DefaultComboBoxModel<String>(
				new String[] { "<Seleccione>", "Lineas telefonicas", "Servicio de internet" }));
		cmbArea_1.setBounds(154, 22, 158, 24);
		panelComercial.add(cmbArea_1);

		panelAdministrativo.setVisible(false);
		panelComercial.setVisible(false);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Registrar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {

					Persona aux = null;

					String codigo = txtcodigo.getText();
					String nombre = textField_1.getText();
					String cedula = textField_2.getText();
					String telefono = textField_3.getText();
					String direccion = textField_4.getText();
					double salario = Double.parseDouble(textField_5.getText());
					String fecha = txtfecha.getText();

					if (btnTrabajo.isSelected()) {
						String area = cmbArea.getSelectedItem().toString();
						aux = new Trabajador(codigo, nombre, cedula, telefono, direccion, salario, fecha, area);

					} else if (btnAdministrativo.isSelected()) {
						String departamento = cmbAdmin.getSelectedItem().toString();
						aux = new Administrativo(codigo, nombre, cedula, telefono, direccion, salario, fecha,
								departamento);

					} else if (btnComercial.isSelected()) {
						String producto = cmbArea_1.getSelectedItem().toString();
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
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	private boolean validarCampos() {

		if (textField_1.getText().isEmpty() || textField_2.getText().isEmpty() || textField_3.getText().isEmpty()
				|| textField_4.getText().isEmpty() || textField_5.getText().isEmpty() || txtfecha.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!textField_1.getText().matches("[a-zA-Z ]+")) {
			JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!textField_2.getText().matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "La cédula solo debe contener números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!textField_3.getText().matches("\\d+")) {
			JOptionPane.showMessageDialog(null, "El teléfono solo debe contener números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!textField_5.getText().matches("\\d+(\\.\\d+)?")) {
			JOptionPane.showMessageDialog(null, "El salario debe ser un número válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (btnTrabajo.isSelected() && cmbArea.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione un área técnica.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (btnAdministrativo.isSelected() && cmbAdmin.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione un área administrativa.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (btnComercial.isSelected() && cmbArea_1.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione un producto.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void clean() {
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		txtfecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

		cmbArea.setSelectedIndex(0);
		cmbAdmin.setSelectedIndex(0);
		cmbArea_1.setSelectedIndex(0);

		btnTrabajo.setSelected(true);
		btnAdministrativo.setSelected(false);
		btnComercial.setSelected(false);

		panelTrabajador.setVisible(true);
		panelAdministrativo.setVisible(false);
		panelComercial.setVisible(false);

		txtcodigo.setText("TRA-" + AlticeSistema.numTecnico);
	}
}