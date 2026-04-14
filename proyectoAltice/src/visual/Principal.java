package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Administrativo;
import logica.AlticeSistema;
import logica.Cliente;
import logica.Comercial;
import logica.Empleado;
import logica.Factura;
import logica.Trabajador;
import logica.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

	private CardLayout cardLayout;
	private JPanel panelContenidoCentral;

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_PRIMARIO = new Color(31, 111, 235);
	private final Color COLOR_PRIMARIO_SUAVE = new Color(235, 242, 255);
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(225, 230, 235);

	private final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 26);
	private final Font FUENTE_SUBTITULO = new Font("Segoe UI", Font.PLAIN, 13);
	private final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 14);
	private final Font FUENTE_LABEL = new Font("Segoe UI", Font.PLAIN, 13);

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
		setTitle("Sistema Altice");
		Dimension dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height - 48);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				try {
					java.io.FileOutputStream fileOut = new java.io.FileOutputStream("empresa1.dat");
					java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut);
					out.writeObject(AlticeSistema.getInstance());
					out.close();
					fileOut.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al guardar los datos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				System.exit(0);
			}
		});

		construirInterfaz();
		actualizarTablas();
	}

	private boolean esAdmin() {
		User user = AlticeSistema.getInstance().getUsuarioLogueado();
		return user != null && user.getTipo() != null && user.getTipo().equalsIgnoreCase("Admin");
	}

	private boolean esCliente() {
		return AlticeSistema.getInstance().esUsuarioCliente();
	}

	private Cliente getClienteLogueado() {
		return AlticeSistema.getInstance().getClienteDelUsuarioLogueado();
	}

	private void construirInterfaz() {
		contentPane = new JPanel(new BorderLayout(20, 20));
		contentPane.setBackground(COLOR_FONDO);
		contentPane.setBorder(new EmptyBorder(18, 18, 18, 18));
		setContentPane(contentPane);

		contentPane.add(crearHeaderSuperior(), BorderLayout.NORTH);
		contentPane.add(crearMenuLateral(), BorderLayout.WEST);
		contentPane.add(crearPanelCentral(), BorderLayout.CENTER);
	}

	private JPanel crearHeaderSuperior() {
		JPanel panelHeader = new JPanel(new BorderLayout());
		panelHeader.setBackground(COLOR_PANEL);
		panelHeader.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(COLOR_BORDE),
				new EmptyBorder(18, 22, 18, 22)));

		String tituloSistema = esCliente() ? "Portal de Cliente Altice" : "Sistema de Gestión Altice";
		JLabel lblTitulo = new JLabel(tituloSistema);
		lblTitulo.setFont(FUENTE_TITULO);
		lblTitulo.setForeground(COLOR_TEXTO);

		User user = AlticeSistema.getInstance().getUsuarioLogueado();
		String nombre = user != null ? user.getNombre() : "Usuario";
		String tipo = user != null ? user.getTipo() : "Acceso";

		JLabel lblSubtitulo = new JLabel("Bienvenido, " + nombre + "  |  Rol: " + tipo);
		lblSubtitulo.setFont(FUENTE_SUBTITULO);
		lblSubtitulo.setForeground(COLOR_SECUNDARIO);

		JPanel panelTextos = new JPanel(new BorderLayout());
		panelTextos.setOpaque(false);
		panelTextos.add(lblTitulo, BorderLayout.NORTH);
		panelTextos.add(lblSubtitulo, BorderLayout.SOUTH);

		panelHeader.add(panelTextos, BorderLayout.WEST);

		JLabel lblAvatar = new JLabel();
		lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvatar.setPreferredSize(new Dimension(60, 60));
		lblAvatar.setIcon(cargarIcono("/imagenes/usuario.png", 42, 42));
		panelHeader.add(lblAvatar, BorderLayout.EAST);

		return panelHeader;
	}

	private JPanel crearMenuLateral() {
		JPanel panelLateral = new JPanel(new BorderLayout());
		panelLateral.setBackground(COLOR_PANEL);
		panelLateral.setPreferredSize(new Dimension(235, 0));
		panelLateral.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(COLOR_BORDE),
				new EmptyBorder(18, 14, 18, 14)));

		JLabel lblMenu = new JLabel("Navegación");
		lblMenu.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblMenu.setForeground(COLOR_TEXTO);
		lblMenu.setBorder(new EmptyBorder(0, 6, 16, 0));
		panelLateral.add(lblMenu, BorderLayout.NORTH);

		JPanel botones;
		if (esCliente()) {
			botones = new JPanel(new GridLayout(4, 1, 0, 10));
			botones.setOpaque(false);
			botones.add(crearBotonMenu("Inicio", "INICIO", "/imagenes/reporte.png"));
			botones.add(crearBotonMenu("Mis Facturas", "FACTURAS", "/imagenes/pagos.png"));
			botones.add(crearBotonAccionDestacado("Pagar Factura", "/imagenes/pagos.png", new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Cliente cliente = getClienteLogueado();
					if (cliente != null) {
						RegPagos pagos = new RegPagos(cliente);
						pagos.setModal(true);
						pagos.setVisible(true);
						actualizarTablas();
						mostrarVista("FACTURAS");
					}
				}
			}));
			botones.add(crearBotonAccionDestacado("Chat Soporte", "/imagenes/usuario.png", new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ChatSoporte chat = new ChatSoporte();
					chat.setVisible(true);
				}
			}));
		} else {
			botones = new JPanel(new GridLayout(5, 1, 0, 10));
			botones.setOpaque(false);
			botones.add(crearBotonMenu("Inicio", "INICIO", "/imagenes/reporte.png"));
			botones.add(crearBotonMenu("Clientes", "CLIENTES", "/imagenes/iconcliente.png"));
			botones.add(crearBotonMenu("Empleados", "EMPLEADOS", "/imagenes/iconpersonal.png"));
			botones.add(crearBotonMenu("Facturas", "FACTURAS", "/imagenes/pagos.png"));
			botones.add(crearBotonAccionDestacado("Registrar Pago", "/imagenes/pagos.png", new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RegPagos pagos = new RegPagos();
					pagos.setModal(true);
					pagos.setVisible(true);
					actualizarTablas();
					mostrarVista("FACTURAS");
				}
			}));
		}

		panelLateral.add(botones, BorderLayout.CENTER);
		return panelLateral;
	}

	private JPanel crearPanelCentral() {
		cardLayout = new CardLayout();
		panelContenidoCentral = new JPanel(cardLayout);
		panelContenidoCentral.setOpaque(false);

		panelContenidoCentral.add(crearVistaInicio(), "INICIO");
		if (!esCliente()) {
			panelContenidoCentral.add(crearVistaClientes(), "CLIENTES");
			panelContenidoCentral.add(crearVistaEmpleados(), "EMPLEADOS");
		}
		panelContenidoCentral.add(crearVistaFacturas(), "FACTURAS");

		cardLayout.show(panelContenidoCentral, "INICIO");
		return panelContenidoCentral;
	}

	private JPanel crearVistaInicio() {
		JPanel panelInicio = new JPanel(new BorderLayout(18, 18));
		panelInicio.setOpaque(false);

		JPanel bienvenida = new JPanel(new BorderLayout(10, 10));
		bienvenida.setBackground(COLOR_PANEL);
		bienvenida.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(COLOR_BORDE),
				new EmptyBorder(22, 22, 22, 22)));

		JLabel titulo = new JLabel("Panel principal");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setForeground(COLOR_TEXTO);

		String subtitulo = esCliente() ? "Accesos rápidos de tu cuenta" : "Accesos rápidos del sistema";
		JLabel texto = new JLabel(subtitulo);
		texto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		texto.setForeground(COLOR_SECUNDARIO);

		bienvenida.add(titulo, BorderLayout.NORTH);
		bienvenida.add(texto, BorderLayout.CENTER);

		panelInicio.add(bienvenida, BorderLayout.NORTH);

		JPanel panelTarjetasInicio = new JPanel(new GridLayout(0, 3, 16, 16));
		panelTarjetasInicio.setOpaque(false);

		List<JPanel> tarjetas = new ArrayList<>();

		if (esCliente()) {
			tarjetas.add(crearTarjetaAccion("Mis Facturas", "Consulta tus facturas pendientes, pagadas y vencidas",
					"/imagenes/pagos.png", new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							mostrarVista("FACTURAS");
						}
					}));

			tarjetas.add(crearTarjetaAccion("Pagar Factura", "Realiza el pago de tus facturas pendientes",
					"/imagenes/pagos.png", new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Cliente cliente = getClienteLogueado();
							if (cliente != null) {
								RegPagos pagos = new RegPagos(cliente);
								pagos.setModal(true);
								pagos.setVisible(true);
								actualizarTablas();
								mostrarVista("FACTURAS");
							}
						}
					}));

			tarjetas.add(crearTarjetaAccion("Chat de Soporte", "Comunícate con soporte mediante el chat interno",
					"/imagenes/usuario.png", new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ChatSoporte chat = new ChatSoporte();
							chat.setVisible(true);
						}
					}));
		} else {
			tarjetas.add(crearTarjetaAccion("Nuevo Contrato", "Crear contrato y factura", "/imagenes/contrato.png",
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							RegContratos contrato = new RegContratos();
							contrato.setModal(true);
							contrato.setVisible(true);
							actualizarTablas();
							mostrarVista("FACTURAS");
						}
					}));

			tarjetas.add(crearTarjetaAccion("Registrar Cliente", "Agregar nuevo cliente", "/imagenes/iconcliente.png",
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Regcliente cliente = new Regcliente();
							cliente.setModal(true);
							cliente.setVisible(true);
							actualizarTablas();
							mostrarVista("CLIENTES");
						}
					}));

			tarjetas.add(crearTarjetaAccion("Registrar Servicio", "Crear nuevo servicio", "/imagenes/service.jpg",
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							RegServicios servicio = new RegServicios();
							servicio.setModal(true);
							servicio.setVisible(true);
						}
					}));

			tarjetas.add(crearTarjetaAccion("Registrar Plan", "Configurar planes", "/imagenes/plan.png",
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							RegPlanes plan = new RegPlanes();
							plan.setModal(true);
							plan.setVisible(true);
						}
					}));

			tarjetas.add(crearTarjetaAccion("Cortes Automáticos", "Aplicar suspensión por mora",
					"/imagenes/corte.png", new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int afectados = AlticeSistema.getInstance().ejecutarCortesAutomaticos();
							JOptionPane.showMessageDialog(null,
									"Proceso completado con éxito.\nContratos suspendidos: " + afectados,
									"Cortes por Morosidad", JOptionPane.INFORMATION_MESSAGE);
							actualizarTablas();
						}
					}));

			if (esAdmin()) {
				tarjetas.add(crearTarjetaAccion("Registrar Empleado", "Agregar nuevo empleado",
						"/imagenes/empleadoregistrar.png", new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								RegEmpleados empleado = new RegEmpleados();
								empleado.setModal(true);
								empleado.setVisible(true);
								actualizarTablas();
								mostrarVista("EMPLEADOS");
							}
						}));

				tarjetas.add(crearTarjetaAccion("Registrar Usuario", "Crear acceso al sistema", "/imagenes/usuario.png",
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								RegUsuarios usuario = new RegUsuarios();
								usuario.setModal(true);
								usuario.setVisible(true);
							}
						}));
			}

			tarjetas.add(crearTarjetaAccion("Reporte General", "Ver resumen del sistema", "/imagenes/reporte.png",
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							mostrarReporteGeneral();
						}
					}));

			tarjetas.add(crearTarjetaAccion("Gráficas", "Visualizar indicadores", "/imagenes/grafico.png",
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Graficas grafica = new Graficas();
							grafica.setVisible(true);
						}
					}));

			tarjetas.add(crearTarjetaDobleChat());
		}

		for (JPanel tarjeta : tarjetas) {
			panelTarjetasInicio.add(tarjeta);
		}

		panelInicio.add(panelTarjetasInicio, BorderLayout.CENTER);
		return panelInicio;
	}

	private JPanel crearVistaClientes() {
		JPanel panel = new JPanel(new BorderLayout(14, 14));
		panel.setOpaque(false);

		JPanel cabecera = crearCabeceraModulo("Gestión de Clientes",
				"Consulta, filtra y revisa el estado general de los clientes.");

		JPanel panelFiltro = new JPanel(new BorderLayout(8, 8));
		panelFiltro.setOpaque(false);

		JLabel lblFiltro = new JLabel("Filtrar por tipo:");
		lblFiltro.setFont(FUENTE_LABEL);

		cmbFiltroClientes = new JComboBox<>(new String[] { "Todos", "Normal", "Empresarial" });
		cmbFiltroClientes.setFont(FUENTE_LABEL);
		cmbFiltroClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTablaClientes();
			}
		});

		panelFiltro.add(lblFiltro, BorderLayout.WEST);
		panelFiltro.add(cmbFiltroClientes, BorderLayout.CENTER);

		cabecera.add(panelFiltro, BorderLayout.EAST);
		panel.add(cabecera, BorderLayout.NORTH);

		modeloClientes = new DefaultTableModel();
		modeloClientes.setColumnIdentifiers(new String[] { "Código", "Nombre", "Cédula", "Teléfono", "Dirección",
				"Tipo", "Categoría", "Estado", "RNC", "Meses Deuda", "Monto Deuda" });

		tablaClientes = new JTable(modeloClientes);
		tablaClientes.setRowHeight(24);
		tablaClientes.getTableHeader().setReorderingAllowed(false);
		tablaClientes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tablaClientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

		JScrollPane scroll = new JScrollPane(tablaClientes);
		scroll.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));

		panel.add(scroll, BorderLayout.CENTER);
		return panel;
	}

	private JPanel crearVistaEmpleados() {
		JPanel panel = new JPanel(new BorderLayout(14, 14));
		panel.setOpaque(false);

		JPanel cabecera = crearCabeceraModulo("Gestión de Empleados",
				"Consulta el personal registrado y filtra por tipo de empleado.");

		JPanel panelFiltro = new JPanel(new BorderLayout(8, 8));
		panelFiltro.setOpaque(false);

		JLabel lblFiltro = new JLabel("Filtrar por tipo:");
		lblFiltro.setFont(FUENTE_LABEL);

		cmbFiltroEmpleados = new JComboBox<>(new String[] { "Todos", "Trabajador", "Administrativo", "Comercial" });
		cmbFiltroEmpleados.setFont(FUENTE_LABEL);
		cmbFiltroEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTablaEmpleados();
			}
		});

		panelFiltro.add(lblFiltro, BorderLayout.WEST);
		panelFiltro.add(cmbFiltroEmpleados, BorderLayout.CENTER);

		cabecera.add(panelFiltro, BorderLayout.EAST);
		panel.add(cabecera, BorderLayout.NORTH);

		modeloEmpleados = new DefaultTableModel();
		modeloEmpleados.setColumnIdentifiers(new String[] { "Código", "Nombre", "Cédula", "Teléfono", "Dirección",
				"Tipo", "Salario", "Fecha Ingreso", "Detalle" });

		tablaEmpleados = new JTable(modeloEmpleados);
		tablaEmpleados.setRowHeight(24);
		tablaEmpleados.getTableHeader().setReorderingAllowed(false);
		tablaEmpleados.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tablaEmpleados.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

		JScrollPane scroll = new JScrollPane(tablaEmpleados);
		scroll.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));

		panel.add(scroll, BorderLayout.CENTER);
		return panel;
	}

	private JPanel crearVistaFacturas() {
		JPanel panel = new JPanel(new BorderLayout(14, 14));
		panel.setOpaque(false);

		String tituloModulo = esCliente() ? "Mis Facturas" : "Facturación";
		String subtitulo = esCliente() ? "Consulta únicamente las facturas asociadas a tu cuenta."
				: "Consulta las facturas generadas y filtra por estado.";

		JPanel cabecera = crearCabeceraModulo(tituloModulo, subtitulo);

		JPanel panelFiltro = new JPanel(new BorderLayout(8, 8));
		panelFiltro.setOpaque(false);

		JLabel lblFiltro = new JLabel("Filtrar por estado:");
		lblFiltro.setFont(FUENTE_LABEL);

		cmbFiltroFacturas = new JComboBox<>(new String[] { "Todas", "Pendiente", "Pagada", "Vencida" });
		cmbFiltroFacturas.setFont(FUENTE_LABEL);
		cmbFiltroFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTablaFacturas();
			}
		});

		panelFiltro.add(lblFiltro, BorderLayout.WEST);
		panelFiltro.add(cmbFiltroFacturas, BorderLayout.CENTER);

		cabecera.add(panelFiltro, BorderLayout.EAST);
		panel.add(cabecera, BorderLayout.NORTH);

		modeloFacturas = new DefaultTableModel();
		modeloFacturas.setColumnIdentifiers(
				new String[] { "Factura", "Fecha", "Estado", "Contrato", "Cliente", "Plan", "Monto" });

		tablaFacturas = new JTable(modeloFacturas);
		tablaFacturas.setRowHeight(24);
		tablaFacturas.getTableHeader().setReorderingAllowed(false);
		tablaFacturas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tablaFacturas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

		JScrollPane scroll = new JScrollPane(tablaFacturas);
		scroll.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));

		panel.add(scroll, BorderLayout.CENTER);
		return panel;
	}

	private JPanel crearCabeceraModulo(String tituloTexto, String subtituloTexto) {
		JPanel cabecera = new JPanel(new BorderLayout(10, 10));
		cabecera.setBackground(COLOR_PANEL);
		cabecera.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(COLOR_BORDE),
				new EmptyBorder(16, 16, 16, 16)));

		JLabel titulo = new JLabel(tituloTexto);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		titulo.setForeground(COLOR_TEXTO);

		JLabel subtitulo = new JLabel(subtituloTexto);
		subtitulo.setFont(FUENTE_SUBTITULO);
		subtitulo.setForeground(COLOR_SECUNDARIO);

		JPanel textos = new JPanel(new BorderLayout());
		textos.setOpaque(false);
		textos.add(titulo, BorderLayout.NORTH);
		textos.add(subtitulo, BorderLayout.SOUTH);

		cabecera.add(textos, BorderLayout.WEST);
		return cabecera;
	}

	private JButton crearBotonMenu(String texto, final String vista, String iconoPath) {
		JButton btn = new JButton(texto);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setBackground(COLOR_PRIMARIO_SUAVE);
		btn.setForeground(COLOR_TEXTO);
		btn.setFont(FUENTE_BOTON);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
		btn.setIcon(cargarIcono(iconoPath, 20, 20));
		btn.setIconTextGap(10);

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarVista(vista);
			}
		});

		return btn;
	}

	private JButton crearBotonAccionDestacado(String texto, String iconoPath, ActionListener accion) {
		JButton btn = new JButton(texto);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setBackground(COLOR_PRIMARIO);
		btn.setForeground(Color.WHITE);
		btn.setFont(FUENTE_BOTON);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
		btn.setIcon(cargarIcono(iconoPath, 20, 20));
		btn.setIconTextGap(10);
		btn.addActionListener(accion);
		return btn;
	}

	private JPanel crearTarjetaAccion(String titulo, String subtitulo, String iconoPath, ActionListener accion) {
		JPanel card = new JPanel(new BorderLayout(12, 12));
		card.setBackground(COLOR_PANEL);
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(COLOR_BORDE),
				new EmptyBorder(18, 18, 18, 18)));

		JLabel icono = new JLabel(cargarIcono(iconoPath, 44, 44));
		icono.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitulo.setForeground(COLOR_TEXTO);

		JLabel lblSub = new JLabel("<html>" + subtitulo + "</html>");
		lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSub.setForeground(COLOR_SECUNDARIO);

		JButton btn = new JButton("Ejecutar");
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setBackground(COLOR_PRIMARIO_SUAVE);
		btn.setForeground(COLOR_TEXTO);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.addActionListener(accion);

		JPanel centro = new JPanel(new BorderLayout(0, 8));
		centro.setOpaque(false);
		centro.add(lblTitulo, BorderLayout.NORTH);
		centro.add(lblSub, BorderLayout.CENTER);

		card.add(icono, BorderLayout.NORTH);
		card.add(centro, BorderLayout.CENTER);
		card.add(btn, BorderLayout.SOUTH);

		return card;
	}

	private JPanel crearTarjetaDobleChat() {
		JPanel card = new JPanel(new BorderLayout(12, 12));
		card.setBackground(COLOR_PANEL);
		card.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(COLOR_BORDE),
				new EmptyBorder(18, 18, 18, 18)));

		JLabel icono = new JLabel(cargarIcono("/imagenes/usuario.png", 42, 42));
		icono.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel titulo = new JLabel("Soporte y Servidor");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		titulo.setForeground(COLOR_TEXTO);

		JLabel subtitulo = new JLabel(
				"<html>Accede al chat interno o enciende el servidor para las pruebas de sockets.</html>");
		subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		subtitulo.setForeground(COLOR_SECUNDARIO);

		JPanel centro = new JPanel(new BorderLayout(0, 8));
		centro.setOpaque(false);
		centro.add(titulo, BorderLayout.NORTH);
		centro.add(subtitulo, BorderLayout.CENTER);

		JPanel botones = new JPanel(new GridLayout(1, 2, 10, 0));
		botones.setOpaque(false);

		JButton btnServidor = new JButton("Servidor");
		btnServidor.setFocusPainted(false);
		btnServidor.setBorderPainted(false);
		btnServidor.setBackground(COLOR_PRIMARIO_SUAVE);
		btnServidor.setForeground(COLOR_TEXTO);
		btnServidor.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnServidor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LanzadorSistema sistema = new LanzadorSistema();
				sistema.setVisible(true);
			}
		});

		JButton btnSoporte = new JButton("Soporte");
		btnSoporte.setFocusPainted(false);
		btnSoporte.setBorderPainted(false);
		btnSoporte.setBackground(COLOR_PRIMARIO);
		btnSoporte.setForeground(Color.WHITE);
		btnSoporte.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSoporte.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSoporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatSoporte chat = new ChatSoporte();
				chat.setVisible(true);
			}
		});

		botones.add(btnServidor);
		botones.add(btnSoporte);

		card.add(icono, BorderLayout.NORTH);
		card.add(centro, BorderLayout.CENTER);
		card.add(botones, BorderLayout.SOUTH);

		return card;
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

	private void mostrarVista(String vista) {
		actualizarTablas();
		cardLayout.show(panelContenidoCentral, vista);
	}

	private void actualizarTablas() {
		AlticeSistema.getInstance().recalcularDeudasClientes();

		if (modeloClientes != null) {
			cargarTablaClientes();
		}
		if (modeloEmpleados != null) {
			cargarTablaEmpleados();
		}
		if (modeloFacturas != null) {
			cargarTablaFacturas();
		}
	}

	private void cargarTablaClientes() {
		modeloClientes.setRowCount(0);

		String filtro = cmbFiltroClientes != null ? cmbFiltroClientes.getSelectedItem().toString() : "Todos";
		List<Cliente> clientes = AlticeSistema.getInstance().filtrarClientesPorTipo(filtro);

		for (Cliente c : clientes) {
			modeloClientes.addRow(new Object[] { c.getId(), c.getNombre(), c.getCedula(), c.getTelefono(),
					c.getDireccion(), c.getTipoCliente(), c.getCategoriaCliente(), c.getEstado(), c.getRNC(),
					c.getMesesDeuda(), String.format("RD$ %.2f", c.getMontoDeuda()) });
		}
	}

	private void cargarTablaEmpleados() {
		modeloEmpleados.setRowCount(0);

		String filtro = cmbFiltroEmpleados != null ? cmbFiltroEmpleados.getSelectedItem().toString() : "Todos";
		List<Empleado> empleados = AlticeSistema.getInstance().filtrarEmpleadosPorTipo(filtro);

		for (Empleado e : empleados) {
			modeloEmpleados.addRow(new Object[] { e.getId(), e.getNombre(), e.getCedula(), e.getTelefono(),
					e.getDireccion(), AlticeSistema.getInstance().obtenerTipoEmpleado(e), e.getSalario(),
					e.getFechaIngreso(), obtenerDetalleEmpleado(e) });
		}
	}

	private void cargarTablaFacturas() {
		modeloFacturas.setRowCount(0);

		String filtro = cmbFiltroFacturas != null ? cmbFiltroFacturas.getSelectedItem().toString() : "Todas";
		List<Factura> facturas;

		if (esCliente()) {
			facturas = AlticeSistema.getInstance().getFacturasDeCliente(getClienteLogueado());
		} else {
			facturas = AlticeSistema.getInstance().getFacturas();
		}

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

				modeloFacturas.addRow(new Object[] { f.getIdFactura(), f.getFecha(), f.getEstado(), idContrato,
						nombreCliente, nombrePlan, String.format("RD$ %.2f", f.getMontoTotal()) });
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
		ReporteGeneralUI reporte = new ReporteGeneralUI();
		reporte.setVisible(true);
	}
}