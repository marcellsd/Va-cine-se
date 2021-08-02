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
	private int dosesAmount = 1;
	
	public VaccinationSite(String name, int districtId){
		this.name = name;
		this.districtId = districtId;
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
	public int getDosesAmount() {
		return dosesAmount;
	}
	public void setDosesAmount(int dosesAmount) {
		this.dosesAmount = dosesAmount;
	}
}
