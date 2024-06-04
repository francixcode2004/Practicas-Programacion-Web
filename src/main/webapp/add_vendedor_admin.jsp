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
	<h1>
		pantalla para añadir a un vendedor o admin
	</h1>

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
							<form action="${pageContext.request.contextPath}/UsuarioServlet" method="post">
							    <div>
							        <label for="idNombre">Nombre:</label>
							        <input type="text" id="idNombre" name="idNombre">
							    </div>
							    <div class="mb-3">
		                            <label for="direccion" class="form-label">Dirección De Correo Electronico</label>
		                            <input type="email" class="form-control" id="direccion" name="direccion" placeholder="Ingresa tu dirección de correo electronico">
		                        </div>
		                        <div class="mb-3">
		                            <label for="usuario" class="form-label">Usuario</label>
		                            <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Ingresa tu usuario" required>
		                        </div>
							    <div>
							        <label for="cmbCategoriaUsuarios">Añadir Usuario:</label>
							        <% 
							           String comboHtml = c1.mostrarUsuarios();
							           out.print(comboHtml);
							          %>
							    </div>
							    <div class="modal-footer">
							        <button type="submit" name="action" value="add_vendedor_admin" class="btn btn-primary">Ingresar</button>
							    </div>
							</form>
					      </div>
					      <div class="modal-footer">
					      </div>
					    </div>
					  </div>
					</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
					
</body>
</html>