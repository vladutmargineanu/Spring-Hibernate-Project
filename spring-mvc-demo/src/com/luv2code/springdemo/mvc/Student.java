package com.luv2code.springdemo.mvc;

import java.util.LinkedHashMap;

public class Student {
	
	private String firstName;
	private String lastName;
	private String countryWhereBorn;
	public String countryToStudy;
	private LinkedHashMap<String, String> countryOptions;
	private String favoriteLanguage;
	private String[] operatingSystems;
	
	public Student() {
		
		// populate country options: used ISO country code
		countryOptions = new LinkedHashMap<>();
		
		countryOptions.put("RO", "Romania");
		countryOptions.put("FR", "France");
		countryOptions.put("IT", "Italy");
		countryOptions.put("DE", "Germany");
		countryOptions.put("SP", "Spain");
		countryOptions.put("US", "United State of America");
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountryWhereBorn() {
		return countryWhereBorn;
	}

	public void setCountryWhereBorn(String countryWhereBorn) {
		this.countryWhereBorn = countryWhereBorn;
	}

	public String getCountryToStudy() {
		return countryToStudy;
	}

	public void setCountryToStudy(String countryToStudy) {
		this.countryToStudy = countryToStudy;
	}

	public LinkedHashMap<String, String> getCountryOptions() {
		return countryOptions;
	}

	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}

	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}

	public String[] getOperatingSystems() {
		return operatingSystems;
	}

	public void setOperatingSystems(String[] operatingSystems) {
		this.operatingSystems = operatingSystems;
	}
}
