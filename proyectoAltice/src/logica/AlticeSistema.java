package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlticeSistema implements Serializable {

	private static final long serialVersionUID = 1L;

	public static AlticeSistema sistema = null;
	public static int numCliente = 1;
	public static int numTecnico = 1;
	public static int numAdministrador = 1;
	public static int numComercial = 1;
	public static int numServicio = 1;
	public static int numPlan = 1;
	public static int numContrato = 1;
	public static int numFactura = 1;
	public static int numPago = 1;

	private ArrayList<Persona> personas;
	private ArrayList<Plan> planes;
	private ArrayList<Servicio> servicios;
	private ArrayList<Factura> facturas;
	private ArrayList<Contrato> contratos;
	private ArrayList<User> usuarios;
	private User usuarioLogueado = null;

	public AlticeSistema() {
		super();
		personas = new ArrayList<>();
		planes = new ArrayList<>();
		servicios = new ArrayList<>();
		facturas = new ArrayList<>();
		contratos = new ArrayList<>();
		usuarios = new ArrayList<>();
	}

	public static AlticeSistema getInstance() {
		if (sistema == null) {
			sistema = new AlticeSistema();
		}
		return sistema;
	}

	public static void setSistema(AlticeSistema temp) {
		AlticeSistema.sistema = temp;
	}

	public boolean regUser(User aux) {
		if (usuarios == null) {
			usuarios = new ArrayList<>();
		}
		if (aux == null || aux.getUsername() == null || aux.getUsername().trim().isEmpty()) {
			return false;
		}
		for (User u : usuarios) {
			if (u.getUsername() != null && u.getUsername().equalsIgnoreCase(aux.getUsername())) {
				return false;
			}
		}
		usuarios.add(aux);
		return true;
	}

	public boolean tieneUsuarios() {
		return usuarios != null && !usuarios.isEmpty();
	}

	public List<User> getUsuarios() {
		return usuarios;
	}

	public User buscarUsuarioPorUsername(String username) {
		if (usuarios == null) {
			return null;
		}
		for (User u : usuarios) {
			if (u.getUsername() != null && u.getUsername().equalsIgnoreCase(username)) {
				return u;
			}
		}
		return null;
	}

	public boolean confirmLogin(String username, String password) {
		if (usuarios == null) {
			usuarios = new ArrayList<>();
		}
		for (User u : usuarios) {
			if (u.getUsername() != null && u.getUsername().equals(username) && u.getPassword() != null
					&& u.getPassword().equals(password)) {
				usuarioLogueado = u;
				return true;
			}
		}
		return false;
	}

	public User getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(User usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public boolean esUsuarioCliente() {
		return usuarioLogueado != null && usuarioLogueado.getTipo() != null
				&& usuarioLogueado.getTipo().equalsIgnoreCase("Cliente");
	}

	public Cliente getClienteDelUsuarioLogueado() {
		if (!esUsuarioCliente()) {
			return null;
		}
		return buscarClientePorId(usuarioLogueado.getIdRelacionado());
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

	public Cliente buscarClientePorId(String id) {
		for (Persona p : personas) {
			if (p instanceof Cliente && p.getId().equalsIgnoreCase(id)) {
				return (Cliente) p;
			}
		}
		return null;
	}

	public Persona buscarClientePorNombre(String nombreSeleccionado) {
		for (Persona c : personas) {
			if (c instanceof Cliente && c.getNombre().equals(nombreSeleccionado)) {
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
		if (e instanceof Trabajador)
			return "Trabajador";
		if (e instanceof Administrativo)
			return "Administrativo";
		if (e instanceof Comercial)
			return "Comercial";
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

	public String[] getNombresComercialesDisponibles() {
		ArrayList<String> nombresComerciales = new ArrayList<>();
		nombresComerciales.add("<Seleccione Comercial>");
		for (Persona p : personas) {
			if (p instanceof Comercial) {
				nombresComerciales.add(p.getNombre());
			}
		}
		return nombresComerciales.toArray(new String[0]);
	}

	public Comercial buscarComercialPorNombre(String nombreBuscado) {
		for (Persona p : personas) {
			if (p instanceof Comercial) {
				if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
					return (Comercial) p;
				}
			}
		}
		return null;
	}

	public boolean hayTecnicosDisponibles() {
		for (Persona p : personas) {
			if (p instanceof Trabajador) {
				return true;
			}
		}
		return false;
	}

	public void recalcularDeudaCliente(Cliente cliente) {
		if (cliente == null) {
			return;
		}
		int meses = 0;
		double monto = 0;

		for (Factura f : facturas) {
			if (f.getContrato() != null && f.getContrato().getCliente() != null
					&& f.getContrato().getCliente().getId().equalsIgnoreCase(cliente.getId())) {

				if (f.getEstado().equalsIgnoreCase("Pendiente") || f.getEstado().equalsIgnoreCase("Vencida")) {
					meses++;
					monto += f.getMontoTotal();
				}
			}
		}

		cliente.setMesesDeuda(meses);
		cliente.setMontoDeuda(monto);
		cliente.setDeuda(monto > 0);
	}

	public void recalcularDeudasClientes() {
		for (Cliente c : getClientes()) {
			recalcularDeudaCliente(c);
		}
	}

	public List<Factura> getFacturasDeCliente(Cliente cliente) {
		List<Factura> resultado = new ArrayList<>();
		if (cliente == null) {
			return resultado;
		}
		for (Factura f : facturas) {
			if (f.getContrato() != null && f.getContrato().getCliente() != null
					&& f.getContrato().getCliente().getId().equalsIgnoreCase(cliente.getId())) {
				resultado.add(f);
			}
		}
		return resultado;
	}

	public List<Factura> getFacturasPendientesDeCliente(Cliente cliente) {
		List<Factura> resultado = new ArrayList<>();
		if (cliente == null) {
			return resultado;
		}
		for (Factura f : getFacturasDeCliente(cliente)) {
			if (f.getEstado().equalsIgnoreCase("Pendiente") || f.getEstado().equalsIgnoreCase("Vencida")) {
				resultado.add(f);
			}
		}
		return resultado;
	}

	public Pago procesarPagoFactura(Factura factura, String metodo, String fechaPago) {
		if (factura == null) {
			return null;
		}
		if (factura.getEstado().equalsIgnoreCase("Pagada")) {
			return null;
		}

		Pago pago = new Pago("PAG-" + numPago, fechaPago, factura.getMontoTotal(), metodo);
		if (pago.procesarPago()) {
			factura.setEstado("Pagada");
			numPago++;

			if (factura.getContrato() != null && factura.getContrato().getCliente() != null) {
				recalcularDeudaCliente(factura.getContrato().getCliente());
			}
			return pago;
		}
		return null;
	}

	public int ejecutarCortesAutomaticos() {
		int contratosSuspendidos = 0;
		for (Contrato c : contratos) {
			if (c.getEstado().equalsIgnoreCase("Activo")) {
				int vencidas = 0;
				for (Factura f : facturas) {
					if (f.getContrato() != null && f.getContrato().getIdContrato().equals(c.getIdContrato())) {
						if (f.getEstado().equalsIgnoreCase("Vencida")) {
							vencidas++;
						}
					}
				}
				if (vencidas > 2) {
					c.setEstado("Suspendido por Falta de Pago");
					contratosSuspendidos++;
				}
			}
		}
		return contratosSuspendidos;
	}

	public int[] obtenerEstadisticasFacturas() {
		int pagadas = 0;
		int pendientes = 0;
		int vencidas = 0;

		for (Factura f : facturas) {
			if (f.getEstado().equalsIgnoreCase("Pagada")) {
				pagadas++;
			} else if (f.getEstado().equalsIgnoreCase("Pendiente")) {
				pendientes++;
			} else if (f.getEstado().equalsIgnoreCase("Vencida")) {
				vencidas++;
			}
		}
		return new int[] { pagadas, pendientes, vencidas };
	}

	public Map<String, Integer> obtenerVentasPorPlan() {
		Map<String, Integer> ventas = new HashMap<>();
		for (Contrato c : contratos) {
			if (c.getEstado().equalsIgnoreCase("Activo") && c.getPlan() != null) {
				String nombrePlan = c.getPlan().getNombre();
				ventas.put(nombrePlan, ventas.getOrDefault(nombrePlan, 0) + 1);
			}
		}
		return ventas;
	}

	public Reporte generarReporteGeneral() {
		return new Reporte("REP-1", "General", "N/A", "N/A", facturas, contratos, personas);
	}
}