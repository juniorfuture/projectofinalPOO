package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Plan implements Serializable{
	private static final long serialVersionUID = 1L;
	private String idPlan;
	private String nombre;
	private String tipo;
	private String descripcion;
	private double precio; 
	private List<Servicio> serviciosIncluidos;

	public Plan(String idPlan, String nombre, String tipo, String descripcion, double precio) {
		super();
		this.idPlan = idPlan;
		this.nombre = nombre;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.serviciosIncluidos = new ArrayList<>();
	}

	public void agregarServicio(Servicio s) {
		if (s != null) {
			serviciosIncluidos.add(s);
		}
	}

	public String getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Servicio> getServiciosIncluidos() {
		return serviciosIncluidos;
	}

	public void setServiciosIncluidos(List<Servicio> serviciosIncluidos) {
		this.serviciosIncluidos = serviciosIncluidos;
	}
}