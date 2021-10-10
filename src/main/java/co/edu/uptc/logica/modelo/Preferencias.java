package co.edu.uptc.logica.modelo;

public class Preferencias {
	private String nombre;
	private int cantidad;
	
	public Preferencias(String nombre, int cantidad) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	
	public void añadirCantidad() {
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
