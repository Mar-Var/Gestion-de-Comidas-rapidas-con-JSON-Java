package co.edu.uptc.logica.modelo;

public class Producto {
	private String name;
	private double price;
	
	public Producto() {
		
	}
	
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
