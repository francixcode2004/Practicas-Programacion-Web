package com.productos.negocio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.productos.datos.Conexion;
public class UsuarioDao {
	public String InicarSesion(String user,String Password) {
		String result="";
		Connection con = null;
		PreparedStatement pr = null;
		try {
			String sql="select usuario,clave,id_per from usuarios where usuario=? and clave=?;";
			Conexion conexion = new Conexion();
			con = conexion.getConexion();
			pr=con.prepareStatement(sql);
			pr.setString(1, user);
			pr.setString(2, Password);
			 try (ResultSet rs = pr.executeQuery()) {
	                if (rs.next()) {
	                    int idPer = rs.getInt("id_per");
	                    if (idPer == 1) {
	                    	result= "administrador";
	                    } else if (idPer == 2) {
	                    	result ="usuario";
	                    }
	                    else if (idPer == 3) {
	                    	result ="vendedor";
	                    }
	                }
	            }
		} catch (Exception e) {
			System.out.println("Hubo un error al encontrar al usuario "+e.getMessage());
			
		}
		return result;
	}
	
	public boolean RegistrarUsuario(Usuario usuario) {
		boolean resultado;
		String result = "Se intenta insertar un usuario ";
		Connection con = null;
		PreparedStatement pr = null;
		try {
			Conexion conexion = new Conexion();
			con=conexion.getConexion();
			String sql="INSERT INTO USUARIOS (id_usuario,nombre,direccion,usuario,clave,id_per) VALUES(?,?,?,?,?,2);";
			pr=con.prepareStatement(sql);
			pr.setInt(1, usuario.getId_usuario());
			pr.setString(2, usuario.getNombre());
			pr.setString(3, usuario.getDireccion());
			pr.setString(4, usuario.getUsuario());
			pr.setString(5, usuario.getClave());
			if (pr.executeUpdate() == 1) {
				result = "Inserción correcta";
				resultado=true;
			} else {
				result = "Error en inserción";
				resultado=false;
			}
		} catch (SQLException ex) {
			result = "Error de SQL: " + ex.getMessage();
			resultado=false;
		} catch (Exception ex) {
			result = "Error: " + ex.getMessage();
			resultado=false;
		} finally {
			if (pr != null) {
				try {
					pr.close();
				} catch (SQLException ex) {
					result = "Error al cerrar el PreparedStatement: " + ex.getMessage();
					resultado=false;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					result = "Error al cerrar la conexión: " + ex.getMessage();
					resultado=false;
				}
			}
		}
		return resultado;
	}
	public boolean RegistrarUsuarioPrivilegiado(Usuario usuario) {
		boolean resultado;
		String result = "Se intenta insertar un usuario ";
		Connection con = null;
		PreparedStatement pr = null;
		try {
			Conexion conexion = new Conexion();
			con=conexion.getConexion();
			String sql="INSERT INTO USUARIOS (id_usuario,nombre,direccion,usuario,clave,id_per) VALUES(?,?,?,?,?,?);";
			pr=con.prepareStatement(sql);
			pr.setInt(1, usuario.getId_usuario());
			pr.setString(2, usuario.getNombre());
			pr.setString(3, usuario.getDireccion());
			pr.setString(4, usuario.getUsuario());
			pr.setString(5, usuario.getClave());
			pr.setInt(6, usuario.getId_per());
			if (pr.executeUpdate() == 1) {
				result = "Inserción correcta";
				resultado=true;
			} else {
				result = "Error en inserción";
				resultado=false;
			}
		} catch (SQLException ex) {
			result = "Error de SQL: " + ex.getMessage();
			resultado=false;
		} catch (Exception ex) {
			result = "Error: " + ex.getMessage();
			resultado=false;
		} finally {
			if (pr != null) {
				try {
					pr.close();
				} catch (SQLException ex) {
					result = "Error al cerrar el PreparedStatement: " + ex.getMessage();
					resultado=false;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					result = "Error al cerrar la conexión: " + ex.getMessage();
					resultado=false;
				}
			}
		}
		return resultado;
	}
	public boolean ActualizarClave(String clave1,int id) {
		boolean resultado;
		String result = "Se intenta insertar un usuario ";
		Connection con = null;
		PreparedStatement pr = null;
		try {
			Conexion conexion = new Conexion();
			con=conexion.getConexion();
			String sql="Update usuarios set clave= ? where id_usuario=?";
			pr=con.prepareStatement(sql);
			pr.setString(1, clave1);
			pr.setInt(2,id );
			
			if (pr.executeUpdate() == 1) {
				result = "Inserción correcta";
				resultado=true;
			} else {
				result = "Error en inserción";
				resultado=false;
			}
		} catch (SQLException ex) {
			result = "Error de SQL: " + ex.getMessage();
			resultado=false;
		} catch (Exception ex) {
			result = "Error: " + ex.getMessage();
			resultado=false;
		} finally {
			if (pr != null) {
				try {
					pr.close();
				} catch (SQLException ex) {
					result = "Error al cerrar el PreparedStatement: " + ex.getMessage();
					resultado=false;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					result = "Error al cerrar la conexión: " + ex.getMessage();
					resultado=false;
				}
			}
		}
		return resultado;
	}
	
}
