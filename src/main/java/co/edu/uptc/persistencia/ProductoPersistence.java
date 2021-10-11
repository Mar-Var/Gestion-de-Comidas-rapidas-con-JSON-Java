package co.edu.uptc.persistencia;

import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.uptc.logica.modelo.Producto;

public class ProductoPersistence {
	private String ruta="Productos.json";
	File file = new File(ruta);
	ArrayList<Producto> products;
	
	public boolean fileExist() throws IOException {
		if(!file.exists()) {
			file.createNewFile();
		}
		
		return false;
	}
	public boolean SobreEscribirArchivoProducto(ArrayList<Producto> contenido){
		
		try {
			fileExist();
		} catch (IOException e1) {
			System.out.println("No existe el archivo");
		}
		JSONArray content = new JSONArray();
		
		for (int i = 0; i < contenido.size(); i++) {
			JSONObject ob= new JSONObject();
			ob.put("name", contenido.get(i).getName());
			ob.put("price", contenido.get(i).getPrice());
			content.add(ob);
		}
		try {
			 Files.write(file.toPath(), content.toJSONString().getBytes());
		    return true;
		} catch (Exception e) {
			return false;
		}

	}
	public boolean AgregarUnNuevoProducto(Producto productoAgregar) {
		try {
			fileExist();
		} catch (IOException e1) {
			System.out.println("No existe el archivo");
		}
		try {
			JSONArray content = new JSONArray();
			if(TraerTodosloProductos()!=null) {
				products =TraerTodosloProductos();
				if(products!=null) {
					for (int i = 0; i < products.size(); i++) {
						JSONObject ob= new JSONObject();
						ob.put("name", products.get(i).getName());
						ob.put("price", products.get(i).getPrice());
						content.add(ob);
					}
				}
			}

			JSONObject nuevoProducto= new JSONObject();
			nuevoProducto.put("name", productoAgregar.getName());
			nuevoProducto.put("price", productoAgregar.getPrice());
			content.add(nuevoProducto);
			Files.write(Paths.get(ruta), content.toJSONString().getBytes());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean actualizarProducto(String nombreProducto, double price) throws IOException {
		JSONArray content = new JSONArray();
		products =TraerTodosloProductos();
		if(products!=null && products.stream()
				.filter(productsAux->productsAux.getName()
				.equals(nombreProducto))
				.findFirst().map(productsAux->productsAux)
				.orElse(null)!=null) {
			System.out.println("Llega");
			
			for (int i = 0; i < products.size(); i++) {
				JSONObject ob= new JSONObject();
				if(products.get(i).getName().equals(nombreProducto)) {
					ob.put("name", nombreProducto);
					ob.put("price", price);
				}else {
					ob.put("name", products.get(i).getName());
					ob.put("price", products.get(i).getPrice());
				}
				content.add(ob);
				
			}
			Files.write(Paths.get(ruta), content.toJSONString().getBytes());
			return true;	
		};
		
		return false;
	}
	public boolean EliminarProducto (String nombreProducto) {
		products=TraerTodosloProductos();
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getName().equalsIgnoreCase(nombreProducto)) {
				products.remove(i);
				i=0;
			}
		}
		
		if(products.get(0).getName().equalsIgnoreCase(nombreProducto)) {
			products.remove(0);
		}
		SobreEscribirArchivoProducto(products);
		if(products.stream()
				.filter(productsAux->productsAux.getName()
				.equals(nombreProducto))
				.findFirst().map(productsAux->productsAux)
				.orElse(null)==null) {
			return true;	
		};

		return false;
	}
	public ArrayList<Producto> TraerTodosloProductos(){
		ObjectMapper mapper = new ObjectMapper();
		products= new ArrayList();
	//	System.out.println("funciona");
		try {
			
			products= mapper.readValue(new File(ruta),
					mapper.getTypeFactory().constructCollectionType(ArrayList.class, Producto.class));
			return products;
		} catch (Exception e) {
			return null;
		}
	}

	
	
	

}
