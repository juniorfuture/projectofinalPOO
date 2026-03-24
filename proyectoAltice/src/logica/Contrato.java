package logica;

import java.util.List;

public class Contrato {

	private String idContrato;
	private String fechaInicio;
	private String estado;
	private Cliente cliente;
	private Plan plan;
	private List<Servicio> servicios;

	public Contrato(String idContrato, String fechaInicio, String estado,
			Cliente cliente, Plan plan, List<Servicio> servicios) {
		this.idContrato = idContrato;
		this.fechaInicio = fechaInicio;
		this.estado = estado;
		this.cliente = cliente;
		this.plan = plan;
		this.servicios = servicios;
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

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public double calcularCostoMensual() {
		double total = plan.getPrecio();

		for (Servicio servi : servicios) {
			if (servi.isActivo()) {
				total += servi.getCosto();
			}
		}
		return total;
	}
}