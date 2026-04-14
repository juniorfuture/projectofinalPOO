package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import logica.AlticeSistema;
import logica.Cliente;
import logica.Persona;
import logica.Factura;
import logica.Pago;

public class RegPagos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JTextField txtNombreCliente;
	private JTextField txtCedulaCliente;
	private JTextField txtIdClienteBusqueda;
	private JTextField txtPlanRelacionado;
	private JTextField txtFechaEmision;
	private JTextField txtEstadoCliente;
	private JTextField txtMontoDeuda;

	private JComboBox<String> cmbFacturasPendientes;
	private JComboBox<String> cmbMetodoPago;
	private JLabel lblMontoTotal;
	private JButton btnBuscarCliente;

	private Cliente clienteSeleccionado = null;
	private Factura facturaSeleccionada = null;
	private List<Factura> listaFacturasPendientes = new ArrayList<>();
	private boolean modoCliente = false;

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_PRIMARIO = new Color(31, 111, 235);
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(220, 225, 230);

	public RegPagos() {
		this(null);
	}

	public RegPagos(Cliente clienteFijo) {
		setTitle("Procesamiento de Pagos");
		setBounds(100, 100, 900, 820);
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

		if (clienteFijo != null) {
			modoCliente = true;
			cargarCliente(clienteFijo);
			btnBuscarCliente.setEnabled(false);
		}
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
		icono.setIcon(cargarIcono("/imagenes/pagos.png", 46, 46));
		header.add(icono, BorderLayout.WEST);

		JPanel textos = new JPanel(new BorderLayout(0, 4));
		textos.setOpaque(false);

		JLabel titulo = new JLabel("Procesamiento de Pago");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setForeground(COLOR_TEXTO);

		JLabel subtitulo = new JLabel("Seleccione el cliente y la factura pendiente para registrar el pago.");
		subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		subtitulo.setForeground(COLOR_SECUNDARIO);

		textos.add(titulo, BorderLayout.NORTH);
		textos.add(subtitulo, BorderLayout.CENTER);

		header.add(textos, BorderLayout.CENTER);

		return header;
	}

	private JPanel crearContenido() {
		JPanel contenedor = new JPanel(new GridLayout(1, 2, 18, 0));
		contenedor.setOpaque(false);

		contenedor.add(crearPanelCliente());
		contenedor.add(crearPanelFactura());

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

		JLabel lblBuscar = new JLabel("Buscar cliente");
		lblBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblBuscar.setBounds(25, 65, 120, 20);
		panelCliente.add(lblBuscar);

		txtIdClienteBusqueda = crearTextField();
		txtIdClienteBusqueda.setEditable(false);
		txtIdClienteBusqueda.setBounds(25, 88, 160, 38);
		panelCliente.add(txtIdClienteBusqueda);

		btnBuscarCliente = new JButton("Seleccionar");
		btnBuscarCliente.setFocusPainted(false);
		btnBuscarCliente.setBorderPainted(false);
		btnBuscarCliente.setBackground(COLOR_PRIMARIO);
		btnBuscarCliente.setForeground(Color.WHITE);
		btnBuscarCliente.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnBuscarCliente.setBounds(200, 88, 120, 38);
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarCliente();
			}
		});
		panelCliente.add(btnBuscarCliente);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNombre.setBounds(25, 145, 120, 20);
		panelCliente.add(lblNombre);

		txtNombreCliente = crearTextField();
		txtNombreCliente.setEditable(false);
		txtNombreCliente.setBounds(25, 168, 295, 38);
		panelCliente.add(txtNombreCliente);

		JLabel lblCedula = new JLabel("Cédula");
		lblCedula.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblCedula.setBounds(25, 225, 120, 20);
		panelCliente.add(lblCedula);

		txtCedulaCliente = crearTextField();
		txtCedulaCliente.setEditable(false);
		txtCedulaCliente.setBounds(25, 248, 295, 38);
		panelCliente.add(txtCedulaCliente);

		JLabel lblEstado = new JLabel("Estado del cliente");
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEstado.setBounds(25, 305, 120, 20);
		panelCliente.add(lblEstado);

		txtEstadoCliente = crearTextField();
		txtEstadoCliente.setEditable(false);
		txtEstadoCliente.setBounds(25, 328, 140, 38);
		panelCliente.add(txtEstadoCliente);

		JLabel lblDeuda = new JLabel("Monto deuda");
		lblDeuda.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDeuda.setBounds(180, 305, 120, 20);
		panelCliente.add(lblDeuda);

		txtMontoDeuda = crearTextField();
		txtMontoDeuda.setEditable(false);
		txtMontoDeuda.setBounds(180, 328, 140, 38);
		panelCliente.add(txtMontoDeuda);

		JPanel panelInfo = new JPanel(new BorderLayout());
		panelInfo.setBackground(new Color(245, 248, 255));
		panelInfo.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(210, 220, 235), 1, true),
				new EmptyBorder(14, 14, 14, 14)));
		panelInfo.setBounds(25, 405, 295, 95);

		JLabel lblInfo = new JLabel(
				"<html>El sistema solo procesa pagos completos de facturas pendientes o vencidas.</html>");
		lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblInfo.setForeground(COLOR_SECUNDARIO);

		panelInfo.add(lblInfo, BorderLayout.CENTER);
		panelCliente.add(panelInfo);

		return panelCliente;
	}

	private JPanel crearPanelFactura() {
		JPanel panelFactura = new JPanel(null);
		panelFactura.setBackground(COLOR_PANEL);
		panelFactura.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(10, 10, 10, 10)));

		JLabel titulo = new JLabel("Factura");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		titulo.setForeground(COLOR_TEXTO);
		titulo.setBounds(25, 20, 180, 25);
		panelFactura.add(titulo);

		JLabel lblFacturas = new JLabel("Facturas pendientes");
		lblFacturas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblFacturas.setBounds(25, 65, 140, 20);
		panelFactura.add(lblFacturas);

		cmbFacturasPendientes = new JComboBox<String>();
		cmbFacturasPendientes.setEnabled(false);
		cmbFacturasPendientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbFacturasPendientes.setBounds(25, 88, 295, 38);
		cmbFacturasPendientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDetalleFactura();
			}
		});
		panelFactura.add(cmbFacturasPendientes);

		JLabel lblFecha = new JLabel("Fecha de emisión");
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblFecha.setBounds(25, 145, 140, 20);
		panelFactura.add(lblFecha);

		txtFechaEmision = crearTextField();
		txtFechaEmision.setEditable(false);
		txtFechaEmision.setBounds(25, 168, 295, 38);
		panelFactura.add(txtFechaEmision);

		JLabel lblPlan = new JLabel("Plan relacionado");
		lblPlan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPlan.setBounds(25, 225, 140, 20);
		panelFactura.add(lblPlan);

		txtPlanRelacionado = crearTextField();
		txtPlanRelacionado.setEditable(false);
		txtPlanRelacionado.setBounds(25, 248, 295, 38);
		panelFactura.add(txtPlanRelacionado);

		JLabel lblMetodo = new JLabel("Método de pago");
		lblMetodo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblMetodo.setBounds(25, 305, 140, 20);
		panelFactura.add(lblMetodo);

		cmbMetodoPago = new JComboBox<String>();
		cmbMetodoPago.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbMetodoPago.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Efectivo", "Tarjeta de Crédito", "Transferencia" }));
		cmbMetodoPago.setBounds(25, 328, 295, 38);
		panelFactura.add(cmbMetodoPago);

		JPanel panelMonto = new JPanel(new BorderLayout());
		panelMonto.setBackground(new Color(245, 248, 255));
		panelMonto.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(210, 220, 235), 1, true),
				new EmptyBorder(16, 16, 16, 16)));
		panelMonto.setBounds(25, 405, 295, 95);

		JLabel lblTextoTotal = new JLabel("Total a pagar");
		lblTextoTotal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTextoTotal.setForeground(COLOR_SECUNDARIO);

		lblMontoTotal = new JLabel("RD$ 0.00");
		lblMontoTotal.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblMontoTotal.setForeground(COLOR_PRIMARIO);

		panelMonto.add(lblTextoTotal, BorderLayout.NORTH);
		panelMonto.add(lblMontoTotal, BorderLayout.CENTER);

		panelFactura.add(panelMonto);

		return panelFactura;
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

		JButton btnProcesar = new JButton("Procesar Pago");
		btnProcesar.setFocusPainted(false);
		btnProcesar.setBorderPainted(false);
		btnProcesar.setBackground(COLOR_PRIMARIO);
		btnProcesar.setForeground(Color.WHITE);
		btnProcesar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnProcesar.setPreferredSize(new java.awt.Dimension(150, 40));
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				procesarPago();
			}
		});
		buttonPane.add(btnProcesar);
		getRootPane().setDefaultButton(btnProcesar);

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
		if (modoCliente) {
			return;
		}
		ListaCliente lista = new ListaCliente();
		lista.setModal(true);
		lista.setVisible(true);
		String nombreSeleccionado = lista.getNombreSeleccionado();

		if (nombreSeleccionado != null && !nombreSeleccionado.isEmpty()) {
			Persona aux = AlticeSistema.getInstance().buscarClientePorNombre(nombreSeleccionado);
			if (aux != null && aux instanceof Cliente) {
				cargarCliente((Cliente) aux);
			} else {
				JOptionPane.showMessageDialog(null, "La persona seleccionada no es un cliente registrado.");
			}
		}
	}

	private void cargarCliente(Cliente cliente) {
		clienteSeleccionado = cliente;
		AlticeSistema.getInstance().recalcularDeudaCliente(clienteSeleccionado);

		txtIdClienteBusqueda.setText(clienteSeleccionado.getId());
		txtNombreCliente.setText(clienteSeleccionado.getNombre());
		txtCedulaCliente.setText(clienteSeleccionado.getCedula());
		txtEstadoCliente.setText(clienteSeleccionado.getEstado());
		txtMontoDeuda.setText(String.format("RD$ %.2f", clienteSeleccionado.getMontoDeuda()));

		cargarFacturasPendientes();
	}

	private void cargarFacturasPendientes() {
		cmbFacturasPendientes.removeAllItems();
		listaFacturasPendientes.clear();
		cmbFacturasPendientes.addItem("<Seleccione una factura>");

		List<Factura> todasLasFacturas = AlticeSistema.getInstance().getFacturasPendientesDeCliente(clienteSeleccionado);

		for (Factura f : todasLasFacturas) {
			listaFacturasPendientes.add(f);
			cmbFacturasPendientes.addItem(f.getIdFactura() + " - " + f.getFecha());
		}

		if (listaFacturasPendientes.isEmpty()) {
			cmbFacturasPendientes.setEnabled(false);
			limpiarDetalles();
			JOptionPane.showMessageDialog(this, "Este cliente no tiene facturas pendientes.", "Al día",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			cmbFacturasPendientes.setEnabled(true);
		}
	}

	private void actualizarDetalleFactura() {
		int index = cmbFacturasPendientes.getSelectedIndex() - 1;

		if (index >= 0 && index < listaFacturasPendientes.size()) {
			facturaSeleccionada = listaFacturasPendientes.get(index);

			txtFechaEmision.setText(facturaSeleccionada.getFecha());

			if (facturaSeleccionada.getContrato() != null && facturaSeleccionada.getContrato().getPlan() != null) {
				txtPlanRelacionado.setText(facturaSeleccionada.getContrato().getPlan().getNombre());
			} else {
				txtPlanRelacionado.setText("N/A");
			}

			lblMontoTotal.setText(String.format("RD$ %.2f", facturaSeleccionada.getMontoTotal()));
		} else {
			facturaSeleccionada = null;
			limpiarDetalles();
		}
	}

	private void limpiarDetalles() {
		txtFechaEmision.setText("");
		txtPlanRelacionado.setText("");
		lblMontoTotal.setText("RD$ 0.00");
	}

	private void procesarPago() {
		if (facturaSeleccionada != null) {
			int confirm = JOptionPane.showConfirmDialog(null,
					"¿Confirma el pago completo por " + lblMontoTotal.getText() + "?",
					"Confirmar Pago", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				String fechaPago = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
				Pago pago = AlticeSistema.getInstance().procesarPagoFactura(facturaSeleccionada,
						cmbMetodoPago.getSelectedItem().toString(), fechaPago);

				if (pago != null) {
					JOptionPane.showMessageDialog(null,
							"Pago registrado exitosamente.\nMétodo: "
									+ cmbMetodoPago.getSelectedItem().toString(),
							"Pago Exitoso", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"No se pudo procesar el pago.\nRecuerda que el sistema solo acepta pago completo.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Por favor, seleccione una factura pendiente para pagar.",
					"Atención", JOptionPane.WARNING_MESSAGE);
		}
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