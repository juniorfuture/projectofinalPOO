package logica;

import java.io.Serializable;

public class Contrato implements Serializable{
	private static final long serialVersionUID = 1L;
	private String idContrato;
	private String fechaInicio;
	private String estado;
	private Cliente cliente;
	private Plan plan;

	public Contrato(String idContrato, String fechaInicio, String estado, Cliente cliente, Plan plan) {
		super();
		this.idContrato = idContrato;
		this.fechaInicio = fechaInicio;
		this.estado = estado;
		this.cliente = cliente;
		this.plan = plan;
	}

	public double calcularCostoMensual() {
		return (plan != null) ? plan.getPrecio() : 0.0;
	}


	public String getIdContrato() {
		return idContrato;
	}


	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}


	public String getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Plan getPlan() {
		return plan;
	}


	public void setPlan(Plan plan) {
		this.plan = plan;
	}
}