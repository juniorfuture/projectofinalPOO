package logica;

public class Administrativo extends Empleado {

	private String departamento;

	public Administrativo(String id, String nombre, String cedula, String telefono, String direccion, double salario, String fechaIngreso, String departamento) {
		super(id, nombre, cedula, telefono, direccion, salario, fechaIngreso);
		this.departamento = departamento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public double calcularSalario() {
		return getSalario();
	}
}