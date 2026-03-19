package logica;

public class Comercial extends Empleado {

	private double ventas;

	public Comercial(String id, String nombre, String cedula, String telefono, String direccion, double salario, String fechaIngreso, double ventas) {
		super(id, nombre, cedula, telefono, direccion, salario, fechaIngreso);
		this.ventas = ventas;
	}

	public double getVentas() {
		return ventas;
	}

	public void setVentas(double ventas) {
		this.ventas = ventas;
	}

	public void registrarVenta(double monto) {
		this.ventas += monto;
	}

	@Override
	public double calcularSalario() {
		return getSalario() + (ventas * 0.10);
	}
}