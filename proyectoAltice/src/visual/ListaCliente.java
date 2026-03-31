package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.AlticeSistema;
import logica.Cliente;
import logica.Persona;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

public class ListaCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Cliente clienteSeleccionado = null;

	public ListaCliente() {
		setTitle("Listado General de Clientes");
		setBounds(100, 100, 600, 450);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPanel.add(scrollPane, BorderLayout.CENTER);

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
		scrollPane.setViewportView(table);

		cargarDatos();

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if (fila >= 0) {
					String id = table.getValueAt(fila, 0).toString();
					clienteSeleccionado = (Cliente) AlticeSistema.getInstance().buscarPersona(id);
					dispose();
				}
			}
		});
		buttonPane.add(btnSeleccionar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clienteSeleccionado = null;
				dispose();
			}
		});
		buttonPane.add(btnCancelar);
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

	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}
}