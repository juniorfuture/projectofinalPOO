package logica;

public abstract class Empleado extends Persona {

	private double salario;
	private String fechaIngreso;

	public Empleado(String id, String nombre, String cedula, String telefono, String direccion, double salario, String fechaIngreso) {
		super(id, nombre, cedula, telefono, direccion);
		this.salario = salario;
		this.fechaIngreso = fechaIngreso;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public abstract double calcularSalario();
}