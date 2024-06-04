<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
    import="com.productos.negocio.*"%>
<%
    ProductoDao p1=new ProductoDao();
    CategoriaDao c1=new CategoriaDao();
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>E-Commerce</title>
<link rel="stylesheet" href="style.css">
<style>
	/* General Styles */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    background-color: #f5f5f5; /* Blanco tirando a plomo */
}

/* Container */
.container {
    padding: 2rem;
}

/* Header Styles */
.header {
    background-color: #000; /* Negro */
    color: white;
    padding: 0 1.5rem;
    height: 56px;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.header .logo {
    display: flex;
    align-items: center;
}

.header .logo img {
    height: 200px;
    width: 200px;
    margin-right: 0.5rem;
}

.header nav {
    display: flex;
    gap: 1rem;
}

.header nav a {
    font-size: 0.875rem;
    font-weight: 500;
    color: white;
    text-decoration: none;
}

.header nav a:hover {
    text-decoration: underline;
    text-underline-offset: 4px;
}

/* Footer Styles */
footer {
    margin-top: 2rem;
    padding: 1rem 0;
    background-color: #000; /* Negro */
    color: white;
    text-align: center;
}

footer p {
    margin: 0 0 1rem;
}

footer ul {
    list-style: none;
    padding: 0;
    margin: 0;
    display: flex;
    justify-content: center;
    gap: 1rem;
}

footer ul li {
    margin: 0;
}

footer ul li a {
    text-decoration: none;
    color: #007bff;
}

footer ul li a:hover {
    text-decoration: underline;
}
	
</style>
</head>
<body>
<div class="container">
<h1>E-Commerce</h1>
    <p>Encuentra nuestros precios como nunca antes </p>
    
    <!-- Header con el logo y navegación -->
    <header class="header">
        <a href="index.jsp" class="logo">
            <img src="./public/logo_img_1.jpg" alt="Logo">
            <span class="sr-only">Compu Smart</span>
        </a>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="buscar.jsp">Buscar por categoría</a>
            <a href="login.jsp">Login</a>
        </nav>
    </header>
    
    <main class="main">
        <%
            ProductoDao productoDao = new ProductoDao();
            try {
                String cards = productoDao.generarCardsProductos();
                out.print(cards);
            } catch (Exception e) {
                e.printStackTrace();
                out.print("<p>Ocurrió un error al generar las tarjetas de productos.</p>");
            }
        %>
    </main>
    <footer>
        <p>Síguenos en nuestras redes sociales:</p>
        <ul>
            <li><a href="https://www.facebook.com/tuusuario" target="_blank">Facebook</a></li>
            <li><a href="https://www.twitter.com/tuusuario" target="_blank">Twitter</a></li>
            <li><a href="https://www.instagram.com/tuusuario" target="_blank">Instagram</a></li>
            <li><a href="https://www.linkedin.com/in/francisco-lopez-61a007304/" target="_blank">LinkedIn</a></li>
        </ul>
    </footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
