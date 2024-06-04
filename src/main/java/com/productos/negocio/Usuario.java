package com.productos.negocio;

public class Usuario {
	private int id_usuario;
	private String nombre;
	private String direccion;
	private String usuario;
	private String clave;
	private int id_per;
	
	public Usuario(int id_usuario, String nombre, String direccion, String usuario, String clave,int id_per) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.direccion = direccion;
		this.usuario = usuario;
		this.clave = clave;
		this.id_per=id_per;
	}
	public Usuario() {
		
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public int getId_per() {
		return id_per;
	}
	public void setId_per(int id_per) {
		this.id_per = id_per;
	}
}
