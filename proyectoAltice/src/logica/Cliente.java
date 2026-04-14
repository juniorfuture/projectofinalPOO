package logica;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	private String tipoCliente;
	private String RNC;
	private String estado;
	private String genero; 

	public Cliente(String id, String nombre, String cedula, String telefono, String direccion, String tipoCliente, String estado, String rnc, String genero) {
		super(id, nombre, cedula, telefono, direccion);
		this.tipoCliente = tipoCliente;
		this.estado = estado;
		this.RNC = rnc;
		this.genero = genero; 
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


	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}