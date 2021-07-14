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
	private final CrudRepository<Scheduling,Long> repository;

	public SchedulingService(CrudRepository<Scheduling, Long> repository) {
		this.repository = repository;
		this.repository.saveAll(defaultSchedulings());
	}

	
	private static List<Scheduling> defaultSchedulings(){
		return List.of(
				new Scheduling(1L, LocalDate.now(), 1L),
				new Scheduling(2L, LocalDate.now(), 2L)
				);
	}

    public List<Scheduling> findAll() {
        List<Scheduling> list = new ArrayList<>();
        Iterable<Scheduling> schedulings = repository.findAll();
        schedulings.forEach(list::add);
        return list;
    }
	public Optional<Scheduling> find(Long id) {
		return repository.findById(id);
	}
	
	public Scheduling create(Scheduling scheduling) {
		Scheduling copy = new Scheduling(
				scheduling.getId(),
				scheduling.getDate(),
				scheduling.getVaccinationSiteId()
				);
		return repository.save(copy);
	}
	
	public Optional<Scheduling> update( Long id, Scheduling newScheduling) {
        return repository.findById(id)
                .map(oldScheduling-> {
                	Scheduling updated = oldScheduling.updateWith(newScheduling);
                   return repository.save(updated);
                });
    }
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
