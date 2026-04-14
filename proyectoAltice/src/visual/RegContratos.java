package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import logica.AlticeSistema;
import logica.Cliente;
import logica.Persona; 
import logica.Plan;
import logica.Contrato;
import logica.Factura;
import logica.Comercial; 

public class RegContratos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdContrato;
	private JTextField txtNombreCliente;
	private JTextField txtCedulaCliente;
	private JTextField txtTelefonoCliente;
	private JTextField txtIdClienteBusqueda;
	private JComboBox<String> cmbPlanes;
	private JComboBox<String> cmbComercial; 
	private JLabel lblMontoTotal;
	
	private Cliente clienteSeleccionado = null;

	public RegContratos() {
		setTitle("Registro de Contratos por Plan");
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
		
		JLabel lblTitulo = new JLabel("REGISTRO DE CONTRATO");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(10, 11, 250, 25);
		panelHeader.add(lblTitulo);
		
		txtIdContrato = new JTextField("CON-" + AlticeSistema.numContrato);
		txtIdContrato.setEditable(false);
		txtIdContrato.setBounds(400, 13, 100, 20);
		panelHeader.add(txtIdContrato);
		
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
				lista.setVisible(true);
				String nombreSeleccionado = lista.getNombreSeleccionado();

				if (nombreSeleccionado != null && !nombreSeleccionado.isEmpty()) {
					Persona aux = AlticeSistema.getInstance().buscarClientePorNombre(nombreSeleccionado);

					if (aux != null && aux instanceof Cliente) {
						clienteSeleccionado = (Cliente) aux; 
						
						txtIdClienteBusqueda.setText(clienteSeleccionado.getId());
						txtNombreCliente.setText(clienteSeleccionado.getNombre());
						txtCedulaCliente.setText(clienteSeleccionado.getCedula());
						txtTelefonoCliente.setText(clienteSeleccionado.getTelefono());
			 
						cmbPlanes.setEnabled(true);
						cmbComercial.setEnabled(true); 
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
		
		JLabel lblCed = new JLabel("Cédula:");
		lblCed.setBounds(15, 150, 80, 14);
		panelCliente.add(lblCed);
		
		txtTelefonoCliente = new JTextField();
		txtTelefonoCliente.setEditable(false);
		txtTelefonoCliente.setBounds(15, 230, 280, 25);
		panelCliente.add(txtTelefonoCliente);
		
		JLabel lblTel = new JLabel("Teléfono:");
		lblTel.setBounds(15, 210, 80, 14);
		panelCliente.add(lblTel);
		
		JPanel panelPlan = new JPanel();
		panelPlan.setBorder(new TitledBorder(new EtchedBorder(), "2. Detalles de Venta"));
		panelPlan.setBounds(350, 60, 320, 350);
		contentPanel.add(panelPlan);
		panelPlan.setLayout(null);

		JLabel lblPlan = new JLabel("Seleccione el Plan:");
		lblPlan.setBounds(15, 25, 150, 14);
		panelPlan.add(lblPlan);

		cmbPlanes = new JComboBox<String>();
		cmbPlanes.setEnabled(false);
		cmbPlanes.setModel(new DefaultComboBoxModel<String>(AlticeSistema.getInstance().getNombresPlanesDisponibles()));
		cmbPlanes.setBounds(15, 45, 280, 25);
		cmbPlanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbPlanes.getSelectedIndex() > 0) {
					Plan p = AlticeSistema.getInstance().buscarPlanPorNombre(cmbPlanes.getSelectedItem().toString());
					if (p != null) {
						lblMontoTotal.setText("RD$ " + String.format("%.2f", p.getPrecio()));
					}
				}
			}
		});
		panelPlan.add(cmbPlanes);

		JLabel lblComercial = new JLabel("Comercial (Vendedor):");
		lblComercial.setBounds(15, 90, 150, 14);
		panelPlan.add(lblComercial);

		cmbComercial = new JComboBox<String>();
		cmbComercial.setEnabled(false);
		cmbComercial.setModel(new DefaultComboBoxModel<String>(AlticeSistema.getInstance().getNombresComercialesDisponibles()));
		cmbComercial.setBounds(15, 110, 280, 25);
		panelPlan.add(cmbComercial);

		JPanel panelResumen = new JPanel();
		panelResumen.setBackground(new Color(240, 248, 255));
		panelResumen.setBounds(0, 430, 684, 45);
		contentPanel.add(panelResumen);
		panelResumen.setLayout(null);
		
		lblMontoTotal = new JLabel("RD$ 0.00");
		lblMontoTotal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMontoTotal.setForeground(Color.RED);
		lblMontoTotal.setBounds(500, 10, 150, 25);
		panelResumen.add(lblMontoTotal);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnRegistrar = new JButton("Registrar Contrato");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clienteSeleccionado != null && cmbPlanes.getSelectedIndex() > 0 && cmbComercial.getSelectedIndex() > 0) {
					
					Plan plan = AlticeSistema.getInstance().buscarPlanPorNombre(cmbPlanes.getSelectedItem().toString());
					Comercial vendedor = AlticeSistema.getInstance().buscarComercialPorNombre(cmbComercial.getSelectedItem().toString());
					
					String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
					
					Contrato c = new Contrato(txtIdContrato.getText(), fecha, "Activo", clienteSeleccionado, plan, vendedor);
					AlticeSistema.getInstance().registrarContrato(c);

					String idFactura = "FAC-" + AlticeSistema.numFactura;
					Factura f = new Factura(idFactura, fecha, "Pendiente", c);
					AlticeSistema.getInstance().registrarFactura(f);
					
					JOptionPane.showMessageDialog(null, "Contrato y Factura (" + idFactura + ") generados con éxito.");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, identifique al cliente, seleccione un plan y asigne un comercial.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		buttonPane.add(btnRegistrar);
	}
}