package br.com.vacine_se.vaccination_site;
import org.springframework.data.annotation.Id;

public class VaccinationSite {
	private final Long id;
	private final String name;
	private final Long districtId;
	
	public VaccinationSite(Long id, String name, Long districtId){
		this.id = id;
		this.name = name;
		this.districtId = districtId;
	}
    @Id
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Long getDistrictId() {
		return this.districtId;
	}
	
	public VaccinationSite updateWith(VaccinationSite district) {
		return new VaccinationSite(this.id, district.getName(), district.getDistrictId());
	}
}
