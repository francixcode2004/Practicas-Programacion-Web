<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="com.productos.negocio.*"%>
	<%
		ProductoDao p1=new ProductoDao();
		CategoriaDao c1=new CategoriaDao();
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<%
HttpSession sesion = request.getSession();
String usuario = (String) sesion.getAttribute("usuario");
Integer perfil = (Integer) sesion.getAttribute("perfil");

if (usuario == null || perfil == null || perfil != 3) {
    response.sendRedirect(request.getContextPath() + "/login.jsp?error=Debe iniciar sesión para acceder a esta página.");
} else {
%>
<h1>Bienvenido, <%= usuario %></h1>

					<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
					  cambiar clave
					</button>
					<!-- Modal -->
					
					
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
<%
}
%>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
					
</body>
</html>