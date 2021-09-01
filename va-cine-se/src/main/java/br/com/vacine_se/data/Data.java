package br.com.vacine_se.data;

import java.time.LocalDate;

public class Data {
	private LocalDate dateOfArrival;
	private int vaccinesQuantity;
	
	public int getVaccinesQuantity() {
		return vaccinesQuantity;
	}
	public void setVaccinesQuantity(int vaccinesQuantity) {
		this.vaccinesQuantity = vaccinesQuantity;
	}
	public LocalDate getDateOfArrival() {
		return dateOfArrival;
	}
	public void setDateOfArrival(LocalDate dateOfArrival) {
		this.dateOfArrival = dateOfArrival;
	}
}
