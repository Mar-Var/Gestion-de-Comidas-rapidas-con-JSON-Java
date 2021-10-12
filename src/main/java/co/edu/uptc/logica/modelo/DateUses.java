package co.edu.uptc.logica.modelo;
/**
 * Clase que define una fecha
 * @author USUARIO
 *
 */
public class DateUses {
	private int year;
	private int month;
	private int day;
	/**
	 * metodo constructor vacio
	 */
	public DateUses() {
		
	}
	/**
	 * metodo Constructor
	 * @param year parametro tipo entero que representa el año de una fecha
	 * @param month parametro de tipo entero que representa el mes de una fecha
	 * @param day Parametro tipo entero  que representa el dia de una fecha
	 */
	public DateUses(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return day + "/" + month + "/" + year   ;
	}
	
	
	
}
