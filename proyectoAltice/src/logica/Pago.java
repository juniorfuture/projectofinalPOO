package logica;

import java.io.Serializable;

public class Pago implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idPago;
	private String fecha;
	private double monto;
	private String metodo;
	private Factura factura;

	public Pago(String idPago, String fecha, double monto, String metodo) {
		this.idPago = idPago;
		this.fecha = fecha;
		this.monto = monto;
		this.metodo = metodo;
	}

	public Pago(String idPago, String fecha, double monto, String metodo, Factura factura) {
		this.idPago = idPago;
		this.fecha = fecha;
		this.monto = monto;
		this.metodo = metodo;
		this.factura = factura;
	}

	public String getIdPago() {
		return idPago;
	}

	public void setIdPago(String idPago) {
		this.idPago = idPago;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public boolean procesarPago() {
		if (factura == null) {
			return false;
		}
		if (!factura.getEstado().equalsIgnoreCase("Pendiente")
				&& !factura.getEstado().equalsIgnoreCase("Vencida")) {
			return false;
		}
		if (Double.compare(monto, factura.getMontoTotal()) != 0) {
			return false;
		}
		factura.setEstado("Pagada");
		return true;
	}
}