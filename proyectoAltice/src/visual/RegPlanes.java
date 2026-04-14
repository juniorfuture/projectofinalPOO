package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_PRIMARIO = new Color(31, 111, 235);
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(220, 225, 230);

	public RegPlanes() {
		setTitle("Registro de Planes");
		setBounds(100, 100, 980, 900);
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

		JLabel icono = new JLabel();
		icono.setHorizontalAlignment(SwingConstants.CENTER);
		icono.setPreferredSize(new Dimension(70, 70));
		icono.setIcon(cargarIcono("/imagenes/plan.png", 46, 46));
		header.add(icono, BorderLayout.WEST);

		JPanel textos = new JPanel(new BorderLayout(0, 4));
		textos.setOpaque(false);

		JLabel titulo = new JLabel("Registrar Plan");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setForeground(COLOR_TEXTO);

		JLabel subtitulo = new JLabel("Configure el plan, seleccione los servicios y asigne precios individuales.");
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

		JPanel panelSuperior = new JPanel(new BorderLayout(18, 0));
		panelSuperior.setOpaque(false);

		JPanel panelFormulario = new JPanel(null);
		panelFormulario.setPreferredSize(new Dimension(500, 430));
		panelFormulario.setBackground(COLOR_PANEL);
		panelFormulario.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(10, 10, 10, 10)));

		JLabel lblDatos = new JLabel("Datos del plan");
		lblDatos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDatos.setForeground(COLOR_TEXTO);
		lblDatos.setBounds(25, 20, 180, 25);
		panelFormulario.add(lblDatos);

		JLabel lblId = new JLabel("ID Plan");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblId.setBounds(25, 65, 120, 20);
		panelFormulario.add(lblId);

		txtIdPlan = crearTextField();
		txtIdPlan.setText("PLN-" + AlticeSistema.numPlan);
		txtIdPlan.setEditable(false);
		txtIdPlan.setBounds(25, 88, 160, 38);
		panelFormulario.add(txtIdPlan);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNombre.setBounds(25, 145, 120, 20);
		panelFormulario.add(lblNombre);

		txtNombre = crearTextField();
		txtNombre.setBounds(25, 168, 320, 38);
		panelFormulario.add(txtNombre);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTipo.setBounds(25, 225, 120, 20);
		panelFormulario.add(lblTipo);

		cmbTipo = new JComboBox<String>();
		cmbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbTipo.setModel(
				new DefaultComboBoxModel<String>(new String[] { "<Seleccione>", "Hogar", "Móvil", "Empresarial" }));
		cmbTipo.setBounds(25, 248, 200, 38);
		panelFormulario.add(cmbTipo);

		JLabel lblDesc = new JLabel("Descripción");
		lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDesc.setBounds(25, 305, 120, 20);
		panelFormulario.add(lblDesc);

		JScrollPane scrollDesc = new JScrollPane();
		scrollDesc.setBounds(25, 330, 320, 90);
		scrollDesc.setBorder(new LineBorder(COLOR_BORDE, 1, true));
		panelFormulario.add(scrollDesc);

		txtDescripcion = new JTextArea();
		txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		txtDescripcion.setBorder(new EmptyBorder(10, 10, 10, 10));
		scrollDesc.setViewportView(txtDescripcion);

		JPanel panelInfo = new JPanel(new BorderLayout());
		panelInfo.setBackground(new Color(245, 248, 255));
		panelInfo.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(210, 220, 235), 1, true),
				new EmptyBorder(14, 14, 14, 14)));
		panelInfo.setPreferredSize(new Dimension(320, 0));

		JLabel lblInfoTitulo = new JLabel("Precio total del plan");
		lblInfoTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblInfoTitulo.setForeground(COLOR_SECUNDARIO);

		lblPrecioAcumulado = new JLabel("RD$ 0.00");
		lblPrecioAcumulado.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioAcumulado.setForeground(COLOR_PRIMARIO);
		lblPrecioAcumulado.setFont(new Font("Segoe UI", Font.BOLD, 30));

		panelInfo.add(lblInfoTitulo, BorderLayout.NORTH);
		panelInfo.add(lblPrecioAcumulado, BorderLayout.CENTER);

		panelSuperior.add(panelFormulario, BorderLayout.CENTER);
		panelSuperior.add(panelInfo, BorderLayout.EAST);

		JPanel panelServicios = new JPanel(new BorderLayout());
		panelServicios.setBackground(COLOR_PANEL);
		panelServicios.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(16, 16, 16, 16)));

		JLabel lblServicios = new JLabel("Servicios disponibles");
		lblServicios.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblServicios.setForeground(COLOR_TEXTO);

		JLabel lblSubServicios = new JLabel("Marque cada servicio que desee incluir y asigne su precio.");
		lblSubServicios.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSubServicios.setForeground(COLOR_SECUNDARIO);

		JPanel encabezadoServicios = new JPanel(new BorderLayout());
		encabezadoServicios.setOpaque(false);
		encabezadoServicios.add(lblServicios, BorderLayout.NORTH);
		encabezadoServicios.add(lblSubServicios, BorderLayout.SOUTH);

		panelServicios.add(encabezadoServicios, BorderLayout.NORTH);

		panelContenedorServicios = new JPanel();
		panelContenedorServicios.setOpaque(false);
		panelContenedorServicios.setLayout(new BoxLayout(panelContenedorServicios, BoxLayout.Y_AXIS));

		JScrollPane scrollServicios = new JScrollPane(panelContenedorServicios);
		scrollServicios.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelServicios.add(scrollServicios, BorderLayout.CENTER);

		cargarListaServiciosDinamica();

		contenedor.add(panelSuperior, BorderLayout.NORTH);
		contenedor.add(panelServicios, BorderLayout.CENTER);

		return contenedor;
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
		btnCancelar.setPreferredSize(new Dimension(120, 40));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(btnCancelar);

		JButton btnGuardar = new JButton("Registrar Plan");
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBorderPainted(false);
		btnGuardar.setBackground(COLOR_PRIMARIO);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnGuardar.setPreferredSize(new Dimension(150, 40));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarPlan();
			}
		});
		buttonPane.add(btnGuardar);
		getRootPane().setDefaultButton(btnGuardar);

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

	private void cargarListaServiciosDinamica() {
		serviciosDisponibles = AlticeSistema.getInstance().getServiciosDisponibles();

		for (final Servicio s : serviciosDisponibles) {
			JPanel filaServicio = new JPanel(new BorderLayout(12, 0));
			filaServicio.setOpaque(false);
			filaServicio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
			filaServicio.setBorder(BorderFactory.createCompoundBorder(
					new LineBorder(new Color(235, 238, 242), 1, true),
					new EmptyBorder(8, 12, 8, 12)));

			JCheckBox chk = new JCheckBox(s.getNombre());
			chk.setFont(new Font("Segoe UI", Font.PLAIN, 13));
			chk.setOpaque(false);

			JPanel panelDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
			panelDerecho.setOpaque(false);

			JLabel lblPrecio = new JLabel("RD$");
			lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 13));

			JTextField txtPrecioIndividual = crearTextField();
			txtPrecioIndividual.setPreferredSize(new Dimension(110, 32));
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

			panelDerecho.add(lblPrecio);
			panelDerecho.add(txtPrecioIndividual);

			filaServicio.add(chk, BorderLayout.CENTER);
			filaServicio.add(panelDerecho, BorderLayout.EAST);

			listaChecks.add(chk);
			listaPrecios.add(txtPrecioIndividual);
			panelContenedorServicios.add(filaServicio);
			panelContenedorServicios.add(new JPanel() {{
				setOpaque(false);
				setMaximumSize(new Dimension(Integer.MAX_VALUE, 8));
			}});
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