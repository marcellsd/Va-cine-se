package br.com.vacine_se.vaccination_site;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
@EnableMapRepositories
public class VaccinationSiteService {
	private final CrudRepository<VaccinationSite, Long> repository;
	
	public VaccinationSiteService(CrudRepository<VaccinationSite, Long> repository) {
		this.repository = repository;
		this.repository.saveAll(getVaccinationSiteFromData());
	}
	
	
	private static List<VaccinationSite> getVaccinationSiteFromData(){
		return List.of(
				new VaccinationSite(1L, "UPA-Neópolis", 1L),
				new VaccinationSite(2L, "UPA-Candelária", 2L)
				);
	}
	
	public List<VaccinationSite> findAll() {
        List<VaccinationSite> list = new ArrayList<>();
        Iterable<VaccinationSite> vaccinationSites = repository.findAll();
        vaccinationSites.forEach(list::add);
        return list;
    }
	public Optional<VaccinationSite> find(Long id) {
		return repository.findById(id);
	}
	
	public VaccinationSite create(VaccinationSite vaccinationSite) {
		VaccinationSite copy = new VaccinationSite(vaccinationSite.getId(), vaccinationSite.getName(), vaccinationSite.getDistrictId());
		return repository.save(copy);
	}
	
	public Optional<VaccinationSite> update( Long id, VaccinationSite newVaccinationSite) {
        return repository.findById(id)
                .map(oldVaccinationSite -> {
                	VaccinationSite updated = oldVaccinationSite.updateWith(newVaccinationSite);
                   return repository.save(updated);
                });
    }
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}

