package br.com.vacine_se.district;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
@EnableMapRepositories
public class DistrictService {
	private final CrudRepository<District, Long> repository;
	
	public DistrictService(CrudRepository<District, Long> repository) {
		this.repository = repository;
		this.repository.saveAll(getDistrictFromData());
	}
	
	
	private static List<District> getDistrictFromData(){
		return List.of(
				new District(1L, "Neópolis"),
				new District(2L, "Candelária")
				);
	}
	
	public List<District> findAll() {
        List<District> list = new ArrayList<>();
        Iterable<District> Districts = repository.findAll();
        Districts.forEach(list::add);
        return list;
    }
	public Optional<District> find(Long id) {
		return repository.findById(id);
	}
	
	public District create(District district) {
		District copy = new District(district.getId(), district.getName());
		return repository.save(copy);
	}
	
	public Optional<District> update( Long id, District newDistrict) {
        return repository.findById(id)
                .map(oldDistrict -> {
                	District updated = oldDistrict.updateWith(newDistrict);
                   return repository.save(updated);
                });
    }
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
