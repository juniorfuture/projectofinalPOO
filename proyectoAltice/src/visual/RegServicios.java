package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import logica.AlticeSistema;
import logica.Servicio;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class RegServicios extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JTextField txtIdServicio;
	private JTextField txtNombre;
	private JTextField txtCosto;
	private JTextField txtTituloHeader;
	private JComboBox<String> cmbTipo;

	public static void main(String[] args) {
		try {
			RegServicios dialog = new RegServicios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegServicios() {
		setTitle("Registro de Servicios");
		setBounds(100, 100, 450, 350);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		txtTituloHeader = new JTextField();
		txtTituloHeader.setBackground(new Color(192, 192, 192));
		txtTituloHeader.setHorizontalAlignment(SwingConstants.CENTER);
		txtTituloHeader.setEditable(false);
		txtTituloHeader.setText("Registro de Servicios");
		txtTituloHeader.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtTituloHeader.setBounds(0, 0, 434, 34);
		contentPanel.add(txtTituloHeader);

		JLabel lblId = new JLabel("ID Servicio:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(30, 60, 80, 23);
		contentPanel.add(lblId);

		txtIdServicio = new JTextField();
		txtIdServicio.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtIdServicio.setText("SRV-" + AlticeSistema.numServicio);
		txtIdServicio.setEditable(false);
		txtIdServicio.setBounds(120, 63, 120, 20);
		contentPanel.add(txtIdServicio);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(30, 100, 80, 23);
		contentPanel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(120, 102, 250, 23);
		contentPanel.add(txtNombre);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipo.setBounds(30, 140, 80, 23);
		contentPanel.add(lblTipo);

		cmbTipo = new JComboBox<String>();
		cmbTipo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "<Seleccione>", "Internet", "Telefonía", "Televisión", "Plan Combo" }));
		cmbTipo.setBounds(120, 142, 150, 23);
		contentPanel.add(cmbTipo);

		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCosto.setBounds(30, 180, 80, 23);
		contentPanel.add(lblCosto);

		txtCosto = new JTextField();
		txtCosto.setBounds(120, 182, 120, 23);
		contentPanel.add(txtCosto);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Registrar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
					String id = txtIdServicio.getText();
					String nombre = txtNombre.getText();
					String tipo = cmbTipo.getSelectedItem().toString();
					double costo = Double.parseDouble(txtCosto.getText());

					Servicio nuevoServicio = new Servicio(id, nombre, tipo, costo);
					AlticeSistema.getInstance().registrarServicio(nuevoServicio);

					JOptionPane.showMessageDialog(null, "Servicio registrado correctamente", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					clean();
				}
			}
		});
		buttonPane.add(okButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);
	}

	private boolean validarCampos() {
		if (txtNombre.getText().trim().isEmpty() || txtCosto.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (cmbTipo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de servicio.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Double.parseDouble(txtCosto.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El costo debe ser un valor numérico.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void clean() {
		txtNombre.setText("");
		txtCosto.setText("");
		cmbTipo.setSelectedIndex(0);
		txtIdServicio.setText("SRV-" + AlticeSistema.numServicio);
	}
}