package br.com.vacine_se.scheduling;

import java.time.LocalDate;


public class Scheduling {
	
	private final Long id;
	private final LocalDate date;
	private final Long vaccinationSiteId;
	
	public Scheduling(Long id, LocalDate date, Long vaccinationSiteId) {
		super();
		this.id = id;
		this.date = date;
		this.vaccinationSiteId = vaccinationSiteId;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public Long getVaccinationSiteId() {
		return vaccinationSiteId;
	}
	
	
    public Scheduling updateWith(Scheduling scheduling) {
        return new Scheduling(
           this.id,
           scheduling.getDate(),
           scheduling.getVaccinationSiteId()
        );
    }
	
}
