package logica;

import java.io.Serializable;

public class Servicio implements Serializable{
	private static final long serialVersionUID = 1L;
	private String idServicio;
	private String nombre;
	private String tipo;
	private String descripcion; 
	private boolean activo;

	public Servicio(String idServicio, String nombre, String tipo, String descripcion) {
		this.idServicio = idServicio;
		this.nombre = nombre;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.activo = true;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}