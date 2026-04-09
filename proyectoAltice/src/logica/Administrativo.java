package logica;

import java.io.Serializable;

public class Administrativo extends Empleado implements Serializable{
	private static final long serialVersionUID = 1L;
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