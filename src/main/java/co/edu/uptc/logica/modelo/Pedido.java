package co.edu.uptc.logica.modelo;

import java.time.LocalDate;
import java.util.*;;

public class Pedido {
	
	private String clientName;
	private String clientLastName;
	private String clientCell;
	private String direction;
	private String domiciliarioid;
	private ArrayList<ProductosSolicitados> orderedProducts;
	private DateUses date;
	private double cost;
	
	public Pedido() {
		
	}
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
