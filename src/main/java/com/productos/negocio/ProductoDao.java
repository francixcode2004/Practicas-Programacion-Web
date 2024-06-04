package com.productos.negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.productos.datos.Conexion;


public class ProductoDao {
	public String consultarTodo() {
		String sql = "SELECT id_producto, nombre, cantidad, precio, descripcion FROM productos";
		Conexion con = new Conexion();
		StringBuilder tabla = new StringBuilder("<table class=\"table table-striped\">"
				+ "<thead class='table-primary'>"
				+ "<tr>"
				+ "<th scope=\"col\">ID</th>"
				+ "<th scope=\"col\">Producto</th>"
				+ "<th scope=\"col\">Cantidad</th>"
				+ "<th scope=\"col\">Precio</th>"
				+ "</tr>"
				+ "</thead>"
				+ "<tbody>");
		ResultSet rs = null;
		rs = con.Consulta(sql);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				tabla.append("<tr>")
				.append("<td>").append(id).append("</td>")
				.append("<td>").append(rs.getString(2)).append("</td>")
				.append("<td>").append(rs.getInt(3)).append("</td>")
				.append("<td>").append(rs.getDouble(4)).append("</td>")
				.append("<td>")
				.append("</td>")
				.append("</tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		tabla.append("</tbody>")
		.append("</table>");
		return tabla.toString();
	}

	public String buscarProductoCategoria(int cat) {
		String sql = "SELECT id_producto, nombre, cantidad, precio, descripcion FROM productos WHERE id_categoria=?";
		Conexion con = new Conexion();
		StringBuilder tabla = new StringBuilder("<table class=\"table table-primary\">"
				+ "<thead>"
				+ "<tr>"
				+ "<th scope=\"col\">ID</th>"
				+ "<th scope=\"col\">Producto</th>"
				+ "<th scope=\"col\">Cantidad</th>"
				+ "<th scope=\"col\">Precio</th>"
				+ "</tr>"
				+ "</thead>"
				+ "<tbody>");
		ResultSet rs = null;

		try (PreparedStatement pst = con.getConexion().prepareStatement(sql)) {
			pst.setInt(1, cat);  // Establecer el parámetro id_categoria
			rs = pst.executeQuery();  // Ejecutar la consulta

			while (rs.next()) {
				tabla.append("<tr>")
				.append("<td>").append(rs.getInt("id_producto")).append("</td>")
				.append("<td>").append(rs.getString("nombre")).append("</td>")
				.append("<td>").append(rs.getInt("cantidad")).append("</td>")
				.append("<td>").append(rs.getDouble("precio")).append("</td>")
				.append("</tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}

		tabla.append("</tbody>")
		.append("</table>");
		return tabla.toString();
	}

	public String ingresarProducto(Producto p1) throws IOException {
		String result = "Se intenta insertar";
		Connection con = null;
		PreparedStatement pr = null;
		FileInputStream streamEntrada = null;
		try {
			Conexion conexion = new Conexion();
			con = conexion.getConexion();
			String sql = "INSERT INTO productos (id_producto, id_categoria, nombre, precio, descripcion, foto, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?)";
			pr = con.prepareStatement(sql);
			pr.setInt(1, p1.getId());
			pr.setInt(2, p1.getCategoria());
			pr.setString(3, p1.getNombre());
			pr.setDouble(4, p1.getPrecio());
			pr.setString(5, p1.getDescripcion());
			pr.setString(6, p1.getFoto());
			pr.setInt(7, p1.getCantidad());

			if (pr.executeUpdate() == 1) {
				result = "Inserción correcta";
			} else {
				result = "Error en inserción";
			}
		} catch (SQLException ex) {
			result = "Error de SQL: " + ex.getMessage();
		} catch (Exception ex) {
			result = "Error: " + ex.getMessage();
		} finally {
			// Cerrar recursos en un bloque finally
			if (streamEntrada != null) {
				try {
					streamEntrada.close();
				} catch (IOException ex) {
					result = "Error al cerrar el stream: " + ex.getMessage();
				}
			}
			if (pr != null) {
				try {
					pr.close();
				} catch (SQLException ex) {
					result = "Error al cerrar el PreparedStatement: " + ex.getMessage();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					result = "Error al cerrar la conexión: " + ex.getMessage();
				}
			}
		}
		return result;
	}

	public String eliminarProducto(int id) {
		String result = "Se intenta eliminar";
		Connection con = null;
		PreparedStatement pr = null;

		try {
			Conexion conexion = new Conexion(); 
			con = conexion.getConexion(); 
			String sql = "DELETE FROM productos WHERE id_producto = ?";
			pr = con.prepareStatement(sql);
			pr.setInt(1, id);

			if (pr.executeUpdate() == 1) {
				result = "Eliminación correcta";
			} else {
				result = "No se encontró el producto con el ID especificado";
			}
		} catch (Exception ex) {
			result = "Error: " + ex.getMessage(); 
		} finally {
			if (pr != null) { 
				try { 
					pr.close(); 
				} catch (SQLException ex) { 
					result = "Error al cerrar el PreparedStatement: " + ex.getMessage(); 
				} 
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					result = "Error al cerrar la conexión: " + ex.getMessage();
				}
			}
		}
		return result;
	}

	public String actualizarProducto(Producto p1) {
		String result = "Se intenta actualizar";
		Connection con = null;
		PreparedStatement pr = null;
		FileInputStream streamEntrada = null;

		try {
			Conexion conexion = new Conexion();
			con = conexion.getConexion();
			String sql = "UPDATE productos SET id_categoria = ?, nombre = ?, precio = ?, descripcion = ?, foto = ?, cantidad = ? WHERE id_producto = ?";
			pr = con.prepareStatement(sql);
			pr.setInt(1, p1.getCategoria());
			pr.setString(2, p1.getNombre());
			pr.setDouble(3, p1.getPrecio());
			pr.setString(4, p1.getDescripcion());
			pr.setString(5, p1.getFoto());
			pr.setInt(6, p1.getCantidad());
			pr.setInt(7, p1.getId()); // Aquí se establece el ID del producto en el WHERE

			if (pr.executeUpdate() == 1) {
				result = "Actualización correcta";
			} else {
				result = "No se encontró el producto con el ID especificado";
			}
		} catch (Exception ex) {
			result = "Error: " + ex.getMessage();
		} finally {
			if (streamEntrada != null) {
				try {
					streamEntrada.close();
				} catch (IOException ex) {
					result = "Error al cerrar el stream: " + ex.getMessage();
				}
			}
			if (pr != null) {
				try {
					pr.close();
				} catch (SQLException ex) {
					result = "Error al cerrar el PreparedStatement: " + ex.getMessage();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					result = "Error al cerrar la conexión: " + ex.getMessage();
				}
			}
		}
		return result;
	}
	public String generarCardsProductos() {
	    String sql = "SELECT id_producto, nombre, cantidad, precio, descripcion, foto FROM productos";
	    Conexion con = new Conexion();
	    StringBuilder cards = new StringBuilder();
	    ResultSet rs = null;
	    rs = con.Consulta(sql);
	    try {
	        while (rs.next()) {
	            int idProducto = rs.getInt("id_producto");
	            String nombre = rs.getString("nombre");
	            int cantidad = rs.getInt("cantidad");
	            double precio = rs.getDouble("precio");
	            String descripcion = rs.getString("descripcion");
	            String foto = rs.getString("foto");
	            String fileName = "not_found.jpeg";
	            if (foto != null && !foto.trim().isEmpty()) {
	                File file = new File(foto);
	                fileName = file.getName();
	            }
	            String relativePath = "./img_db/" + fileName;
	            cards.append("<div class=\"col-md-4\">")
	                .append("<div class=\"card\">")
	                .append("<img src=\"").append(relativePath).append("\" class=\"card-img-top\" alt=\"Imagen del producto\">")
	                .append("<div class=\"card-body\">")
	                .append("<h5 class=\"card-title\">").append(nombre).append("</h5>")
	                .append("<p class=\"card-text\">").append(descripcion).append("</p>")
	                .append("<p class=\"card-text\">Cantidad: ").append(cantidad).append("</p>")
	                .append("<p class=\"card-text\">Precio: $").append(precio).append("</p>")
	                .append("<button class=\"btn btn-primary\" onclick=\"addToCart(").append(rs.getInt("id_producto")).append(", '").append(nombre).append("', ").append(precio).append(")\">Añadir al carrito</button>")
	                .append("</div>")
	                .append("</div>")
	                .append("</div>");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }
	    return cards.toString();
	}

}
