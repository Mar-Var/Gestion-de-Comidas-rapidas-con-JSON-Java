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
/**
 * Esta clase gestiona la persistencia de los datos de los {@link Producto} contenidos en el archivo JSON
 * @author Marcos Esteban Vargas Avella
 *
 */
public class ProductoPersistence {
	private String ruta="Productos.json";
	File file = new File(ruta);
	ArrayList<Producto> products;
	/**
	 * metodo que permite crear un archivo JSON en caso de que no exista la ruta que se ingresó.
	 * @return Un valor boolean
	 * @throws IOException Lanza Excepcion
	 */
	public boolean fileExist() throws IOException {
		if(!file.exists()) {
			file.createNewFile();
		}
		
		return false;
	}
	/**
	 * Permite Sobreescribir el archivo JSON con la informacion suministrada por un {@link ArrayList} de tipo {@link Producto}
	 * @param contenido Parametro de tipo {@link ArrayList} de tipo {@link Producto} que va a ser añadida al archivo JSON
	 * @return Retorna un valor tipo boolean, true si puede sobreescribir el archivo correctamente, de lo contrario lanza false
	 */
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
	/**
	 * metodo que permite agregar un nuevo registro de {@link Producto} dentro del archivo JSON
	 * @param productoAgregar parametro de tipo {@link Producto} que representa el nuevo producto que se va a agregar.
	 * @return Retorna un dato tipo booleano, true si puede agregar el producto, de lo contrario manda false
	 */
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
	/**
	 * metodo que sirve para buscar un registro dentro de un archivo JSON y modificarlo
	 * @param nombreProducto Representa un valor de tipo String del nombre el producto que se quiere actualizar
	 * @param price Representa un valor de tipo dluble que representa el nuevo precio de el producto
	 * @return Retorna un dato de tipo boolean, true si puede actualizar el archivo y el registro con exito, false en caso de que no
	 * @throws IOException Lanza Excepcion
	 */
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
	/**
	 * metodo que permite buscar el nombre de un producto dentro del archivo JSON y si lo encuentra lo elimina
	 * @param nombreProducto Parametro de tipo String que representa el nombre del producto a eliminar.
	 * @return Retorna un valor de tipo boolean, true si se pudo eliminar el producto y false si no se logró.
	 */
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
	/**
	 * Metodo que permite trear toda la informacion del archivo Json a un {@link ArrayList} de tipo {@link Producto}
	 * @return Retorna un dato de tipo {@link ArrayList} de tipo {@link Producto} con la informacion contenida en el Archivo JSON
	 */
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
