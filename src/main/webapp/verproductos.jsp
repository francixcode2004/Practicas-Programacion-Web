<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="com.productos.negocio.*"%>
<!DOCTYPE html>
	<%
		ProductoDao p1=new ProductoDao();
		CategoriaDao c1=new CategoriaDao();
	%>
<html lang="es">
<head>
<meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>E-Commerce</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<%
HttpSession sesion = request.getSession();
String usuario = (String) sesion.getAttribute("usuario");
Integer perfil = (Integer) sesion.getAttribute("perfil");

if (usuario == null || perfil == null || perfil != 2) {
    response.sendRedirect(request.getContextPath() + "/login.jsp?error=Debe iniciar sesión para acceder a esta página.");
} else {
%>
<h1>Bienvenido, <%= usuario %></h1>
       <p>Encuentra nuestros precios como nunca antes </p>
    <header>
        <nav>
        <ul>
            <li><a href="buscar.jsp">Buscar por categoría</a></li>
            <li><a href="add_vendedor_admin.jsp">Añadir vendedor/administrador</a></li>
            <li><a href="${pageContext.request.contextPath}/UsuarioServlet?action=cerrar">Cerrar Sesión</a></li>
        </ul>
    </nav>
    </header>
     <main>
        <div id="content">
            <section>
                <article>
                   <div
                    class="table-responsive-md"
                   >
                   <% 
                    String tablaHtml = p1.consultarTodo();
                    out.print(tablaHtml); // Imprime la tabla HTML en la página
                    %>
                   </div>
                   <!-- Button trigger modal -->
					<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
					  Añadir productos
					</button>
					
					<!-- Modal -->
					<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
							<form action="ProductoServlet" method="post" enctype="multipart/form-data">
							    <div>
							        <label for="idInput">Id:</label>
							        <input type="number" id="idInput" name="idInput">
							    </div>
							    <div>
							        <label for="cmbCategoria">Buscar por categoría:</label>
							        <% 
							           String comboHtml = c1.mostrarCategoria();
							           out.print(comboHtml); %>
							    </div>
							    <div>
							        <label for="idNombre">Nombre:</label>
							        <input type="text" id="idNombre" name="idNombre">
							    </div>
							    <div>
							        <label for="idCantidad">Cantidad:</label>
							        <input type="Number" id="idCantidad" name="idCantidad">
							    </div>
							    <div>
							        <label for="idPrecio">Precio:</label>
							        <input type="text" id="idPrecio" name="idPrecio">
							    </div>
							    <div>
							        <label for="idImagen">Imagen:</label>
							        <input type="file" id="idImagen" name="idImagen" accept="image/png,image/jpeg">
							    </div>
							    <div>
							        <label for="idDescripcion">Descripcion:</label>
							        <input type="text" id="idDescripcion" name="idDescripcion">
							    </div>
							    <div class="modal-footer">
							        <button type="submit" name="action" value="add" class="btn btn-primary">Ingresar</button>
							        <button type="submit" name="action" value="update" class="btn btn-success">Actualizar</button>
							        <button type="submit" name="action" value="delete" class="btn btn-danger">Eliminar</button>
							    </div>
							</form>
					      </div>
					      <div class="modal-footer">
					      </div>
					    </div>
					  </div>
					</div>
                </article>
                                   
            </section>
            <section>
            	<h2>Actualizar clave</h2>
            	<form action="ProductoServlet" method="post" enctype="multipart/form-data">
							    <div class="mb-3">
		                            <label for="clave" class="form-label">Contraseña</label>
		                            <input type="password" class="form-control" id="clave" name="clave" placeholder="Ingresa tu contraseña" required>
		                        </div>
		                        <div class="mb-3">
		                            <label for="clave_conf" class="form-label">Confirmar Contraseña</label>
		                            <input type="password" class="form-control" id="clave_conf" name="clave_conf" placeholder="Confirma tu contraseña" required>
		                        </div>
							    
							    <div class="modal-footer">
							        <button type="submit" name="action" value="update_password" class="btn btn-primary">Actualizar Clave</button>
							    </div>
							</form>
            </section>
            <aside>
                <h2>Ofertas destacadas</h2>
                <div class="ofertas">
                    <div class="oferta">
                        
                        <h3>Producto 1</h3>
                        <p>¡Aprovecha esta oferta especial!</p>
                        <span class="precio-oferta">$19.99</span>
                        <del class="precio-original">$29.99</del>
                        <a href="#" class="btn-comprar">Comprar ahora</a>
                    </div>
                    <div class="oferta">
                        
                        <h3>Producto 2</h3>
                        <p>No te pierdas esta promoción exclusiva</p>
                        <span class="precio-oferta">$14.99</span>
                        <del class="precio-original">$24.99</del>
                        <a href="#" class="btn-comprar">Comprar ahora</a>
                    </div>
                </div>
                <h2>Últimas noticias</h2>
                <ul class="noticias">
                    <li><a href="#">Nuevo producto disponible</a></li>
                    <li><a href="#">Promoción de primavera</a></li>
                    <li><a href="#">Conoce nuestros descuentos</a></li>
                </ul>
            </aside>            
        </div>
    </main>
   <footer>
    <p>Síguenos en nuestras redes sociales:</p>
    <ul>
        <li><a href="https://www.facebook.com/tuusuario" target="_blank">Facebook</a></li>
        <li><a href="https://www.twitter.com/tuusuario" target="_blank">Twitter</a></li>
        <li><a href="https://www.instagram.com/tuusuario" target="_blank">Instagram</a></li>
        <li><a href="https://www.linkedin.com/in/tuusuario" target="_blank">LinkedIn</a></li>
    </ul>
</footer>
<%
}
%>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
