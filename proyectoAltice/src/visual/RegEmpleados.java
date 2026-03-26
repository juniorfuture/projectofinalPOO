package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Administrativo;
import logica.AlticeSistema;
import logica.Comercial;
import logica.Persona;
import logica.Trabajador;

import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.border.CompoundBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	private JComboBox cmbArea;
	private JTextField textField_5;
	private JComboBox cmbAdmin;
	private JTextField txtfecha;
    private JRadioButton btnTrabajo;
	private JPanel panelTrabajador;
	private JPanel panelAdministrativo;
    private JRadioButton btnAdministrativo;
    private JRadioButton btnComercial;
    private JPanel panelComercial;
    private JComboBox cmbArea_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEmpleados dialog = new RegEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegEmpleados() {
		setBounds(100, 100, 633, 459);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Codigo:\r\n");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(161, 44, 59, 23);
			contentPanel.add(lblNewLabel);
		}
		{
			txtcodigo = new JTextField();
			txtcodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtcodigo.setText("TRA-"+AlticeSistema.numTecnico);
			txtcodigo.setEditable(false);
			txtcodigo.setColumns(10);
			txtcodigo.setBounds(209, 47, 96, 18);
			contentPanel.add(txtcodigo);
		}
		{
			JLabel txtnombre = new JLabel("Nombre:\t\t\t");
			txtnombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtnombre.setBounds(161, 80, 59, 12);
			contentPanel.add(txtnombre);
		}
		{
			textField_1 = new JTextField();
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textField_1.setColumns(10);
			textField_1.setBounds(161, 102, 205, 23);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textField_2.setColumns(10);
			textField_2.setBounds(383, 102, 205, 23);
			contentPanel.add(textField_2);
		}
		{
			JLabel txtcedula = new JLabel("Cedula:");
			txtcedula.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtcedula.setBounds(383, 80, 44, 12);
			contentPanel.add(txtcedula);
		}
		{
			JLabel txttelefono = new JLabel("Telefono:");
			txttelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txttelefono.setBounds(161, 130, 59, 12);
			contentPanel.add(txttelefono);
		}
		{
			textField_3 = new JTextField();
			textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textField_3.setColumns(10);
			textField_3.setBounds(161, 152, 205, 23);
			contentPanel.add(textField_3);
		}
		{
			JLabel txtdireccion = new JLabel("Direccion:");
			txtdireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtdireccion.setBounds(383, 133, 59, 12);
			contentPanel.add(txtdireccion);
		}
		{
			textField_4 = new JTextField();
			textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textField_4.setColumns(10);
			textField_4.setBounds(383, 152, 205, 23);
			contentPanel.add(textField_4);
		}
		{
			txtRegistroDeEmpleados = new JTextField();
			txtRegistroDeEmpleados.setBackground(new Color(192, 192, 192));
			txtRegistroDeEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
			txtRegistroDeEmpleados.setEditable(false);
			txtRegistroDeEmpleados.setText("Registro de Empleados");
			txtRegistroDeEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 19));
			txtRegistroDeEmpleados.setColumns(10);
			txtRegistroDeEmpleados.setBounds(0, 0, 619, 34);
			contentPanel.add(txtRegistroDeEmpleados);
		}
		{
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
					cmbArea.setVisible(true);
					cmbAdmin.setVisible(false);
					cmbArea_1.setVisible(false);
					txtcodigo.setText("TRA-"+AlticeSistema.numTecnico);
				}
			});
			btnTrabajo.setSelected(true);
			btnTrabajo.setBounds(24, 6, 102, 20);
			panel.add(btnTrabajo);
			
			btnAdministrativo = new JRadioButton("Administrador");
			btnAdministrativo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnAdministrativo.setSelected(true);
					btnComercial.setSelected(false);
					btnTrabajo.setSelected(false);
					panelTrabajador.setVisible(false);
					panelAdministrativo.setVisible(true);
					cmbAdmin.setVisible(true);
					cmbArea.setVisible(false);
					cmbArea_1.setVisible(false);
					txtcodigo.setText("ADM-"+AlticeSistema.numAdministrador);
				}
			});
			btnAdministrativo.setBounds(244, 6, 116, 20);
			panel.add(btnAdministrativo);
			
			btnComercial = new JRadioButton("Comercial");
			btnComercial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnAdministrativo.setSelected(false);
					btnComercial.setSelected(true);
					btnTrabajo.setSelected(false);
					panelTrabajador.setVisible(false);
					panelAdministrativo.setVisible(false);
					cmbAdmin.setVisible(false);
					panelComercial.setVisible(true);
					cmbArea.setVisible(false);
					cmbAdmin.setVisible(false);
					cmbArea_1.setVisible(true);
					txtcodigo.setText("COM-"+AlticeSistema.numComercial);
				}
			});
			btnComercial.setBounds(456, 6, 102, 20);
			panel.add(btnComercial);
		}
		
		panelTrabajador = new JPanel();
		panelTrabajador.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTrabajador.setBounds(10, 296, 599, 66);
		contentPanel.add(panelTrabajador);
		panelTrabajador.setLayout(null);
		
	    cmbArea = new JComboBox();
		cmbArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbArea.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>", "Cajero","Servicio al cliente","Tecnico","Ingeniero","Call Center" }));
		cmbArea.setBounds(124, 22, 159, 25);
		panelTrabajador.add(cmbArea);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Area Tecnica");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1_1.setBounds(29, 27, 97, 12);
		panelTrabajador.add(lblNewLabel_1_1_1_1_1);
		{
			textField_5 = new JTextField();
			textField_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textField_5.setColumns(10);
			textField_5.setBounds(161, 207, 205, 23);
			contentPanel.add(textField_5);
		}
		{
			JLabel txtsalario = new JLabel("Salario:");
			txtsalario.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtsalario.setBounds(161, 185, 59, 12);
			contentPanel.add(txtsalario);
		}
		{
			JLabel lblNewLabel_1_1_1_1 = new JLabel("Fecha de Ingreso:");
			lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1_1_1.setBounds(383, 188, 111, 12);
			contentPanel.add(lblNewLabel_1_1_1_1);
		}
		{
			txtfecha = new JTextField();
			txtfecha.setEditable(false);
			txtfecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtfecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			txtfecha.setColumns(10);
			txtfecha.setBounds(383, 207, 205, 23);
			contentPanel.add(txtfecha);
		}
		{
			panelAdministrativo = new JPanel();
			panelAdministrativo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelAdministrativo.setBounds(10, 296, 599, 66);
			contentPanel.add(panelAdministrativo);
			panelAdministrativo.setLayout(null);
			{
				JLabel labelarea = new JLabel("Area Administrativa");
				labelarea.setFont(new Font("Tahoma", Font.PLAIN, 13));
				labelarea.setBounds(29, 27, 119, 12);
				panelAdministrativo.add(labelarea);
			}
			{
				cmbAdmin = new JComboBox();
				cmbAdmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cmbAdmin.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>", "Ventas","Servicio al cliente","Tecnico","Ingeniero","Call Center" }));
				cmbAdmin.setBounds(144, 22, 159, 25);
				panelAdministrativo.add(cmbAdmin);
			}
			
		}
		
		panelComercial = new JPanel();
		panelComercial.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelComercial.setBounds(10, 296, 599, 66);
		contentPanel.add(panelComercial);
		panelComercial.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Producto que vende");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1_1_1.setBounds(29, 27, 146, 19);
		panelComercial.add(lblNewLabel_1_1_1_1_1_1);
		
		cmbArea_1 = new JComboBox();
		cmbArea_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbArea_1.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>", "Lineas telefonicas","Servicio de internet"}));
		cmbArea_1.setBounds(154, 22, 158, 24);
		panelComercial.add(cmbArea_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(validarCampos())
						{
							
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
								aux = new Trabajador(codigo, nombre, cedula, telefono, direccion,
										salario, fecha, area);
							}
							else if (btnAdministrativo.isSelected()) {

								String departamento = cmbAdmin.getSelectedItem().toString();

								aux = new Administrativo(codigo, nombre, cedula, telefono, direccion,
										salario, fecha, departamento);
							}
							else if (btnComercial.isSelected()) {

								String producto = cmbArea_1.getSelectedItem().toString();

								aux = new Comercial(codigo, nombre, cedula, telefono, direccion,
										salario, fecha, 0, producto);
							}
							AlticeSistema.getInstance().registrarPersona(aux);
							clean();

							JOptionPane.showMessageDialog(null, "Registro exitoso","Información", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private boolean validarCampos() {
	    boolean aux = true;

	    if (textField_1.getText().isEmpty() ||textField_2.getText().isEmpty() ||textField_3.getText().isEmpty() ||textField_4.getText().isEmpty() ||
	        textField_5.getText().isEmpty() || txtfecha.getText().isEmpty()) {

	        JOptionPane.showMessageDialog(null,"Por favor, completa todos los campos.","Error",JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (!textField_1.getText().matches("[a-zA-Z ]+")) {
	        JOptionPane.showMessageDialog(null,"El nombre solo debe contener letras.","Error",JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (!textField_2.getText().matches("\\d+")) {
	        JOptionPane.showMessageDialog(null,"La cédula solo debe contener números.","Error",JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (!textField_3.getText().matches("\\d+")) {
	        JOptionPane.showMessageDialog(null,"El teléfono solo debe contener números.","Error",JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (!textField_5.getText().matches("\\d+(\\.\\d+)?")) {
	        JOptionPane.showMessageDialog(null,"El salario debe ser un número válido.","Error",JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (btnTrabajo.isSelected()) {
	        if (cmbArea.getSelectedIndex() == 0) {
	            JOptionPane.showMessageDialog(null,"Seleccione un área técnica.","Error",JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    }

	    if (btnAdministrativo.isSelected()) {
	        if (cmbAdmin.getSelectedIndex() == 0) {
	            JOptionPane.showMessageDialog(null,"Seleccione un área administrativa.","Error",JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    }

	    return aux;
	}
	private void clean() {

		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		txtfecha.setText("");
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
