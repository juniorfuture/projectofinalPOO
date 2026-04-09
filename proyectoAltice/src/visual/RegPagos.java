package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import logica.AlticeSistema;
import logica.Cliente;
import logica.Persona;
import logica.Factura;

public class RegPagos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreCliente;
	private JTextField txtCedulaCliente;
	private JTextField txtIdClienteBusqueda;
	private JTextField txtPlanRelacionado;
	private JTextField txtFechaEmision;
	private JComboBox<String> cmbFacturasPendientes;
	private JComboBox<String> cmbMetodoPago;
	private JLabel lblMontoTotal;
	
	private Cliente clienteSeleccionado = null;
	private Factura facturaSeleccionada = null;
	private List<Factura> listaFacturasPendientes = new ArrayList<>();

	public RegPagos() {
		setTitle("Módulo de Caja y Pagos");
		setBounds(100, 100, 700, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(192, 192, 192));
		panelHeader.setBounds(0, 0, 684, 45);
		contentPanel.add(panelHeader);
		panelHeader.setLayout(null);
		
		JLabel lblTitulo = new JLabel("PROCESAMIENTO DE PAGO");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(10, 11, 280, 25);
		panelHeader.add(lblTitulo);
		
		JPanel panelCliente = new JPanel();
		panelCliente.setBorder(new TitledBorder(new EtchedBorder(), "1. Identificar Cliente"));
		panelCliente.setBounds(15, 60, 320, 350);
		contentPanel.add(panelCliente);
		panelCliente.setLayout(null);
		
		txtIdClienteBusqueda = new JTextField();
		txtIdClienteBusqueda.setEditable(false);
		txtIdClienteBusqueda.setBounds(15, 45, 130, 25);
		panelCliente.add(txtIdClienteBusqueda);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCliente lista = new ListaCliente();
				lista.setModal(true);
				lista.setVisible(true);
				String nombreSeleccionado = lista.getNombreSeleccionado();

				if (nombreSeleccionado != null && !nombreSeleccionado.isEmpty()) {
					Persona aux = AlticeSistema.getInstance().buscarClientePorNombre(nombreSeleccionado);

					if (aux != null && aux instanceof Cliente) {
						clienteSeleccionado = (Cliente) aux; 
						
						txtIdClienteBusqueda.setText(clienteSeleccionado.getId());
						txtNombreCliente.setText(clienteSeleccionado.getNombre());
						txtCedulaCliente.setText(clienteSeleccionado.getCedula());
						
						cargarFacturasPendientes();
					} else {
						JOptionPane.showMessageDialog(null, "La persona seleccionada no es un Cliente registrado.");
					}
				}
			}
		});
		btnBuscar.setBounds(155, 44, 90, 26);
		panelCliente.add(btnBuscar);

		JLabel lblNom = new JLabel("Nombre:");
		lblNom.setBounds(15, 90, 80, 14);
		panelCliente.add(lblNom);

		txtNombreCliente = new JTextField();
		txtNombreCliente.setEditable(false);
		txtNombreCliente.setBounds(15, 110, 280, 25);
		panelCliente.add(txtNombreCliente);
		
		txtCedulaCliente = new JTextField();
		txtCedulaCliente.setEditable(false);
		txtCedulaCliente.setBounds(15, 170, 280, 25);
		panelCliente.add(txtCedulaCliente);
		panelCliente.add(new JLabel("Cédula:")).setBounds(15, 150, 80, 14);
		
		JPanel panelFactura = new JPanel();
		panelFactura.setBorder(new TitledBorder(new EtchedBorder(), "2. Detalle de Facturación"));
		panelFactura.setBounds(350, 60, 320, 350);
		contentPanel.add(panelFactura);
		panelFactura.setLayout(null);

		panelFactura.add(new JLabel("Facturas Pendientes:")).setBounds(15, 25, 150, 14);
		
		cmbFacturasPendientes = new JComboBox<String>();
		cmbFacturasPendientes.setEnabled(false);
		cmbFacturasPendientes.setBounds(15, 45, 280, 25);
		cmbFacturasPendientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDetalleFactura();
			}
		});
		panelFactura.add(cmbFacturasPendientes);

		panelFactura.add(new JLabel("Fecha de Emisión:")).setBounds(15, 90, 150, 14);
		txtFechaEmision = new JTextField();
		txtFechaEmision.setEditable(false);
		txtFechaEmision.setBounds(15, 110, 280, 25);
		panelFactura.add(txtFechaEmision);

		panelFactura.add(new JLabel("Plan Relacionado:")).setBounds(15, 150, 150, 14);
		txtPlanRelacionado = new JTextField();
		txtPlanRelacionado.setEditable(false);
		txtPlanRelacionado.setBounds(15, 170, 280, 25);
		panelFactura.add(txtPlanRelacionado);
		
		panelFactura.add(new JLabel("Método de Pago:")).setBounds(15, 210, 150, 14);
		cmbMetodoPago = new JComboBox<String>();
		cmbMetodoPago.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Tarjeta de Crédito", "Transferencia"}));
		cmbMetodoPago.setBounds(15, 230, 280, 25);
		panelFactura.add(cmbMetodoPago);

		JPanel panelResumen = new JPanel();
		panelResumen.setBackground(new Color(240, 248, 255));
		panelResumen.setBounds(0, 430, 684, 45);
		contentPanel.add(panelResumen);
		panelResumen.setLayout(null);
		
		JLabel lblTextoTotal = new JLabel("TOTAL A PAGAR:");
		lblTextoTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTextoTotal.setBounds(350, 10, 150, 25);
		panelResumen.add(lblTextoTotal);
		
		lblMontoTotal = new JLabel("RD$ 0.00");
		lblMontoTotal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMontoTotal.setForeground(Color.RED);
		lblMontoTotal.setBounds(490, 10, 180, 25);
		panelResumen.add(lblMontoTotal);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnProcesar = new JButton("Procesar Pago");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (facturaSeleccionada != null) {
					int confirm = JOptionPane.showConfirmDialog(null, "¿Confirma el pago por " + lblMontoTotal.getText() + "?", "Confirmar Pago", JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION) {
						facturaSeleccionada.setEstado("Pagada");
						
						JOptionPane.showMessageDialog(null, "Pago registrado exitosamente.\nMétodo: " + cmbMetodoPago.getSelectedItem().toString(), "Pago Exitoso", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una factura pendiente para pagar.", "Atención", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		buttonPane.add(btnProcesar);
	}

	private void cargarFacturasPendientes() {
		cmbFacturasPendientes.removeAllItems();
		listaFacturasPendientes.clear();
		cmbFacturasPendientes.addItem("<Seleccione una factura>");
		
		List<Factura> todasLasFacturas = AlticeSistema.getInstance().getFacturas();
		
		for (Factura f : todasLasFacturas) {
			if (f.getContrato() != null && f.getContrato().getCliente() != null) {
				if (f.getContrato().getCliente().getId().equals(clienteSeleccionado.getId()) && 
					f.getEstado().equalsIgnoreCase("Pendiente")) {
					
					listaFacturasPendientes.add(f);
					cmbFacturasPendientes.addItem(f.getIdFactura() + " - " + f.getFecha());
				}
			}
		}
		
		if (listaFacturasPendientes.isEmpty()) {
			cmbFacturasPendientes.setEnabled(false);
			limpiarDetalles();
			JOptionPane.showMessageDialog(this, "Este cliente no tiene facturas pendientes.", "Al día", JOptionPane.INFORMATION_MESSAGE);
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
}