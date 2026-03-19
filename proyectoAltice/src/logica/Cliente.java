package logica;

public class Cliente extends Persona {

	private String codigoCliente;
	private String tipoCliente;
	private String estado;

	public Cliente(String id, String nombre, String cedula, String telefono, String direccion, String codigoCliente, String tipoCliente, String estado) {
		super(id, nombre, cedula, telefono, direccion);
		this.codigoCliente = codigoCliente;
		this.tipoCliente = tipoCliente;
		this.estado = estado;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}