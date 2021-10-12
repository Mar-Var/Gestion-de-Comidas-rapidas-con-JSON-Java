package co.edu.uptc.logica.modelo;
/**
 * Clase que define la entidad de productos que agregados al carrito
 * @author Marcos Esteban Vargas Avella
 *
 */
public class ProductosSolicitados {
	private String name;
	private int amount;
	/**
	 * Cosntructor de clase vacio
	 */
	public ProductosSolicitados() {
		
	}
	/**
	 * Constructor de clase para instanciar
	 * @param name parametro de tipo String que representa el nombre del producto solicitado
	 * @param amount Paramtro de tipo entero que representa la cantidad de productos que se solicitan
	 */
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
		return name + ":" + amount + "\n";
	}

	
	

}
