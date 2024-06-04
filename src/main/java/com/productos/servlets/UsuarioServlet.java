package com.productos.servlets;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.productos.negocio.Usuario;
import com.productos.negocio.UsuarioDao;
import java.io.IOException;
@MultipartConfig
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDao usuariodao = new UsuarioDao();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "iniciar":
                    iniciarSesion(request, response);
                    break;
                case "registrarse":
                	registrarse(request,response);
                    break;
                case "cerrar":
                	cerrarSesion(request,response);
                	break;
                case "add_vendedor_admin":
                	registrarUsusarioVA(request,response);
                	break;
                case "update_password":
                	break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no soportada");
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no especificada");
        }
    }
    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String user = request.getParameter("username");
            String password = request.getParameter("password");
            String page = usuariodao.InicarSesion(user, password);
            HttpSession sesion = request.getSession();
            if ("usuario".equals(page)) {
                sesion.setAttribute("usuario", user);
                sesion.setAttribute("perfil", 1);
                response.sendRedirect(request.getContextPath() + "/carrito.jsp");
            } else if ("administrador".equals(page)) {
                sesion.setAttribute("usuario", user);
                sesion.setAttribute("perfil", 2);
                response.sendRedirect(request.getContextPath() + "/verproductos.jsp");
            } else if("vendedor".equals(page)){
            	sesion.setAttribute("usuario", user);
                sesion.setAttribute("perfil", 3);
                response.sendRedirect(request.getContextPath() + "/vendedor.jsp");
            }else {
            	 response.sendRedirect(request.getContextPath() + "/login.jsp?error=Datos incorrectos. Vuelva a intentarlo.");
            }
               
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=Error en el servidor. Intente nuevamente.");
        }
    }
    private void registrarse(HttpServletRequest request, HttpServletResponse response)throws  IOException{
    	try {
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String usuario = request.getParameter("usuario");
            String clave = request.getParameter("clave");
            String claveConf = request.getParameter("clave_conf");

            if (clave.equals(claveConf)) {
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setDireccion(direccion);
                nuevoUsuario.setUsuario(usuario);
                nuevoUsuario.setClave(clave);
                nuevoUsuario.setId_per(2); // usuario

                boolean registrado = usuariodao.RegistrarUsuario(nuevoUsuario);
                if (registrado) {
                    response.sendRedirect(request.getContextPath() + "/login.jsp?mensaje=Registro exitoso. Por favor, inicie sesión.");
                } else {
                    response.sendRedirect(request.getContextPath() + "/registrarse.jsp?error=Error al registrar el usuario. Intente nuevamente.");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/registrarse.jsp?error=Las contraseñas no coinciden. Intente nuevamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/registrarse.jsp?error=Error en el servidor. Intente nuevamente.");
        }
    }
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); // Obtener la sesión si existe, sin crear una nueva
        if (session != null) {
            session.invalidate(); // Invalidar la sesión
        }
        try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void registrarUsusarioVA(HttpServletRequest request, HttpServletResponse response)throws  IOException{
    	try {
    		String nombre = request.getParameter("idNombre");
            String direccion = request.getParameter("direccion");
            String usuario = request.getParameter("usuario");
			int categoria_usuario= Integer.parseInt(request.getParameter("cmbCategoriaUsuarios"));
			String clave="654321";

                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setDireccion(direccion);
                nuevoUsuario.setUsuario(usuario);
                nuevoUsuario.setClave("654321");
                nuevoUsuario.setId_per(categoria_usuario); // usuario

                boolean registrado = usuariodao.RegistrarUsuarioPrivilegiado(nuevoUsuario);
//              if (registrado) {
//                	 response.sendRedirect("add_vendedor_admin.jsp");
//                } else {
//                    response.sendRedirect( "add_vendedor_admin.jsp?error=Error al registrar el usuario. Intente nuevamente.");
//                }
//                response.sendRedirect("/add_vendedor_admin.jsp?error=Las contraseñas no coinciden. Intente nuevamente.");
                try {
        			response.sendRedirect("verproductos.jsp");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/add_vendedor_admin.jsp?error=Error en el servidor. Intente nuevamente.");
        }
    }
    private void actualizarVendedor(HttpServletRequest request, HttpServletResponse response)throws  IOException{
    	try {
    		String clave1 = request.getParameter("clave");
            String clave2 = request.getParameter("clave_conf");
            int id=5;	

                boolean registrado = usuariodao.ActualizarClave(clave1,id);
//              if (registrado) {
//                	 response.sendRedirect("add_vendedor_admin.jsp");
//                } else {
//                    response.sendRedirect( "add_vendedor_admin.jsp?error=Error al registrar el usuario. Intente nuevamente.");
//                }
//                response.sendRedirect("/add_vendedor_admin.jsp?error=Las contraseñas no coinciden. Intente nuevamente.");
                try {
        			response.sendRedirect("verproductos.jsp");
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/add_vendedor_admin.jsp?error=Error en el servidor. Intente nuevamente.");
        }
    }
}