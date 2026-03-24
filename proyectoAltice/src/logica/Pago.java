package logica;

public class Pago {

	private String idPago;
	private String fecha;
	private double monto;
	private String metodo;

	public Pago(String idPago, String fecha, double monto, String metodo) {
		this.idPago = idPago;
		this.fecha = fecha;
		this.monto = monto;
		this.metodo = metodo;
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

	public boolean procesarPago() {
		return true;
	}
}
