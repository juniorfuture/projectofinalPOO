package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logica.AlticeSistema;
import logica.Cliente;
import logica.Persona;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Regcliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtRegistrarCliente;
	private JTextField txtcod;
	private JTextField txtnom;
	private JTextField txtcedula;
	private JTextField txttel;
	private JTextField txtdireccion;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Regcliente dialog = new Regcliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Regcliente() {
		setTitle("Registrar Cliente");
		setBounds(100, 100, 277, 446);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setForeground(SystemColor.activeCaptionBorder);
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Codigo:\r\n");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(24, 55, 59, 23);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:\t\t\t");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(24, 91, 59, 12);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_1_1 = new JLabel("Cedula:");
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1.setBounds(24, 143, 44, 12);
			panel.add(lblNewLabel_1_1);
			
			JLabel lblNewLabel_1_1_1 = new JLabel("Telefono:");
			lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1_1.setBounds(24, 193, 59, 12);
			panel.add(lblNewLabel_1_1_1);
			
			JLabel lblNewLabel_1_1_1_1 = new JLabel("Direccion:");
			lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1_1_1.setBounds(24, 235, 59, 12);
			panel.add(lblNewLabel_1_1_1_1);
			
			JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Tipo de Cliente:\r\n");
			lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1_1_1_1_1.setBounds(24, 282, 97, 12);
			panel.add(lblNewLabel_1_1_1_1_1);
			
			txtcod = new JTextField();
			txtcod.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtcod.setText("CLI-"+AlticeSistema.numCliente);
			txtcod.setBounds(72, 58, 96, 18);
			txtcod.setEditable(false);
			panel.add(txtcod);
			txtcod.setColumns(10);
			
			txtnom = new JTextField();
			txtnom.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtnom.setBounds(24, 113, 205, 18);
			txtnom.setColumns(10);
			panel.add(txtnom);
			
			txtcedula = new JTextField();
			txtcedula.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtcedula.setBounds(24, 165, 205, 18);
			txtcedula.setColumns(10);
			panel.add(txtcedula);
			
			txttel = new JTextField();
			txttel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txttel.setBounds(24, 215, 205, 18);
			txttel.setColumns(10);
			panel.add(txttel);
			
			txtdireccion = new JTextField();
			txtdireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtdireccion.setBounds(24, 254, 205, 18);
			txtdireccion.setColumns(10);
			panel.add(txtdireccion);
			
			comboBox = new JComboBox();
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>", "Normal","Valor" }));
			comboBox.setBounds(24, 304, 130, 20);
			panel.add(comboBox);
			
			txtRegistrarCliente = new JTextField();
			txtRegistrarCliente.setBounds(-24, 0, 277, 31);
			panel.add(txtRegistrarCliente);
			txtRegistrarCliente.setForeground(Color.WHITE);
			txtRegistrarCliente.setFont(new Font("Times New Roman", Font.BOLD, 21));
			txtRegistrarCliente.setText("              Registrar Cliente");
			txtRegistrarCliente.setBackground(new Color(0, 128, 0));
			txtRegistrarCliente.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar\r\n");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(validarCampos())
						{
							Persona aux=null;
							String codigo=txtcod.getText();
							String nombre=txtnom.getText();
							String cedula=txtcedula.getText();
							String telefono=txttel.getText();
							String direccion=txtdireccion.getText();
							String tipo=comboBox.getSelectedItem().toString();
							aux=new Cliente(codigo,nombre,cedula,telefono,direccion,tipo,"Activo");
							AlticeSistema.getInstance().registrarPersona(aux);
							clean();
							JOptionPane.showMessageDialog(null, "Registro exitoso", "Informacion",JOptionPane.INFORMATION_MESSAGE);
						}
					
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private boolean validarCampos() {
		boolean aux = true;
		if (txtnom.getText().isEmpty() || txtcedula.getText().isEmpty() ||txttel.getText().isEmpty() || txtdireccion.getText().isEmpty() ||
			comboBox.getSelectedIndex() == 0) {

			JOptionPane.showMessageDialog(null,
					"Por favor, completa todos los campos antes de continuar.", "Error",
					JOptionPane.ERROR_MESSAGE);
			aux = false;
		}

		else if (!txtnom.getText().matches("[a-zA-Z ]+")) {
			JOptionPane.showMessageDialog(null,
					"El nombre no debe contener números.","Error",
					JOptionPane.ERROR_MESSAGE);
			aux = false;
		}

		else if (!txtcedula.getText().matches("\\d+")) {
			JOptionPane.showMessageDialog(null,
					"La cédula solo debe contener números.","Error",
					JOptionPane.ERROR_MESSAGE);
			aux = false;
		}

		else if (!txttel.getText().matches("\\d+")) {
			JOptionPane.showMessageDialog(null,
					"El teléfono solo debe contener números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			aux = false;
		}

		return aux;
	}
	private void clean() {
		txtcod.setText("CLI-" + AlticeSistema.numCliente);
		txtnom.setText("");
		txtcedula.setText("");
		txttel.setText("");
		txtdireccion.setText("");
		comboBox.setSelectedIndex(0);
	}
}
