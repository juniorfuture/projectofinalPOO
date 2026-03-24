package logica;

public class Factura {

	private String idFactura;
	private String fecha;
	private double montoTotal;
	private String estado;
	private Contrato contrato;

	public Factura(String idFactura, String fecha, String estado, Contrato contrato) {
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.estado = estado;
		this.contrato = contrato;
		this.montoTotal = calcularTotal();
	}

	public String getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public double calcularTotal() {
		return contrato.calcularCostoMensual();
	}
}
