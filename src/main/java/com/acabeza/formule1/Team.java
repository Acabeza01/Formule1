package com.acabeza.formule1;

import java.util.Comparator;
import java.util.Map;


public class Team {
	private String naam;
	private Map<String, Integer> coureurs;
	
	public Team(String naam) {
		super();
		this.naam = naam;
	}
	
	public Team(String naam, Map<String, Integer> coureurs) {
		super();
		this.naam = naam;
		this.coureurs = coureurs;
	}



	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Map<String, Integer> getCoureurs() {
		return coureurs;
	}

	public void setCoureurs(Map<String, Integer> coureurs) {
		
		this.coureurs = coureurs;

		
	}


	
}
