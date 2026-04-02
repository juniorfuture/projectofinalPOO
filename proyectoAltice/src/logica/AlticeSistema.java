package logica;

import java.util.ArrayList;
import java.util.List;

public class AlticeSistema {

	public static AlticeSistema sistema = null;
	public static int numCliente = 1;
	public static int numTecnico = 1;
	public static int numAdministrador = 1;
	public static int numComercial = 1;
	public static int numServicio = 1;
	public static int numPlan = 1;
	public static int numContrato = 1;
	public static int numFactura = 1;

	private ArrayList<Persona> personas;
	private ArrayList<Plan> planes;
	private ArrayList<Servicio> servicios;
	private ArrayList<Factura> facturas;
	private ArrayList<Contrato> contratos;

	public AlticeSistema() {
		personas = new ArrayList<>();
		planes = new ArrayList<>();
		servicios = new ArrayList<>();
		facturas = new ArrayList<>();
		contratos = new ArrayList<>();
	}

	public static AlticeSistema getInstance() {
		if (sistema == null) {
			sistema = new AlticeSistema();
		}
		return sistema;
	}

	public void registrarPersona(Persona aux) {
		personas.add(aux);

		if (aux instanceof Cliente)
			numCliente++;

		if (aux instanceof Trabajador)
			numTecnico++;

		if (aux instanceof Comercial)
			numComercial++;

		if (aux instanceof Administrativo)
			numAdministrador++;
	}

	public void registrarServicio(Servicio aux) {
		servicios.add(aux);
		numServicio++;
	}

	public void registrarPlan(Plan aux) {
		planes.add(aux);
		numPlan++;
	}

	public void registrarFactura(Factura aux) {
		facturas.add(aux);
		numFactura++;
	}

	public void registrarContrato(Contrato nuevoContrato) {
		contratos.add(nuevoContrato);
		numContrato++;
	}

	public Persona buscarPersona(String id) {
		for (Persona c : personas) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public List<Cliente> getClientes() {
		List<Cliente> clientes = new ArrayList<>();

		for (Persona p : personas) {
			if (p instanceof Cliente) {
				clientes.add((Cliente) p);
			}
		}
		return clientes;
	}

	public List<Empleado> getEmpleados() {
		List<Empleado> empleados = new ArrayList<>();

		for (Persona p : personas) {
			if (p instanceof Empleado) {
				empleados.add((Empleado) p);
			}
		}
		return empleados;
	}

	public List<Cliente> filtrarClientesPorTipo(String tipoCliente) {
		List<Cliente> clientesFiltrados = new ArrayList<>();

		for (Cliente c : getClientes()) {
			if (tipoCliente.equalsIgnoreCase("Todos") || c.getTipoCliente().equalsIgnoreCase(tipoCliente)) {
				clientesFiltrados.add(c);
			}
		}
		return clientesFiltrados;
	}

	public List<Empleado> filtrarEmpleadosPorTipo(String tipoEmpleado) {
		List<Empleado> empleadosFiltrados = new ArrayList<>();

		for (Empleado e : getEmpleados()) {
			if (tipoEmpleado.equalsIgnoreCase("Todos") || obtenerTipoEmpleado(e).equalsIgnoreCase(tipoEmpleado)) {
				empleadosFiltrados.add(e);
			}
		}
		return empleadosFiltrados;
	}

	public String obtenerTipoEmpleado(Empleado e) {
		if (e instanceof Trabajador) {
			return "Trabajador";
		}
		if (e instanceof Administrativo) {
			return "Administrativo";
		}
		if (e instanceof Comercial) {
			return "Comercial";
		}
		return "Empleado";
	}

	public Plan buscarPlanPorNombre(String nombre) {
		for (Plan c : planes) {
			if (c.getNombre().equals(nombre)) {
				return c;
			}
		}
		return null;
	}

	public ArrayList<Servicio> getServiciosDisponibles() {
		ArrayList<Servicio> disponibles = new ArrayList<>();
		for (Servicio temp : servicios) {
			if (temp.isActivo()) {
				disponibles.add(temp);
			}
		}
		return disponibles;
	}

	public String[] getNombresPlanesDisponibles() {
		String[] nombres = new String[planes.size() + 1];
		nombres[0] = "<Seleccione>";
		for (int i = 0; i < planes.size(); i++) {
			nombres[i + 1] = planes.get(i).getNombre();
		}
		return nombres;
	}

	public Persona buscarClientePorNombre(String nombreSeleccionado) {
		for (Persona c : personas) {
			if (c.getNombre().equals(nombreSeleccionado)) {
				return c;
			}
		}
		return null;
	}

	public Reporte generarReporteGeneral() {
		return new Reporte("REP-1", "General", "N/A", "N/A", facturas, contratos, personas);
	}
}