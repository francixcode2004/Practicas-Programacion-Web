<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="com.productos.negocio.*"%>
	<%
		ProductoDao p1=new ProductoDao();
		CategoriaDao c1=new CategoriaDao();
	%>
<html lang="es">
<head>
<meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>E-Commerce</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
<%
HttpSession sesion = request.getSession();
String usuario = (String) sesion.getAttribute("usuario");
Integer perfil = (Integer) sesion.getAttribute("perfil");

if (usuario == null || perfil == null || perfil != 1) {
    response.sendRedirect(request.getContextPath() + "/login.jsp?error=Debe iniciar sesión para acceder a esta página.");
} else {
%>
<h1>Bienvenido, <%= usuario %></h1>
 <header>
        <nav>
        <ul>
            <li><a href="buscar.jsp">Buscar por categoría</a></li>
            <li><a href="${pageContext.request.contextPath}/UsuarioServlet?action=cerrar">Cerrar Sesión</a></li>
            <li><span>Numero de productos 0</span></li>
            <li><button class="btn btn-success" onclick="comprar()">Comprar</button>

            </li> 
        </ul>
    </nav>
    </header>
   
<div id="carritoDetalle"></div>
    
   	<main>
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
        <li><a href="https://www.linkedin.com/in/tuusuario" target="_blank">LinkedIn</a></li>
    </ul>
</footer>
<script>
var carrito = [];

function addToCart(idProducto, nombreProducto, precioProducto) {
    // Buscar si el producto ya está en el carrito
    var productoExistente = carrito.find(producto => producto.id === idProducto);

    // Si el producto ya está en el carrito, aumentar su cantidad
    if (productoExistente) {
        productoExistente.cantidad++;
    } else {
        // Si el producto no está en el carrito, agregarlo con cantidad 1
        carrito.push({ id: idProducto, nombre: nombreProducto, precio: precioProducto, cantidad: 1 });
    }

    alert('Producto ' + nombreProducto + ' añadido al carrito.');
}

function comprar() {
    var total = 0;
    var listaProductos = '<ol>';
    // Calcular el total y construir la lista de productos
    carrito.forEach(function(producto) {
        var precioTotalProducto = producto.precio * producto.cantidad;
        total += precioTotalProducto;
        listaProductos += '<li>' + producto.nombre + ' x' + producto.cantidad + ' - $' + precioTotalProducto.toFixed(2) + '</li>';
    });
    listaProductos += '</ol>';
    listaProductos += '<strong>Total: $' + total.toFixed(2) + '</strong>';

    // Insertar la lista de productos y el total en el contenedor
    document.getElementById('carritoDetalle').innerHTML = listaProductos;

    // Opcionalmente, reiniciar el carrito
    // carrito = [];
}
</script>



<%
}
%>



   
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
