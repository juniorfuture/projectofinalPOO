package logica;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String username;
	private String password;
	private String tipo;
	private String idRelacionado;

	public User(String nombre, String username, String password, String tipo) {
		super();
		this.nombre = nombre;
		this.username = username;
		this.password = password;
		this.tipo = tipo;
		this.idRelacionado = "";
	}

	public User(String nombre, String username, String password, String tipo, String idRelacionado) {
		super();
		this.nombre = nombre;
		this.username = username;
		this.password = password;
		this.tipo = tipo;
		this.idRelacionado = idRelacionado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIdRelacionado() {
		return idRelacionado;
	}

	public void setIdRelacionado(String idRelacionado) {
		this.idRelacionado = idRelacionado;
	}
}