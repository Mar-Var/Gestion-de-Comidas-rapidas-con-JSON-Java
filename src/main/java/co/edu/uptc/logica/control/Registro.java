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

public class Registro {
	
	ArrayList<Producto> productos;
	ArrayList<Pedido> pedidos;
	ArrayList<Domiciliario> domiciliario;
	PedidoPersistence pedidoPersistence;
	DomiciliariosPersistence domiciliarioPersistence;
	ProductoPersistence productoPersistence;
	double mayor = 0;
	
	public Registro() {
		this.productoPersistence= new ProductoPersistence();
		this.pedidoPersistence= new PedidoPersistence();
		this.domiciliarioPersistence=new DomiciliariosPersistence();
	}
	
	public boolean agregarProducto(String name, double price) {
		Producto p = new Producto(name, price);
		return productoPersistence.AgregarUnNuevoProducto(p);
	}
	
	public boolean actualizarProducto(String name , double price) throws IOException {
		return productoPersistence.actualizarProducto(name, price);
	}
	public boolean eliminarProducto(String name) {
		return productoPersistence.EliminarProducto(name);
	}
	public ArrayList<Producto> mostrarTodoslosProductos(){
		return productoPersistence.TraerTodosloProductos();
	}
	public ArrayList<Domiciliario> mostrarTodoslosDomiciliarios(){
		return domiciliarioPersistence.TraerTodoslosdomiciliarios();
	}
	public ArrayList<Pedido> mostrarTodoslosPedidos(){
		return pedidoPersistence.TraerTodoslosPedidios();
	}
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
	public boolean agregarDomiciliario(String name, String lastName, int identification, DateUses birthday, String cel,String direction){
		Domiciliario d = new Domiciliario(name,lastName,identification,birthday,cel,direction);
		return domiciliarioPersistence.agregarUnNuevoDomiciliario(d);		
	}
	public boolean actualizarDomiciliario(String name, String lastName, int identification, DateUses birthday, String cel,String direction) throws IOException {
		return domiciliarioPersistence.actualizarDomiciliario(name, lastName, identification, birthday,cel,direction);
	}
	public boolean eliminarDomiciliario(int identification) {
		return domiciliarioPersistence.EliminarDomiciliario(identification);
	}
	
	public ArrayList<String[]> obtenerLiquidaciones(){
		pedidos= pedidoPersistence.TraerTodoslosPedidios();
		domiciliario= domiciliarioPersistence.TraerTodoslosdomiciliarios();
		productos=productoPersistence.TraerTodosloProductos();
		ArrayList<String[]> liquidar = new ArrayList<>();
		mayor=0;

		for (int i = 0; i < domiciliario.size(); i++) {
			double aPagar=0;
			for (int j = 0; j < pedidos.size(); j++) {
				
				if(pedidos.get(j).getDomiciliarioid().equals(String.valueOf(domiciliario.get(i).getIdentification()))) {
					System.out.println("Entra"+pedidos.get(j).getCost());
					aPagar+=pedidos.get(j).getCost()*0.2;
				}
			}
			String[] personalIquidar= {domiciliario.get(i).getName(),domiciliario.get(i).getLastName()
					,String.valueOf(aPagar)};
			System.out.println(personalIquidar[0]+"/"+aPagar);
			liquidar.add(personalIquidar);
		}
		
		
		for (String[] object : liquidar) {

			if(Double.parseDouble(object[2])>mayor) {
				mayor=Double.parseDouble(object[2]);
			}
		}
		for (int i = 0; i < liquidar.size(); i++) {
			if(Double.parseDouble(liquidar.get(i)[2]) == mayor ) {
				double añadirbono=Double.parseDouble(liquidar.get(i)[2])+20000;
				liquidar.get(i)[2]=String.valueOf(añadirbono);
			}
			
		}
		 return liquidar;
	}
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
	
	public double obtenerGananciaPorFecha(ArrayList<Pedido> pedidosEnRango) {
		double value=0;
		for (Pedido pedido : pedidosEnRango) {
			value+=pedido.getCost();
		}
		return value;
	}
	
	public String mostrarPreferenciaproducto(){	
		return mostrarPreferenciasListado().get(mostrarPreferenciasListado().size()-1).getNombre();
	}
	
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
