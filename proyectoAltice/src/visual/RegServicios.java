package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import logica.Servicio;

public class RegServicios extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JTextField txtIdServicio;
	private JTextField txtNombre;
	private JComboBox<String> cmbTipo;
	private JTextArea txtDescripcion;

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_PRIMARIO = new Color(31, 111, 235);
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(220, 225, 230);

	public static void main(String[] args) {
		try {
			RegServicios dialog = new RegServicios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegServicios() {
		setTitle("Registro de Servicios");
		setBounds(100, 100, 760, 740);
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
		icono.setPreferredSize(new java.awt.Dimension(70, 70));
		icono.setIcon(cargarIcono("/imagenes/service.jpg", 48, 48));
		header.add(icono, BorderLayout.WEST);

		JPanel textos = new JPanel(new BorderLayout(0, 4));
		textos.setOpaque(false);

		JLabel titulo = new JLabel("Registrar Servicio");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setForeground(COLOR_TEXTO);

		JLabel subtitulo = new JLabel("Complete los datos del servicio para agregarlo al sistema.");
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

		JPanel panelFormulario = new JPanel(null);
		panelFormulario.setBackground(COLOR_PANEL);
		panelFormulario.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(10, 10, 10, 10)));

		JLabel lblDatos = new JLabel("Datos del servicio");
		lblDatos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDatos.setForeground(COLOR_TEXTO);
		lblDatos.setBounds(25, 20, 180, 25);
		panelFormulario.add(lblDatos);

		JLabel lblId = new JLabel("ID Servicio");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblId.setForeground(COLOR_TEXTO);
		lblId.setBounds(25, 70, 120, 20);
		panelFormulario.add(lblId);

		txtIdServicio = crearTextField();
		txtIdServicio.setText("SRV-" + AlticeSistema.numServicio);
		txtIdServicio.setEditable(false);
		txtIdServicio.setBounds(25, 95, 180, 40);
		panelFormulario.add(txtIdServicio);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNombre.setForeground(COLOR_TEXTO);
		lblNombre.setBounds(25, 160, 120, 20);
		panelFormulario.add(lblNombre);

		txtNombre = crearTextField();
		txtNombre.setBounds(25, 185, 300, 40);
		panelFormulario.add(txtNombre);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTipo.setForeground(COLOR_TEXTO);
		lblTipo.setBounds(360, 160, 120, 20);
		panelFormulario.add(lblTipo);

		cmbTipo = new JComboBox<String>();
		cmbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbTipo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "<Seleccione>", "Internet", "Cable", "Telefonía", "Mantenimiento" }));
		cmbTipo.setBounds(360, 185, 250, 40);
		panelFormulario.add(cmbTipo);

		JLabel lblDesc = new JLabel("Descripción");
		lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDesc.setForeground(COLOR_TEXTO);
		lblDesc.setBounds(25, 255, 120, 20);
		panelFormulario.add(lblDesc);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 280, 585, 140);
		scrollPane.setBorder(new LineBorder(COLOR_BORDE, 1, true));
		panelFormulario.add(scrollPane);

		txtDescripcion = new JTextArea();
		txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		txtDescripcion.setBorder(new EmptyBorder(10, 10, 10, 10));
		scrollPane.setViewportView(txtDescripcion);

		contenedor.add(panelFormulario, BorderLayout.CENTER);
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
		btnCancelar.setPreferredSize(new java.awt.Dimension(120, 40));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(btnCancelar);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setBorderPainted(false);
		btnRegistrar.setBackground(COLOR_PRIMARIO);
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRegistrar.setPreferredSize(new java.awt.Dimension(130, 40));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarServicio();
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

	private void registrarServicio() {
		if (validar()) {
			String id = txtIdServicio.getText();
			String nombre = txtNombre.getText();
			String tipo = cmbTipo.getSelectedItem().toString();
			String desc = txtDescripcion.getText();

			Servicio nuevo = new Servicio(id, nombre, tipo, desc);
			AlticeSistema.getInstance().registrarServicio(nuevo);

			JOptionPane.showMessageDialog(null, "Servicio registrado con éxito.");
			clean();
			dispose();
		}
	}

	private boolean validar() {
		if (txtNombre.getText().trim().isEmpty() || txtDescripcion.getText().trim().isEmpty()
				|| cmbTipo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
			return false;
		}
		return true;
	}

	private void clean() {
		txtNombre.setText("");
		txtDescripcion.setText("");
		cmbTipo.setSelectedIndex(0);
		txtIdServicio.setText("SRV-" + AlticeSistema.numServicio);
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