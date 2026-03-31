package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import logica.AlticeSistema;
import logica.Plan;
import logica.Servicio;

public class RegPlanes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdPlan;
	private JTextField txtNombre;
	private JComboBox<String> cmbTipo;
	private JTextArea txtDescripcion;
	private JPanel panelContenedorServicios;
	private JLabel lblPrecioAcumulado;

	private ArrayList<JCheckBox> listaChecks = new ArrayList<>();
	private ArrayList<JTextField> listaPrecios = new ArrayList<>();
	private ArrayList<Servicio> serviciosDisponibles;

	public RegPlanes() {
		setTitle("Registro de Plan con Precios Personalizados");
		setBounds(100, 100, 550, 680);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblTitulo = new JLabel("CONFIGURAR PLAN Y PRECIOS");
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(45, 45, 45));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(0, 0, 534, 40);
		contentPanel.add(lblTitulo);
		JLabel lblId = new JLabel("ID Plan:");
		lblId.setBounds(25, 60, 80, 14);
		contentPanel.add(lblId);

		txtIdPlan = new JTextField("PLN-" + AlticeSistema.numPlan);
		txtIdPlan.setEditable(false);
		txtIdPlan.setBounds(110, 57, 120, 20);
		contentPanel.add(txtIdPlan);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(25, 95, 80, 14);
		contentPanel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(110, 92, 390, 23);
		contentPanel.add(txtNombre);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(25, 130, 80, 14);
		contentPanel.add(lblTipo);

		cmbTipo = new JComboBox<String>();
		cmbTipo.setModel(
				new DefaultComboBoxModel<String>(new String[] { "<Seleccione>", "Hogar", "Móvil", "Empresarial" }));
		cmbTipo.setBounds(110, 126, 150, 23);
		contentPanel.add(cmbTipo);

		JLabel lblDesc = new JLabel("Descripción:");
		lblDesc.setBounds(25, 165, 80, 14);
		contentPanel.add(lblDesc);

		JScrollPane scrollDesc = new JScrollPane();
		scrollDesc.setBounds(110, 165, 390, 50);
		contentPanel.add(scrollDesc);

		txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		scrollDesc.setViewportView(txtDescripcion);

		JLabel lblServHeader = new JLabel("Servicios Disponibles (Marque y asigne precio):");
		lblServHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblServHeader.setBounds(25, 230, 350, 14);
		contentPanel.add(lblServHeader);

		panelContenedorServicios = new JPanel();
		panelContenedorServicios.setLayout(new BoxLayout(panelContenedorServicios, BoxLayout.Y_AXIS));

		JScrollPane scrollServicios = new JScrollPane(panelContenedorServicios);
		scrollServicios.setBounds(25, 250, 475, 250);
		contentPanel.add(scrollServicios);
		JPanel panelResumen = new JPanel();
		panelResumen.setBackground(new Color(245, 245, 245));
		panelResumen.setBorder(new MatteBorder(1, 0, 0, 0, Color.GRAY));
		panelResumen.setBounds(25, 510, 475, 50);
		contentPanel.add(panelResumen);
		panelResumen.setLayout(null);

		JLabel lblTextoTotal = new JLabel("PRECIO TOTAL DEL PLAN:");
		lblTextoTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTextoTotal.setBounds(10, 15, 180, 20);
		panelResumen.add(lblTextoTotal);

		lblPrecioAcumulado = new JLabel("RD$ 0.00");
		lblPrecioAcumulado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioAcumulado.setForeground(new Color(204, 0, 0));
		lblPrecioAcumulado.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrecioAcumulado.setBounds(265, 15, 200, 20);
		panelResumen.add(lblPrecioAcumulado);

		cargarListaServiciosDinamica();

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnGuardar = new JButton("Registrar Plan");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarPlan();
			}
		});
		buttonPane.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(btnCancelar);
	}

	private void cargarListaServiciosDinamica() {
		serviciosDisponibles = AlticeSistema.getInstance().getServiciosDisponibles();

		for (final Servicio s : serviciosDisponibles) {
			JPanel filaServicio = new JPanel();
			filaServicio.setLayout(new FlowLayout(FlowLayout.LEFT));
			filaServicio.setMaximumSize(new Dimension(450, 35));

			JCheckBox chk = new JCheckBox(s.getNombre());
			chk.setPreferredSize(new Dimension(220, 20));

			JTextField txtPrecioIndividual = new JTextField();
			txtPrecioIndividual.setPreferredSize(new Dimension(100, 20));
			txtPrecioIndividual.setEnabled(false);
			chk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtPrecioIndividual.setEnabled(chk.isSelected());
					if (!chk.isSelected()) {
						txtPrecioIndividual.setText("");
					}
					actualizarPrecioTotalVisual();
				}
			});
			txtPrecioIndividual.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					actualizarPrecioTotalVisual();
				}
			});

			filaServicio.add(chk);
			filaServicio.add(new JLabel("RD$:"));
			filaServicio.add(txtPrecioIndividual);

			listaChecks.add(chk);
			listaPrecios.add(txtPrecioIndividual);
			panelContenedorServicios.add(filaServicio);
		}
	}

	private void actualizarPrecioTotalVisual() {
		double total = 0;
		for (int i = 0; i < listaChecks.size(); i++) {
			if (listaChecks.get(i).isSelected()) {
				try {
					String valor = listaPrecios.get(i).getText();
					if (!valor.isEmpty()) {
						total += Double.parseDouble(valor);
					}
				} catch (NumberFormatException e) {
				}
			}
		}
		lblPrecioAcumulado.setText("RD$ " + String.format("%.2f", total));
	}

	private void guardarPlan() {
		if (txtNombre.getText().isEmpty() || cmbTipo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Complete el nombre y el tipo del plan.");
			return;
		}

		double precioAcumulado = 0;
		int seleccionados = 0;
		for (int i = 0; i < listaChecks.size(); i++) {
			if (listaChecks.get(i).isSelected()) {
				String strPrecio = listaPrecios.get(i).getText();
				if (strPrecio.isEmpty()) {
					JOptionPane.showMessageDialog(this,
							"El servicio " + serviciosDisponibles.get(i).getNombre() + " no tiene precio.");
					return;
				}
				try {
					precioAcumulado += Double.parseDouble(strPrecio);
					seleccionados++;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this,
							"Precio inválido en: " + serviciosDisponibles.get(i).getNombre());
					return;
				}
			}
		}

		if (seleccionados == 0) {
			JOptionPane.showMessageDialog(this, "Seleccione al menos un servicio.");
			return;
		}

		Plan nuevoPlan = new Plan(txtIdPlan.getText(), txtNombre.getText(), cmbTipo.getSelectedItem().toString(),
				txtDescripcion.getText(), precioAcumulado);

		for (int i = 0; i < listaChecks.size(); i++) {
			if (listaChecks.get(i).isSelected()) {
				nuevoPlan.agregarServicio(serviciosDisponibles.get(i));
			}
		}
		AlticeSistema.getInstance().registrarPlan(nuevoPlan);
		JOptionPane.showMessageDialog(this, "Plan registrado con éxito.");
		dispose();
	}
}