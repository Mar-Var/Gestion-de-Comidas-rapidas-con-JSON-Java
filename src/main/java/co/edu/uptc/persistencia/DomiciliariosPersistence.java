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
import co.edu.uptc.logica.modelo.Pedido;
import co.edu.uptc.logica.modelo.Producto;
/**
 * Clase que maneja la persistencia de los datos en un archivo JSON designado por una Ruta
 * @author Marcos Esteban Vargas Avella
 *
 */
public class DomiciliariosPersistence {

	private String ruta="Domiciliarios.json";
	File file = new File(ruta);
	ArrayList<Domiciliario> domiciliario;
	 /**
	  * Metodo que permite verificar si un archiov existe y si no existe lo crea
	  * @return Retorna un dato tipo booleano, true si crea el archivo y de lo contrario retorna false
	  * @throws IOException
	  */
	public boolean fileExist() throws IOException {
		if(!file.exists()) {
			file.createNewFile();
			return true;
		}
		return false;
	}
	/**
	 * Metodo que permite sobreescribir un archivo JSON dado un {@link ArrayList} de tipo {@link Domiciliario}
	 * @param domiciliario Parametro de tipo {@link ArrayList} de tipo {@link Domiciliario} con toda la infromacion que se
	  	quiere agragar al archivo JSON
	 * @return Retorna un dato de tipo boolean, true en caso de que se pueda agregar toda la informacion contenida en el 
	 	{@link ArrayList} de tipo {@link Domiciliario} dentro del archivo JSON
	 */
	
	public boolean SobreEscribirArchivoProducto(ArrayList<Domiciliario> domiciliario){
		
		try {
			fileExist();
		} catch (IOException e1) {

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
			ob.put("cel", domiciliario.get(i).getCel());
			ob.put("adress", domiciliario.get(i).getAdress());
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
	 * Metodo que permite agregar registros a partir de una variable tipo {@link Domiciliario} con la informacion del 
	  domiciliario
	 * @param domiciliarioAgregar parametro de tipo {@link ArrayList} de tipo {@link Domiciliario} con la informacion de 
	 	los nuevos registros o del registro
	 * @return Retorna un valor tipo booleano, true si se hapidido añadir el nuevo registro y false si no se pudo.
	 */
	public boolean agregarUnNuevoDomiciliario(Domiciliario domiciliarioAgregar) {
		try {
			fileExist();
		} catch (IOException e1) {
		
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
					ob.put("cel", domiciliario.get(i).getCel());
					ob.put("adress", domiciliario.get(i).getAdress());

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
			nuevoProducto.put("cel", domiciliarioAgregar.getCel());

			nuevoProducto.put("adress", domiciliarioAgregar.getAdress());

			content.add(nuevoProducto);
			Files.write(Paths.get(ruta), content.toJSONString().getBytes());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	/**
	 * metodo que permite buscar un registro en el archivo JSON y modificar las veriables que contiene.
	 * @param name parametro de tipo String que representa el nuevo nombre del domiciliario
	 * @param lastName parametro de tipo String que prepresenta el nuevo apellido del domiciliario
	 * @param identification Parametro de tipo long que prepresenta el numero de identificacion deldomiciliario
	 	este es inmodificable.
	 * @param birthday Parametro que rpepresenta un dato de tipo {@link DateUses} que contiene la informacion del dia,
	 	del mes y el año de nacimiento del domiciliario
	 * @param cel parametro tipo String que representa el nuevo numero de celular de el {@link Domiciliario}
	 * @param direction Parametro de tipo String que prepresenta el nuevo domicilio de el {@link Domiciliario}
	 * @return Retorna un dato de tipo boolean, true si se puede actualizar y false si hubo algun problema con el proceso.
	 * @throws IOException
	 */
	public boolean actualizarDomiciliario(String name, String lastName, long identification, DateUses birthday,String cel,String direction) throws IOException {

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
					ob.put("cel", cel);
					ob.put("adress", direction);
				}else {
					ob.put("name", domiciliario.get(i).getName());
					ob.put("lastName", domiciliario.get(i).getLastName());
					ob.put("identification", domiciliario.get(i).getIdentification());
					
					JSONObject date1 = new JSONObject();
					date1.put("year",domiciliario.get(i).getBirthday().getYear());
					date1.put("month",domiciliario.get(i).getBirthday().getMonth());
					date1.put("day",domiciliario.get(i).getBirthday().getDay());
					ob.put("birthday",date1);
					ob.put("cel", domiciliario.get(i).getCel());
					ob.put("adress", domiciliario.get(i).getAdress());
					
				}
				content.add(ob);
			}
			
			Files.write(Paths.get(ruta), content.toJSONString().getBytes());
			
			return true;	
		};
		
		return false;
	}
	/**
	 * Permite realizar una busqueda dentro del archivo JSON y eliminar un registro quer coinciada con el parametro ingresado
	 * @param identification Parametro que representa un dato tipo entero para identificar al Domiciliario que se va a eliminar del registro 
	 * @return Retorna un valor de tipo boolean, true cuando se haya eliminado y false cuando haya un error
	 */
	public boolean EliminarDomiciliario (int identification) {
		domiciliario=TraerTodoslosdomiciliarios();
		for (int i = 0; i < domiciliario.size(); i++) {
			
			if(domiciliario.get(i).getIdentification()==identification) {
				domiciliario.remove(i);
				i=0;
			}
		}
		if(domiciliario.get(0).getIdentification()==identification) {
			domiciliario.remove(0);
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
	/**
	 * Metodo que permite traer toda la informacion de el archivo JSOn donde se almacena la informacion y presentarlo en formad e 
	 	{@link ArrayList} de tipo {@link Domiciliario}
	 * @return Retorna un {@link ArrayList} de tipo {@link Domiciliario} con toda la informacion contenida en el archivo JSON
	 */
	public ArrayList<Domiciliario> TraerTodoslosdomiciliarios(){
		ObjectMapper mapper = new ObjectMapper();
		domiciliario= new ArrayList();
		try {
			domiciliario= mapper.readValue(new File(ruta),
					mapper.getTypeFactory().constructCollectionType(ArrayList.class, Domiciliario.class));

			return domiciliario;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

}
