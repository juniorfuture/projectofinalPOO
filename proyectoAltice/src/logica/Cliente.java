package logica;

public class Cliente extends Persona {

	private String tipoCliente;
	private String RNC;
	private String estado;

	public Cliente(String id, String nombre, String cedula, String telefono, String direccion, String tipoCliente, String estado, String rnc) {
		super(id, nombre, cedula, telefono, direccion);
		this.tipoCliente = tipoCliente;
		this.estado = estado;
		this.RNC= rnc;
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

	public String getRNC() {
		return RNC;
	}

	public void setRNC(String rNC) {
		RNC = rNC;
	}

}