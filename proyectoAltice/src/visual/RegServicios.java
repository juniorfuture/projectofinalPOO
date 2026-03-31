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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class RegServicios extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JTextField txtIdServicio;
	private JTextField txtNombre;
	private JComboBox<String> cmbTipo;
	private JTextArea txtDescripcion; 

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
		setTitle("Registro de Servicios Técnicos");
		setBounds(100, 100, 480, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JTextField txtTitulo = new JTextField();
		txtTitulo.setBackground(new Color(192, 192, 192));
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo.setEditable(false);
		txtTitulo.setText("Registro de Servicios");
		txtTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtTitulo.setBounds(0, 0, 464, 35);
		contentPanel.add(txtTitulo);

		JLabel lblId = new JLabel("ID Servicio:");
		lblId.setBounds(30, 60, 80, 14);
		contentPanel.add(lblId);

		txtIdServicio = new JTextField();
		txtIdServicio.setText("SRV-" + AlticeSistema.numServicio);
		txtIdServicio.setEditable(false);
		txtIdServicio.setBounds(120, 57, 120, 20);
		contentPanel.add(txtIdServicio);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 100, 80, 14);
		contentPanel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(120, 97, 300, 23);
		contentPanel.add(txtNombre);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(30, 140, 80, 14);
		contentPanel.add(lblTipo);

		cmbTipo = new JComboBox<String>();
		cmbTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Internet", "Cable", "Telefonía", "Mantenimiento"}));
		cmbTipo.setBounds(120, 136, 150, 23);
		contentPanel.add(cmbTipo);

		JLabel lblDesc = new JLabel("Descripción:");
		lblDesc.setBounds(30, 180, 80, 14);
		contentPanel.add(lblDesc);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 180, 300, 100);
		contentPanel.add(scrollPane);

		txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true); 
		txtDescripcion.setWrapStyleWord(true); 
		scrollPane.setViewportView(txtDescripcion);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validar()) {
					String id = txtIdServicio.getText();
					String nombre = txtNombre.getText();
					String tipo = cmbTipo.getSelectedItem().toString();
					String desc = txtDescripcion.getText();

					Servicio nuevo = new Servicio(id, nombre, tipo, desc);
					AlticeSistema.getInstance().registrarServicio(nuevo);

					JOptionPane.showMessageDialog(null, "Servicio registrado con éxito.");
					clean();
					dispose();
				}
			}
		});
		buttonPane.add(btnRegistrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(btnCancelar);
	}

	private boolean validar() {
		if (txtNombre.getText().trim().isEmpty() || txtDescripcion.getText().trim().isEmpty() || cmbTipo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
			return false;
		}
		return true;
	}

	private void clean() {
		txtNombre.setText("");
		txtDescripcion.setText("");
		cmbTipo.setSelectedIndex(0);
		txtIdServicio.setText("SRV-" + AlticeSistema.numServicio);
	}
}