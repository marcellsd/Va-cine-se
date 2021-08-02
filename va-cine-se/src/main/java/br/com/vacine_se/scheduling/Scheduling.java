package br.com.vacine_se.scheduling;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;



public class Scheduling {
	private int id;
	@NotNull(message = "vaccination date is required")
	private  LocalDate date;
	@NotNull(message = "Vaccination Site ID is required")
	private int vaccinationSiteId;
	
	public Scheduling(LocalDate date, int vaccinationSiteId) {
		this.date = date;
		this.vaccinationSiteId = vaccinationSiteId;
	}

	@Id
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getVaccinationSiteId() {
		return this.vaccinationSiteId;
	}
	public void setVaccinationSiteId(int vaccinationSiteId) {
		 this.vaccinationSiteId = vaccinationSiteId;
	}
	
	
    public Scheduling updateWith(Scheduling scheduling) {
        return new Scheduling(
           scheduling.getDate(),
           scheduling.getVaccinationSiteId()
        );
    }
	
}
