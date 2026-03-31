package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;

import logica.AlticeSistema;
import logica.Cliente;
import logica.Plan;
import logica.Servicio;
import logica.Contrato;
import logica.Factura;

public class RegContratos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private JTextField txtIdContrato;
	private JTextField txtFechaInfo;
	private JTextField txtNombreCliente;
	private JTextField txtCedulaCliente;
	private JTextField txtTelefonoCliente;
	
	private JComboBox<String> cmbPlanes;
	private JPanel panelServiciosCheckBoxes;
	private JScrollPane scrollServicios;
	private JLabel lblMontoTotal;
	
	// Variables auxiliares de lógica
	private Cliente clienteSeleccionado = null;
	private ArrayList<JCheckBox> listaCheckBoxesServicios = new ArrayList<>();
	private double montoTotalCalculado = 0.0;
	private JTextField txtIdClienteBusqueda;

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
		setTitle("Gestión de Nuevos Contratos");
		setBounds(100, 100, 950, 611);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(192, 192, 192));
		panelHeader.setBounds(0, 0, 934, 45);
		contentPanel.add(panelHeader);
		panelHeader.setLayout(null);
		
		JLabel lblTitulo = new JLabel("REGISTRO DE CONTRATO DE SERVICIO");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(10, 11, 400, 25);
		panelHeader.add(lblTitulo);
		
		JLabel lblIdC = new JLabel("No. Contrato:");
		lblIdC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdC.setBounds(480, 16, 85, 14);
		panelHeader.add(lblIdC);
		
		txtIdContrato = new JTextField();
		txtIdContrato.setText("CON-" + AlticeSistema.numContrato);
		txtIdContrato.setEditable(false);
		txtIdContrato.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtIdContrato.setBounds(570, 13, 110, 20);
		panelHeader.add(txtIdContrato);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setBounds(720, 16, 50, 14);
		panelHeader.add(lblFecha);
		
		txtFechaInfo = new JTextField();
		txtFechaInfo.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		txtFechaInfo.setEditable(false);
		txtFechaInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFechaInfo.setBounds(770, 13, 130, 20);
		panelHeader.add(txtFechaInfo);
		JPanel panelCliente = new JPanel();
		panelCliente.setBorder(new TitledBorder(new EtchedBorder(), "1. Identificar Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelCliente.setBounds(15, 60, 290, 420);
		contentPanel.add(panelCliente);
		panelCliente.setLayout(null);
		
		JLabel lblBusqueda = new JLabel("Ingrese ID del Cliente:");
		lblBusqueda.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBusqueda.setBounds(15, 30, 150, 14);
		panelCliente.add(lblBusqueda);
		
		JButton btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				        ListaCliente lista = new ListaCliente();
				        lista.setVisible(true); 

				        Cliente seleccionado = lista.getClienteSeleccionado();
				        
				        if (seleccionado != null) {
				            clienteSeleccionado = seleccionado;
				            txtIdClienteBusqueda.setText(clienteSeleccionado.getId());
				            txtNombreCliente.setText(clienteSeleccionado.getNombre());
				            txtCedulaCliente.setText(clienteSeleccionado.getCedula());
				            txtTelefonoCliente.setText(clienteSeleccionado.getTelefono());
				            
				            cmbPlanes.setEnabled(true);
				            actualizarMontoTotal();
				        }
			}
		});
		btnBuscarCliente.setBounds(150, 54, 90, 26);
		panelCliente.add(btnBuscarCliente);
		
		JPanel panelInfoDetalle = new JPanel();
		panelInfoDetalle.setBorder(new EtchedBorder());
		panelInfoDetalle.setBounds(10, 100, 270, 180);
		panelCliente.add(panelInfoDetalle);
		panelInfoDetalle.setLayout(null);
		
		JLabel lblNom = new JLabel("Nombre:");
		lblNom.setBounds(10, 15, 60, 14);
		panelInfoDetalle.add(lblNom);
		txtNombreCliente = new JTextField();
		txtNombreCliente.setEditable(false);
		txtNombreCliente.setBounds(10, 35, 250, 20);
		panelInfoDetalle.add(txtNombreCliente);
		
		JLabel lblCed = new JLabel("Cédula:");
		lblCed.setBounds(10, 70, 60, 14);
		panelInfoDetalle.add(lblCed);
		txtCedulaCliente = new JTextField();
		txtCedulaCliente.setEditable(false);
		txtCedulaCliente.setBounds(10, 90, 250, 20);
		panelInfoDetalle.add(txtCedulaCliente);
		
		JLabel lblTel = new JLabel("Teléfono:");
		lblTel.setBounds(10, 125, 60, 14);
		panelInfoDetalle.add(lblTel);
		txtTelefonoCliente = new JTextField();
		txtTelefonoCliente.setEditable(false);
		txtTelefonoCliente.setBounds(10, 145, 250, 20);
		panelInfoDetalle.add(txtTelefonoCliente);
		
		txtIdClienteBusqueda = new JTextField();
		txtIdClienteBusqueda.setBounds(10, 54, 130, 26);
		panelCliente.add(txtIdClienteBusqueda);
		txtIdClienteBusqueda.setColumns(10);

		JPanel panelPlan = new JPanel();
		panelPlan.setBorder(new TitledBorder(new EtchedBorder(), "2. Seleccionar Plan Base", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelPlan.setBounds(320, 60, 290, 420);
		contentPanel.add(panelPlan);
		panelPlan.setLayout(null);
		
		JLabel lblSeleccionePlan = new JLabel("Planes Disponibles:");
		lblSeleccionePlan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccionePlan.setBounds(15, 30, 150, 14);
		panelPlan.add(lblSeleccionePlan);
		
		cmbPlanes = new JComboBox<String>();
		cmbPlanes.setEnabled(false);
		
		cmbPlanes.setBounds(15, 50, 260, 25);
		cmbPlanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarMontoTotal();
				if (cmbPlanes.getSelectedIndex() > 0) {
					panelServiciosCheckBoxes.setEnabled(true);
					for (JCheckBox cb : listaCheckBoxesServicios) {
						cb.setEnabled(true);
					}
				}
			}
		});
		panelPlan.add(cmbPlanes);
		
		JPanel panelServicios = new JPanel();
		panelServicios.setBorder(new TitledBorder(new EtchedBorder(), "3. Servicios Adicionales", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelServicios.setBounds(625, 60, 290, 420);
		contentPanel.add(panelServicios);
		panelServicios.setLayout(null);
		
		JLabel lblAdd = new JLabel("Marque los servicios extra:");
		lblAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdd.setBounds(15, 30, 200, 14);
		panelServicios.add(lblAdd);
		
		panelServiciosCheckBoxes = new JPanel();
		panelServiciosCheckBoxes.setEnabled(false);
		panelServiciosCheckBoxes.setLayout(new BoxLayout(panelServiciosCheckBoxes, BoxLayout.Y_AXIS));
		
		cargarCheckBoxesServicios();
		
		scrollServicios = new JScrollPane(panelServiciosCheckBoxes);
		scrollServicios.setBounds(15, 50, 260, 350);
		scrollServicios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelServicios.add(scrollServicios);
		JPanel panelResumen = new JPanel();
		panelResumen.setBorder(new MatteBorder(2, 0, 0, 0, Color.BLACK));
		panelResumen.setBackground(new Color(240, 248, 255));
		panelResumen.setBounds(0, 490, 934, 45);
		contentPanel.add(panelResumen);
		panelResumen.setLayout(null);
		
		JLabel lblTotal = new JLabel("MONTO TOTAL A PAGAR (Plan + Adicionales):");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(20, 13, 350, 20);
		panelResumen.add(lblTotal);
		
		lblMontoTotal = new JLabel("RD$ 0.00");
		lblMontoTotal.setForeground(Color.RED);
		lblMontoTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontoTotal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMontoTotal.setBounds(750, 10, 150, 25);
		panelResumen.add(lblMontoTotal);
		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnRegistrar = new JButton("Confirmar y Registrar Contrato");
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (validarTodo()) {

		            String idContrato = txtIdContrato.getText();
		            String fechaInicio = txtFechaInfo.getText();
		            String estado = "Activo"; 
		            Plan planBase = AlticeSistema.getInstance().buscarPlanPorNombre(cmbPlanes.getSelectedItem().toString());
		            ArrayList<Servicio> serviciosAdicionales = obtenerServiciosSeleccionados();
		            Contrato nuevoContrato = new Contrato(idContrato, fechaInicio, estado, clienteSeleccionado, planBase, serviciosAdicionales);
		            AlticeSistema.getInstance().registrarContrato(nuevoContrato);
		            double totalReal = nuevoContrato.calcularCostoMensual();
		            String idFactura = "FAC-" + AlticeSistema.numFactura;
		            Factura nuevaFactura = new Factura(idFactura, fechaInicio, "Pendiente", nuevoContrato);
		            AlticeSistema.getInstance().registrarFactura(nuevaFactura);
		            JOptionPane.showMessageDialog(null, "Contrato y Factura (" + idFactura + ") generados con éxito.");
		            cleanAll();
		            dispose();
		        }
		    }
		});
		buttonPane.add(btnRegistrar);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);
	}

	private void cargarCheckBoxesServicios() {
		ArrayList<Servicio> serviciosDisponibles = AlticeSistema.getInstance().getServiciosDisponibles();
		
		for (Servicio serv : serviciosDisponibles) {
			JCheckBox cb = new JCheckBox(serv.getNombre() + " (RD$ " + serv.getCosto() + ")");
			cb.setEnabled(false); 
			cb.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarMontoTotal();
				}
			});
			listaCheckBoxesServicios.add(cb);
			panelServiciosCheckBoxes.add(cb);
		}
	}

	private void actualizarMontoTotal() {
		montoTotalCalculado = 0.0;
		if (cmbPlanes.getSelectedIndex() > 0) {
			Plan plan = AlticeSistema.getInstance().buscarPlanPorNombre(cmbPlanes.getSelectedItem().toString());
			if (plan != null) {
				montoTotalCalculado += plan.getPrecio();
			}
		}
		for (int i = 0; i < listaCheckBoxesServicios.size(); i++) {
			if (listaCheckBoxesServicios.get(i).isSelected()) {
				Servicio s = AlticeSistema.getInstance().getServiciosDisponibles().get(i);
				montoTotalCalculado += s.getCosto();
			}
		}
		lblMontoTotal.setText("RD$ " + String.format("%.2f", montoTotalCalculado));
	}

	private ArrayList<Servicio> obtenerServiciosSeleccionados() {
		ArrayList<Servicio> seleccionados = new ArrayList<>();
		ArrayList<Servicio> disponibles = AlticeSistema.getInstance().getServiciosDisponibles();
		
		for (int i = 0; i < listaCheckBoxesServicios.size(); i++) {
			if (listaCheckBoxesServicios.get(i).isSelected()) {
				seleccionados.add(disponibles.get(i));
			}
		}
		return seleccionados;
	}

	private boolean validarTodo() {
		if (clienteSeleccionado == null) {
			JOptionPane.showMessageDialog(null, "Debe identificar un cliente válido (Paso 1).", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (cmbPlanes.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un Plan Base (Paso 2).", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}

	private void limpiarDatosCliente() {
		clienteSeleccionado = null;
		txtNombreCliente.setText("");
		txtCedulaCliente.setText("");
		txtTelefonoCliente.setText("");
		cmbPlanes.setSelectedIndex(0);
		cmbPlanes.setEnabled(false);
		deshabilitarYDesmarcarServicios();
		actualizarMontoTotal();
	}
	
	private void deshabilitarYDesmarcarServicios() {
		panelServiciosCheckBoxes.setEnabled(false);
		for (JCheckBox cb : listaCheckBoxesServicios) {
			cb.setSelected(false);
			cb.setEnabled(false);
		}
	}

	private void cleanAll() {
		txtIdClienteBusqueda.setText("");
		limpiarDatosCliente();
		txtIdContrato.setText("CON-" + AlticeSistema.numContrato);
	}
}