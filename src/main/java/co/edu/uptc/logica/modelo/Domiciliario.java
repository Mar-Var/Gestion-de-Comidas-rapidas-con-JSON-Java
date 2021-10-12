package co.edu.uptc.logica.modelo;

import java.time.LocalDate;
import java.util.Date;
/**
 * Clase que define a la entidad Domiciliario
 * @author USUARIO
 *
 */
public class Domiciliario {
	private String name;
	private String lastName;
	private long identification;
	private String cel;
	private String adress;
	private DateUses birthday;
	/**
	 * Constructor de clase vacio
	 */
	public Domiciliario() {
		
	}
	/**
	 * Constructor de clase para instanciacion
	 * @param name Parametro tipo String que representa el nombre del domiciliario
	 * @param lastName Parametro tipo String que representan el apellido de el domiciliario
	 * @param identification parametro de tipo long que representa el numero de identificaciond el domiciliario
	 * @param birthday Representa un datot ipo DateUses que contiene la informacion relevante a una fecha
	 * @param cel representa un dato tipo String que representa el numero de telefono del Domiciliario
	 * @param adress Parametro de tipo String que representa la direccion de residencia del Domiciliario
	 */
	
	public Domiciliario(String name, String lastName, long identification, DateUses birthday, String cel,String adress) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.identification = identification;
		this.birthday = birthday;
		this.cel=cel;
		this.adress=adress;
	}
	
	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setIdentification(long identification) {
		this.identification = identification;
	}

	public long getIdentification() {
		return identification;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public DateUses getBirthday() {
		return birthday;
	}
	public void setBirthday(DateUses birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Domiciliario [name=" + name + ", lastName=" + lastName + ", identification=" + identification + ", cel="
				+ cel + ", adress=" + adress + ", birthday=" + birthday + "]";
	}

	
	
	
}
