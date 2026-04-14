package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
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
	private JComboBox<String> comboBox;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	private ButtonGroup grupoGenero;

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
		setBounds(100, 100, 320, 460);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblTitulo = new JLabel("Registrar Cliente");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(70, 130, 180));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(0, 0, 304, 35);
		contentPanel.add(lblTitulo);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigo.setBounds(20, 55, 60, 20);
		contentPanel.add(lblCodigo);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(20, 95, 60, 20);
		contentPanel.add(lblNombre);

		JLabel lblCedula = new JLabel("Cédula:");
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCedula.setBounds(20, 135, 60, 20);
		contentPanel.add(lblCedula);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefono.setBounds(20, 175, 60, 20);
		contentPanel.add(lblTelefono);

		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDireccion.setBounds(20, 215, 60, 20);
		contentPanel.add(lblDireccion);

		JLabel lblGenero = new JLabel("Género:");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGenero.setBounds(20, 255, 60, 20);
		contentPanel.add(lblGenero);

		JLabel lblTipoCliente = new JLabel("Tipo:");
		lblTipoCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipoCliente.setBounds(20, 295, 60, 20);
		contentPanel.add(lblTipoCliente);

		txtcod = new JTextField();
		txtcod.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtcod.setText("CLI-" + AlticeSistema.numCliente);
		txtcod.setBounds(90, 55, 100, 22);
		txtcod.setEditable(false);
		contentPanel.add(txtcod);

		txtnom = new JTextField();
		txtnom.setBounds(90, 95, 190, 22);
		contentPanel.add(txtnom);

		txtcedula = new JTextField();
		txtcedula.setBounds(90, 135, 190, 22);
		contentPanel.add(txtcedula);

		txttel = new JTextField();
		txttel.setBounds(90, 175, 190, 22);
		contentPanel.add(txttel);

		txtdireccion = new JTextField();
		txtdireccion.setBounds(90, 215, 190, 22);
		contentPanel.add(txtdireccion);

		rdbtnM = new JRadioButton("M");
		rdbtnM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnM.setBounds(90, 255, 50, 20);
		contentPanel.add(rdbtnM);

		rdbtnF = new JRadioButton("F");
		rdbtnF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnF.setBounds(140, 255, 50, 20);
		contentPanel.add(rdbtnF);

		grupoGenero = new ButtonGroup();
		grupoGenero.add(rdbtnM);
		grupoGenero.add(rdbtnF);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "<Seleccione>", "Normal", "Empresarial" }));
		comboBox.setBounds(90, 295, 190, 22);
		contentPanel.add(comboBox);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Registrar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
					String genero = rdbtnM.isSelected() ? "M" : "F";
					String codigo = txtcod.getText();
					String nombre = txtnom.getText();
					String cedula = txtcedula.getText();
					String telefono = txttel.getText();
					String direccion = txtdireccion.getText();
					String tipo = comboBox.getSelectedItem().toString();
					String valorExtra = "";

					if (tipo.equals("Valor")) {
						valorExtra = cedula;
					}

					Persona aux = new Cliente(codigo, nombre, cedula, telefono, direccion, tipo, "Activo", valorExtra,
							genero);

					AlticeSistema.getInstance().registrarPersona(aux);

					clean();
					JOptionPane.showMessageDialog(null, "Registro exitoso", "Información",
							JOptionPane.INFORMATION_MESSAGE);
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
		if (txtnom.getText().trim().isEmpty() || txtcedula.getText().trim().isEmpty()
				|| txttel.getText().trim().isEmpty() || txtdireccion.getText().trim().isEmpty()
				|| comboBox.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos de texto y tipo.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!rdbtnM.isSelected() && !rdbtnF.isSelected()) {
			JOptionPane.showMessageDialog(null, "Por favor, seleccione el género (M o F).", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!txtnom.getText().matches("[a-zA-Z áéíóúÁÉÍÓÚñÑ]+")) {
			JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras.", "Error",
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

		return true;
	}

	private void clean() {
		txtcod.setText("CLI-" + AlticeSistema.numCliente);
		txtnom.setText("");
		txtcedula.setText("");
		txttel.setText("");
		txtdireccion.setText("");
		comboBox.setSelectedIndex(0);
		grupoGenero.clearSelection();
	}
}