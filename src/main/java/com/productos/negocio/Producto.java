package com.productos.negocio;

public class Producto{
	private Integer id;
	private String nombre;
	private int cantidad;
	private double precio;
	private String foto;
	private String descripcion;
	private Integer categoria;
	public Producto(Integer id, Integer categoria,String nombre, double precio ,String descrip,String foto,Integer cantidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion=descrip;
		this.foto = foto;
		this.cantidad = cantidad;
		this.categoria=categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Integer getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	

}