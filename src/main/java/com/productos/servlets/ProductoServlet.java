package com.productos.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.productos.negocio.Producto;
import com.productos.negocio.ProductoDao;

@MultipartConfig
@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductoDao productodao=new ProductoDao();
	private String pathfile="C:\\eclipse\\eclipse\\francisco_workspace\\practica05_conexionBd\\src\\main\\webapp\\img_db";
	private File uploads=new File(pathfile);
	private String [] extens= {".ico",".png",".jpg","jpeg"};
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		switch (action) {
		case "add":
			saveProducto(request,response);
			break;

		default:
			break;
		}
	}
	private void saveProducto(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        // Obtener los valores de los campos del formulario
	        int id = Integer.parseInt(request.getParameter("idInput"));
	        int categoria = Integer.parseInt(request.getParameter("cmbCategoria"));
	        String nombre = request.getParameter("idNombre");
	        int cantidad = Integer.parseInt(request.getParameter("idCantidad"));
	        double precio = Double.parseDouble(request.getParameter("idPrecio"));
	        String descripcion = request.getParameter("idDescripcion");

	        // Procesar la imagen (si es necesario)
	        Part part = request.getPart("idImagen");
	        if(part==null) {
	        	System.out.println("No ha selecionado un archivo");
	        	return;
	        }
	        if(isExtension(part.getSubmittedFileName(),extens)) {
	        	String photo=SaveFile(part,uploads);
	            Producto producto = new Producto(id, categoria, nombre, precio, descripcion, photo, cantidad);
	            productodao.ingresarProducto(producto);
	        }
	        
	        
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String SaveFile(Part part,File pathUpload) {
		String pathAbsolute="";
		try {
			Path path=Paths.get(part.getSubmittedFileName());
			String fileName=path.getFileName().toString();
			InputStream input=part.getInputStream();
			
			if(input!=null) {
				File file=new File(pathUpload,fileName);
				pathAbsolute=file.getAbsolutePath();
				Files.copy(input, file.toPath());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return pathAbsolute;
	}
	
	private boolean isExtension(String fileName,String[] extensions) {
		for(String et:extensions) {
			if(fileName.toLowerCase().endsWith(et)) {
				return true;
			}
		}
		return false;
	}

}
