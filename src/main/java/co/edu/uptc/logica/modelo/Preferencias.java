package co.edu.uptc.logica.modelo;
/**
 * Clase que define la el nombre  del producto t la cantidad de ellos que se ha vendido 
 * @author Marcos Esteban Vargas Avella
 *
 */
public class Preferencias {
	private String nombre;
	private int cantidad;
	
	/**
	 * Metodo constructor de la clase
	 * @param nombre parametro de tipo String que representa el nombre del producto
	 * @param cantidad parametro de tipo entero que representa la cantidad de productos vendidos.
	 */
	
	public Preferencias(String nombre, int cantidad) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	
	public void anadirCantidad() {
		this.cantidad+=1;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Preferencias [nombre=" + nombre + ", cantidad=" + cantidad + "]";
	}
	
	

}
