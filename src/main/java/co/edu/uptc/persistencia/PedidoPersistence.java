package co.edu.uptc.persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.uptc.logica.modelo.Pedido;
import co.edu.uptc.logica.modelo.Producto;
import co.edu.uptc.utilidades.Archivo;

public class PedidoPersistence {
	
	private String Ruta="Pedidos.json";
	File file = new File(Ruta);
	ArrayList<Pedido> pedidos;
	
	public PedidoPersistence() {
		
	}
	public boolean fileExist() throws IOException {
		if(!file.exists()) {
			file.createNewFile();
		}
		
		return false;
	}	
	
	public boolean SobreEscribirArchivoProducto(ArrayList<Pedido> contenido) throws IOException{
		
		fileExist();
		
		JSONArray content = new JSONArray();
		
		for (int i = 0; i < contenido.size(); i++) {
			JSONObject ob= new JSONObject();
			ob.put("clientName", contenido.get(i).getClientName());
			ob.put("clientLastName", contenido.get(i).getClientLastName());
			ob.put("clientCell", contenido.get(i).getClientCell());
			ob.put("direction", contenido.get(i).getDirection());
			ob.put("domiciliarioid", contenido.get(i).getDomiciliarioid());
			
			JSONArray pedidos = new JSONArray();
			JSONObject producto = new JSONObject();
			for (int j = 0; j < contenido.get(i).getOrderedProducts().size(); j++) {
				producto.put("name", contenido.get(i).getOrderedProducts().get(j).getName());
				producto.put("amount", contenido.get(i).getOrderedProducts().get(j).getAmount());
			}
			pedidos.add(producto);

			
			
			ob.put("orderedProducts", pedidos);
			
			JSONObject date1 = new JSONObject();
			date1.put("year",contenido.get(i).getDate().getYear());
			date1.put("month",contenido.get(i).getDate().getMonth());
			date1.put("day",contenido.get(i).getDate().getDay());
			
			ob.put("date",date1);
			ob.put("cost", contenido.get(i).getCost());
			content.add(ob);
		}
		try {
			 Files.write(file.toPath(), content.toJSONString().getBytes());
		    return true;
		} catch (Exception e) {
			return false;
		}

	}
	public boolean AgregarUnNuevoPedido(Pedido pedidoAgregar) {
		try {
			fileExist();
		} catch (IOException e1) {
			System.out.println("No existe el archivo");
		}
		try {
			JSONArray content = new JSONArray();
			pedidos =TraerTodoslosPedidios();
			if(pedidos!=null) {
				for (int i = 0; i < pedidos.size(); i++) {
					JSONObject ob= new JSONObject();
					ob.put("clientName", pedidos.get(i).getClientName());
					ob.put("clientLastName", pedidos.get(i).getClientLastName());
					ob.put("clientCell", pedidos.get(i).getClientCell());
					ob.put("direction", pedidos.get(i).getDirection());
					ob.put("domiciliarioid", pedidos.get(i).getDomiciliarioid());
					
					
					JSONArray pedidosCliente = new JSONArray();
					JSONObject producto = new JSONObject();
					for (int j = 0; j < pedidos.get(i).getOrderedProducts().size(); j++) {
						producto.put("name", pedidos.get(i).getOrderedProducts().get(j).getName());
						producto.put("amount", pedidos.get(i).getOrderedProducts().get(j).getAmount());
					}
					pedidosCliente.add(producto);
					
					ob.put("orderedProducts", pedidosCliente);
					
					JSONObject date1 = new JSONObject();
					date1.put("year",pedidos.get(i).getDate().getYear());
					date1.put("month",pedidos.get(i).getDate().getMonth());
					date1.put("day",pedidos.get(i).getDate().getDay());
					
					ob.put("date", date1);
					
					
					ob.put("cost", pedidos.get(i).getCost());
					content.add(ob);
				}
			}
			JSONObject nuevoProducto= new JSONObject();
			nuevoProducto.put("clientName", pedidoAgregar.getClientName());
			nuevoProducto.put("clientLastName", pedidoAgregar.getClientLastName());
			nuevoProducto.put("clientCell", pedidoAgregar.getClientCell());
			nuevoProducto.put("direction", pedidoAgregar.getDirection());
			nuevoProducto.put("domiciliarioid", pedidoAgregar.getDomiciliarioid());
			JSONArray pedidosCliente = new JSONArray();
			JSONObject producto = new JSONObject();
			for (int j = 0; j < pedidoAgregar.getOrderedProducts().size(); j++) {
				producto.put("name", pedidoAgregar.getOrderedProducts().get(j).getName());
				producto.put("amount", pedidoAgregar.getOrderedProducts().get(j).getAmount());
			}
			pedidosCliente.add(producto);
			
			nuevoProducto.put("orderedProducts",pedidosCliente);
			
			JSONObject date1 = new JSONObject();
			date1.put("year",pedidoAgregar.getDate().getYear());
			date1.put("month",pedidoAgregar.getDate().getMonth());
			date1.put("day",pedidoAgregar.getDate().getDay());
			
			nuevoProducto.put("date", date1);
			nuevoProducto.put("cost", pedidoAgregar.getCost());
			content.add(nuevoProducto);
			Files.write(Paths.get(Ruta), content.toJSONString().getBytes());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public ArrayList<Pedido> TraerTodoslosPedidios(){
		ObjectMapper mapper = new ObjectMapper();
		pedidos= new ArrayList();
		
		try {
			pedidos= mapper.readValue(new File(Ruta),
					mapper.getTypeFactory().constructCollectionType(ArrayList.class, Pedido.class));
			return pedidos;
		} catch (Exception e) {
			return null;
		}
	}
	
/*	public boolean EliminarProductopedido (String nombreProducto) { // hay que corregir
		pedidos=TraerTodosloProductos();
		for (int i = 0; i < products.size(); i++) {
			if(pedidos.get(i).getName().equalsIgnoreCase(nombreProducto)) {
				pedidos.remove(i);
				i=0;
			}
		}
		SobreEscribirArchivoProducto(products);
		return false;
	}
	
	*/

	
}
