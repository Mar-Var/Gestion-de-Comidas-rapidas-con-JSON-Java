package co.edu.uptc.logica.modelo;

import java.time.LocalDate;
import java.util.Date;

public class Domiciliario {
	private String name;
	private String lastName;
	private long identification;
	private DateUses birthday;
	
	public Domiciliario() {
		
	}
	
	public Domiciliario(String name, String lastName, long identification, DateUses birthday) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.identification = identification;
		this.birthday = birthday;
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
	public long getIdentification() {
		return identification;
	}
	public void setIdentification(int identification) {
		this.identification = identification;
	}
	public DateUses getBirthday() {
		return birthday;
	}
	public void setBirthday(DateUses birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Domiciliario [name=" + name + ", lastName=" + lastName + ", identification=" + identification
				+ ", birthday=" + birthday + "]";
	}
	
	
}
