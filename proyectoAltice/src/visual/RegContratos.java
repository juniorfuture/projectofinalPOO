package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logica.AlticeSistema;
import logica.Cliente;
import logica.Comercial;
import logica.Contrato;
import logica.Factura;
import logica.Persona;
import logica.Plan;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegContratos extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JTextField txtIdContrato;
	private JTextField txtIdClienteBusqueda;
	private JTextField txtNombreCliente;
	private JTextField txtCedulaCliente;
	private JTextField txtTelefonoCliente;
	private JTextField txtEstadoCliente;
	private JTextField txtMontoDeuda;

	private JComboBox<String> cmbPlanes;
	private JComboBox<String> cmbComercial;
	private JTextField txtFechaContrato;
	private JLabel lblMontoTotal;

	private Cliente clienteSeleccionado = null;

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_PRIMARIO = new Color(31, 111, 235);
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(220, 225, 230);

	public static void main(String[] args) {
		try {
			RegContratos dialog = new RegContratos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegContratos() {
		setTitle("Registro de Contratos");
		setBounds(100, 100, 900, 840);
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

		JLabel icono = new JLabel("CON");
		icono.setHorizontalAlignment(SwingConstants.CENTER);
		icono.setOpaque(true);
		icono.setBackground(new Color(235, 242, 255));
		icono.setForeground(COLOR_PRIMARIO);
		icono.setFont(new Font("Segoe UI", Font.BOLD, 22));
		icono.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
		header.add(icono, BorderLayout.WEST);

		JPanel textos = new JPanel(new BorderLayout(0, 4));
		textos.setOpaque(false);

		JLabel titulo = new JLabel("Registrar Contrato");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setForeground(COLOR_TEXTO);

		JLabel subtitulo = new JLabel("Seleccione el cliente, el plan y el comercial para generar el contrato.");
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

		JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 18, 0));
		panelSuperior.setOpaque(false);

		panelSuperior.add(crearPanelCliente());
		panelSuperior.add(crearPanelVenta());

		contenedor.add(panelSuperior, BorderLayout.CENTER);

		return contenedor;
	}

	private JPanel crearPanelCliente() {
		JPanel panelCliente = new JPanel(null);
		panelCliente.setBackground(COLOR_PANEL);
		panelCliente.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(10, 10, 10, 10)));

		JLabel titulo = new JLabel("Cliente");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		titulo.setForeground(COLOR_TEXTO);
		titulo.setBounds(25, 20, 180, 25);
		panelCliente.add(titulo);

		JLabel lblIdContrato = new JLabel("ID Contrato");
		lblIdContrato.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblIdContrato.setBounds(25, 60, 120, 20);
		panelCliente.add(lblIdContrato);

		txtIdContrato = crearTextField();
		txtIdContrato.setEditable(false);
		txtIdContrato.setText("CON-" + AlticeSistema.numContrato);
		txtIdContrato.setBounds(25, 83, 160, 38);
		panelCliente.add(txtIdContrato);

		JLabel lblBuscar = new JLabel("Buscar cliente");
		lblBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblBuscar.setBounds(25, 140, 120, 20);
		panelCliente.add(lblBuscar);

		txtIdClienteBusqueda = crearTextField();
		txtIdClienteBusqueda.setEditable(false);
		txtIdClienteBusqueda.setBounds(25, 163, 160, 38);
		panelCliente.add(txtIdClienteBusqueda);

		JButton btnBuscar = new JButton("Seleccionar");
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBackground(COLOR_PRIMARIO);
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnBuscar.setBounds(200, 163, 120, 38);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarCliente();
			}
		});
		panelCliente.add(btnBuscar);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNombre.setBounds(25, 220, 120, 20);
		panelCliente.add(lblNombre);

		txtNombreCliente = crearTextField();
		txtNombreCliente.setEditable(false);
		txtNombreCliente.setBounds(25, 243, 295, 38);
		panelCliente.add(txtNombreCliente);

		JLabel lblCedula = new JLabel("Cédula");
		lblCedula.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCedula.setBounds(25, 300, 120, 20);
		panelCliente.add(lblCedula);

		txtCedulaCliente = crearTextField();
		txtCedulaCliente.setEditable(false);
		txtCedulaCliente.setBounds(25, 323, 295, 38);
		panelCliente.add(txtCedulaCliente);

		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTelefono.setBounds(25, 380, 120, 20);
		panelCliente.add(lblTelefono);

		txtTelefonoCliente = crearTextField();
		txtTelefonoCliente.setEditable(false);
		txtTelefonoCliente.setBounds(25, 403, 295, 38);
		panelCliente.add(txtTelefonoCliente);

		JLabel lblEstado = new JLabel("Estado del cliente");
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEstado.setBounds(25, 460, 120, 20);
		panelCliente.add(lblEstado);

		txtEstadoCliente = crearTextField();
		txtEstadoCliente.setEditable(false);
		txtEstadoCliente.setBounds(25, 483, 140, 38);
		panelCliente.add(txtEstadoCliente);

		JLabel lblDeuda = new JLabel("Monto deuda");
		lblDeuda.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDeuda.setBounds(180, 460, 120, 20);
		panelCliente.add(lblDeuda);

		txtMontoDeuda = crearTextField();
		txtMontoDeuda.setEditable(false);
		txtMontoDeuda.setBounds(180, 483, 140, 38);
		panelCliente.add(txtMontoDeuda);

		return panelCliente;
	}

	private JPanel crearPanelVenta() {
		JPanel panelVenta = new JPanel(null);
		panelVenta.setBackground(COLOR_PANEL);
		panelVenta.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(10, 10, 10, 10)));

		JLabel titulo = new JLabel("Detalles de venta");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		titulo.setForeground(COLOR_TEXTO);
		titulo.setBounds(25, 20, 180, 25);
		panelVenta.add(titulo);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblFecha.setBounds(25, 60, 120, 20);
		panelVenta.add(lblFecha);

		txtFechaContrato = crearTextField();
		txtFechaContrato.setEditable(false);
		txtFechaContrato.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		txtFechaContrato.setBounds(25, 83, 295, 38);
		panelVenta.add(txtFechaContrato);

		JLabel lblPlan = new JLabel("Plan");
		lblPlan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPlan.setBounds(25, 140, 120, 20);
		panelVenta.add(lblPlan);

		cmbPlanes = new JComboBox<String>();
		cmbPlanes.setEnabled(false);
		cmbPlanes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbPlanes.setModel(new DefaultComboBoxModel<String>(AlticeSistema.getInstance().getNombresPlanesDisponibles()));
		cmbPlanes.setBounds(25, 163, 295, 38);
		cmbPlanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarMontoPlan();
			}
		});
		panelVenta.add(cmbPlanes);

		JLabel lblComercial = new JLabel("Comercial");
		lblComercial.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblComercial.setBounds(25, 220, 120, 20);
		panelVenta.add(lblComercial);

		cmbComercial = new JComboBox<String>();
		cmbComercial.setEnabled(false);
		cmbComercial.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbComercial.setModel(new DefaultComboBoxModel<String>(
				AlticeSistema.getInstance().getNombresComercialesDisponibles()));
		cmbComercial.setBounds(25, 243, 295, 38);
		panelVenta.add(cmbComercial);

		JLabel lblInfo = new JLabel(
				"<html>Seleccione un plan y el comercial responsable para generar el contrato y la factura inicial.</html>");
		lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblInfo.setForeground(COLOR_SECUNDARIO);
		lblInfo.setBounds(25, 310, 310, 55);
		panelVenta.add(lblInfo);

		JPanel panelMonto = new JPanel(new BorderLayout());
		panelMonto.setBackground(new Color(245, 248, 255));
		panelMonto.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(210, 220, 235), 1, true),
				new EmptyBorder(16, 16, 16, 16)));
		panelMonto.setBounds(25, 390, 295, 100);

		JLabel lblTextoTotal = new JLabel("Monto del plan");
		lblTextoTotal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTextoTotal.setForeground(COLOR_SECUNDARIO);

		lblMontoTotal = new JLabel("RD$ 0.00");
		lblMontoTotal.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblMontoTotal.setForeground(COLOR_PRIMARIO);

		panelMonto.add(lblTextoTotal, BorderLayout.NORTH);
		panelMonto.add(lblMontoTotal, BorderLayout.CENTER);

		panelVenta.add(panelMonto);

		return panelVenta;
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

		JButton btnRegistrar = new JButton("Registrar Contrato");
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setBorderPainted(false);
		btnRegistrar.setBackground(COLOR_PRIMARIO);
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRegistrar.setPreferredSize(new java.awt.Dimension(160, 40));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarContrato();
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

	private void seleccionarCliente() {
		ListaCliente lista = new ListaCliente();
		lista.setVisible(true);
		String nombreSeleccionado = lista.getNombreSeleccionado();

		if (nombreSeleccionado != null && !nombreSeleccionado.isEmpty()) {
			Persona aux = AlticeSistema.getInstance().buscarClientePorNombre(nombreSeleccionado);

			if (aux != null && aux instanceof Cliente) {
				clienteSeleccionado = (Cliente) aux;

				AlticeSistema.getInstance().recalcularDeudaCliente(clienteSeleccionado);

				txtIdClienteBusqueda.setText(clienteSeleccionado.getId());
				txtNombreCliente.setText(clienteSeleccionado.getNombre());
				txtCedulaCliente.setText(clienteSeleccionado.getCedula());
				txtTelefonoCliente.setText(clienteSeleccionado.getTelefono());
				txtEstadoCliente.setText(clienteSeleccionado.getEstado());
				txtMontoDeuda.setText(String.format("RD$ %.2f", clienteSeleccionado.getMontoDeuda()));

				cmbPlanes.setEnabled(true);
				cmbComercial.setEnabled(true);

				if (clienteSeleccionado.isDeuda()) {
					JOptionPane.showMessageDialog(null,
							"Este cliente tiene deuda pendiente por RD$ "
									+ String.format("%.2f", clienteSeleccionado.getMontoDeuda())
									+ ".\nNo puede generar un contrato nuevo hasta pagar.",
							"Cliente con deuda", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "La persona seleccionada no es un cliente registrado.");
			}
		}
	}

	private void actualizarMontoPlan() {
		if (cmbPlanes.getSelectedIndex() > 0) {
			Plan p = AlticeSistema.getInstance().buscarPlanPorNombre(cmbPlanes.getSelectedItem().toString());
			if (p != null) {
				lblMontoTotal.setText("RD$ " + String.format("%.2f", p.getPrecio()));
			}
		} else {
			lblMontoTotal.setText("RD$ 0.00");
		}
	}

	private void registrarContrato() {
		if (clienteSeleccionado == null || cmbPlanes.getSelectedIndex() <= 0 || cmbComercial.getSelectedIndex() <= 0) {
			JOptionPane.showMessageDialog(null,
					"Por favor, identifique al cliente, seleccione un plan y asigne un comercial.",
					"Campos incompletos", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (!AlticeSistema.getInstance().hayTecnicosDisponibles()) {
			JOptionPane.showMessageDialog(null,
					"No hay técnicos registrados.\nNo se puede generar el contrato.",
					"Contrato denegado", JOptionPane.WARNING_MESSAGE);
			return;
		}

		AlticeSistema.getInstance().recalcularDeudaCliente(clienteSeleccionado);

		if (!clienteSeleccionado.puedeContratar()) {
			JOptionPane.showMessageDialog(null,
					"El cliente tiene deuda pendiente de RD$ "
							+ String.format("%.2f", clienteSeleccionado.getMontoDeuda())
							+ ".\nDebe pagar primero para crear un contrato nuevo.",
					"Contrato denegado", JOptionPane.WARNING_MESSAGE);
			return;
		}

		Plan plan = AlticeSistema.getInstance().buscarPlanPorNombre(cmbPlanes.getSelectedItem().toString());
		Comercial vendedor = AlticeSistema.getInstance()
				.buscarComercialPorNombre(cmbComercial.getSelectedItem().toString());

		String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

		Contrato c = new Contrato(txtIdContrato.getText(), fecha, "Activo", clienteSeleccionado, plan, vendedor);
		AlticeSistema.getInstance().registrarContrato(c);

		String idFactura = "FAC-" + AlticeSistema.numFactura;
		Factura f = new Factura(idFactura, fecha, "Pendiente", c);
		AlticeSistema.getInstance().registrarFactura(f);

		JOptionPane.showMessageDialog(null,
				"Contrato y factura (" + idFactura + ") generados con éxito.",
				"Registro completado", JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
}