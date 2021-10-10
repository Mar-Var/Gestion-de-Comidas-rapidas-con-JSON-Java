package co.edu.uptc.logica.modelo;

public class ProductosSolicitados {
	private String name;
	private int amount;
	public ProductosSolicitados() {
		
	}
	public ProductosSolicitados(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "ProductosSolicitados [name=" + name + ", amount=" + amount + "]";
	}

	
	

}
