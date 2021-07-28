package br.com.vacine_se.vaccination_site;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class VaccinationSiteService {

	private VaccinationSiteRepository repository;
	
	private static List<VaccinationSite> getVaccinationSiteFromData(){
		return List.of(
				new VaccinationSite("UPA-Neópolis", 1L),
				new VaccinationSite("UPA-Candelária", 2L)
				);
	}
	
	public VaccinationSiteService(VaccinationSiteRepository repository) {
		this.repository = repository;
		for (VaccinationSite vacin : getVaccinationSiteFromData()) {
			this.repository.save(vacin);
		}
	}
	
	
	public List<VaccinationSite> findAll() {
        List<VaccinationSite> list = new ArrayList<>();
        Iterable<VaccinationSite> vaccinationSites = repository.getAll();
        vaccinationSites.forEach(list::add);
        return list;
    }
	public Optional<VaccinationSite> find(int id) {
		return repository.getById(id);
	}
	
	public VaccinationSite create(VaccinationSite vaccinationSite) {
		return repository.save(vaccinationSite);
	}
	
	public VaccinationSite update(int id, VaccinationSite newVaccinationSite){
		VaccinationSite vaccinationSite = this.find(id).orElseThrow();
		newVaccinationSite.setId(vaccinationSite.getId());
		return repository.update(newVaccinationSite);
	}
	
	public void delete(int id) {
		VaccinationSite vaccinationSite = this.find(id).orElseThrow();
		repository.delete(vaccinationSite);
	}
}

