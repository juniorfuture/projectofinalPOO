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
import logica.Plan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class RegPlanes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JTextField txtIdPlan;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtTituloHeader;
	private JComboBox<String> cmbTipo;
	private JTextArea txtDescripcion;

	public static void main(String[] args) {
		try {
			RegPlanes dialog = new RegPlanes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegPlanes() {
		setTitle("Registro de Planes");
		setBounds(100, 100, 500, 450);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		txtTituloHeader = new JTextField();
		txtTituloHeader.setBackground(new Color(192, 192, 192));
		txtTituloHeader.setHorizontalAlignment(SwingConstants.CENTER);
		txtTituloHeader.setEditable(false);
		txtTituloHeader.setText("Registro de Planes");
		txtTituloHeader.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtTituloHeader.setBounds(0, 0, 484, 34);
		contentPanel.add(txtTituloHeader);

		JLabel lblId = new JLabel("ID Plan:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(30, 60, 80, 23);
		contentPanel.add(lblId);

		txtIdPlan = new JTextField();
		txtIdPlan.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtIdPlan.setText("PLN-" + AlticeSistema.numPlan);
		txtIdPlan.setEditable(false);
		txtIdPlan.setBounds(120, 63, 120, 20);
		contentPanel.add(txtIdPlan);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(30, 100, 80, 23);
		contentPanel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(120, 102, 310, 23);
		contentPanel.add(txtNombre);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipo.setBounds(30, 140, 80, 23);
		contentPanel.add(lblTipo);

		cmbTipo = new JComboBox<String>();
		cmbTipo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "<Seleccione>", "Prepago", "Pospago", "Empresarial", "Hogar" }));
		cmbTipo.setBounds(120, 142, 150, 23);
		contentPanel.add(cmbTipo);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecio.setBounds(30, 180, 80, 23);
		contentPanel.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(120, 182, 120, 23);
		contentPanel.add(txtPrecio);

		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescripcion.setBounds(30, 220, 80, 23);
		contentPanel.add(lblDescripcion);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 220, 310, 100);
		contentPanel.add(scrollPane);

		txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		scrollPane.setViewportView(txtDescripcion);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Registrar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
					String id = txtIdPlan.getText();
					String nombre = txtNombre.getText();
					String tipo = cmbTipo.getSelectedItem().toString();
					double precio = Double.parseDouble(txtPrecio.getText());
					String descripcion = txtDescripcion.getText();

					Plan nuevoPlan = new Plan(id, nombre, tipo, precio, descripcion);
					AlticeSistema.getInstance().registrarPlan(nuevoPlan);

					JOptionPane.showMessageDialog(null, "Plan registrado correctamente", "Éxito",
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
		if (txtNombre.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty()
				|| txtDescripcion.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (cmbTipo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de plan.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Double.parseDouble(txtPrecio.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El precio debe ser un valor numérico.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void clean() {
		txtNombre.setText("");
		txtPrecio.setText("");
		txtDescripcion.setText("");
		cmbTipo.setSelectedIndex(0);
		txtIdPlan.setText("PLN-" + AlticeSistema.numPlan);
	}
}