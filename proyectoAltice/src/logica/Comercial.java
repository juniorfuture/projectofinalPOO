package logica;

import java.io.Serializable;

public class Comercial extends Empleado implements Serializable{
	private static final long serialVersionUID = 1L;
	private double ventas;
	private String producto;

	public Comercial(String id, String nombre, String cedula, String telefono, String direccion, double salario, String fechaIngreso, double ventas, String producto) {
		super(id, nombre, cedula, telefono, direccion, salario, fechaIngreso);
		this.ventas = ventas;
		this.producto = producto;
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
	
	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	@Override
	public double calcularSalario() {
		return getSalario() + (ventas * 0.10);
	}

	
}