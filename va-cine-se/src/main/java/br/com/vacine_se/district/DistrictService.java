package br.com.vacine_se.district;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class DistrictService {

	private DistrictRepository repository;
	
	private static List<District> getDistrictFromData(){
		return List.of(
				new District("Neópolis"),
				new District("Candelária")
				);
	}

	public DistrictService(DistrictRepository repository) {
		this.repository = repository;
		for (District dis : getDistrictFromData()) {
			this.repository.save(dis);
		}
	}
	
	public List<District> findAll() {
        List<District> list = new ArrayList<>();
        Iterable<District> Districts = repository.getAll();
        Districts.forEach(list::add);
        return list;
    }
	public Optional<District> find(int id) {
		return repository.getById(id);
	}
}

