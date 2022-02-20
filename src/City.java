/**
 * @author Sofia Rhoades Ortega - scrhoadesortega
 * CIS175 - Fall 2021
 * Feb 8, 2022
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cities")
public class City {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private	int	id;
	@Column(name="NAME")
	private String name;
	@Column(name="ZIPCODE")
	private String zip;
	@Column(name="POPULATION")
	private int population;
	
	public City() {
		super();
	}

	public City(String name, String zip, int population) {
		super();
		this.name = name;
		this.zip = zip;
		this.population = population;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int newPop) {
		this.population = newPop;
	}
	
	public String returnCityInfo() {
		return this.name + "(" + this.zip + "): " + this.population + " Residents";	
	}
}
