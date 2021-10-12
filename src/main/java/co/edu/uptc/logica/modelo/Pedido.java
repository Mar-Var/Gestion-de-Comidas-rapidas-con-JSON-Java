package co.edu.uptc.logica.modelo;

import java.time.LocalDate;
import java.util.*;;
/**
 * Clase que define la entidad de Pedido
 * @author Marcos Esteban Vargas Avella
 *
 */
public class Pedido {
	
	private String clientName;
	private String clientLastName;
	private String clientCell;
	private String direction;
	private String domiciliarioid;
	private ArrayList<ProductosSolicitados> orderedProducts;
	private DateUses date;
	private double cost;
	/**
	 * constructor de clase vacio
	 */
	public Pedido() {
		
	}
	/**
	 * constructor de clase para instanciacion
	 * @param clientName parametro de tipo String que representa el nombre de el cliente
	 * @param clientLastName Parametro de itpo String que representa el apellido del Cliente
	 * @param clientCell Paramatro de tipo String que representa el numero de contacto del Cliente
	 * @param direction parametro tipo String que representa la direccion a la que hay que llevar el pedido
	 * @param domiciliarioid Parametro tipo String que representa el numero de identificacion de quien realiza el domicilio.
	 * @param orderedProducts parametro de tipo {@link ArrayList} de tipo {@link ProductosSolicitados} con la informacion
	 	de los productos que se agregaron al pedido
	 * @param date parametro tipo {@link DateUses} que contiene la informacion de la fecha actual
	 * @param cost Parametro de tipo double que estima el valor de la venta del pedido
	 */
	public Pedido(String clientName, String clientLastName, String clientCell,String direction, String domiciliarioid,
			ArrayList<ProductosSolicitados> orderedProducts,DateUses date, double cost) {
		super();
		this.clientName = clientName;
		this.clientLastName = clientLastName;
		this.clientCell = clientCell;
		this.direction =  direction;
		this.domiciliarioid = domiciliarioid;
		this.orderedProducts = orderedProducts;
		this.date = date;
		this.cost=cost;
	}
	
	
	public double getCost() {
		return cost;
	}


	public void setCost(double cost) {
		this.cost = cost;
	}


	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}


	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientLastName() {
		return clientLastName;
	}
	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}
	public String getClientCell() {
		return clientCell;
	}
	public void setClientCell(String clientCell) {
		this.clientCell = clientCell;
	}
	public String getDomiciliarioid() {
		return domiciliarioid;
	}
	public void setDomiciliarioid(String domiciliarioid) {
		this.domiciliarioid = domiciliarioid;
	}

	public ArrayList<ProductosSolicitados> getOrderedProducts() {
		return orderedProducts;
	}
	public void setOrderedProducts(ArrayList<ProductosSolicitados> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}
	public DateUses getDate() {
		return date;
	}
	public void setDate(DateUses date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Pedido [clientName=" + clientName + ", clientLastName=" + clientLastName + ", clientCell=" + clientCell
				+ ", direction=" + direction + ", domiciliarioid=" + domiciliarioid + ", orderedProducts="
				+ orderedProducts.toString() + ", date=" + date + "]";
	}


	
	
	
	
	

}
