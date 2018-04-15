package com.hanszimmer.models;

import com.hanszimmer.annotation.Column;
import com.hanszimmer.annotation.Table;

@Table(name="city")
public class City {

	/**
	 * 
	 * @param id
	 * @param name
	 * @param countryCode
	 * @param district
	 * @param population
	 */
	public City(int id, String name, String countryCode, String district, Long population) {
		super();
		this.id = id;
		this.name = name;
		this.countryCode = countryCode;
		this.district = district;
		this.population = population;
	}
	
	public City() {
		super();
	}

	@Column(name="ID",isPrimary=true)
	private int id;
	@Column(name="Name")
	private String name;
	@Column(name="CountryCode")
	private String countryCode;
	@Column(name="District")
	private String district;
	@Column(name="Population")
	private Long population;

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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", district=" + district + ", population=" + population + "]";
	}
	
	
	
	
}
