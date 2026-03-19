package logica;

public class Servicio {

	private String idServicio;
	private String nombre;
	private String tipo;
	private double costo;
	private boolean activo;

	public Servicio(String idServicio, String nombre, String tipo, double costo) {
		this.idServicio = idServicio;
		this.nombre = nombre;
		this.tipo = tipo;
		this.costo = costo;
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

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void activarServicio() {
		this.activo = true;
	}

	public void desactivarServicio() {
		this.activo = false;
	}
}