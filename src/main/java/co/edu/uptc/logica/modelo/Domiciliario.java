package co.edu.uptc.logica.modelo;

import java.time.LocalDate;
import java.util.Date;

public class Domiciliario {
	private String name;
	private String lastName;
	private long identification;
	private String cel;
	private String adress;
	private DateUses birthday;
	
	public Domiciliario() {
		
	}
	
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
