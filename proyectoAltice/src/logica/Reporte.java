package logica;

import java.util.List;

public class Reporte {

	private String idReporte;
	private String tipo;
	private String fechaInicio;
	private String fechaFin;

	public Reporte(String idReporte, String tipo, String fechaInicio, String fechaFin) {
		this.idReporte = idReporte;
		this.tipo = tipo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public String getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(String idReporte) {
		this.idReporte = idReporte;
	}
}
