package co.edu.uptc.logica.control;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import co.edu.uptc.logica.modelo.DateUses;
import co.edu.uptc.logica.modelo.Domiciliario;
import co.edu.uptc.logica.modelo.Pedido;
import co.edu.uptc.logica.modelo.Preferencias;
import co.edu.uptc.logica.modelo.Producto;
import co.edu.uptc.logica.modelo.ProductosSolicitados;
import co.edu.uptc.persistencia.DomiciliariosPersistence;
import co.edu.uptc.persistencia.PedidoPersistence;
import co.edu.uptc.persistencia.ProductoPersistence;
/**
 * Esta clase permite gestionar la informacion proveniente del paquete persistencia, para ser usada con el GUI
 * @author Marcos Esteban Vargas Avella
 *
 */
public class Registro {
	
	ArrayList<Producto> productos;
	ArrayList<Pedido> pedidos;
	ArrayList<Domiciliario> domiciliario;
	PedidoPersistence pedidoPersistence;
	DomiciliariosPersistence domiciliarioPersistence;
	ProductoPersistence productoPersistence;
	double mayor = 0;
	/**
	 * Constructor de la clase: Trae toda la informacion proveniente de los archivos JSON
	 */
	
	public Registro() {
		this.productoPersistence= new ProductoPersistence();
		this.pedidoPersistence= new PedidoPersistence();
		this.domiciliarioPersistence=new DomiciliariosPersistence();
	}
	
	public Producto encontrarProducto(String nombre) {
		
		return productoPersistence
				.TraerTodosloProductos()
				.stream()
				.filter(productoAux->productoAux.getName().equals(nombre))
				.findFirst()
				.map(productoAux->productoAux)
				.orElse(null);
	}
	
	public Domiciliario encontrarDomiciliario(long id) {
		return domiciliarioPersistence
				.TraerTodoslosdomiciliarios()
				.stream()
				.filter(domiciliarioAux->domiciliarioAux.getIdentification()==id)
				.findFirst()
				.map(domiciliarioAux->domiciliarioAux)
				.orElse(null);
	}
	/**
	 * Metodo que agrega un objeto de tipo {@link Producto} dentro de un archivo JSON
	 * @param name El parametro name es un dato de tipo String y representa el nombre de un nuevo producto
	 * @param price El parametro price es un dato de tipo entero y representa el valor que tendra ese nuevo producto
	 * @return Retorna un valor booleano, true si se ha agregado correctamente al Archivo JSON o caso contrario retornara false
	 */
	
	public boolean agregarProducto(String name, double price) {
		Producto p = new Producto(name, price);
		return productoPersistence.AgregarUnNuevoProducto(p);
	}
	/**
	 * Este metodo permite
	 * @param name Parametro de tipo String que representa el nombre del {@link Producto} que se quiere actualizar
	 * @param price Parametro de tipo double que representa el nuevo precio que se le quiere asignar al {@link Producto} 
	 * @return Retorna un dato booleano, true si se puede actualizar el campo en el Archivo JSON , false de lo contrario.
	 * @throws IOException lanza Excepciones
	 */
	
	public boolean actualizarProducto(String name , double price) throws IOException {
		return productoPersistence.actualizarProducto(name, price);
	}
	
	/**
	 * Metodo que permite eliminar un registro de un {@link Producto} en el archivo JSON 
	 * @param name Parametro de tipo String que representa el nombre del {@link Producto} que se quiere eliminar
	 * @return Retorna un valor booleano. true si puede eliminar el registro del archivo JSON y false si no se puede
	 	o no se encuentra el name.
	 */
	public boolean eliminarProducto(String name) {
		return productoPersistence.EliminarProducto(name);
	}
	/**
	 * metodo que permite guardar traer toda la informacion de los productos registratados en el archivo JSON
	 * @return Retorna un ArrayList de tipo {@link Producto} con la informacion contenida en el archivo JSON 
	 */
	public ArrayList<Producto> mostrarTodoslosProductos(){
		return productoPersistence.TraerTodosloProductos();
	}
	/**
	 * metodo que permite guardar traer toda la informacion de los domiciliarios registratados en el archivo JSON
	 * @return Retorna un ArrayList de tipo {@link Domiciliario} con la informacion contenida en el archivo JSON
	 */
	public ArrayList<Domiciliario> mostrarTodoslosDomiciliarios(){
		return domiciliarioPersistence.TraerTodoslosdomiciliarios();
	}
	/**
	 * metodo que permite guardar traer toda la informacion de los pedidos registratados en el archivo JSON
	 * @return Retorna un ArrayList de tipo {@link Pedido} con la informacion contenida en el archivo JSON
	 */
	public ArrayList<Pedido> mostrarTodoslosPedidos(){
		return pedidoPersistence.TraerTodoslosPedidios();
	}
	/**
	 * Metodo que permite ingresar un nuevo registro(objeto) de {@link Pedido} a un archivo JSON
	 * @param clientname Parametro de tipo String que representa el nombre de el nombre de el cliente.
	 * @param clienteLastName Parametro de tipo String que representa el apellido del cliente.
	 * @param clienteCell Parametro de tipo String que reprecenta un numero de telefono de contacto del cliente
	 * @param direction Parametro de tipo String que representa la direccion de domicilio del cliente
	 * @param domiciliarioid Parametro de tipo String que representa el numero de identificacion del Domiciliario que 
	 	realizara el  pedido
	 * @param orderedProducts Parametro de tipo ArrayList de la clase {@link ProductosSolicitados} que muestra los productos 
	 	que se añaden a un pedido
	 * @return Retorna un valor boolean,true si se puede agregar el pedido y false si por alguna razon no se puede.
	 */
	public boolean agregarPedido(String clientname,String clienteLastName, String clienteCell ,String direction
			,String domiciliarioid,ArrayList<ProductosSolicitados> orderedProducts) {
		//Hayq ue quitar ese cost del parametro del metodo
		double cost=0;
		
		for (ProductosSolicitados productosSolicitados : orderedProducts) {
			for (Producto product : productoPersistence.TraerTodosloProductos()) {
				if(product.getName().equals(productosSolicitados.getName())) {
					cost+=product.getPrice()*productosSolicitados.getAmount();
				}
			}
		}
		cost+=(cost*0.3);
		
		DateUses date= new DateUses(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
		Pedido p = new Pedido(clientname, clienteLastName, clienteCell, direction, domiciliarioid, orderedProducts, date, cost);	
		return pedidoPersistence.AgregarUnNuevoPedido(p);
	}
	/**
	 * Metodo que permite agregar un nuevo registro(objeto) de la clase {@link Domiciliario} a un archivo JSON 
	 * @param name Parametro de tipo String que representa el nombre del domiciliario 
	 * @param lastName Parametro de tipo String que representa el apellido del nuevo Domiciliario
	 * @param identification parametro de tipo entero que representa el numero de identificacion de el Domiciliario
	 * @param birthday Parametro de tipo {@link DateUses} donde se almacenan el dia, el mes y el año de nacimiento del Domiciliario
	 * @param cel Parametro de tipo String que representa el numero de celular de el Domiciliario
	 * @param direction Parametro de tipo String que represetnta la direccion del domicilio del domiciliario;
	 * @return retorna un dato de tipo booleano, true si el nuevo domiciliario pudo ser agregado y false si no se puedo completar el proceso;
	 */
	public boolean agregarDomiciliario(String name, String lastName, int identification, DateUses birthday, String cel,String direction){
		Domiciliario d = new Domiciliario(name,lastName,identification,birthday,cel,direction);
		return domiciliarioPersistence.agregarUnNuevoDomiciliario(d);		
	}
	/**
	 * Metodo que permite buscar a un {@link Domiciliario} dentro de un archivo JSOn y modificar el registro.
	 * @param name parametro de tipo String que representa el nuevo nombre del Domiciliario
	 * @param lastName Parametro de tipo String que representa el nuevo apellido del Domiciliario
	 * @param identification Parametro de tipo entero que representa el numero de identificacion del Domiciliario. No se puede modificar.
	 * @param birthday parametro de tipo {@link DateUses} que contiene el nuevo dia, mes y año de nacimiento del Domiciliario
	 * @param cel paramatro de tipo String que representa el nuevo numero de contacto del Domicialiario
	 * @param direction Parametro de tipo String que prepresenta la nueva direccionde domicilio del Domiciliario
	 * @return Retorna un valor de tipo booleano, true si fue agregado con exito, de lo contrario retornara false
	 * @throws IOException Lanza Excepcion
	 */
	public boolean actualizarDomiciliario(String name, String lastName, int identification, DateUses birthday, String cel,String direction) throws IOException {
		return domiciliarioPersistence.actualizarDomiciliario(name, lastName, identification, birthday,cel,direction);
	}
	/**
	 * Metodo que permite eliminar un registro de tipo {@link Domiciliario} de el registro del arcivo JSON
	 * @param identification Parametro de tipo entero que sirve para rastrear el numero de identificacion del Domiciliario
	 * @return Retorna un valor de tipo boolean, true si se pudo eliminar el registro, de lo contratrio y si no existe reportna false;
	 */
	public boolean eliminarDomiciliario(int identification) {
		return domiciliarioPersistence.EliminarDomiciliario(identification);
	}
	
	/**
	 * Metodo que permite obetener un ArrayList de tipo arreglo de estrings donded se guardara la informacion de el nombre 
	 	de el Domiciliario y el total que se le debe pagar mas bonos	
	 * @param date Indica una fecha en la que se desea buscar la liquidacion del Domiciliario
	 * @return Retorna un dato de tipo {@link ArrayList} con la informacion de Domiciliarios y su liquidacion de acuerdo 
	 	con las ventas realizadas y bono por ser el mayor vendedor.
	 */
	public ArrayList<String[]> obtenerLiquidaciones(DateUses date){
		pedidos= pedidoPersistence.TraerTodoslosPedidios();
		domiciliario= domiciliarioPersistence.TraerTodoslosdomiciliarios();
		productos=productoPersistence.TraerTodosloProductos();
		ArrayList<String[]> liquidar = new ArrayList<>();
		mayor=0;
		
		ArrayList<Pedido> pedidosEnRango = new ArrayList<Pedido>();
		for (Pedido pedido : pedidos) {
			if(pedido.getDate().getYear()==date.getYear() && pedido.getDate().getMonth()==date.getMonth()
					&& pedido.getDate().getDay()==date.getDay()) {
				pedidosEnRango.add(pedido);
			}
			else {
				return null;
				
			}
		}		
		
		

		for (int i = 0; i < domiciliario.size(); i++) {
			double aPagar=0;
			for (int j = 0; j < pedidosEnRango.size(); j++) {
				
				if(pedidosEnRango.get(j).getDomiciliarioid().equals(String.valueOf(domiciliario.get(i).getIdentification()))) {

					aPagar+=pedidosEnRango.get(j).getCost()*0.2;
				}
			}
			String[] personalIquidar= {domiciliario.get(i).getName(),domiciliario.get(i).getLastName()
					,String.valueOf(aPagar)};

			liquidar.add(personalIquidar);
		}
		
		
		for (String[] object : liquidar) {

			if(Double.parseDouble(object[2])>mayor) {
				mayor=Double.parseDouble(object[2]);
			}
		}
		for (int i = 0; i < liquidar.size(); i++) {
			if(Double.parseDouble(liquidar.get(i)[2]) == mayor ) {
				double anadirbono=Double.parseDouble(liquidar.get(i)[2])+20000;
				liquidar.get(i)[2]=String.valueOf(anadirbono);
			}
			
		}
		 return liquidar;
	}
	/**
	 * metodo que permite obtener un ArrayLsit de tipo {@link Pedido} con los pedidos realizados en determinada fecha
	 * @param date Parametro de tipo {@link DateUses} que señala la fecha que se desea buscar dentro del archivo JSON de Pedidos
	 * @return Retorna un {@link ArrayList} de tipo {@link Pedido} con la informacion de todos los pedidos generados en una fecha en
	 	especifico
	 */
	public ArrayList<Pedido> listarFechasGanancias(DateUses date){
		pedidos= pedidoPersistence.TraerTodoslosPedidios();
		ArrayList<Pedido> pedidosEnRango = new ArrayList<Pedido>();
		for (Pedido pedido : pedidos) {
			if(pedido.getDate().getYear()==date.getYear() && pedido.getDate().getMonth()==date.getMonth()
					&& pedido.getDate().getDay()==date.getDay()) {
				pedidosEnRango.add(pedido);
			}
		}
		return pedidosEnRango;
	}
	/**
	 * Metodo que permite obtener el valor total de la ventas realizadas en una determinada fecha
	 * @param pedidosEnRango parametro de tipo {@link ArrayList} de tipo {@link Pedido} con la informacion de los productos
	 	vendidos en determinada fecha.
	 * @return Retorna un valor de tipo double con el total del valor de la suma de todos los pedidos vendidos en una fecha determinada
	 */
	public double obtenerGananciaPorFecha(ArrayList<Pedido> pedidosEnRango) {
		double value=0;
		for (Pedido pedido : pedidosEnRango) {
			value+=pedido.getCost();
		}
		return value;
	}
	
	/**
	 * metodo que permite Mostrar que pedido fue el que mas se vendio en todo el registro
	 * @return Retirna un valor tipo String con el nombre el {@link Producto} que mas se ha vendido
	 */
	
	public String mostrarPreferenciaproducto(){	
		return mostrarPreferenciasListado().get(mostrarPreferenciasListado().size()-1).getNombre();
	}
	/**
	 * metodo que permite Mostrar una lista ordenada de los productos segun la cantidad de productos vendidos.
	 * @return Retorna un {@link ArrayList} de tipo {@link Preferencias} ordenado segun la cantidad de ejemplares vendidos.
	 */
	
	public ArrayList<Preferencias> mostrarPreferenciasListado(){
		pedidos= pedidoPersistence.TraerTodoslosPedidios();
		productos= productoPersistence.TraerTodosloProductos();
		ArrayList<Preferencias> preferencias = new ArrayList<Preferencias>();
		int contador=0;
		for (Producto p : productos) {
			contador=0;
			for (Pedido pe : pedidos) {
				for (int i = 0; i < pe.getOrderedProducts().size(); i++) {
					if(pe.getOrderedProducts().get(i).getName().equals(p.getName())) {
						contador+=pe.getOrderedProducts().get(i).getAmount();
					}
				}
			}
			preferencias.add(new Preferencias(p.getName(), contador));
		}
		Collections.sort(preferencias, new Comparator<Preferencias>() {
			@Override
			public int compare(Preferencias p1, Preferencias p2) {
				return new Integer(p1.getCantidad()).compareTo(new Integer(p2.getCantidad()));
			}
		});
		
		return preferencias;
	}
	
}
