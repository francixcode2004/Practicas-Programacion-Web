<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Registrarse</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
 <h1>E-Commerce</h1>
       <p>Encuentra nuestros precios como nunca antes </p>
    <header>
        <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="buscar.jsp">Buscar por categoría</a></li>
            <li><a href="login.jsp">Login</a></li>
            <li><span>Numero de productos 0</span></li>
        </ul>
    </nav>
    </header>
  	<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <h5 class="card-header">Registro de Usuario</h5>
                <div class="card-body">
                    <% String error = request.getParameter("error"); %>
							<% if (error != null) { %>
							<div class="alert alert-danger"><%= error %></div>
							<% } %>
	                    <form action="${pageContext.request.contextPath}/UsuarioServlet" method="post">
                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresa tu nombre" required>
                        </div>
                        <div class="mb-3">
                            <label for="direccion" class="form-label">Dirección De Correo Electronico</label>
                            <input type="email" class="form-control" id="direccion" name="direccion" placeholder="Ingresa tu dirección de correo electronico">
                        </div>
                        <div class="mb-3">
                            <label for="usuario" class="form-label">Usuario</label>
                            <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Ingresa tu usuario" required>
                        </div>
                        <div class="mb-3">
                            <label for="clave" class="form-label">Contraseña</label>
                            <input type="password" class="form-control" id="clave" name="clave" placeholder="Ingresa tu contraseña" required>
                        </div>
                        <div class="mb-3">
                            <label for="clave_conf" class="form-label">Confirmar Contraseña</label>
                            <input type="password" class="form-control" id="clave_conf" name="clave_conf" placeholder="Confirma tu contraseña" required>
                        </div>
                       
                        <div class="d-grid">
                            <button type="submit" name="action" value="registrarse" class="btn btn-primary">Registrarse</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
  	
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
