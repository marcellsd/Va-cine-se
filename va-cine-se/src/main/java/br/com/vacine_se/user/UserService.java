package br.com.vacine_se.user;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.vacine_se.district.DistrictService;
import br.com.vacine_se.scheduling.Scheduling;
import br.com.vacine_se.scheduling.SchedulingService;
import br.com.vacine_se.vaccination_site.VaccinationSite;
import br.com.vacine_se.vaccination_site.VaccinationSiteService;

@Service
public class UserService {

	private UserRepository repository;


	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> users = repository.getAll();
        users.forEach(list::add);
        return list;
    }

	public Optional<User> find(int id) {
		return repository.getById(id);
	}
	
	public User create(User user) {
		return repository.save(user);
	}
	
	public User update(int id, User newUser) {
		User user = this.find(id).orElseThrow();
		newUser.setId(user.getId());
		return repository.update(newUser);
      }
	
	public void delete(int id) {
		User user = this.find(id).orElseThrow();
		repository.delete(user);
	}
	
	public int setUserScheduling(User user, DistrictService districtService, 
										VaccinationSiteService vaccinationSiteService,
										SchedulingService schedulingService) {
		VaccinationSite nearestVacSite = null;
		
		int distance = Integer.MAX_VALUE;
		
		for (VaccinationSite vacSite : vaccinationSiteService.findAll()) {
			int currentDistance = districtService.getDistance(districtService.find(user.getDistrictId()).orElseThrow(),
														districtService.find(vacSite.getDistrictId()).orElseThrow());
			
			if (user.getDistrictId() == vacSite.getDistrictId()) {
				nearestVacSite = vacSite;
				Scheduling scheduling = new Scheduling(LocalDate.now().plusDays(30), nearestVacSite.getId());
				scheduling = schedulingService.create(scheduling);
				return scheduling.getId();
			}
			if(currentDistance < distance) {
				distance = currentDistance;
				nearestVacSite = vacSite;
			}
		}
		Scheduling scheduling = new Scheduling(LocalDate.now().plusDays(30), nearestVacSite.getId());
		scheduling = schedulingService.create(scheduling);
		return scheduling.getId();
	}
}
