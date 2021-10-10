package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import co.edu.uptc.logica.control.Registro;
import co.edu.uptc.logica.modelo.DateUses;
import co.edu.uptc.logica.modelo.Producto;
import co.edu.uptc.logica.modelo.ProductosSolicitados;
import co.edu.uptc.persistencia.ProductoPersistence;

public class Runner {

	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Registro r= new Registro();
		Producto hamburguesa = new Producto("Hamburguesa",12);
		r.agregarProducto("Hamburguesa", 12);
	//	p.EliminarProducto("hamburguesa");
		r.mostrarTodoslosProductos().forEach(System.out::println);
		LocalDate now = LocalDate.now();
		DateUses d = new DateUses(2021, 10, 9);
		System.out.println(r.agregarDomiciliario("Pedrito", "Perez", 123456789,d ));;
		System.out.println(r.mostrarTodoslosDomiciliarios());;
		ArrayList<ProductosSolicitados> a= new ArrayList<>();
		ProductosSolicitados ps= new ProductosSolicitados("hamburguesa", 55);
		a.add(ps);
		System.out.println(r.agregarPedido("Pedro", "Pablo", "3224031216", "N/A","1052416590", a));
		r.mostrarTodoslosPedidos().forEach(System.out::println);
	}

}
