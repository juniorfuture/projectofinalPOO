package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Administrativo;
import logica.AlticeSistema;
import logica.Cliente;
import logica.Comercial;
import logica.Empleado;
import logica.Factura;
import logica.Reporte;
import logica.Trabajador;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTable tablaClientes;
	private JTable tablaEmpleados;
	private JTable tablaFacturas;

	private DefaultTableModel modeloClientes;
	private DefaultTableModel modeloEmpleados;
	private DefaultTableModel modeloFacturas;

	private JComboBox<String> cmbFiltroClientes;
	private JComboBox<String> cmbFiltroEmpleados;
	private JComboBox<String> cmbFiltroFacturas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		setTitle("Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		Dimension dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height - 48);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(40, 40, 40));
		menuBar.setForeground(Color.WHITE);
		menuBar.setPreferredSize(new Dimension(menuBar.getWidth(), 50));
		setJMenuBar(menuBar);

		JMenu mnRegistrar = new JMenu("Registrar");
		mnRegistrar.setForeground(SystemColor.window);
		mnRegistrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnRegistrar);

		JMenuItem itemCliente = new JMenuItem("Cliente");
		itemCliente.setForeground(Color.BLACK);
		itemCliente.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/iconcliente.png"));
		java.awt.Image img = icon.getImage().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		itemCliente.setIcon(new ImageIcon(img));
		itemCliente.addActionListener(e -> {
			Regcliente cliente = new Regcliente();
			cliente.setModal(true);
			cliente.setVisible(true);
			actualizarTablas();
		});
		mnRegistrar.add(itemCliente);

		JMenuItem itemPersonal = new JMenuItem("Personal");
		itemPersonal.setForeground(Color.BLACK);
		itemPersonal.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/imagenes/iconpersonal.png"));
		java.awt.Image img2 = icon2.getImage().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		itemPersonal.setIcon(new ImageIcon(img2));
		itemPersonal.addActionListener(e -> {
			RegEmpleados empleado = new RegEmpleados();
			empleado.setModal(true);
			empleado.setVisible(true);
			actualizarTablas();
		});
		mnRegistrar.add(itemPersonal);

		JMenuItem itemServicio = new JMenuItem("Servicio");
		itemServicio.setForeground(Color.BLACK);
		itemServicio.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		ImageIcon icon3 = new ImageIcon(getClass().getResource("/imagenes/service.jpg"));
		java.awt.Image img3 = icon3.getImage().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		itemServicio.setIcon(new ImageIcon(img3));
		itemServicio.addActionListener(e -> {
			RegServicios servicio = new RegServicios();
			servicio.setModal(true);
			servicio.setVisible(true);
		});
		mnRegistrar.add(itemServicio);

		JMenuItem itemPlan = new JMenuItem("Plan");
		itemPlan.setForeground(Color.BLACK);
		itemPlan.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		ImageIcon icon4 = new ImageIcon(getClass().getResource("/imagenes/plan.png"));
		java.awt.Image img4 = icon4.getImage().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		itemPlan.setIcon(new ImageIcon(img4));
		itemPlan.addActionListener(e -> {
			RegPlanes pn = new RegPlanes();
			pn.setModal(true);
			pn.setVisible(true);
		});
		mnRegistrar.add(itemPlan);

		JMenu mnVentas = new JMenu("Ventas");
		mnVentas.setForeground(Color.WHITE);
		mnVentas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnVentas);

		JMenuItem itemContrato = new JMenuItem("Contrato");
		itemContrato.setForeground(Color.BLACK);
		itemContrato.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		itemContrato.addActionListener(e -> {
			RegContratos contrato = new RegContratos();
			contrato.setModal(true);
			contrato.setVisible(true);
			actualizarTablas();
		});
		mnVentas.add(itemContrato);

		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setForeground(Color.WHITE);
		mnReportes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnReportes);

		JMenuItem itemReporteGeneral = new JMenuItem("Reporte General");
		itemReporteGeneral.setForeground(Color.BLACK);
		itemReporteGeneral.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		itemReporteGeneral.addActionListener(e -> mostrarReporteGeneral());
		mnReportes.add(itemReporteGeneral);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panelGestion = new JPanel(new BorderLayout(10, 10));
		tabbedPane.addTab("Clientes / Empleados", panelGestion);

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.5);
		panelGestion.add(splitPane, BorderLayout.CENTER);

		JPanel panelClientes = new JPanel(new BorderLayout(10, 10));
		panelClientes.setBorder(new TitledBorder(null, "Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setTopComponent(panelClientes);

		JPanel panelFiltroClientes = new JPanel(new BorderLayout(5, 5));
		panelClientes.add(panelFiltroClientes, BorderLayout.NORTH);

		JLabel lblFiltroClientes = new JLabel("Filtrar por tipo de cliente:");
		lblFiltroClientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelFiltroClientes.add(lblFiltroClientes, BorderLayout.WEST);

		cmbFiltroClientes = new JComboBox<>(new String[] { "Todos", "Normal", "Empresarial" });
		cmbFiltroClientes.addActionListener(e -> cargarTablaClientes());
		panelFiltroClientes.add(cmbFiltroClientes, BorderLayout.CENTER);

		modeloClientes = new DefaultTableModel();
		modeloClientes.setColumnIdentifiers(
				new String[] { "Código", "Nombre", "Cédula", "Teléfono", "Dirección", "Tipo", "Estado", "RNC" });

		tablaClientes = new JTable(modeloClientes);
		panelClientes.add(new JScrollPane(tablaClientes), BorderLayout.CENTER);

		JPanel panelEmpleados = new JPanel(new BorderLayout(10, 10));
		panelEmpleados.setBorder(new TitledBorder(null, "Empleados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setBottomComponent(panelEmpleados);

		JPanel panelFiltroEmpleados = new JPanel(new BorderLayout(5, 5));
		panelEmpleados.add(panelFiltroEmpleados, BorderLayout.NORTH);

		JLabel lblFiltroEmpleados = new JLabel("Filtrar por tipo de empleado:");
		lblFiltroEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelFiltroEmpleados.add(lblFiltroEmpleados, BorderLayout.WEST);

		cmbFiltroEmpleados = new JComboBox<>(new String[] { "Todos", "Trabajador", "Administrativo", "Comercial" });
		cmbFiltroEmpleados.addActionListener(e -> cargarTablaEmpleados());
		panelFiltroEmpleados.add(cmbFiltroEmpleados, BorderLayout.CENTER);

		modeloEmpleados = new DefaultTableModel();
		modeloEmpleados.setColumnIdentifiers(new String[] {
				"Código", "Nombre", "Cédula", "Teléfono", "Dirección", "Tipo", "Salario", "Fecha Ingreso", "Detalle"
		});

		tablaEmpleados = new JTable(modeloEmpleados);
		panelEmpleados.add(new JScrollPane(tablaEmpleados), BorderLayout.CENTER);

		JPanel panelFacturas = new JPanel(new BorderLayout(10, 10));
		tabbedPane.addTab("Facturas", panelFacturas);

		JPanel panelSuperiorFacturas = new JPanel(new BorderLayout(10, 10));
		panelFacturas.add(panelSuperiorFacturas, BorderLayout.NORTH);

		JLabel lblFiltroFacturas = new JLabel("Filtrar por estado:");
		lblFiltroFacturas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelSuperiorFacturas.add(lblFiltroFacturas, BorderLayout.WEST);

		cmbFiltroFacturas = new JComboBox<>(new String[] { "Todas", "Pendiente", "Pagada", "Vencida" });
		cmbFiltroFacturas.addActionListener(e -> cargarTablaFacturas());
		panelSuperiorFacturas.add(cmbFiltroFacturas, BorderLayout.CENTER);

		modeloFacturas = new DefaultTableModel();
		modeloFacturas.setColumnIdentifiers(new String[] {
				"Factura", "Fecha", "Estado", "Contrato", "Cliente", "Plan", "Monto"
		});

		tablaFacturas = new JTable(modeloFacturas);
		panelFacturas.add(new JScrollPane(tablaFacturas), BorderLayout.CENTER);

		actualizarTablas();
	}

	private void actualizarTablas() {
		cargarTablaClientes();
		cargarTablaEmpleados();
		cargarTablaFacturas();
	}

	private void cargarTablaClientes() {
		modeloClientes.setRowCount(0);

		String filtro = cmbFiltroClientes.getSelectedItem().toString();
		List<Cliente> clientes = AlticeSistema.getInstance().filtrarClientesPorTipo(filtro);

		for (Cliente c : clientes) {
			modeloClientes.addRow(new Object[] {
					c.getId(), c.getNombre(), c.getCedula(), c.getTelefono(),
					c.getDireccion(), c.getTipoCliente(), c.getEstado(), c.getRNC()
			});
		}
	}

	private void cargarTablaEmpleados() {
		modeloEmpleados.setRowCount(0);

		String filtro = cmbFiltroEmpleados.getSelectedItem().toString();
		List<Empleado> empleados = AlticeSistema.getInstance().filtrarEmpleadosPorTipo(filtro);

		for (Empleado e : empleados) {
			modeloEmpleados.addRow(new Object[] {
					e.getId(), e.getNombre(), e.getCedula(), e.getTelefono(),
					e.getDireccion(), AlticeSistema.getInstance().obtenerTipoEmpleado(e), e.getSalario(),
					e.getFechaIngreso(), obtenerDetalleEmpleado(e)
			});
		}
	}

	private void cargarTablaFacturas() {
		modeloFacturas.setRowCount(0);

		String filtro = cmbFiltroFacturas.getSelectedItem().toString();
		List<Factura> facturas = AlticeSistema.getInstance().getFacturas();

		for (Factura f : facturas) {
			if (filtro.equalsIgnoreCase("Todas") || f.getEstado().equalsIgnoreCase(filtro)) {
				String idContrato = "";
				String nombreCliente = "";
				String nombrePlan = "";

				if (f.getContrato() != null) {
					idContrato = f.getContrato().getIdContrato();

					if (f.getContrato().getCliente() != null) {
						nombreCliente = f.getContrato().getCliente().getNombre();
					}

					if (f.getContrato().getPlan() != null) {
						nombrePlan = f.getContrato().getPlan().getNombre();
					}
				}

				modeloFacturas.addRow(new Object[] {
						f.getIdFactura(),
						f.getFecha(),
						f.getEstado(),
						idContrato,
						nombreCliente,
						nombrePlan,
						String.format("RD$ %.2f", f.getMontoTotal())
				});
			}
		}
	}

	private String obtenerDetalleEmpleado(Empleado e) {
		if (e instanceof Trabajador) {
			return ((Trabajador) e).getAreaTecnica();
		}
		if (e instanceof Administrativo) {
			return ((Administrativo) e).getDepartamento();
		}
		if (e instanceof Comercial) {
			return ((Comercial) e).getProducto();
		}
		return "";
	}

	private void mostrarReporteGeneral() {
		Reporte reporte = AlticeSistema.getInstance().generarReporteGeneral();

		JDialog dialog = new JDialog(this, "Reporte General", true);
		dialog.setSize(600, 450);
		dialog.setLocationRelativeTo(this);
		dialog.setLayout(new BorderLayout());

		JTextArea txtReporte = new JTextArea();
		txtReporte.setEditable(false);
		txtReporte.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtReporte.setText(reporte.generarContenido());

		dialog.add(new JScrollPane(txtReporte), BorderLayout.CENTER);
		dialog.setVisible(true);
	}
}