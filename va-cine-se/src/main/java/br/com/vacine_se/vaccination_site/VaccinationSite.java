package br.com.vacine_se.vaccination_site;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;


public class VaccinationSite {
	private int id;
	@NotNull(message = "name required")
	@Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$", message = "name must be a string")
	private final String name;
	@NotNull(message = "districtId is required")
    @Positive(message = "districtId must be positive")
	private final int districtId;
	private int totalOfVaccines;
	
	public VaccinationSite(String name, int districtId){
		this.name = name;
		this.districtId = districtId;
		this.totalOfVaccines = 100;
	}
    @Id
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}
	
	public int getDistrictId() {
		return this.districtId;
	}

	public int getTotalOfVaccines() {
		return this.totalOfVaccines;
	}

	public void setTotalOfVacines(int total) {
		this.totalOfVaccines = total;
	}
}
