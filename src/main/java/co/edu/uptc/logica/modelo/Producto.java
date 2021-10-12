package co.edu.uptc.logica.modelo;
/**
 * clase que define a la entidad Producto
 * @author Marcos Esteban Vargas Avella
 *
 */
public class Producto {
	private String name;
	private double price;
	/**
	 * Constructor de clase vacio
	 */
	public Producto() {
		
	}
	/**
	 * Constructor de la clase para instanciar
	 * @param name parametro que representa el nombre del producto, des de tipo String
	 * @param price Parametro de tipo double que representa el valor del producto
	 */
	public Producto(String name,double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Producto [nombre=" + name + ", precio=" + price + "]";
	}


	
	
	
	

}
