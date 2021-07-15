package br.com.vacine_se.scheduling;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import br.com.vacine_se.utils.IdGenerator;


public class Scheduling {
	
	private String id;
	private final LocalDate date;
	private final String vaccinationSiteId;
	
	public Scheduling(LocalDate date, String vaccinationSiteId) {
		super();
		this.id = IdGenerator.getHash();
		this.date = date;
		this.vaccinationSiteId = vaccinationSiteId;
	}

	@Id
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public String getVaccinationSiteId() {
		return vaccinationSiteId;
	}
	
	
    public Scheduling updateWith(Scheduling scheduling) {
        return new Scheduling(
           scheduling.getDate(),
           scheduling.getVaccinationSiteId()
        );
    }
	
}
