package br.com.vacine_se.scheduling;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@EnableMapRepositories
public class SchedulingService {
	private final CrudRepository<Scheduling, String> repository;

	public SchedulingService(CrudRepository<Scheduling, String> repository) {
		this.repository = repository;
		// this.repository.saveAll(defaultSchedulings());
	}

	
	// TODO: adicionar vacinationsite service para buscar os IDs
	// private static List<Scheduling> defaultSchedulings(){
	// 	return List.of(
	// 			new Scheduling(LocalDate.now(), 1L),
	// 			new Scheduling(LocalDate.now(), 2L)
	// 			);
	// }

    public List<Scheduling> findAll() {
        List<Scheduling> list = new ArrayList<>();
        Iterable<Scheduling> schedulings = repository.findAll();
        schedulings.forEach(list::add);
        return list;
    }
	public Optional<Scheduling> find(String id) {
		return repository.findById(id);
	}
	
	public Scheduling create(Scheduling scheduling) {
		Scheduling copy = new Scheduling(
				scheduling.getDate(),
				scheduling.getVaccinationSiteId()
				);
		return repository.save(copy);
	}
	
	public Optional<Scheduling> update(String id, Scheduling newScheduling) {
        return repository.findById(id)
                .map(oldScheduling-> {
                	Scheduling updated = oldScheduling.updateWith(newScheduling);
                   return repository.save(updated);
                });
    }
	
	public void delete(String id) {
		repository.deleteById(id);
	}
}
