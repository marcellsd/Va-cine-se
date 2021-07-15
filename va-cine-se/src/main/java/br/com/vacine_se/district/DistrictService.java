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
	
	
	// TODO: Adicionar todos os bairros
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
}

