package logica;

import java.io.Serializable;
import java.util.List;

public class Reporte implements Serializable {
	private static final long serialVersionUID = 1L;
	private String idReporte;
	private String tipo;
	private String fechaInicio;
	private String fechaFin;

	private List<Factura> facturas;
	private List<Contrato> contratos;
	private List<Persona> personas;

	public Reporte(String idReporte, String tipo, String fechaInicio, String fechaFin, List<Factura> facturas,
			List<Contrato> contratos, List<Persona> personas) {
		this.idReporte = idReporte;
		this.tipo = tipo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.facturas = facturas;
		this.contratos = contratos;
		this.personas = personas;
	}

	public String getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(String idReporte) {
		this.idReporte = idReporte;
	}

	public String generarContenido() {
		int totalClientes = 0;
		int totalEmpleados = 0;
		int totalFacturas = 0;
		int facturasPendientes = 0;
		int facturasPagadas = 0;
		int facturasVencidas = 0;
		int clientesConDeuda = 0;
		double montoFacturado = 0;
		double montoPendiente = 0;

		for (Persona p : personas) {
			if (p instanceof Cliente) {
				totalClientes++;
				Cliente c = (Cliente) p;
				if (c.isDeuda()) {
					clientesConDeuda++;
				}
			}
			if (p instanceof Empleado) {
				totalEmpleados++;
			}
		}

		totalFacturas = facturas.size();

		for (Factura f : facturas) {
			montoFacturado += f.getMontoTotal();

			if (f.getEstado().equalsIgnoreCase("Pendiente")) {
				facturasPendientes++;
				montoPendiente += f.getMontoTotal();
			} else if (f.getEstado().equalsIgnoreCase("Pagada")) {
				facturasPagadas++;
			} else if (f.getEstado().equalsIgnoreCase("Vencida")) {
				facturasVencidas++;
				montoPendiente += f.getMontoTotal();
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append("=========== REPORTE GENERAL DEL SISTEMA ===========\n\n");
		sb.append("ID Reporte: ").append(idReporte).append("\n");
		sb.append("Tipo: ").append(tipo).append("\n");
		sb.append("Rango: ").append(fechaInicio).append(" - ").append(fechaFin).append("\n\n");

		sb.append("--------------- RESUMEN GENERAL -------------------\n");
		sb.append("Total de clientes registrados: ").append(totalClientes).append("\n");
		sb.append("Total de empleados registrados: ").append(totalEmpleados).append("\n");
		sb.append("Total de contratos generados: ").append(contratos.size()).append("\n");
		sb.append("Total de facturas generadas: ").append(totalFacturas).append("\n");
		sb.append("Facturas pendientes: ").append(facturasPendientes).append("\n");
		sb.append("Facturas pagadas: ").append(facturasPagadas).append("\n");
		sb.append("Facturas vencidas: ").append(facturasVencidas).append("\n");
		sb.append("Clientes con deuda: ").append(clientesConDeuda).append("\n");
		sb.append("Monto total facturado: RD$ ").append(String.format("%.2f", montoFacturado)).append("\n");
		sb.append("Monto pendiente por cobrar: RD$ ").append(String.format("%.2f", montoPendiente)).append("\n\n");

		sb.append("---------------- DETALLE DE FACTURAS --------------\n");
		if (facturas.isEmpty()) {
			sb.append("No hay facturas registradas.\n");
		} else {
			for (Factura f : facturas) {
				String cliente = "";
				String plan = "";

				if (f.getContrato() != null) {
					if (f.getContrato().getCliente() != null) {
						cliente = f.getContrato().getCliente().getNombre();
					}
					if (f.getContrato().getPlan() != null) {
						plan = f.getContrato().getPlan().getNombre();
					}
				}

				sb.append("Factura: ").append(f.getIdFactura()).append(" | Fecha: ").append(f.getFecha())
						.append(" | Cliente: ").append(cliente).append(" | Plan: ").append(plan).append(" | Estado: ")
						.append(f.getEstado()).append(" | Monto: RD$ ")
						.append(String.format("%.2f", f.getMontoTotal())).append("\n");
			}
		}

		sb.append("\n---------------- DETALLE DE CLIENTES --------------\n");
		for (Persona p : personas) {
			if (p instanceof Cliente) {
				Cliente c = (Cliente) p;
				sb.append("Cliente: ").append(c.getNombre()).append(" | Tipo: ").append(c.getTipoCliente())
						.append(" | Categoría: ").append(c.getCategoriaCliente()).append(" | Estado: ")
						.append(c.getEstado()).append(" | Deuda: RD$ ")
						.append(String.format("%.2f", c.getMontoDeuda())).append(" | Meses adeudados: ")
						.append(c.getMesesDeuda()).append("\n");
			}
		}

		return sb.toString();
	}
}