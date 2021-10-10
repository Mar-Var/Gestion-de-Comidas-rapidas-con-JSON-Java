package co.edu.uptc.persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.uptc.logica.modelo.DateUses;
import co.edu.uptc.logica.modelo.Domiciliario;
import co.edu.uptc.logica.modelo.Producto;

public class DomiciliariosPersistence {
	
	private String ruta="Domiciliarios.json";
	File file = new File(ruta);
	ArrayList<Domiciliario> domiciliario;
	
	public boolean fileExist() throws IOException {
		if(!file.exists()) {
			file.createNewFile();
		}
		return false;
	}
	
	public boolean SobreEscribirArchivoProducto(ArrayList<Domiciliario> domiciliario){
		
		try {
			fileExist();
		} catch (IOException e1) {
			System.out.println("No existe el archivo");
		}
		JSONArray content = new JSONArray();
		
		for (int i = 0; i < domiciliario.size(); i++) {
			JSONObject ob= new JSONObject();
			ob.put("name", domiciliario.get(i).getName());
			ob.put("lastName", domiciliario.get(i).getLastName());
			ob.put("identification", domiciliario.get(i).getIdentification());
			
			JSONObject date1 = new JSONObject();
			date1.put("year",domiciliario.get(i).getBirthday().getYear());
			date1.put("month",domiciliario.get(i).getBirthday().getMonth());
			date1.put("day",domiciliario.get(i).getBirthday().getDay());
			
			ob.put("birthday", date1);
			content.add(ob);
		}
		try {
			 Files.write(file.toPath(), content.toJSONString().getBytes());
		    return true;
		} catch (Exception e) {
			return false;
		}

	}
	public boolean agregarUnNuevoDomiciliario(Domiciliario domiciliarioAgregar) {
		try {
			fileExist();
		} catch (IOException e1) {
			System.out.println("No existe el archivo");
		}
		try {
			JSONArray content = new JSONArray();
			ObjectMapper mapper = new ObjectMapper();
			domiciliario =TraerTodoslosdomiciliarios();
			
			
			
			if(domiciliario!=null) {
				for (int i = 0; i < domiciliario.size(); i++) {
					JSONObject ob= new JSONObject();
					
					ob.put("name", domiciliario.get(i).getName());
					ob.put("lastName", domiciliario.get(i).getLastName());
					ob.put("identification", domiciliario.get(i).getIdentification());
					
					JSONObject date1 = new JSONObject();
					date1.put("year",domiciliario.get(i).getBirthday().getYear());
					date1.put("month",domiciliario.get(i).getBirthday().getMonth());
					date1.put("day",domiciliario.get(i).getBirthday().getDay());
					
					ob.put("birthday", date1);

					content.add(ob);
				}
			}
			JSONObject nuevoProducto= new JSONObject();
			nuevoProducto.put("name", domiciliarioAgregar.getName());
			nuevoProducto.put("lastName", domiciliarioAgregar.getLastName());
			nuevoProducto.put("identification", domiciliarioAgregar.getIdentification());
			
			JSONObject date = new JSONObject();
			date.put("year",domiciliarioAgregar.getBirthday().getYear());
			date.put("month",domiciliarioAgregar.getBirthday().getMonth());
			date.put("day",domiciliarioAgregar.getBirthday().getDay());
			
			nuevoProducto.put("birthday", date);
			content.add(nuevoProducto);
			Files.write(Paths.get(ruta), content.toJSONString().getBytes());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean actualizarDomiciliario(String name, String lastName, int identification, DateUses birthday) throws IOException {
		JSONArray content = new JSONArray();
		domiciliario =TraerTodoslosdomiciliarios();
		if(domiciliario!=null && domiciliario.stream()
				.filter(domiciliarioAux->domiciliarioAux.getIdentification()
				==identification)
				.findFirst().map(productsAux->productsAux)
				.orElse(null)!=null) {
			
			for (int i = 0; i < domiciliario.size(); i++) {
				JSONObject ob= new JSONObject();
				if(domiciliario.get(i).getIdentification()==identification) {
					ob.put("name", name );
					ob.put("lastName", lastName );
					ob.put("identification", identification);
					
					JSONObject date1 = new JSONObject();
					date1.put("year",birthday.getYear());
					date1.put("month",birthday.getMonth());
					date1.put("day",birthday.getDay());
					
					ob.put("birthday",  date1);
				}else {
					ob.put("name", domiciliario.get(i).getName());
					ob.put("lastName", domiciliario.get(i).getLastName());
					ob.put("identification", domiciliario.get(i).getIdentification());
					
					JSONObject date1 = new JSONObject();
					date1.put("year",domiciliario.get(i).getBirthday().getYear());
					date1.put("month",domiciliario.get(i).getBirthday().getMonth());
					date1.put("day",domiciliario.get(i).getBirthday().getDay());
					ob.put("birthday",date1);
					
				}
				content.add(ob);
			}
			
			Files.write(Paths.get(ruta), content.toJSONString().getBytes());
			
			return true;	
		};
		
		return false;
	}
	
	public boolean EliminarDomiciliario (int identification) {
		domiciliario=TraerTodoslosdomiciliarios();
		for (int i = 0; i < domiciliario.size(); i++) {
			
			if(domiciliario.get(i).getIdentification()==identification) {
				domiciliario.remove(i);
				i=0;
			}
		}
		SobreEscribirArchivoProducto(domiciliario);
		if(domiciliario.stream()
				.filter(productsAux->String.valueOf(productsAux.getIdentification())
				.equals(identification))
				.findFirst().map(productsAux->productsAux)
				.orElse(null)==null) {
			return true;	
		};

		return false;
	}
	public ArrayList<Domiciliario> TraerTodoslosdomiciliarios(){
		ObjectMapper mapper = new ObjectMapper();
		domiciliario= new ArrayList();
		
		try {
			domiciliario= mapper.readValue(new File(ruta),
					mapper.getTypeFactory().constructCollectionType(ArrayList.class, Domiciliario.class));
			return domiciliario;
		} catch (Exception e) {
			return null;
		}
	}

}
