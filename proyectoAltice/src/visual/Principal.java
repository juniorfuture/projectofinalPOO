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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Administrativo;
import logica.AlticeSistema;
import logica.Cliente;
import logica.Comercial;
import logica.Empleado;
import logica.Trabajador;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTable tablaClientes;
	private JTable tablaEmpleados;

	private DefaultTableModel modeloClientes;
	private DefaultTableModel modeloEmpleados;

	private JComboBox<String> cmbFiltroClientes;
	private JComboBox<String> cmbFiltroEmpleados;

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

		JMenu mnNewMenu = new JMenu("Registrar");
		mnNewMenu.setForeground(SystemColor.window);
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Cliente");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/iconcliente.png"));
		java.awt.Image img = icon.getImage().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		ImageIcon iconoPequeno = new ImageIcon(img);
		mntmNewMenuItem.setIcon(iconoPequeno);
		mntmNewMenuItem.addActionListener(e -> {
			Regcliente cliente = new Regcliente();
			cliente.setModal(true);
			cliente.setVisible(true);
			actualizarTablas();
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Personal");
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/imagenes/iconpersonal.png"));
		java.awt.Image img2 = icon2.getImage().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		ImageIcon iconoPequeno2 = new ImageIcon(img2);
		mntmNewMenuItem_1.setIcon(iconoPequeno2);
		mntmNewMenuItem_1.addActionListener(e -> {
			RegEmpleados empleado = new RegEmpleados();
			empleado.setModal(true);
			empleado.setVisible(true);
			actualizarTablas();
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.5);
		contentPane.add(splitPane, BorderLayout.CENTER);

		// PANEL CLIENTES
		JPanel panelClientes = new JPanel(new BorderLayout(10, 10));
		panelClientes.setBorder(new TitledBorder(null, "Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setTopComponent(panelClientes);

		JPanel panelFiltroClientes = new JPanel(new BorderLayout(5, 5));
		panelClientes.add(panelFiltroClientes, BorderLayout.NORTH);

		JLabel lblFiltroClientes = new JLabel("Filtrar por tipo de cliente:");
		lblFiltroClientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelFiltroClientes.add(lblFiltroClientes, BorderLayout.WEST);

		cmbFiltroClientes = new JComboBox<>(new String[] { "Todos", "Normal", "Valor" });
		cmbFiltroClientes.addActionListener(e -> cargarTablaClientes());
		panelFiltroClientes.add(cmbFiltroClientes, BorderLayout.CENTER);

		modeloClientes = new DefaultTableModel();
		modeloClientes.setColumnIdentifiers(new String[] {
				"Código", "Nombre", "Cédula", "Teléfono", "Dirección", "Tipo", "Estado", "RNC"
		});

		tablaClientes = new JTable(modeloClientes);
		panelClientes.add(new JScrollPane(tablaClientes), BorderLayout.CENTER);

		// PANEL EMPLEADOS
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

		actualizarTablas();
	}

	private void actualizarTablas() {
		cargarTablaClientes();
		cargarTablaEmpleados();
	}

	private void cargarTablaClientes() {
		modeloClientes.setRowCount(0);

		String filtro = cmbFiltroClientes.getSelectedItem().toString();
		List<Cliente> clientes = AlticeSistema.getInstance().filtrarClientesPorTipo(filtro);

		for (Cliente c : clientes) {
			modeloClientes.addRow(new Object[] {
					c.getId(),
					c.getNombre(),
					c.getCedula(),
					c.getTelefono(),
					c.getDireccion(),
					c.getTipoCliente(),
					c.getEstado(),
					c.getRNC()
			});
		}
	}

	private void cargarTablaEmpleados() {
		modeloEmpleados.setRowCount(0);

		String filtro = cmbFiltroEmpleados.getSelectedItem().toString();
		List<Empleado> empleados = AlticeSistema.getInstance().filtrarEmpleadosPorTipo(filtro);

		for (Empleado e : empleados) {
			modeloEmpleados.addRow(new Object[] {
					e.getId(),
					e.getNombre(),
					e.getCedula(),
					e.getTelefono(),
					e.getDireccion(),
					AlticeSistema.getInstance().obtenerTipoEmpleado(e),
					e.getSalario(),
					e.getFechaIngreso(),
					obtenerDetalleEmpleado(e)
			});
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
}