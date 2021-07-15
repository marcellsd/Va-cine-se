package br.com.vacine_se.vaccination_site;
import org.springframework.data.annotation.Id;

import br.com.vacine_se.utils.IdGenerator;

public class VaccinationSite {
	private final String id;
	private final String name;
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
