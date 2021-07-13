package br.com.vacine_se.district;

import java.util.List;

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
}
