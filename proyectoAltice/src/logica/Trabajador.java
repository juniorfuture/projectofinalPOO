package logica;

public class Trabajador extends Empleado {

	private String areaTecnica;

	public Trabajador(String id, String nombre, String cedula, String telefono, String direccion, double salario, String fechaIngreso, String areaTecnica) {
		super(id, nombre, cedula, telefono, direccion, salario, fechaIngreso);
		this.areaTecnica = areaTecnica;
	}

	public String getAreaTecnica() {
		return areaTecnica;
	}

	public void setAreaTecnica(String areaTecnica) {
		this.areaTecnica = areaTecnica;
	}

	@Override
	public double calcularSalario() {
		return getSalario();
	}
}