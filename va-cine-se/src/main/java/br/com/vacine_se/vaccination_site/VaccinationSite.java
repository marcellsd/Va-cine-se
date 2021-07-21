package br.com.vacine_se.vaccination_site;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;

import br.com.vacine_se.utils.IdGenerator;

public class VaccinationSite {
	private String id;
	@NotNull(message = "name required")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "name must be a string")
	private final String name;
	@NotNull(message = "districtId is required")
    @Positive(message = "districtId must be positive")
	private final Long districtId;
	
	public VaccinationSite(String name, Long districtId){
		this.id = IdGenerator.getHash();
		this.name = name;
		this.districtId = districtId;
	}
    @Id
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	
	public Long getDistrictId() {
		return this.districtId;
	}
	
	public VaccinationSite updateWith(VaccinationSite district) {
		return new VaccinationSite(district.getName(), district.getDistrictId());
	}
}
