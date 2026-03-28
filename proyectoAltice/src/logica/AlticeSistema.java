package logica;

import java.util.ArrayList;
import java.util.List;

public class AlticeSistema {

	public static AlticeSistema sistema = null;
	public static int numCliente = 1;
	public static int numTecnico = 1;
	public static int numAdministrador = 1;
	public static int numComercial = 1;

	private ArrayList<Persona> personas;
	private List<Plan> planes;
	private List<Servicio> servicios;
	private List<Factura> facturas;

	public AlticeSistema() {
		personas = new ArrayList<>();
		planes = new ArrayList<>();
		servicios = new ArrayList<>();
		facturas = new ArrayList<>();
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

	public static AlticeSistema getInstance() {
		if (sistema == null) {
			sistema = new AlticeSistema();
		}
		return sistema;
	}

	public Persona buscarCliente(String id) {
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
}