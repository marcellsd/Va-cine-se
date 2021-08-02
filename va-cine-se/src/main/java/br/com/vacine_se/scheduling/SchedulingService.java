package br.com.vacine_se.scheduling;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class SchedulingService {
	private SchedulingRepository repository;

	public SchedulingService(SchedulingRepository repository) {
		this.repository = repository;
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
        Iterable<Scheduling> schedulings = repository.getAll();
        schedulings.forEach(list::add);
        return list;
    }

	public Optional<Scheduling> find(int id) {
		return repository.getById(id);
	}
	
	public Scheduling create(Scheduling scheduling) {
		return repository.save(scheduling);
	}
	
   
	public Scheduling update(int id, Scheduling newScheduling) {
		 newScheduling.setId(id);
		 return repository.save(newScheduling);
		//return repository.findById(id)
                //.map(oldScheduling-> {
                //	Scheduling updated = oldScheduling.updateWith(newScheduling);
                //   return repository.save(updated);
                //});
    }
	
	public void delete(int id) {
		Scheduling scheduling = this.find(id).orElseThrow();
		repository.delete(scheduling);
	}

	public int totalSchedulesForADate(LocalDate date, int vaccinationSiteId) {
		int result = (int)this.findAll().stream().filter(s -> s.getDate() == date && s.getVaccinationSiteId() == vaccinationSiteId).count();
		return result;
	}

}
