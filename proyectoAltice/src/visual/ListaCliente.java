package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import logica.AlticeSistema;
import logica.Cliente;
import logica.Persona;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListaCliente extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private String nombreSeleccionado = "";

	private final Color COLOR_FONDO = new Color(245, 247, 250);
	private final Color COLOR_PANEL = Color.WHITE;
	private final Color COLOR_PRIMARIO = new Color(31, 111, 235);
	private final Color COLOR_TEXTO = new Color(33, 37, 41);
	private final Color COLOR_SECUNDARIO = new Color(108, 117, 125);
	private final Color COLOR_BORDE = new Color(220, 225, 230);

	public ListaCliente() {
		setTitle("Listado de Clientes");
		setBounds(100, 100, 860, 540);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(COLOR_FONDO);

		contentPanel.setBackground(COLOR_FONDO);
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPanel.setLayout(new BorderLayout(18, 18));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		contentPanel.add(crearHeader(), BorderLayout.NORTH);
		contentPanel.add(crearTabla(), BorderLayout.CENTER);
		contentPanel.add(crearBotonera(), BorderLayout.SOUTH);

		cargarDatos();
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
		icono.setIcon(cargarIcono("/imagenes/iconcliente.png", 46, 46));
		header.add(icono, BorderLayout.WEST);

		JPanel textos = new JPanel(new BorderLayout(0, 4));
		textos.setOpaque(false);

		JLabel titulo = new JLabel("Seleccionar Cliente");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setForeground(COLOR_TEXTO);

		JLabel subtitulo = new JLabel("Seleccione un cliente del listado para continuar con la operación.");
		subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		subtitulo.setForeground(COLOR_SECUNDARIO);

		textos.add(titulo, BorderLayout.NORTH);
		textos.add(subtitulo, BorderLayout.CENTER);

		header.add(textos, BorderLayout.CENTER);
		return header;
	}

	private JPanel crearTabla() {
		JPanel panelTabla = new JPanel(new BorderLayout());
		panelTabla.setBackground(COLOR_PANEL);
		panelTabla.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(COLOR_BORDE, 1, true),
				new EmptyBorder(16, 16, 16, 16)));

		JLabel lblTabla = new JLabel("Clientes registrados");
		lblTabla.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTabla.setForeground(COLOR_TEXTO);
		lblTabla.setBorder(new EmptyBorder(0, 0, 12, 0));
		panelTabla.add(lblTabla, BorderLayout.NORTH);

		String[] columnas = { "ID", "Cédula", "Nombre", "Teléfono", "Dirección" };
		model = new DefaultTableModel(null, columnas) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(26);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		table.setGridColor(new Color(235, 235, 235));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new LineBorder(COLOR_BORDE, 1, true));
		panelTabla.add(scrollPane, BorderLayout.CENTER);

		return panelTabla;
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
				nombreSeleccionado = "";
				dispose();
			}
		});
		buttonPane.add(btnCancelar);

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setFocusPainted(false);
		btnSeleccionar.setBorderPainted(false);
		btnSeleccionar.setBackground(COLOR_PRIMARIO);
		btnSeleccionar.setForeground(Color.WHITE);
		btnSeleccionar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSeleccionar.setPreferredSize(new java.awt.Dimension(140, 40));
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarCliente();
			}
		});
		buttonPane.add(btnSeleccionar);
		getRootPane().setDefaultButton(btnSeleccionar);

		return buttonPane;
	}

	private void seleccionarCliente() {
		int fila = table.getSelectedRow();
		if (fila >= 0) {
			nombreSeleccionado = table.getValueAt(fila, 2).toString();
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"Seleccione un cliente de la tabla para continuar.",
					"Atención",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void cargarDatos() {
		model.setRowCount(0);
		for (Persona p : AlticeSistema.getInstance().getPersonas()) {
			if (p instanceof Cliente) {
				Cliente c = (Cliente) p;
				Object[] fila = {
						c.getId(),
						c.getCedula(),
						c.getNombre(),
						c.getTelefono(),
						c.getDireccion()
				};
				model.addRow(fila);
			}
		}
	}

	public String getNombreSeleccionado() {
		return nombreSeleccionado;
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