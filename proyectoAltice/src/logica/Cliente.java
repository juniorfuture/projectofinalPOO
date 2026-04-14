package logica;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tipoCliente;
	private String estado;
	private String RNC;
	private String genero;
	private String categoriaCliente;
	private int mesesDeuda;
	private double montoDeuda;
	private boolean deuda;

	public Cliente(String id, String nombre, String cedula, String telefono, String direccion, String tipoCliente,
			String estado, String rnc, String genero) {
		super(id, nombre, cedula, telefono, direccion);
		this.tipoCliente = tipoCliente;
		this.estado = estado;
		this.RNC = rnc;
		this.genero = genero;
		this.categoriaCliente = "Fisico";
		this.mesesDeuda = 0;
		this.montoDeuda = 0;
		this.deuda = false;
	}

	public Cliente(String id, String nombre, String cedula, String telefono, String direccion, String tipoCliente,
			String estado, String rnc, String genero, String categoriaCliente) {
		super(id, nombre, cedula, telefono, direccion);
		this.tipoCliente = tipoCliente;
		this.estado = estado;
		this.RNC = rnc;
		this.genero = genero;
		this.categoriaCliente = categoriaCliente;
		this.mesesDeuda = 0;
		this.montoDeuda = 0;
		this.deuda = false;
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

	public String getCategoriaCliente() {
		return categoriaCliente;
	}

	public void setCategoriaCliente(String categoriaCliente) {
		this.categoriaCliente = categoriaCliente;
	}

	public int getMesesDeuda() {
		return mesesDeuda;
	}

	public void setMesesDeuda(int mesesDeuda) {
		this.mesesDeuda = mesesDeuda;
	}

	public double getMontoDeuda() {
		return montoDeuda;
	}

	public void setMontoDeuda(double montoDeuda) {
		this.montoDeuda = montoDeuda;
	}

	public boolean isDeuda() {
		return deuda;
	}

	public void setDeuda(boolean deuda) {
		this.deuda = deuda;
	}

	public boolean puedeContratar() {
		return !deuda;
	}
}