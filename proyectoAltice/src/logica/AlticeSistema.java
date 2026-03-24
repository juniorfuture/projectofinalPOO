package logica;

import java.util.ArrayList;
import java.util.List;

public class AlticeSistema {

	public static AlticeSistema sistema = null;
	public static int numCliente=1;
	private ArrayList<Persona> personas;
	private List<Plan> planes;
	private List<Servicio> servicios;
	private List<Factura> facturas;

	public AlticeSistema() {
		personas = new ArrayList<>();
		planes = new ArrayList<>();
		servicios = new ArrayList<>();
		facturas = new ArrayList<>();
	}

	public void registrarPersona(Persona aux) {
		personas.add(aux);
		if(aux instanceof Cliente)
			numCliente++;
	}
	public static AlticeSistema getInstance()
	{
		if(sistema==null)
		{
			sistema=new AlticeSistema();
		}
		return sistema;
	}
	public Persona buscarCliente(String id) {
		for (Persona c : personas) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}
}